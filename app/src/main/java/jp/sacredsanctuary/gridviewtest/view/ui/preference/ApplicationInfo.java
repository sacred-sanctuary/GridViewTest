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
package jp.sacredsanctuary.gridviewtest.view.ui.preference;

import android.os.Bundle;

import jp.sacredsanctuary.common.base.activity.BaseActivity;
import jp.sacredsanctuary.gridviewtest.R;

public class ApplicationInfo extends BaseActivity {
    private static final String ClassName = ApplicationInfo.class.getSimpleName();

    public ApplicationInfo() {
        super(ClassName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.application_info);
    }
}
