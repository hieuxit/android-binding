package io.fruitful.binding;

import android.graphics.drawable.Drawable;
import android.support.annotation.Px;
import android.widget.ImageView;

/**
 * Created by hieuxit on 9/24/16.
 */

public abstract class ImageLoader {

    abstract void loadImage(ImageView imageView, String firstUrl, String secondUrl,
                            @ImageBinding.TwoMode int twomode, Drawable placeHolder,
                            @ImageBinding.TransformType int transformType,
                            @Px int cornerRadius, @ImageBinding.ScaleType int scaleType);

    public void loadNormal(ImageView imageView, String imageUrl, Drawable placeHolder,
                           @ImageBinding.ScaleType int scaleType) {
        loadImage(imageView, imageUrl, null, ImageBinding.TWOMODE_BACKUP, placeHolder,
                ImageBinding.TRANSFORM_NO_TRANSFORM, ImageBinding.NO_RADIUS, scaleType);
    }

    public void loadNormal(ImageView imageView, String imageUrl, Drawable placeHolder) {
        loadNormal(imageView, imageUrl, placeHolder, ImageBinding.SCALE_AUTO);
    }

    public void loadNormal(ImageView imageView, String imageUrl, Drawable placeHolder, boolean circle) {
        loadImage(imageView, imageUrl, null, ImageBinding.TWOMODE_BACKUP, placeHolder,
                circle ? ImageBinding.TRANSFORM_CIRCLE : ImageBinding.TRANSFORM_NO_TRANSFORM,
                ImageBinding.NO_RADIUS, ImageBinding.SCALE_AUTO);
    }

    public void loadNormal(ImageView imageView, String imageUrl, Drawable placeHolder,
                           @ImageBinding.TransformType int cornerType, @Px int radius) {
        loadImage(imageView, imageUrl, null, ImageBinding.TWOMODE_BACKUP, placeHolder, cornerType,
                radius, ImageBinding.SCALE_AUTO);
    }

    public void load2Images(ImageView imageView, String firstUrl, String secondUrl, Drawable placeHolder,
                            @ImageBinding.ScaleType int scaleType) {
        loadImage(imageView, firstUrl, secondUrl, ImageBinding.TWOMODE_PRIORITY, placeHolder,
                ImageBinding.TRANSFORM_NO_TRANSFORM, ImageBinding.NO_RADIUS, scaleType);
    }

    public void load2Images(ImageView imageView, String firstUrl, String secondUrl, Drawable placeHolder) {
        load2Images(imageView, firstUrl, secondUrl, placeHolder, false);
    }

    public void load2Images(ImageView imageView, String firstUrl, String secondUrl, Drawable placeHolder, boolean circle) {
        loadImage(imageView, firstUrl, secondUrl, ImageBinding.TWOMODE_PRIORITY, placeHolder,
                circle ? ImageBinding.TRANSFORM_CIRCLE : ImageBinding.TRANSFORM_NO_TRANSFORM,
                ImageBinding.NO_RADIUS, ImageBinding.SCALE_AUTO);
    }

    public void load2Images(ImageView imageView, String firstUrl, String secondUrl, Drawable placeHolder,
                            @ImageBinding.TransformType int cornerType, @Px int radius) {
        loadImage(imageView, firstUrl, secondUrl, ImageBinding.TWOMODE_PRIORITY, placeHolder,
                cornerType, radius, ImageBinding.SCALE_AUTO);
    }

}
