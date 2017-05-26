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

    @BindingAdapter("image")
    public static void loadImage(ImageView view, Bitmap bm) {
        view.setImageBitmap(bm);
    }

    @BindingAdapter("image")
    public static void loadImage(ImageView view, Drawable drawable) {
        view.setImageDrawable(drawable);
    }

    @BindingAdapter("image")
    public static void loadImage(ImageView view, int imageId) {
        view.setImageResource(imageId);
    }

    @BindingAdapter({"image", "transformType", "cornerRadius", "scaleType"})
    public static <T> void loadImage(ImageView view, T data, @ImageBinding.TransformType int transformType,
                                     @Px int cornerRadius, @ImageBinding.ScaleType int scaleType) {
        imageLoader.loadImageLocal(view, data, transformType, scaleType, cornerRadius);
    }

    @BindingAdapter({"image", "scaleType"})
    public static <T> void loadImage(ImageView view, T data, @ImageBinding.ScaleType int scaleType) {
        imageLoader.loadImageLocal(view, data, TRANSFORM_NO_TRANSFORM, scaleType, NO_RADIUS);
    }

    @BindingAdapter({"image"})
    public static <T> void loadImage(ImageView view, T data) {
        imageLoader.loadImageLocal(view, data, TRANSFORM_NO_TRANSFORM, SCALE_AUTO, NO_RADIUS);
    }

    @BindingAdapter({"image", "cornerType", "cornerRadius"})
    public static <T> void loadImage(ImageView view, T data, @ImageBinding.TransformType int cornerType,
                                     @Px int cornerRadius) {
        imageLoader.loadImageLocal(view, data, cornerType, SCALE_AUTO, cornerRadius);
    }

    @BindingAdapter({"image", "circle"})
    public static <T> void loadImage(ImageView view, T data, boolean circle) {
        imageLoader.loadImageLocal(view, data, circle ? TRANSFORM_CIRCLE : TRANSFORM_NO_TRANSFORM, SCALE_AUTO, NO_RADIUS);
    }

    @BindingAdapter({"firstUrl", "secondUrl", "twomode", "placeHolder",
            "transformType", "cornerRadius", "scaleType"})
    public static void load2Images2Mode(ImageView imageView, String firstUrl, String secondUrl,
                                        @ImageBinding.TwoMode int twomode, Drawable placeHolder,
                                        @ImageBinding.TransformType int transformType,
                                        @Px int cornerRadius, @ImageBinding.ScaleType int scaleType) {
        imageLoader.loadImage(imageView, firstUrl, secondUrl, twomode, placeHolder, transformType,
                cornerRadius, scaleType);
    }

    @BindingAdapter({"firstUrl", "secondUrl", "twomode", "placeHolder",
            "transformType", "cornerRadius"})
    public static void load2Images2Mode(ImageView imageView, String firstUrl, String secondUrl,
                                        @ImageBinding.TwoMode int twomode, Drawable placeHolder,
                                        @ImageBinding.TransformType int transformType,
                                        @Px int cornerRadius) {
        imageLoader.loadImage(imageView, firstUrl, secondUrl, twomode, placeHolder, transformType,
                cornerRadius, SCALE_AUTO);
    }

    @BindingAdapter({"firstUrl", "secondUrl", "twomode"})
    public static void load2ImagesWith2Mode(ImageView imageView, String firstUrl, String secondUrl,
                                            @ImageBinding.TwoMode int twomode) {
        imageLoader.loadImage(imageView, firstUrl, secondUrl, twomode, null, TRANSFORM_NO_TRANSFORM,
                ImageBinding.NO_RADIUS, SCALE_AUTO);
    }

    @BindingAdapter({"firstUrl", "secondUrl", "placeHolder", "scaleType"})
    public static void load2Images(ImageView imageView, String firstUrl, String secondUrl, Drawable placeHolder,
                                   @ImageBinding.ScaleType int scaleType) {
        imageLoader.load2Images(imageView, firstUrl, secondUrl, placeHolder, scaleType);
    }

    @BindingAdapter({"firstUrl", "secondUrl", "placeHolder"})
    public static void load2Images(ImageView imageView, String firstUrl, String secondUrl, Drawable placeHolder) {
        imageLoader.load2Images(imageView, firstUrl, secondUrl, placeHolder);
    }

    @BindingAdapter({"firstUrl", "secondUrl"})
    public static void load2Images(ImageView imageView, String firstUrl, String secondUrl) {
        imageLoader.load2Images(imageView, firstUrl, secondUrl, null);
    }

    @BindingAdapter({"firstUrl", "secondUrl", "scaleType"})
    public static void load2ImageWithScaleType(ImageView imageView, String firstUrl,
                                               String secondUrl, @ImageBinding.ScaleType int scaleType) {
        imageLoader.load2Images(imageView, firstUrl, secondUrl, null, scaleType);
    }

    @BindingAdapter({"firstUrl", "secondUrl", "placeHolder", "circle"})
    public static void load2Images(ImageView imageView, String firstUrl, String secondUrl, Drawable placeHolder, boolean circle) {
        imageLoader.load2Images(imageView, firstUrl, secondUrl, placeHolder, circle);
    }

    @BindingAdapter({"firstUrl", "secondUrl", "placeHolder", "transformType", "cornerRadius"})
    public static void load2Images(ImageView imageView, String firstUrl, String secondUrl, Drawable placeHolder,
                                   @ImageBinding.TransformType int cornerType, @Px int radius) {
        imageLoader.load2Images(imageView, firstUrl, secondUrl, placeHolder, cornerType, radius);
    }

    @BindingAdapter({"imageUrl", "placeHolder", "scaleType"})
    public static void loadImage(ImageView imageView, String imageUrl, Drawable placeHolder,
                                 @ImageBinding.ScaleType int scaleType) {
        imageLoader.loadNormal(imageView, imageUrl, placeHolder, scaleType);
    }

    @BindingAdapter({"imageUrl", "placeHolder"})
    public static void loadImage(ImageView imageView, String imageUrl, Drawable placeHolder) {
        imageLoader.loadNormal(imageView, imageUrl, placeHolder);
    }

    @BindingAdapter({"imageUrl", "scaleType"})
    public static void loadImage(ImageView imageView, String imageUrl, @ImageBinding.ScaleType int scaleType) {
        imageLoader.loadNormal(imageView, imageUrl, null, scaleType);
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, String imageUrl) {
        imageLoader.loadNormal(imageView, imageUrl, null);
    }

    @BindingAdapter({"imageUrl", "circle"})
    public static void loadImage(ImageView imageView, String imageUrl, boolean circle) {
        imageLoader.loadNormal(imageView, imageUrl, null, circle);
    }

    @BindingAdapter({"imageUrl", "placeHolder", "circle"})
    public static void loadImage(ImageView imageView, String imageUrl, Drawable placeHolder, boolean circle) {
        imageLoader.loadNormal(imageView, imageUrl, placeHolder, circle);
    }

    @BindingAdapter({"imageUrl", "placeHolder", "cornerType", "cornerRadius"})
    public static void loadImage(ImageView imageView, String imageUrl, Drawable placeHolder,
                                 @ImageBinding.TransformType int cornerType, @Px int radius) {
        imageLoader.loadNormal(imageView, imageUrl, placeHolder, cornerType, radius);
    }
}
