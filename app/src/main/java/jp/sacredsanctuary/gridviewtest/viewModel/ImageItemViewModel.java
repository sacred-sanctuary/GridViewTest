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
package jp.sacredsanctuary.gridviewtest.viewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import jp.sacredsanctuary.common.util.LogUtil;
import jp.sacredsanctuary.gridviewtest.Application;
import jp.sacredsanctuary.gridviewtest.util.thread.LoadImageItemListThread;

public class ImageItemViewModel extends ViewModel {
    private static final String ClassName = ImageItemViewModel.class.getSimpleName();
    private MutableLiveData<List<String>> mImageItemList;

    public LiveData<List<String>> getImageItemList() {
        if (mImageItemList == null) {
            mImageItemList = new MutableLiveData<>();
            mImageItemList.postValue(loadImageItemList());
        }
        return mImageItemList;
    }

    private List<String> loadImageItemList() {
        List<String> list = new ArrayList<>();
        ExecutorService exec = Executors.newSingleThreadExecutor();
        Future<List<String>> future = exec.submit(
                new LoadImageItemListThread(Application.getApplication().getContext()));
        try {
            list.addAll(future.get());
        } catch (ExecutionException e) {
            LogUtil.E(ClassName, "loadImageItemList() [ERR] " + e.getMessage());
        } catch (InterruptedException e) {
            LogUtil.E(ClassName, "loadImageItemList() [ERR] " + e.getMessage());
        }
        return list;
    }
}
