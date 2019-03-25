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

import jp.sacredsanctuary.common.util.LogUtil;
import jp.sacredsanctuary.gridviewtest.Application;
import jp.sacredsanctuary.gridviewtest.R;

public class PreferenceActivity extends android.preference.PreferenceActivity {
    private static final String ClassName = PreferenceActivity.class.getSimpleName();

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtil.V(ClassName, "onCreate() ");
        super.onCreate(savedInstanceState);

        getPreferenceManager().setSharedPreferencesName(
                Application.getDefaultSharedPreferencesName(this));
        getPreferenceManager().setSharedPreferencesMode(
                Application.getDefaultSharedPreferencesMode());
        addPreferencesFromResource(R.xml.preferences);
    }
}
