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
package jp.sacredsanctuary.gridviewtest.view.ui;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;
import jp.sacredsanctuary.gridviewtest.R;
import jp.sacredsanctuary.gridviewtest.loader.ImageViewLoader;

public class ViewHolder extends RecyclerView.ViewHolder {
    private static final String ClassName = ViewHolder.class.getSimpleName();
    public ImageViewLoader imageView;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.file_list_itme_image);
        imageView.setImageDrawable(ResourcesCompat.getDrawable(itemView.getContext().getResources(),
                R.drawable.progress_small, null));
    }
}
