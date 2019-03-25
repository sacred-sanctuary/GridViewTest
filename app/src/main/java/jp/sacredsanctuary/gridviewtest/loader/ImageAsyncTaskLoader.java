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
 *
 * See the following code:
 * https://android.googlesource.com/platform/packages/apps/UnifiedEmail/+/master/src/com/android
 * /mail/ui/MailAsyncTaskLoader.java
 * https://android.googlesource.com/platform/frameworks/opt/photoviewer/+/refs/heads/master/src
 * /com/android/ex/photo/loaders/PhotoBitmapLoader.java
 */
package jp.sacredsanctuary.gridviewtest.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.graphics.Bitmap;

import jp.sacredsanctuary.gridviewtest.Application;
import jp.sacredsanctuary.gridviewtest.util.CreateThumbnails;

/**
 * A custom Loader that load the image data from a URL.
 */
public class ImageAsyncTaskLoader extends AsyncTaskLoader<Bitmap> {
    private static final String ClassName = ImageAsyncTaskLoader.class.getSimpleName();
    private String mUrl;
    private Bitmap mBitmap;
    private CreateThumbnails mCreateThumbnails;

    public ImageAsyncTaskLoader(Context context, String url) {
        super(context);
        this.mUrl = url;
        mCreateThumbnails = Application.getApplication().getCreateThumbnails();
    }

    /**
     * This is where the bulk of our work is done.  This function is
     * called in a background thread and should generate a new set of
     * data to be published by the loader.
     */
    @Override
    public Bitmap loadInBackground() {
        return mCreateThumbnails.getThumbnails(mUrl);
    }

    /**
     * Called when there is new data to deliver to the client.  The
     * super class will take care of delivering it; the implementation
     * here just adds a little more logic.
     */
    @Override
    public void deliverResult(Bitmap bitmap) {
        if (isReset()) {
            // An async query came in while the loader is stopped.  We
            // don't need the result.
            if (null != bitmap) {
                onReleaseResources(bitmap);
            }
            return;
        }
        Bitmap oldBitmap = mBitmap;
        mBitmap = bitmap;

        if (isStarted()) {
            // If the Loader is currently started, we can immediately
            // deliver its results.
            super.deliverResult(bitmap);
        }

        // At this point we can release the resources associated with
        // 'oldBitmap' if needed; now that the new result is delivered we
        // know that it is no longer in use.
        if ((null != oldBitmap) && (oldBitmap != bitmap) && !oldBitmap.isRecycled()) {
            onReleaseResources(oldBitmap);
        }
    }

    /**
     * Handles a request to start the Loader.
     */
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (mBitmap != null) {
            deliverResult(mBitmap);
        }

        if (takeContentChanged() || (null == mBitmap)) {
            // If the data has changed since the last time it was loaded
            // or is not currently available, start a load.
            forceLoad();
        }
    }

    /**
     * Handles a request to stop the Loader.
     */
    @Override
    protected void onStopLoading() {
        super.onStopLoading();
        cancelLoad();
    }

    /**
     * Handles a request to cancel a load.
     */
    @Override
    public void onCanceled(Bitmap bitmap) {
        super.onCanceled(bitmap);

        // At this point we can release the resources associated with 'bitmap'
        // if needed.
        if (null != bitmap) {
            onReleaseResources(bitmap);
        }
    }

    /**
     * Handles a request to completely reset the Loader.
     */
    @Override
    protected void onReset() {
        super.onReset();

        // Ensure the loader is stopped
        onStopLoading();

        // At this point we can release the resources associated with 'bitmap'
        // if needed.
        if (null != mBitmap) {
            onReleaseResources(mBitmap);
            mBitmap = null;
        }
    }

    /**
     * Helper function to take care of releasing resources associated
     * with an actively loaded data set.
     */
    private void onReleaseResources(Bitmap bitmap) {
        if ((null != bitmap) && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }
}
