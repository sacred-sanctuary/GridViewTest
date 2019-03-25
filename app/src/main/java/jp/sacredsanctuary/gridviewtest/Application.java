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
package jp.sacredsanctuary.gridviewtest;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import jp.sacredsanctuary.common.util.LogUtil;
import jp.sacredsanctuary.gridviewtest.util.CreateThumbnails;

/**
 * Top-level Application class for the GridViewTest app.
 */
public class Application extends android.app.Application {
    private static final String ClassName = Application.class.getSimpleName();
    private static Application sApplication = null;
    private CreateThumbnails mCreateThumbnails;

    public Application() {
        super();
        LogUtil.V(ClassName, "Application() ");
        sApplication = this;
    }

    /**
     * Returns the only instance of Application.
     *
     * @return the only instance of Application
     */
    public static Application getApplication() {
        return sApplication;
    }

    /**
     * Returns the application's default SharedPreferences.
     *
     * @param context application context we can use for shared preferences access
     * @return the application's default SharedPreferences
     */
    public static SharedPreferences getDefaultSharedPreferences(Context context) {
        return context.getSharedPreferences(getDefaultSharedPreferencesName(context),
                getDefaultSharedPreferencesMode());
    }

    /**
     * Returns the application's SharedPreferences name.
     *
     * @param context application context we can use for shared preferences access
     * @return the application's SharedPreferences name
     */
    public static String getDefaultSharedPreferencesName(Context context) {
        return context.getPackageName() + "_preferences";
    }

    /**
     * Returns the application's shared preferences mode.
     *
     * @return the application's shared preferences mode
     */
    public static int getDefaultSharedPreferencesMode() {
        return MODE_PRIVATE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate() {
        LogUtil.V(ClassName, "onCreate() ");
        super.onCreate();

        mCreateThumbnails = new CreateThumbnails(getContext());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onTerminate() {
        LogUtil.V(ClassName, "onTerminate() ");
        super.onTerminate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        LogUtil.V(ClassName, "onConfigurationChanged() newConfig:" + newConfig);
        super.onConfigurationChanged(newConfig);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onLowMemory() {
        LogUtil.V(ClassName, "onLowMemory() ");
        super.onLowMemory();
    }

    /**
     * Returns the application context.
     *
     * @return application context
     */
    public Context getContext() {
        return getApplicationContext();
    }

    public CreateThumbnails getCreateThumbnails() {
        return mCreateThumbnails;
    }
}
