/*
 * Copyright (C) 2019 Sacred Sanctuary Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jp.sacredsanctuary.gridviewtest.loader;

import android.annotation.SuppressLint;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

import jp.sacredsanctuary.gridviewtest.R;

/**
 * Helper class to handle all the callbacks that occur when interacting with loaders.
 */
@SuppressLint("AppCompatCustomView")
public class ImageViewLoader extends ImageView implements LoaderManager.LoaderCallbacks<Bitmap> {
    private static final String ClassName = ImageViewLoader.class.getSimpleName();
    private String mUrl;
    private WeakReference<ImageViewLoader> mImageViewReference;

    public ImageViewLoader(Context context) {
        super(context);
        mUrl = null;
        // Use a WeakReference to ensure the ImageViewLoader can be garbage collected
        mImageViewReference = new WeakReference<ImageViewLoader>(this);
    }

    public ImageViewLoader(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mUrl = null;
        // Use a WeakReference to ensure the ImageViewLoader can be garbage collected
        mImageViewReference = new WeakReference<ImageViewLoader>(this);
    }

    public ImageViewLoader(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public void setImageUrl(String url) {
        this.mUrl = url;
    }

    @Override
    public Loader<Bitmap> onCreateLoader(int id, Bundle args) {
        if (null == mImageViewReference) {
            // Use a WeakReference to ensure the ImageViewLoader can be garbage collected
            mImageViewReference = new WeakReference<ImageViewLoader>(this);
        }
        return new ImageAsyncTaskLoader(getContext(), mUrl);
    }

    @Override
    public void onLoadFinished(Loader<Bitmap> loader, Bitmap bitmap) {
        if (null != mImageViewReference) {
            ImageViewLoader imageView = mImageViewReference.get();
            if (null != imageView) {
                if (null != bitmap) {
                    ViewGroup.MarginLayoutParams lp =
                            (ViewGroup.MarginLayoutParams) getLayoutParams();
                    lp.leftMargin = 0;
                    lp.rightMargin = 0;
                    lp.topMargin = 0;
                    lp.bottomMargin = 0;
                    imageView.setImageBitmap(bitmap);
                } else {
                    imageView.setImageResource(R.drawable.progress_small);
                }
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Bitmap> loader) {
    }
}
