package com.manishdear.unofficialcoder.popularmovies.ui.moviedetails.cast;

import com.manishdear.unofficialcoder.popularmovies.data.local.model.Cast;

import java.util.List;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class CastBinding {

    @BindingAdapter("items")
    public static void setItems(RecyclerView recyclerView, List<Cast> items) {
        CastAdapter adapter = (CastAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.submitList(items);
        }
    }
}
