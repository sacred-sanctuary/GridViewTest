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
package jp.sacredsanctuary.gridviewtest.util.thread;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import jp.sacredsanctuary.common.util.LogUtil;
import jp.sacredsanctuary.common.util.Preconditions;

public class LoadImageItemListThread implements Callable<List<String>> {
    private static final String ClassName = LoadImageItemListThread.class.getSimpleName();
    private final Context mContext;

    public LoadImageItemListThread(Context context) {
        LogUtil.V(ClassName, "LoadImageItemListThread() [INF] context:" + context);
        this.mContext = context;
    }

    public List<String> call() throws Exception {
        LogUtil.E(ClassName, "call() [INF] ");
        return loadImageItemList();
    }

    private List<String> loadImageItemList() {
        LogUtil.V(ClassName, "loadImageItemList() [I N] ");
        List<String> list = new ArrayList<>();

        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Cursor cursor = mContext.getContentResolver().query(uri, null, null, null, null);
        if (!Preconditions.checkNotNull(cursor)) {
            LogUtil.E(ClassName, "loadImageItemList() [OUT] cursor is null pointer");
            return list;
        }
        cursor.moveToLast();

        for (int i = 0; i < cursor.getCount(); i++) {
            String filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            list.add(filePath);
            cursor.moveToPrevious();
        }
        LogUtil.V(ClassName, "loadImageItemList() [OUT] list.size:" + list.size());
        return list;
    }
}
