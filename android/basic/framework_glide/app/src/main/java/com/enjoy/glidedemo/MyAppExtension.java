package com.enjoy.glidedemo;

import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.load.resource.bitmap.Rotate;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;

@GlideExtension
public class MyAppExtension {

    private MyAppExtension() {
    } // utility class

    @GlideOption
    public static BaseRequestOptions<?> defaultImg(BaseRequestOptions<?> options) {
        DrawableCrossFadeFactory factory =
                new DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build();
        return options
                .placeholder(R.drawable.hold)
                .error(R.drawable.error)
                .fallback(R.drawable.empty)
                .transform(new Rotate(90));
    }
}

