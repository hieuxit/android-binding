package io.fruitful.binding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

/**
 * Created by hieuxit on 2/3/16.
 */
public class RecyclerViewBinding {

    @BindingAdapter("itemDecoration")
    public static void addItemDecoration(RecyclerView recyclerView, RecyclerView.ItemDecoration itemDecoration) {
        recyclerView.removeItemDecoration(itemDecoration);
        recyclerView.addItemDecoration(itemDecoration);
    }
}
