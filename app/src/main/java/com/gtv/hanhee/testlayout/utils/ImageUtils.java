package com.gtv.hanhee.testlayout.utils;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gtv.hanhee.testlayout.api.Constants;

import de.hdodenhof.circleimageview.CircleImageView;

public class ImageUtils {

    public static void loadImageByGlide(Context mContext, String imageUrl, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions();

        requestOptions.centerCrop();

        if (imageUrl.contains("http")) {
            Glide.with(mContext)
                    .setDefaultRequestOptions(requestOptions)
                    .load(imageUrl)
                    .into(imageView);
        } else {
            Glide.with(mContext)
                    .setDefaultRequestOptions(requestOptions)
                    .load(Constants.API_ENDPOINT2 + imageUrl)
                    .into(imageView);
        }
    }

    public static void loadImageLocalbyGlide(Context mContext, String imageUrl, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions();

        requestOptions.centerCrop();

        if (imageUrl.contains("http")) {
            Glide.with(mContext)
                    .setDefaultRequestOptions(requestOptions)
                    .load(imageUrl)
                    .into(imageView);
        } else {
            Glide.with(mContext)
                    .setDefaultRequestOptions(requestOptions)
                    .load(Constants.API_ENDPOINT2 + imageUrl)
                    .into(imageView);
        }
    }

    public static void loadImageByGlideWithResize(Context mContext, String imageUrl, ImageView imageView, int width, int height) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop();

        if (imageUrl.contains("http")) {
            Glide.with(mContext)
                    .setDefaultRequestOptions(requestOptions)
                    .load(imageUrl)
                    .apply(new RequestOptions().override(width, height))
                    .into(imageView);
        } else {
            Glide.with(mContext)
                    .setDefaultRequestOptions(requestOptions)
                    .load(Constants.API_ENDPOINT2 + imageUrl)
                    .apply(new RequestOptions().override(width, height))
                    .into(imageView);
        }
    }

    public static void loadImageInsideFragmentByGlide(Fragment fragment, String imageUrl, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop();

        if (imageUrl.contains("http")) {
            Glide.with(fragment)
                    .setDefaultRequestOptions(requestOptions)
                    .load(imageUrl)
                    .into(imageView);
        } else {
            Glide.with(fragment)
                    .setDefaultRequestOptions(requestOptions)
                    .load(Constants.API_ENDPOINT2 + imageUrl)
                    .into(imageView);
        }
    }

    public static void loadCircleImageByGlide(Context mContext, String imageUrl, CircleImageView imageView) {
        RequestOptions requestOptions = new RequestOptions();

        requestOptions.centerCrop();

        if (imageUrl.contains("http")) {
            Glide.with(mContext)
                    .setDefaultRequestOptions(requestOptions)
                    .load(imageUrl)
                    .into(imageView);
        } else {
            Glide.with(mContext)
                    .setDefaultRequestOptions(requestOptions)
                    .load(Constants.API_ENDPOINT2 + imageUrl)
                    .into(imageView);
        }
    }

    public static void loadCircleImageByGlideResize(Context mContext, String imageUrl, CircleImageView imageView, int width, int height) {
        RequestOptions requestOptions = new RequestOptions();

        requestOptions.centerCrop();

        if (imageUrl.contains("http")) {
            Glide.with(mContext)
                    .setDefaultRequestOptions(requestOptions)
                    .load(imageUrl)
                    .apply(new RequestOptions().override(width, height))
                    .into(imageView);
        } else {
            Glide.with(mContext)
                    .setDefaultRequestOptions(requestOptions)
                    .load(Constants.API_ENDPOINT2 + imageUrl)
                    .apply(new RequestOptions().override(width, height))
                    .into(imageView);
        }
    }

    public static void loadCircleImageInsideFragmentByGlide(Fragment fragment, String imageUrl, CircleImageView imageView) {
        RequestOptions requestOptions = new RequestOptions();

        requestOptions.centerCrop();

        if (imageUrl.contains("http")) {
            Glide.with(fragment)
                    .setDefaultRequestOptions(requestOptions)
                    .load(imageUrl)
                    .into(imageView);
        } else {
            Glide.with(fragment)
                    .setDefaultRequestOptions(requestOptions)
                    .load(Constants.API_ENDPOINT2 + imageUrl)
                    .into(imageView);
        }
    }


}
