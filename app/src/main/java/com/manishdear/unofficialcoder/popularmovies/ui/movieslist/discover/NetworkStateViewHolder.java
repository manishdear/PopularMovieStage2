package com.manishdear.unofficialcoder.popularmovies.ui.movieslist.discover;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.manishdear.unofficialcoder.popularmovies.data.local.model.Resource;
import com.manishdear.unofficialcoder.popularmovies.databinding.ItemNetworkStateBinding;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NetworkStateViewHolder extends RecyclerView.ViewHolder {

    private ItemNetworkStateBinding binding;

    public NetworkStateViewHolder(@NonNull ItemNetworkStateBinding binding,
                                  final DiscoverMoviesViewModel viewModel) {
        super(binding.getRoot());
        this.binding = binding;

        binding.retryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.retry();
            }
        });
    }

    public static NetworkStateViewHolder create(ViewGroup parent, DiscoverMoviesViewModel viewModel) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        ItemNetworkStateBinding binding =
                ItemNetworkStateBinding.inflate(layoutInflater, parent, false);
        return new NetworkStateViewHolder(binding, viewModel);
    }

    public void bindTo(Resource resource) {
        binding.setResource(resource);
        binding.executePendingBindings();
    }
}
