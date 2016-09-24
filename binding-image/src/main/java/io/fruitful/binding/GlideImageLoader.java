package io.fruitful.binding;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Px;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by hieuxit on 9/24/16.
 */

public class GlideImageLoader extends ImageLoader {

    @Override
    void loadImage(ImageView imageView, String firstUrl, String secondUrl,
                   @ImageBinding.TwoMode int twomode, Drawable placeHolder,
                   @ImageBinding.TransformType int transformType, @Px int cornerRadius,
                   @ImageBinding.ScaleType int scaleType) {
        if (TextUtils.isEmpty(firstUrl) && TextUtils.isEmpty(secondUrl)) {
            imageView.setImageDrawable(placeHolder);
            return;
        }

        /**
         * Re-calculate scale type
         * if transformType is not NO_TRANSFORM scaleType must be CENTER_CROP
         * else if scaleType is not set(AUTO) lookup the LayoutParams to decide
         */
        if (transformType == ImageBinding.TRANSFORM_NO_TRANSFORM) {
            if (scaleType == ImageBinding.SCALE_AUTO) {
                ViewGroup.LayoutParams lp = imageView.getLayoutParams();
                scaleType = lp.width == ViewGroup.LayoutParams.WRAP_CONTENT || lp.height == ViewGroup.LayoutParams.WRAP_CONTENT ?
                        ImageBinding.SCALE_CENTER_FIT : ImageBinding.SCALE_CENTER_CROP;
            }
        } else {
            scaleType = ImageBinding.SCALE_CENTER_CROP;
        }
        Context context = imageView.getContext();
        RequestListener<String, GlideDrawable> imageLoaderListener = null;
        if (TextUtils.isEmpty(firstUrl) && TextUtils.isEmpty(secondUrl)) {
            imageLoaderListener = new TwoImagesLoaderListener(imageView, secondUrl, twomode, transformType, cornerRadius, scaleType);
        }
        DrawableRequestBuilder<String> builder = Glide.with(context)
                .load(TextUtils.isEmpty(firstUrl) ? secondUrl : firstUrl)
                .override(100, 100)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(imageLoaderListener);

        if (placeHolder != null) {
            builder.placeholder(placeHolder);
        }

        if (transformType == ImageBinding.TRANSFORM_CIRCLE) {
            builder = builder.bitmapTransform(new CropCircleTransformation(context));
        } else {
            BitmapTransformation scaleTransformation = scaleType == ImageBinding.SCALE_CENTER_FIT ?
                    new FitCenter(context) : new CenterCrop(context);
            if (transformType == ImageBinding.TRANSFORM_NO_TRANSFORM) {
                builder = builder.bitmapTransform(scaleTransformation);
            } else {
                // Mapping transformType with RoundedCornerTransform
                RoundedCornersTransformation.CornerType cornerType = mappingToGlideCornerType(transformType);
                builder = builder.bitmapTransform(
                        new RoundedCornersTransformation(context, cornerRadius, 0, cornerType),
                        new CenterCrop(context));
            }
        }
        builder.dontAnimate().into(imageView);
    }

    private RoundedCornersTransformation.CornerType mappingToGlideCornerType(@ImageBinding.TransformType int transformType) {
        switch (transformType) {
            case ImageBinding.TRANSFORM_ROUNDED_TOP_LEFT:
                return RoundedCornersTransformation.CornerType.TOP_LEFT;
            case ImageBinding.TRANSFORM_ROUNDED_TOP_RIGHT:
                return RoundedCornersTransformation.CornerType.TOP_RIGHT;
            case ImageBinding.TRANSFORM_ROUNDED_BOTTOM_LEFT:
                return RoundedCornersTransformation.CornerType.BOTTOM_LEFT;
            case ImageBinding.TRANSFORM_ROUNDED_BOTTOM_RIGHT:
                return RoundedCornersTransformation.CornerType.BOTTOM_RIGHT;
            case ImageBinding.TRANSFORM_ROUNDED_TOP:
                return RoundedCornersTransformation.CornerType.TOP;
            case ImageBinding.TRANSFORM_ROUNDED_BOTTOM:
                return RoundedCornersTransformation.CornerType.BOTTOM;
            case ImageBinding.TRANSFORM_ROUNDED_LEFT:
                return RoundedCornersTransformation.CornerType.LEFT;
            case ImageBinding.TRANSFORM_ROUNDED_RIGHT:
                return RoundedCornersTransformation.CornerType.RIGHT;
            case ImageBinding.TRANSFORM_ROUNDED_OTHER_TOP_LEFT:
                return RoundedCornersTransformation.CornerType.OTHER_TOP_LEFT;
            case ImageBinding.TRANSFORM_ROUNDED_OTHER_TOP_RIGHT:
                return RoundedCornersTransformation.CornerType.OTHER_TOP_RIGHT;
            case ImageBinding.TRANSFORM_ROUNDED_OTHER_BOTTOM_LEFT:
                return RoundedCornersTransformation.CornerType.OTHER_BOTTOM_LEFT;
            case ImageBinding.TRANSFORM_ROUNDED_OTHER_BOTTOM_RIGHT:
                return RoundedCornersTransformation.CornerType.OTHER_BOTTOM_RIGHT;
            case ImageBinding.TRANSFORM_ROUNDED_DIAGONAL_FROM_TOP_LEFT:
                return RoundedCornersTransformation.CornerType.DIAGONAL_FROM_TOP_LEFT;
            case ImageBinding.TRANSFORM_ROUNDED_DIAGONAL_FROM_TOP_RIGHT:
                return RoundedCornersTransformation.CornerType.DIAGONAL_FROM_TOP_RIGHT;
            default:
                return RoundedCornersTransformation.CornerType.ALL;
        }
    }

    private class TwoImagesLoaderListener implements RequestListener<String, GlideDrawable> {

        private ImageView imageView;
        private String secondUrl;

        @ImageBinding.TwoMode
        private int twomode;

        @ImageBinding.TransformType
        private int transformType;

        @ImageBinding.ScaleType
        private int scaleType;

        @Px
        private int cornerRadius;

        TwoImagesLoaderListener(ImageView imageView, String secondUrl,
                                @ImageBinding.TwoMode int twomode,
                                @ImageBinding.TransformType int transformType,
                                @Px int cornerRadius, @ImageBinding.ScaleType int scaleType) {
            this.imageView = imageView;
            this.secondUrl = secondUrl;
            this.twomode = twomode;
            this.transformType = transformType;
            this.cornerRadius = cornerRadius;
            this.scaleType = scaleType;
        }

        @Override
        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
            e.printStackTrace();
            // load second image here
            loadImage(imageView, secondUrl, null, ImageBinding.TWOMODE_PRIORITY, null,
                    transformType, cornerRadius, scaleType);
            return false;
        }

        @Override
        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target,
                                       boolean isFromMemoryCache, boolean isFirstResource) {
            if (twomode == ImageBinding.TWOMODE_BACKUP) return true;
            loadImage(imageView, secondUrl, null, twomode, null, transformType, cornerRadius, scaleType);
            return false;
        }
    }
}