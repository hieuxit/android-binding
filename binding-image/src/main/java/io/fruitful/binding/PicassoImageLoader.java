package io.fruitful.binding;

import android.graphics.drawable.Drawable;
import android.support.annotation.Px;
import android.widget.ImageView;

/**
 * Created by hieuxit on 9/24/16.
 */

public class PicassoImageLoader extends ImageLoader {
    @Override
    <T> void loadImageLocal(ImageView view, T image, @ImageBinding.TransformType int transformType, @ImageBinding.ScaleType int scaleType, @Px int cornerRadius) {

    }

    @Override
    void loadImage(ImageView imageView, String firstUrl, String secondUrl, @ImageBinding.TwoMode int twomode, Drawable placeHolder, @ImageBinding.TransformType int transformType, @Px int cornerRadius, @ImageBinding.ScaleType int scaleType) {

    }
}
