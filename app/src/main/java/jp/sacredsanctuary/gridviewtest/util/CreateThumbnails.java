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
package jp.sacredsanctuary.gridviewtest.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.provider.MediaStore;

import jp.sacredsanctuary.common.util.LogUtil;

public class CreateThumbnails {
    private static final String ClassName = CreateThumbnails.class.getSimpleName();
    private Context mContext;

    public CreateThumbnails(Context context) {
        LogUtil.V(ClassName, "ImageListFactory() ");
        this.mContext = context;
    }

    /**
     * Gets thumbnail.
     */
    public Bitmap getThumbnails(String path) {
        LogUtil.V(ClassName, "getThumbnails() path:" + path);

        Bitmap image = null;
        ContentResolver resolver = mContext.getContentResolver();
        Cursor cursor = resolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, // data type
                null, // projection (null is all items)
                MediaStore.Images.ImageColumns.DATA + " = ?",
                // filter selection (null is no selection)
                new String[]{path}, // filter parameters
                null // sort order
        );
        if (cursor.moveToFirst()) {
            // Get thumbnail
            long id = cursor.getLong(cursor.getColumnIndex("_id"));
            image = MediaStore.Images.Thumbnails.getThumbnail(
                    resolver, id, MediaStore.Images.Thumbnails.MICRO_KIND, null);
        }
        return image;

    }
}
