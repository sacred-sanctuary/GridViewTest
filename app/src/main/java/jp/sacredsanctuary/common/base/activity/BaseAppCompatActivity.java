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
package jp.sacredsanctuary.common.base.activity;

import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;

import java.util.Arrays;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.view.ActionMode;
import androidx.core.app.TaskStackBuilder;
import jp.sacredsanctuary.common.util.LogUtil;
import jp.sacredsanctuary.common.util.PermissionUtils;

/**
 * @braif A wrapper over {@link AppCompatActivity} which allows to override some methods.
 * @class BaseAppCompatActivity
 */
public class BaseAppCompatActivity extends AppCompatActivity {
    private static final int REQUEST_ALL_PERMISSIONS = 1;
    private final String CLASS_NAME;

    /**
     * Default constructor.
     */
    public BaseAppCompatActivity() {
        super();
        this.CLASS_NAME = "BaseActivity";
    }

    /**
     * Default constructor.
     */
    public BaseAppCompatActivity(String className) {
        super();
        this.CLASS_NAME = className + "Base";
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtil.V(CLASS_NAME, "onCreate() [I N] ");
        super.onCreate(savedInstanceState);
        LogUtil.V(CLASS_NAME, "onCreate() [OUT] ");
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        LogUtil.V(CLASS_NAME, "onPostCreate() [I N] ");
        super.onPostCreate(savedInstanceState);
        LogUtil.V(CLASS_NAME, "onPostCreate() [OUT] ");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        LogUtil.V(CLASS_NAME, "onConfigurationChanged() [I N] ");
        super.onConfigurationChanged(newConfig);
        LogUtil.V(CLASS_NAME, "onConfigurationChanged() [OUT] ");
    }

    @Override
    protected void onPostResume() {
        LogUtil.V(CLASS_NAME, "onPostResume() [I N] ");
        super.onPostResume();
        LogUtil.V(CLASS_NAME, "onPostResume() [OUT] ");
    }

    @Override
    protected void onStart() {
        LogUtil.V(CLASS_NAME, "onStart() [I N] ");
        super.onStart();
        LogUtil.V(CLASS_NAME, "onStart() [OUT] ");
    }

    @Override
    protected void onResume() {
        LogUtil.V(CLASS_NAME, "onResume() [I N] ");
        super.onResume();
        LogUtil.V(CLASS_NAME, "onResume() [OUT] ");
    }

    @Override
    protected void onStop() {
        LogUtil.V(CLASS_NAME, "onStop() [I N] ");
        super.onStop();
        LogUtil.V(CLASS_NAME, "onStop() [OUT] ");
    }

    @Override
    protected void onDestroy() {
        LogUtil.V(CLASS_NAME, "onDestroy() [I N] ");
        super.onDestroy();
        LogUtil.V(CLASS_NAME, "onDestroy() [OUT] ");
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        LogUtil.V(CLASS_NAME, "onTitleChanged() [I N] ");
        super.onTitleChanged(title, color);
        LogUtil.V(CLASS_NAME, "onTitleChanged() [OUT] ");
    }

    /**
     * Notifies the Activity that a support action mode has been started.
     * Activity subclasses overriding this method should call the superclass implementation.
     *
     * @param mode The new action mode.
     */
    @Override
    @CallSuper
    public void onSupportActionModeStarted(@NonNull ActionMode mode) {
        LogUtil.V(CLASS_NAME, "onSupportActionModeStarted() [I N] ");
        super.onSupportActionModeStarted(mode);
        LogUtil.V(CLASS_NAME, "onSupportActionModeStarted() [OUT] ");
    }

    /**
     * Notifies the activity that a support action mode has finished.
     * Activity subclasses overriding this method should call the superclass implementation.
     *
     * @param mode The action mode that just finished.
     */
    @Override
    @CallSuper
    public void onSupportActionModeFinished(@NonNull ActionMode mode) {
        LogUtil.V(CLASS_NAME, "onSupportActionModeFinished() [I N] ");
        super.onSupportActionModeFinished(mode);
        LogUtil.V(CLASS_NAME, "onSupportActionModeFinished() [OUT] ");
    }

    /**
     * Called when a support action mode is being started for this window. Gives the
     * callback an opportunity to handle the action mode in its own unique and
     * beautiful way. If this method returns null the system can choose a way
     * to present the mode or choose not to start the mode at all.
     *
     * @param callback Callback to control the lifecycle of this action mode
     * @return The ActionMode that was started, or null if the system should present it
     */
    @Nullable
    @Override
    public ActionMode onWindowStartingSupportActionMode(@NonNull ActionMode.Callback callback) {
        LogUtil.V(CLASS_NAME, "onWindowStartingSupportActionMode() [I N] ");
        ActionMode actionMode = super.onWindowStartingSupportActionMode(callback);
        LogUtil.V(CLASS_NAME, "onWindowStartingSupportActionMode() [OUT] ");
        return actionMode;
    }

    /**
     * Support version of {@link #onCreateNavigateUpTaskStack(android.app.TaskStackBuilder)}.
     * This method will be called on all platform versions.
     *
     * Define the synthetic task stack that will be generated during Up navigation from
     * a different task.
     *
     * <p>The default implementation of this method adds the parent chain of this activity
     * as specified in the manifest to the supplied {@link androidx.core.app.TaskStackBuilder}.
     * Applications
     * may choose to override this method to construct the desired task stack in a different
     * way.</p>
     *
     * <p>This method will be invoked by the default implementation of {@link #onNavigateUp()}
     * if {@link #shouldUpRecreateTask(android.content.Intent)} returns true when supplied with
     * the intent
     * returned by {@link #getParentActivityIntent()}.</p>
     *
     * <p>Applications that wish to supply extra Intent parameters to the parent stack defined
     * by the manifest should override
     * {@link #onPrepareSupportNavigateUpTaskStack(androidx.core.app.TaskStackBuilder)}.</p>
     *
     * @param builder An empty TaskStackBuilder - the application should add intents representing
     *                the desired task stack
     */
    @Override
    public void onCreateSupportNavigateUpTaskStack(@NonNull TaskStackBuilder builder) {
        LogUtil.V(CLASS_NAME, "onCreateSupportNavigateUpTaskStack() [I N] ");
        super.onCreateSupportNavigateUpTaskStack(builder);
        LogUtil.V(CLASS_NAME, "onCreateSupportNavigateUpTaskStack() [OUT] ");
    }

    /**
     * Support version of {@link #onPrepareNavigateUpTaskStack(android.app.TaskStackBuilder)}.
     * This method will be called on all platform versions.
     *
     * Prepare the synthetic task stack that will be generated during Up navigation
     * from a different task.
     *
     * <p>This method receives the {@link androidx.core.app.TaskStackBuilder} with the
     * constructed series of
     * Intents as generated by
     * {@link #onCreateSupportNavigateUpTaskStack(androidx.core.app.TaskStackBuilder)}.
     * If any extra data should be added to these intents before launching the new task,
     * the application should override this method and add that data here.</p>
     *
     * @param builder A TaskStackBuilder that has been populated with Intents by
     *                onCreateNavigateUpTaskStack.
     */
    @Override
    public void onPrepareSupportNavigateUpTaskStack(@NonNull TaskStackBuilder builder) {
        LogUtil.V(CLASS_NAME, "onPrepareSupportNavigateUpTaskStack() [I N] ");
        super.onPrepareSupportNavigateUpTaskStack(builder);
        LogUtil.V(CLASS_NAME, "onPrepareSupportNavigateUpTaskStack() [OUT] ");
    }

    /**
     * This method is called whenever the user chooses to navigate Up within your application's
     * activity hierarchy from the action bar.
     *
     * <p>If a parent was specified in the manifest for this activity or an activity-alias to it,
     * default Up navigation will be handled automatically. See
     * {@link #getSupportParentActivityIntent()} for how to specify the parent. If any activity
     * along the parent chain requires extra Intent arguments, the Activity subclass
     * should override the method
     * {@link #onPrepareSupportNavigateUpTaskStack(androidx.core.app.TaskStackBuilder)}
     * to supply those arguments.</p>
     *
     * <p>See <a href="{@docRoot}guide/topics/fundamentals/tasks-and-back-stack.html">Tasks and
     * Back Stack</a> from the developer guide and
     * <a href="{@docRoot}design/patterns/navigation.html">Navigation</a> from the design guide
     * for more information about navigating within your app.</p>
     *
     * <p>See the {@link androidx.core.app.TaskStackBuilder} class and the Activity methods
     * {@link #getSupportParentActivityIntent()},
     * {@link #supportShouldUpRecreateTask(android.content.Intent)}, and
     * {@link #supportNavigateUpTo(android.content.Intent)} for help implementing custom Up
     * navigation.</p>
     *
     * @return true if Up navigation completed successfully and this Activity was finished,
     * false otherwise.
     */
    @Override
    public boolean onSupportNavigateUp() {
        LogUtil.V(CLASS_NAME, "onSupportNavigateUp() [I N] ");
        boolean ret = super.onSupportNavigateUp();
        LogUtil.V(CLASS_NAME, "onSupportNavigateUp() [OUT] ret:" + ret);
        return ret;
    }

    @Override
    public void onContentChanged() {
        LogUtil.V(CLASS_NAME, "onContentChanged() [I N] ");
        super.onContentChanged();
        LogUtil.V(CLASS_NAME, "onContentChanged() [OUT] ");
    }

    /**
     * @deprecated Use {@link #onContentChanged()} instead.
     */
    @Deprecated
    @Override
    public void onSupportContentChanged() {
        LogUtil.V(CLASS_NAME, "onSupportContentChanged() [I N] ");
        super.onSupportContentChanged();
        LogUtil.V(CLASS_NAME, "onSupportContentChanged() [OUT] ");
    }

    /**
     * {@inheritDoc}
     *
     * <p>Please note: AppCompat uses its own feature id for the action bar:
     * {@link AppCompatDelegate#FEATURE_SUPPORT_ACTION_BAR FEATURE_SUPPORT_ACTION_BAR}.</p>
     */
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        LogUtil.V(CLASS_NAME, "onMenuOpened() [I N] ");
        boolean ret = super.onMenuOpened(featureId, menu);
        LogUtil.V(CLASS_NAME, "onMenuOpened() [OUT] ret:" + ret);
        return ret;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Please note: AppCompat uses its own feature id for the action bar:
     * {@link AppCompatDelegate#FEATURE_SUPPORT_ACTION_BAR FEATURE_SUPPORT_ACTION_BAR}.</p>
     */
    @Override
    public void onPanelClosed(int featureId, Menu menu) {
        LogUtil.V(CLASS_NAME, "onPanelClosed() [I N] ");
        super.onPanelClosed(featureId, menu);
        LogUtil.V(CLASS_NAME, "onPanelClosed() [OUT] ");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        LogUtil.V(CLASS_NAME, "onSaveInstanceState() [I N] ");
        super.onSaveInstanceState(outState);
        LogUtil.V(CLASS_NAME, "onSaveInstanceState() [OUT] ");
    }

    protected boolean arePermissionsGranted(String permissions[], int[] grantResult) {
        String[] list = PermissionUtils.getUnauthorizedPermissionList(getBaseContext());
        for (int i = 0; i < permissions.length; i++) {
            if (grantResult[i] != PackageManager.PERMISSION_GRANTED
                    && Arrays.asList(list).contains(permissions[i])) {
                return false;
            }
        }
        return true;
    }
}
