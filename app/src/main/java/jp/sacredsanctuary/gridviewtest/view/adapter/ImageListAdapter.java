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
package jp.sacredsanctuary.gridviewtest.view.adapter;

import android.app.LoaderManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import jp.sacredsanctuary.common.util.LogUtil;
import jp.sacredsanctuary.gridviewtest.R;
import jp.sacredsanctuary.gridviewtest.view.ui.ViewHolder;

/**
 * Adapter for a GridView containing image items from the Image data of the device.
 */
public class ImageListAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final String ClassName = ImageListAdapter.class.getSimpleName();
    private final LoaderManager mLoaderManager;
    private View.OnClickListener mItemClickCallback;
    private List<String> mImageFilePath;

    public ImageListAdapter(View.OnClickListener callback, LoaderManager loaderManager) {
        this.mItemClickCallback = callback;
        this.mLoaderManager = loaderManager;
        this.mImageFilePath = new ArrayList<>();
    }

    public void setItems(List<String> imageFilePath) {
        LogUtil.V(ClassName, "ImageListAdapter setItems() imageFilePath.size:"
                + imageFilePath.size());
        mImageFilePath.clear();
        mImageFilePath.addAll(imageFilePath);
        notifyItemRangeInserted(0, getItemCount());
    }

    public void clearItems() {
        LogUtil.V(ClassName, "ImageListAdapter clearItems() ");
        mImageFilePath.clear();
    }

    public List<String> getAllItem() {
        LogUtil.V(ClassName, "ImageListAdapter getAllItem() ");
        return mImageFilePath;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LogUtil.V(ClassName, "ImageListAdapter onCreateViewHolder() [I N] ");
        View inflate = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_list_item, parent, false);
        ViewHolder holder = new ViewHolder(inflate);

        LogUtil.V(ClassName, "ImageListAdapter onCreateViewHolder() [OUT] ");
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageUrl(mImageFilePath.get(position));
        holder.imageView.setOnClickListener(mItemClickCallback);
        holder.imageView.setTag(mImageFilePath.get(position));
        LOOP:
        try {
            mLoaderManager.initLoader(position, null, holder.imageView);
        } catch (Exception e) {
            break LOOP;
        }
    }

    @Override
    public int getItemCount() {
        return (mImageFilePath != null) ? mImageFilePath.size() : 0;
    }
}
