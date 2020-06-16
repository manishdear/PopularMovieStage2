package com.manishdear.unofficialcoder.popularmovies.data;

import com.manishdear.unofficialcoder.popularmovies.data.local.model.Resource;
import com.manishdear.unofficialcoder.popularmovies.data.remote.api.ApiResponse;
import com.manishdear.unofficialcoder.popularmovies.utils.AppExecutors;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

public abstract class NetworkBoundResource<ResultType, RequestType> {

    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    private AppExecutors mExecutors;

    @MainThread
    public NetworkBoundResource(AppExecutors appExecutors) {
        mExecutors = appExecutors;
        // Send loading state to UI
        result.setValue(Resource.<ResultType>loading(null));
        final LiveData<ResultType> dbSource = loadFromDb();
        result.addSource(dbSource, new Observer<ResultType>() {
            @Override
            public void onChanged(ResultType data) {
                result.removeSource(dbSource);
                if (shouldFetch(data)) {
                    fetchFromNetwork(dbSource);
                } else {
                    result.addSource(dbSource, new Observer<ResultType>() {
                        @Override
                        public void onChanged(ResultType newData) {
                            setValue(Resource.success(newData));
                        }
                    });
                }
            }
        });
    }

    @MainThread
    private void setValue(Resource<ResultType> newValue) {
        if (result.getValue() != newValue) {
            result.setValue(newValue);
        }
    }

    private void fetchFromNetwork(final LiveData<ResultType> dbSource) {
        final LiveData<ApiResponse<RequestType>> apiResponse = createCall();

        result.addSource(dbSource, new Observer<ResultType>() {
            @Override
            public void onChanged(ResultType newData) {
                setValue(Resource.loading(newData));
            }
        });
        result.addSource(apiResponse, new Observer<ApiResponse<RequestType>>() {
            @Override
            public void onChanged(final ApiResponse<RequestType> response) {
                result.removeSource(apiResponse);
                result.removeSource(dbSource);
                if (response.isSuccessful()) {
                    mExecutors.diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            saveCallResult(response.body);
                            mExecutors.mainThread().execute(new Runnable() {
                                @Override
                                public void run() {

                                    result.addSource(loadFromDb(), new Observer<ResultType>() {
                                        @Override
                                        public void onChanged(ResultType newData) {
                                            setValue(Resource.success(newData));
                                        }
                                    });
                                }
                            });
                        }
                    });
                } else {
                    onFetchFailed();
                    result.addSource(dbSource, new Observer<ResultType>() {
                        @Override
                        public void onChanged(ResultType newData) {
                            setValue(Resource.error(response.getError().getMessage(), newData));
                        }
                    });
                }
            }
        });
    }

    @WorkerThread
    protected abstract void saveCallResult(@NonNull RequestType item);

    @MainThread
    protected abstract boolean shouldFetch(@Nullable ResultType data);

    @NonNull
    @MainThread
    protected abstract LiveData<ResultType> loadFromDb();

    @NonNull
    @MainThread
    protected abstract LiveData<ApiResponse<RequestType>> createCall();

    @NonNull
    @MainThread
    protected abstract void onFetchFailed();

    public final LiveData<Resource<ResultType>> getAsLiveData() {
        return result;
    }
}
