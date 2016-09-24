package io.fruitful.binding;

/**
 * Created by hieuxit on 9/24/16.
 */

public class ImageLoaderFactory {
    final static boolean HAS_PICASSO = canHasClass("com.squareup.picasso.Picasso");
    final static boolean HAS_GLIDE = canHasClass("com.bumptech.glide.Glide");
    final static boolean HAS_UIL = canHasClass("com.nostra13.universalimageloader.core.ImageLoader");

    static boolean canHasClass(String className) {
        try {
            Class.forName(className);
            return true;
        } catch (ClassNotFoundException e) {
        }
        return false;
    }

    static ImageLoader resolveImageLoader() {
        if (HAS_PICASSO) {
            return new PicassoImageLoader();
        }
        if (HAS_GLIDE) {
            return new GlideImageLoader();
        }
        if (HAS_UIL) {
            return new UILImageLoader();
        }
        throw new IllegalStateException("You must provide a ImageLoader. Must be one of Picasso, Glide or UIL");
    }
}
