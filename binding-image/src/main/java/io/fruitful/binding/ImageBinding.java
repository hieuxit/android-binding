package io.fruitful.binding;

import android.databinding.BindingAdapter;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntDef;
import android.support.annotation.Px;
import android.widget.ImageView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by hieuxit on 9/24/16.
 */

public class ImageBinding {

    @Px
    public static final int NO_RADIUS = 0;

    public static final int TRANSFORM_NO_TRANSFORM = 0;
    public static final int TRANSFORM_CIRCLE = 1;
    public static final int TRANSFORM_ROUNDED_ALL = 2;
    public static final int TRANSFORM_ROUNDED_TOP_LEFT = 3;
    public static final int TRANSFORM_ROUNDED_TOP_RIGHT = 4;
    public static final int TRANSFORM_ROUNDED_BOTTOM_LEFT = 5;
    public static final int TRANSFORM_ROUNDED_BOTTOM_RIGHT = 6;
    public static final int TRANSFORM_ROUNDED_TOP = 7;
    public static final int TRANSFORM_ROUNDED_BOTTOM = 8;
    public static final int TRANSFORM_ROUNDED_LEFT = 9;
    public static final int TRANSFORM_ROUNDED_RIGHT = 10;
    public static final int TRANSFORM_ROUNDED_OTHER_TOP_LEFT = 11;
    public static final int TRANSFORM_ROUNDED_OTHER_TOP_RIGHT = 12;
    public static final int TRANSFORM_ROUNDED_OTHER_BOTTOM_LEFT = 13;
    public static final int TRANSFORM_ROUNDED_OTHER_BOTTOM_RIGHT = 14;
    public static final int TRANSFORM_ROUNDED_DIAGONAL_FROM_TOP_LEFT = 15;
    public static final int TRANSFORM_ROUNDED_DIAGONAL_FROM_TOP_RIGHT = 16;

    @IntDef({TRANSFORM_NO_TRANSFORM, TRANSFORM_CIRCLE, TRANSFORM_ROUNDED_ALL, TRANSFORM_ROUNDED_TOP_LEFT,
            TRANSFORM_ROUNDED_TOP_RIGHT, TRANSFORM_ROUNDED_BOTTOM_LEFT, TRANSFORM_ROUNDED_BOTTOM_RIGHT,
            TRANSFORM_ROUNDED_TOP, TRANSFORM_ROUNDED_BOTTOM, TRANSFORM_ROUNDED_LEFT, TRANSFORM_ROUNDED_RIGHT,
            TRANSFORM_ROUNDED_OTHER_TOP_LEFT, TRANSFORM_ROUNDED_OTHER_TOP_RIGHT, TRANSFORM_ROUNDED_OTHER_BOTTOM_LEFT,
            TRANSFORM_ROUNDED_OTHER_BOTTOM_RIGHT, TRANSFORM_ROUNDED_DIAGONAL_FROM_TOP_LEFT,
            TRANSFORM_ROUNDED_DIAGONAL_FROM_TOP_RIGHT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TransformType {
    }

    public static final int TWOMODE_PRIORITY = 0;
    public static final int TWOMODE_BACKUP = 1;

    @IntDef({TWOMODE_PRIORITY, TWOMODE_BACKUP})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TwoMode {
    }

    public static final int SCALE_AUTO = 0;
    public static final int SCALE_CENTER_FIT = 1;
    public static final int SCALE_CENTER_CROP = 2;

    @IntDef({SCALE_AUTO, SCALE_CENTER_CROP, SCALE_CENTER_FIT})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ScaleType {
    }

    private static final ImageLoader imageLoader = ImageLoaderFactory.resolveImageLoader();

    @BindingAdapter("bind:image")
    public static void loadImage(ImageView view, Bitmap bm) {
        view.setImageBitmap(bm);
    }

    @BindingAdapter("bind:image")
    public static void loadImage(ImageView view, Drawable image) {
        view.setImageDrawable(image);
    }

    @BindingAdapter("bind:image")
    public static void loadImage(ImageView view, int imageId) {
        view.setImageResource(imageId);
    }

    @BindingAdapter({"bind:firstUrl", "bind:secondUrl", "bind:twomode", "bind:placeHolder",
            "bind:transformType", "bind:cornerRadius", "bind:scaleType"})
    public static void loadImage(ImageView imageView, String firstUrl, String secondUrl,
                                 @ImageBinding.TwoMode int twomode, Drawable placeHolder,
                                 @ImageBinding.TransformType int transformType,
                                 @Px int cornerRadius, @ImageBinding.ScaleType int scaleType) {
        imageLoader.loadImage(imageView, firstUrl, secondUrl, twomode, placeHolder, transformType,
                cornerRadius, scaleType);
    }

    @BindingAdapter({"bind:firstUrl", "bind:secondUrl", "bind:twomode", "bind:placeHolder",
            "bind:transformType", "bind:cornerRadius"})
    public static void loadImage(ImageView imageView, String firstUrl, String secondUrl,
                                 @ImageBinding.TwoMode int twomode, Drawable placeHolder,
                                 @ImageBinding.TransformType int transformType,
                                 @Px int cornerRadius) {
        imageLoader.loadImage(imageView, firstUrl, secondUrl, twomode, placeHolder, transformType,
                cornerRadius, SCALE_AUTO);
    }

    @BindingAdapter({"bind:firstUrl", "bind:secondUrl", "bind:placeHolder", "bind:scaleType"})
    public static void loadImage(ImageView imageView, String firstUrl, String secondUrl, Drawable placeHolder,
                                 @ImageBinding.ScaleType int scaleType) {
        imageLoader.load2Images(imageView, firstUrl, secondUrl, placeHolder, scaleType);
    }

    @BindingAdapter({"bind:firstUrl", "bind:secondUrl", "bind:placeHolder"})
    public static void loadImage(ImageView imageView, String firstUrl, String secondUrl, Drawable placeHolder) {
        imageLoader.load2Images(imageView, firstUrl, secondUrl, placeHolder);
    }

    @BindingAdapter({"bind:firstUrl", "bind:secondUrl", "bind:placeHolder", "bind:circle"})
    public static void loadImage(ImageView imageView, String firstUrl, String secondUrl, Drawable placeHolder, boolean circle) {
        imageLoader.load2Images(imageView, firstUrl, secondUrl, placeHolder, circle);
    }

    @BindingAdapter({"bind:firstUrl", "bind:secondUrl", "bind:placeHolder", "bind:transformType", "bind:cornerRadius"})
    public static void loadImage(ImageView imageView, String firstUrl, String secondUrl, Drawable placeHolder,
                                 @ImageBinding.TransformType int cornerType, @Px int radius) {
        imageLoader.load2Images(imageView, firstUrl, secondUrl, placeHolder, cornerType, radius);
    }

    @BindingAdapter({"bind:imageUrl", "bind:placeHolder", "bind:scaleType"})
    public static void loadImage(ImageView imageView, String imageUrl, Drawable placeHolder,
                                 @ImageBinding.ScaleType int scaleType) {
        imageLoader.loadNormal(imageView, imageUrl, placeHolder, scaleType);
    }

    @BindingAdapter({"bind:imageUrl", "bind:placeHolder"})
    public static void loadImage(ImageView imageView, String imageUrl, Drawable placeHolder) {
        imageLoader.loadNormal(imageView, imageUrl, placeHolder);
    }

    @BindingAdapter({"bind:imageUrl", "bind:scaleType"})
    public static void loadImage(ImageView imageView, String imageUrl, @ImageBinding.ScaleType int scaleType) {
        imageLoader.loadNormal(imageView, imageUrl, null, scaleType);
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView imageView, String imageUrl) {
        imageLoader.loadNormal(imageView, imageUrl, null);
    }

    @BindingAdapter({"bind:imageUrl", "bind:circle"})
    public static void loadImage(ImageView imageView, String imageUrl, boolean circle) {
        imageLoader.loadNormal(imageView, imageUrl, null, circle);
    }

    @BindingAdapter({"bind:imageUrl", "bind:placeHolder", "bind:circle"})
    public static void loadImage(ImageView imageView, String imageUrl, Drawable placeHolder, boolean circle) {
        imageLoader.loadNormal(imageView, imageUrl, placeHolder, circle);
    }

    @BindingAdapter({"bind:imageUrl", "bind:placeHolder", "bind:cornerType", "bind:cornerRadius"})
    public static void loadImage(ImageView imageView, String imageUrl, Drawable placeHolder,
                                 @ImageBinding.TransformType int cornerType, @Px int radius) {
        imageLoader.loadNormal(imageView, imageUrl, placeHolder, cornerType, radius);
    }
}
