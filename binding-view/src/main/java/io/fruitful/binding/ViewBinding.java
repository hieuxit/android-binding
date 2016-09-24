package io.fruitful.binding;

import android.databinding.BindingAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by hieuxit on 2/3/16.
 */
public class ViewBinding {
    @BindingAdapter("bind:visible")
    public static void setVisible(View view, boolean visible) {
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("app:enabled")
    public static void setEnabled(View view, boolean enabled) {
        view.setEnabled(enabled);
    }

    @BindingAdapter({"app:marginLeft", "app:marginTop", "app:marginRight", "app:marginBottom"})
    public static void setMargin(View view, int marginLeft, int marginTop, int marginRight, int marginBottom) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) lp;
            mlp.setMargins(marginLeft, marginTop, marginRight, marginBottom);
        }
    }

    @BindingAdapter("app:marginLeft")
    public static void setMarginLeft(View view, int marginLeft) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) lp;
            mlp.setMargins(marginLeft, mlp.topMargin, mlp.rightMargin, mlp.bottomMargin);
        }
    }

    @BindingAdapter("app:marginTop")
    public static void setMarginTop(View view, int marginTop) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) lp;
            mlp.setMargins(mlp.leftMargin, marginTop, mlp.rightMargin, mlp.bottomMargin);
        }
    }

    @BindingAdapter("app:marginRight")
    public static void setMarginRight(View view, int marginRight) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) lp;
            mlp.setMargins(mlp.leftMargin, mlp.topMargin, marginRight, mlp.bottomMargin);
        }
    }

    @BindingAdapter("app:marginBottom")
    public static void setMarginBottom(View view, int marginBottom) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) lp;
            mlp.setMargins(mlp.leftMargin, mlp.topMargin, mlp.rightMargin, marginBottom);
        }
    }
}
