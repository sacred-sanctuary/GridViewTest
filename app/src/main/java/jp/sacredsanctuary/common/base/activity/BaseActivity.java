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

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.app.TaskStackBuilder;
import android.app.VoiceInteractor;
import android.app.assist.AssistContent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.AttributeSet;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.KeyEvent;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;

import java.util.List;

import androidx.annotation.CallSuper;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import jp.sacredsanctuary.common.util.LogUtil;


/**
 * @braif A wrapper over {@link Activity} which allows to override some methods.
 * @class BaseActivity
 */
public abstract class BaseActivity extends Activity {
    private final String CLASS_NAME;

    /**
     * Default constructor.
     */
    public BaseActivity() {
        super();
        this.CLASS_NAME = "BaseActivity";
    }

    /**
     * Default constructor.
     */
    public BaseActivity(String className) {
        super();
        this.CLASS_NAME = className + "Base";
    }

    /**
     * Called when the activity is starting.  This is where most initialization
     * should go: calling {@link #setContentView(int)} to inflate the
     * activity's UI, using {@link #findViewById} to programmatically interact
     * with widgets in the UI, calling
     * {@link #managedQuery(android.net.Uri, String[], String, String[], String)} to retrieve
     * cursors for data being displayed, etc.
     *
     * <p>You can call {@link #finish} from within this function, in
     * which case onDestroy() will be immediately called without any of the rest
     * of the activity lifecycle ({@link #onStart}, {@link #onResume},
     * {@link #onPause}, etc) executing.
     *
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it
     *                           most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note:
     *                           Otherwise it is null.</i></b>
     * @see #onStart
     * @see #onSaveInstanceState
     * @see #onRestoreInstanceState
     * @see #onPostCreate
     */
    @MainThread
    @CallSuper
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtil.V(CLASS_NAME, "onCreate() [I N] ");
        super.onCreate(savedInstanceState);
        LogUtil.V(CLASS_NAME, "onCreate() [OUT] ");
    }

    /**
     * Same as {@link #onCreate(android.os.Bundle)} but called for those activities created with
     * the attribute {@link android.R.attr#persistableMode} set to
     * <code>persistAcrossReboots</code>.
     *
     * @param savedInstanceState if the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it
     *                           most
     *                           recently supplied in {@link #onSaveInstanceState}.
     *                           <b><i>Note: Otherwise it is null.</i></b>
     * @param persistentState    if the activity is being re-initialized after
     *                           previously being shut down or powered off then this Bundle
     *                           contains the data it most
     *                           recently supplied to outPersistentState in
     *                           {@link #onSaveInstanceState}.
     *                           <b><i>Note: Otherwise it is null.</i></b>
     * @see #onCreate(android.os.Bundle)
     * @see #onStart
     * @see #onSaveInstanceState
     * @see #onRestoreInstanceState
     * @see #onPostCreate
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState,
            @Nullable PersistableBundle persistentState) {
        LogUtil.V(CLASS_NAME, "onCreate() [I N] ");
        super.onCreate(savedInstanceState, persistentState);
        LogUtil.V(CLASS_NAME, "onCreate() [OUT] ");
    }

    /**
     * This method is called after {@link #onStart} when the activity is
     * being re-initialized from a previously saved state, given here in
     * <var>savedInstanceState</var>.  Most implementations will simply use {@link #onCreate}
     * to restore their state, but it is sometimes convenient to do it here
     * after all of the initialization has been done or to allow subclasses to
     * decide whether to use your default implementation.  The default
     * implementation of this method performs a restore of any view state that
     * had previously been frozen by {@link #onSaveInstanceState}.
     *
     * <p>This method is called between {@link #onStart} and
     * {@link #onPostCreate}.
     *
     * @param savedInstanceState the data most recently supplied in {@link #onSaveInstanceState}.
     * @see #onCreate
     * @see #onPostCreate
     * @see #onResume
     * @see #onSaveInstanceState
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        LogUtil.V(CLASS_NAME, "onRestoreInstanceState() [I N] ");
        super.onRestoreInstanceState(savedInstanceState);
        LogUtil.V(CLASS_NAME, "onRestoreInstanceState() [OUT] ");
    }

    /**
     * This is the same as {@link #onRestoreInstanceState(Bundle)} but is called for activities
     * created with the attribute {@link android.R.attr#persistableMode} set to
     * <code>persistAcrossReboots</code>. The {@link android.os.PersistableBundle} passed
     * came from the restored PersistableBundle first
     * saved in {@link #onSaveInstanceState(Bundle, PersistableBundle)}.
     *
     * <p>This method is called between {@link #onStart} and
     * {@link #onPostCreate}.
     *
     * <p>If this method is called {@link #onRestoreInstanceState(Bundle)} will not be called.
     *
     * @param savedInstanceState the data most recently supplied in {@link #onSaveInstanceState}.
     * @param persistentState    the data most recently supplied in {@link #onSaveInstanceState}.
     * @see #onRestoreInstanceState(Bundle)
     * @see #onCreate
     * @see #onPostCreate
     * @see #onResume
     * @see #onSaveInstanceState
     */
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState,
            PersistableBundle persistentState) {
        LogUtil.V(CLASS_NAME, "onRestoreInstanceState() [I N] ");
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        LogUtil.V(CLASS_NAME, "onRestoreInstanceState() [OUT] ");
    }

    /**
     * Called when activity start-up is complete (after {@link #onStart}
     * and {@link #onRestoreInstanceState} have been called).  Applications will
     * generally not implement this method; it is intended for system
     * classes to do final initialization after application code has run.
     *
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it
     *                           most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note:
     *                           Otherwise it is null.</i></b>
     * @see #onCreate
     */
    @CallSuper
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        LogUtil.V(CLASS_NAME, "onPostCreate() [I N] ");
        super.onPostCreate(savedInstanceState);
        LogUtil.V(CLASS_NAME, "onPostCreate() [OUT] ");
    }

    /**
     * This is the same as {@link #onPostCreate(Bundle)} but is called for activities
     * created with the attribute {@link android.R.attr#persistableMode} set to
     * <code>persistAcrossReboots</code>.
     *
     * @param savedInstanceState The data most recently supplied in {@link #onSaveInstanceState}
     * @param persistentState    The data caming from the PersistableBundle first
     *                           saved in {@link #onSaveInstanceState(Bundle, PersistableBundle)}.
     * @see #onCreate
     */
    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState,
            @Nullable PersistableBundle persistentState) {
        LogUtil.V(CLASS_NAME, "onPostCreate() [I N] ");
        super.onPostCreate(savedInstanceState, persistentState);
        LogUtil.V(CLASS_NAME, "onPostCreate() [OUT] ");
    }

    /**
     * Called after {@link #onCreate} &mdash; or after {@link #onRestart} when
     * the activity had been stopped, but is now again being displayed to the
     * user.  It will be followed by {@link #onResume}.
     *
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @see #onCreate
     * @see #onStop
     * @see #onResume
     */
    @CallSuper
    @Override
    protected void onStart() {
        LogUtil.V(CLASS_NAME, "onStart() [I N] ");
        super.onStart();
        LogUtil.V(CLASS_NAME, "onStart() [OUT] ");
    }

    /**
     * Called after {@link #onStop} when the current activity is being
     * re-displayed to the user (the user has navigated back to it).  It will
     * be followed by {@link #onStart} and then {@link #onResume}.
     *
     * <p>For activities that are using raw {@link Cursor} objects (instead of
     * creating them through
     * {@link #managedQuery(android.net.Uri, String[], String, String[], String)},
     * this is usually the place
     * where the cursor should be requeried (because you had deactivated it in
     * {@link #onStop}.
     *
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @see #onStop
     * @see #onStart
     * @see #onResume
     */
    @CallSuper
    @Override
    protected void onRestart() {
        LogUtil.V(CLASS_NAME, "onRestart() [I N] ");
        super.onRestart();
        LogUtil.V(CLASS_NAME, "onRestart() [OUT] ");
    }

    /**
     * Called when an {@link #onResume} is coming up, prior to other pre-resume callbacks
     * such as {@link #onNewIntent} and {@link #onActivityResult}.  This is primarily intended
     * to give the activity a hint that its state is no longer saved -- it will generally
     * be called after {@link #onSaveInstanceState} and prior to the activity being
     * resumed/started again.
     */
    @Override
    public void onStateNotSaved() {
        LogUtil.V(CLASS_NAME, "onStateNotSaved() [I N] ");
        super.onStateNotSaved();
        LogUtil.V(CLASS_NAME, "onStateNotSaved() [OUT] ");
    }

    /**
     * Called after {@link #onRestoreInstanceState}, {@link #onRestart}, or
     * {@link #onPause}, for your activity to start interacting with the user.
     * This is a good place to begin animations, open exclusive-access devices
     * (such as the camera), etc.
     *
     * <p>Keep in mind that onResume is not the best indicator that your activity
     * is visible to the user; a system window such as the keyguard may be in
     * front.  Use {@link #onWindowFocusChanged} to know for certain that your
     * activity is visible to the user (for example, to resume a game).
     *
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @see #onRestoreInstanceState
     * @see #onRestart
     * @see #onPostResume
     * @see #onPause
     */
    @CallSuper
    @Override
    protected void onResume() {
        LogUtil.V(CLASS_NAME, "onResume() [I N] ");
        super.onResume();
        LogUtil.V(CLASS_NAME, "onResume() [OUT] ");
    }

    /**
     * Called when activity resume is complete (after {@link #onResume} has
     * been called). Applications will generally not implement this method;
     * it is intended for system classes to do final setup after application
     * resume code has run.
     *
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @see #onResume
     */
    @CallSuper
    @Override
    protected void onPostResume() {
        LogUtil.V(CLASS_NAME, "onPostResume() [I N] ");
        super.onPostResume();
        LogUtil.V(CLASS_NAME, "onPostResume() [OUT] ");
    }

    /**
     * Callback to indicate that {@link #startLocalVoiceInteraction(Bundle)} has resulted in a
     * voice interaction session being started. You can now retrieve a voice interactor using
     * {@link #getVoiceInteractor()}.
     */
    @Override
    public void onLocalVoiceInteractionStarted() {
        LogUtil.V(CLASS_NAME, "onLocalVoiceInteractionStarted() [I N] ");
        super.onLocalVoiceInteractionStarted();
        LogUtil.V(CLASS_NAME, "onLocalVoiceInteractionStarted() [OUT] ");
    }

    /**
     * Callback to indicate that the local voice interaction has stopped either
     * because it was requested through a call to {@link #stopLocalVoiceInteraction()}
     * or because it was canceled by the user. The previously acquired {@link VoiceInteractor}
     * is no longer valid after this.
     */
    @Override
    public void onLocalVoiceInteractionStopped() {
        LogUtil.V(CLASS_NAME, "onLocalVoiceInteractionStopped() [I N] ");
        super.onLocalVoiceInteractionStopped();
        LogUtil.V(CLASS_NAME, "onLocalVoiceInteractionStopped() [OUT] ");
    }

    /**
     * This is called for activities that set launchMode to "singleTop" in
     * their package, or if a client used the {@link Intent#FLAG_ACTIVITY_SINGLE_TOP}
     * flag when calling {@link #startActivity}.  In either case, when the
     * activity is re-launched while at the top of the activity stack instead
     * of a new instance of the activity being started, onNewIntent() will be
     * called on the existing instance with the Intent that was used to
     * re-launch it.
     *
     * <p>An activity will always be paused before receiving a new intent, so
     * you can count on {@link #onResume} being called after this method.
     *
     * <p>Note that {@link #getIntent} still returns the original Intent.  You
     * can use {@link #setIntent} to update it to this new Intent.
     *
     * @param intent The new intent that was started for the activity.
     * @see #getIntent
     * @see #setIntent
     * @see #onResume
     */
    @Override
    protected void onNewIntent(Intent intent) {
        LogUtil.V(CLASS_NAME, "onNewIntent() [I N] ");
        super.onNewIntent(intent);
        LogUtil.V(CLASS_NAME, "onNewIntent() [OUT] ");
    }

    /**
     * Called to retrieve per-instance state from an activity before being killed
     * so that the state can be restored in {@link #onCreate} or
     * {@link #onRestoreInstanceState} (the {@link Bundle} populated by this method
     * will be passed to both).
     *
     * <p>This method is called before an activity may be killed so that when it
     * comes back some time in the future it can restore its state.  For example,
     * if activity B is launched in front of activity A, and at some point activity
     * A is killed to reclaim resources, activity A will have a chance to save the
     * current state of its user interface via this method so that when the user
     * returns to activity A, the state of the user interface can be restored
     * via {@link #onCreate} or {@link #onRestoreInstanceState}.
     *
     * <p>Do not confuse this method with activity lifecycle callbacks such as
     * {@link #onPause}, which is always called when an activity is being placed
     * in the background or on its way to destruction, or {@link #onStop} which
     * is called before destruction.  One example of when {@link #onPause} and
     * {@link #onStop} is called and not this method is when a user navigates back
     * from activity B to activity A: there is no need to call {@link #onSaveInstanceState}
     * on B because that particular instance will never be restored, so the
     * system avoids calling it.  An example when {@link #onPause} is called and
     * not {@link #onSaveInstanceState} is when activity B is launched in front of activity A:
     * the system may avoid calling {@link #onSaveInstanceState} on activity A if it isn't
     * killed during the lifetime of B since the state of the user interface of
     * A will stay intact.
     *
     * <p>The default implementation takes care of most of the UI per-instance
     * state for you by calling {@link android.view.View#onSaveInstanceState()} on each
     * view in the hierarchy that has an id, and by saving the id of the currently
     * focused view (all of which is restored by the default implementation of
     * {@link #onRestoreInstanceState}).  If you override this method to save additional
     * information not captured by each individual view, you will likely want to
     * call through to the default implementation, otherwise be prepared to save
     * all of the state of each view yourself.
     *
     * <p>If called, this method will occur before {@link #onStop}.  There are
     * no guarantees about whether it will occur before or after {@link #onPause}.
     *
     * @param outState Bundle in which to place your saved state.
     * @see #onCreate
     * @see #onRestoreInstanceState
     * @see #onPause
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        LogUtil.V(CLASS_NAME, "onSaveInstanceState() [I N] ");
        super.onSaveInstanceState(outState);
        LogUtil.V(CLASS_NAME, "onSaveInstanceState() [OUT] ");
    }

    /**
     * This is the same as {@link #onSaveInstanceState} but is called for activities
     * created with the attribute {@link android.R.attr#persistableMode} set to
     * <code>persistAcrossReboots</code>. The {@link android.os.PersistableBundle} passed
     * in will be saved and presented in {@link #onCreate(Bundle, PersistableBundle)}
     * the first time that this activity is restarted following the next device reboot.
     *
     * @param outState           Bundle in which to place your saved state.
     * @param outPersistentState State which will be saved across reboots.
     * @see #onSaveInstanceState(Bundle)
     * @see #onCreate
     * @see #onRestoreInstanceState(Bundle, PersistableBundle)
     * @see #onPause
     */
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        LogUtil.V(CLASS_NAME, "onSaveInstanceState() [I N] ");
        super.onSaveInstanceState(outState, outPersistentState);
        LogUtil.V(CLASS_NAME, "onSaveInstanceState() [OUT] ");
    }

    /**
     * Called as part of the activity lifecycle when an activity is going into
     * the background, but has not (yet) been killed.  The counterpart to
     * {@link #onResume}.
     *
     * <p>When activity B is launched in front of activity A, this callback will
     * be invoked on A.  B will not be created until A's {@link #onPause} returns,
     * so be sure to not do anything lengthy here.
     *
     * <p>This callback is mostly used for saving any persistent state the
     * activity is editing, to present a "edit in place" model to the user and
     * making sure nothing is lost if there are not enough resources to start
     * the new activity without first killing this one.  This is also a good
     * place to do things like stop animations and other things that consume a
     * noticeable amount of CPU in order to make the switch to the next activity
     * as fast as possible, or to close resources that are exclusive access
     * such as the camera.
     *
     * <p>In situations where the system needs more memory it may kill paused
     * processes to reclaim resources.  Because of this, you should be sure
     * that all of your state is saved by the time you return from
     * this function.  In general {@link #onSaveInstanceState} is used to save
     * per-instance state in the activity and this method is used to store
     * global persistent data (in content providers, files, etc.)
     *
     * <p>After receiving this call you will usually receive a following call
     * to {@link #onStop} (after the next activity has been resumed and
     * displayed), however in some cases there will be a direct call back to
     * {@link #onResume} without going through the stopped state.
     *
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @see #onResume
     * @see #onSaveInstanceState
     * @see #onStop
     */
    @CallSuper
    @Override
    protected void onPause() {
        LogUtil.V(CLASS_NAME, "onPause() [I N] ");
        super.onPause();
        LogUtil.V(CLASS_NAME, "onPause() [OUT] ");
    }

    /**
     * Called as part of the activity lifecycle when an activity is about to go
     * into the background as the result of user choice.  For example, when the
     * user presses the Home key, {@link #onUserLeaveHint} will be called, but
     * when an incoming phone call causes the in-call Activity to be automatically
     * brought to the foreground, {@link #onUserLeaveHint} will not be called on
     * the activity being interrupted.  In cases when it is invoked, this method
     * is called right before the activity's {@link #onPause} callback.
     *
     * <p>This callback and {@link #onUserInteraction} are intended to help
     * activities manage status bar notifications intelligently; specifically,
     * for helping activities determine the proper time to cancel a notfication.
     *
     * @see #onUserInteraction()
     */
    @Override
    protected void onUserLeaveHint() {
        LogUtil.V(CLASS_NAME, "onUserLeaveHint() [I N] ");
        super.onUserLeaveHint();
        LogUtil.V(CLASS_NAME, "onUserLeaveHint() [OUT] ");
    }

    /**
     * Generate a new thumbnail for this activity.  This method is called before
     * pausing the activity, and should draw into <var>outBitmap</var> the
     * imagery for the desired thumbnail in the dimensions of that bitmap.  It
     * can use the given <var>canvas</var>, which is configured to draw into the
     * bitmap, for rendering if desired.
     *
     * <p>The default implementation returns fails and does not draw a thumbnail;
     * this will result in the platform creating its own thumbnail if needed.
     *
     * @param outBitmap The bitmap to contain the thumbnail.
     * @param canvas    Can be used to render into the bitmap.
     * @return Return true if you have drawn into the bitmap; otherwise after
     * you return it will be filled with a default thumbnail.
     * @see #onCreateDescription
     * @see #onSaveInstanceState
     * @see #onPause
     */
    @Override
    public boolean onCreateThumbnail(Bitmap outBitmap, Canvas canvas) {
        LogUtil.V(CLASS_NAME, "onCreateThumbnail() [I N] ");
        boolean ret = super.onCreateThumbnail(outBitmap, canvas);
        LogUtil.V(CLASS_NAME, "onCreateThumbnail() [OUT] ret:" + ret);
        return ret;
    }

    /**
     * Generate a new description for this activity.  This method is called
     * before pausing the activity and can, if desired, return some textual
     * description of its current state to be displayed to the user.
     *
     * <p>The default implementation returns null, which will cause you to
     * inherit the description from the previous activity.  If all activities
     * return null, generally the label of the top activity will be used as the
     * description.
     *
     * @return A description of what the user is doing.  It should be short and
     * sweet (only a few words).
     * @see #onCreateThumbnail
     * @see #onSaveInstanceState
     * @see #onPause
     */
    @Nullable
    @Override
    public CharSequence onCreateDescription() {
        LogUtil.V(CLASS_NAME, "onCreateDescription() [I N] ");
        CharSequence charSequence = super.onCreateDescription();
        LogUtil.V(CLASS_NAME, "onCreateDescription() [OUT] ");
        return charSequence;
    }

    /**
     * This is called when the user is requesting an assist, to build a full
     * {@link Intent#ACTION_ASSIST} Intent with all of the context of the current
     * application.  You can override this method to place into the bundle anything
     * you would like to appear in the {@link Intent#EXTRA_ASSIST_CONTEXT} part
     * of the assist Intent.
     *
     * <p>This function will be called after any global assist callbacks that had
     * been registered with {@link Application#registerOnProvideAssistDataListener
     * Application.registerOnProvideAssistDataListener}.
     */
    @Override
    public void onProvideAssistData(Bundle data) {
        LogUtil.V(CLASS_NAME, "onProvideAssistData() [I N] ");
        super.onProvideAssistData(data);
        LogUtil.V(CLASS_NAME, "onProvideAssistData() [OUT] ");
    }

    /**
     * This is called when the user is requesting an assist, to provide references
     * to content related to the current activity.  Before being called, the
     * {@code outContent} Intent is filled with the base Intent of the activity (the Intent
     * returned by {@link #getIntent()}).  The Intent's extras are stripped of any types
     * that are not valid for {@link PersistableBundle} or non-framework Parcelables, and
     * the flags {@link Intent#FLAG_GRANT_WRITE_URI_PERMISSION} and
     * {@link Intent#FLAG_GRANT_PERSISTABLE_URI_PERMISSION} are cleared from the Intent.
     *
     * <p>Custom implementation may adjust the content intent to better reflect the top-level
     * context of the activity, and fill in its ClipData with additional content of
     * interest that the user is currently viewing.  For example, an image gallery application
     * that has launched in to an activity allowing the user to swipe through pictures should
     * modify the intent to reference the current image they are looking it; such an
     * application when showing a list of pictures should add a ClipData that has
     * references to all of the pictures currently visible on screen.</p>
     *
     * @param outContent The assist content to return.
     */
    @Override
    public void onProvideAssistContent(AssistContent outContent) {
        LogUtil.V(CLASS_NAME, "onProvideAssistContent() [I N] ");
        super.onProvideAssistContent(outContent);
        LogUtil.V(CLASS_NAME, "onProvideAssistContent() [OUT] ");
    }

    @Override
    public void onProvideKeyboardShortcuts(
            List<KeyboardShortcutGroup> data, Menu menu, int deviceId) {
        LogUtil.V(CLASS_NAME, "onProvideKeyboardShortcuts() [I N] ");
        super.onProvideKeyboardShortcuts(data, menu, deviceId);
        LogUtil.V(CLASS_NAME, "onProvideKeyboardShortcuts() [OUT] ");
    }

    /**
     * Called when you are no longer visible to the user.  You will next
     * receive either {@link #onRestart}, {@link #onDestroy}, or nothing,
     * depending on later user activity.
     *
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @see #onRestart
     * @see #onResume
     * @see #onSaveInstanceState
     * @see #onDestroy
     */
    @CallSuper
    @Override
    protected void onStop() {
        LogUtil.V(CLASS_NAME, "onStop() [I N] ");
        super.onStop();
        LogUtil.V(CLASS_NAME, "onStop() [OUT] ");
    }

    /**
     * Perform any final cleanup before an activity is destroyed.  This can
     * happen either because the activity is finishing (someone called
     * {@link #finish} on it, or because the system is temporarily destroying
     * this instance of the activity to save space.  You can distinguish
     * between these two scenarios with the {@link #isFinishing} method.
     *
     * <p><em>Note: do not count on this method being called as a place for
     * saving data! For example, if an activity is editing data in a content
     * provider, those edits should be committed in either {@link #onPause} or
     * {@link #onSaveInstanceState}, not here.</em> This method is usually implemented to
     * free resources like threads that are associated with an activity, so
     * that a destroyed activity does not leave such things around while the
     * rest of its application is still running.  There are situations where
     * the system will simply kill the activity's hosting process without
     * calling this method (or any others) in it, so it should not be used to
     * do things that are intended to remain around after the process goes
     * away.
     *
     * <p><em>Derived classes must call through to the super class's
     * implementation of this method.  If they do not, an exception will be
     * thrown.</em></p>
     *
     * @see #onPause
     * @see #onStop
     * @see #finish
     * @see #isFinishing
     */
    @CallSuper
    @Override
    protected void onDestroy() {
        LogUtil.V(CLASS_NAME, "onDestroy() [I N] ");
        super.onDestroy();
        LogUtil.V(CLASS_NAME, "onDestroy() [OUT] ");
    }

    /**
     * Called by the system when the activity changes from fullscreen mode to multi-window mode and
     * visa-versa. This method provides the same configuration that will be sent in the following
     * {@link #onConfigurationChanged(Configuration)} call after the activity enters this mode.
     *
     * @param isInMultiWindowMode True if the activity is in multi-window mode.
     * @param newConfig           The new configuration of the activity with the state
     *                            {@param isInMultiWindowMode}.
     * @see android.R.attr#resizeableActivity
     */
    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode, Configuration newConfig) {
        LogUtil.V(CLASS_NAME, "onMultiWindowModeChanged() [I N] ");
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig);
        LogUtil.V(CLASS_NAME, "onMultiWindowModeChanged() [OUT] ");
    }

    /**
     * Called by the system when the activity changes from fullscreen mode to multi-window mode and
     * visa-versa.
     *
     * @param isInMultiWindowMode True if the activity is in multi-window mode.
     * @see android.R.attr#resizeableActivity
     * @deprecated Use {@link #onMultiWindowModeChanged(boolean, Configuration)} instead.
     */
    @Deprecated
    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode) {
        LogUtil.V(CLASS_NAME, "onMultiWindowModeChanged() [I N] ");
        super.onMultiWindowModeChanged(isInMultiWindowMode);
        LogUtil.V(CLASS_NAME, "onMultiWindowModeChanged() [OUT] ");
    }

    /**
     * Called by the system when the activity changes to and from picture-in-picture mode. This
     * method provides the same configuration that will be sent in the following
     * {@link #onConfigurationChanged(Configuration)} call after the activity enters this mode.
     *
     * @param isInPictureInPictureMode True if the activity is in picture-in-picture mode.
     * @param newConfig                The new configuration of the activity with the state
     *                                 {@param isInPictureInPictureMode}.
     * @see android.R.attr#supportsPictureInPicture
     */
    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode,
            Configuration newConfig) {
        LogUtil.V(CLASS_NAME, "onPictureInPictureModeChanged() [I N] ");
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);
        LogUtil.V(CLASS_NAME, "onPictureInPictureModeChanged() [OUT] ");
    }

    /**
     * Called by the system when the activity changes to and from picture-in-picture mode.
     *
     * @param isInPictureInPictureMode True if the activity is in picture-in-picture mode.
     * @see android.R.attr#supportsPictureInPicture
     * @deprecated Use {@link #onPictureInPictureModeChanged(boolean, Configuration)} instead.
     */
    @Deprecated
    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        LogUtil.V(CLASS_NAME, "onPictureInPictureModeChanged() [I N] ");
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);
        LogUtil.V(CLASS_NAME, "onPictureInPictureModeChanged() [OUT] ");
    }

    /**
     * Called by the system when the device configuration changes while your
     * activity is running.  Note that this will <em>only</em> be called if
     * you have selected configurations you would like to handle with the
     * {@link android.R.attr#configChanges} attribute in your manifest.  If
     * any configuration change occurs that is not selected to be reported
     * by that attribute, then instead of reporting it the system will stop
     * and restart the activity (to have it launched with the new
     * configuration).
     *
     * <p>At the time that this function has been called, your Resources
     * object will have been updated to return resource values matching the
     * new configuration.
     *
     * @param newConfig The new device configuration.
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        LogUtil.V(CLASS_NAME, "onConfigurationChanged() [I N] ");
        super.onConfigurationChanged(newConfig);
        LogUtil.V(CLASS_NAME, "onConfigurationChanged() [OUT] ");
    }

    /**
     * Called by the system, as part of destroying an
     * activity due to a configuration change, when it is known that a new
     * instance will immediately be created for the new configuration.  You
     * can return any object you like here, including the activity instance
     * itself, which can later be retrieved by calling
     * {@link #getLastNonConfigurationInstance()} in the new activity
     * instance.
     *
     * <em>If you are targeting {@link android.os.Build.VERSION_CODES#HONEYCOMB}
     * or later, consider instead using a {@link Fragment} with
     * {@link Fragment#setRetainInstance(boolean)
     * Fragment.setRetainInstance(boolean}.</em>
     *
     * <p>This function is called purely as an optimization, and you must
     * not rely on it being called.  When it is called, a number of guarantees
     * will be made to help optimize configuration switching:
     * <ul>
     * <li> The function will be called between {@link #onStop} and
     * {@link #onDestroy}.
     * <li> A new instance of the activity will <em>always</em> be immediately
     * created after this one's {@link #onDestroy()} is called.  In particular,
     * <em>no</em> messages will be dispatched during this time (when the returned
     * object does not have an activity to be associated with).
     * <li> The object you return here will <em>always</em> be available from
     * the {@link #getLastNonConfigurationInstance()} method of the following
     * activity instance as described there.
     * </ul>
     *
     * <p>These guarantees are designed so that an activity can use this API
     * to propagate extensive state from the old to new activity instance, from
     * loaded bitmaps, to network connections, to evenly actively running
     * threads.  Note that you should <em>not</em> propagate any data that
     * may change based on the configuration, including any data loaded from
     * resources such as strings, layouts, or drawables.
     *
     * <p>The guarantee of no message handling during the switch to the next
     * activity simplifies use with active objects.  For example if your retained
     * state is an {@link android.os.AsyncTask} you are guaranteed that its
     * call back functions (like {@link android.os.AsyncTask#onPostExecute}) will
     * not be called from the call here until you execute the next instance's
     * {@link #onCreate(Bundle)}.  (Note however that there is of course no such
     * guarantee for {@link android.os.AsyncTask#doInBackground} since that is
     * running in a separate thread.)
     *
     * <p><strong>Note:</strong> For most cases you should use the {@link Fragment} API
     * {@link Fragment#setRetainInstance(boolean)} instead; this is also
     * available on older platforms through the Android support libraries.
     *
     * @return any Object holding the desired state to propagate to the
     * next activity instance
     */
    @Override
    public Object onRetainNonConfigurationInstance() {
        LogUtil.V(CLASS_NAME, "onRetainNonConfigurationInstance() [I N] ");
        Object object = super.onRetainNonConfigurationInstance();
        LogUtil.V(CLASS_NAME, "onRetainNonConfigurationInstance() [OUT] ");
        return object;
    }

    @Override
    public void onLowMemory() {
        LogUtil.V(CLASS_NAME, "onLowMemory() [I N] ");
        super.onLowMemory();
        LogUtil.V(CLASS_NAME, "onLowMemory() [OUT] ");
    }

    @Override
    public void onTrimMemory(int level) {
        LogUtil.V(CLASS_NAME, "onTrimMemory() [I N] ");
        super.onTrimMemory(level);
        LogUtil.V(CLASS_NAME, "onTrimMemory() [OUT] ");
    }

    /**
     * Called when a Fragment is being attached to this activity, immediately
     * after the call to its {@link Fragment#onAttach Fragment.onAttach()}
     * method and before {@link Fragment#onCreate Fragment.onCreate()}.
     */
    @Override
    public void onAttachFragment(Fragment fragment) {
        LogUtil.V(CLASS_NAME, "onAttachFragment() [I N] ");
        super.onAttachFragment(fragment);
        LogUtil.V(CLASS_NAME, "onAttachFragment() [OUT] ");
    }

    /**
     * Called when a key was pressed down and not handled by any of the views
     * inside of the activity. So, for example, key presses while the cursor
     * is inside a TextView will not trigger the event (unless it is a navigation
     * to another object) because TextView handles its own key presses.
     *
     * <p>If the focused view didn't want this event, this method is called.
     *
     * <p>The default implementation takes care of {@link KeyEvent#KEYCODE_BACK}
     * by calling {@link #onBackPressed()}, though the behavior varies based
     * on the application compatibility mode: for
     * {@link android.os.Build.VERSION_CODES#ECLAIR} or later applications,
     * it will set up the dispatch to call {@link #onKeyUp} where the action
     * will be performed; for earlier applications, it will perform the
     * action immediately in on-down, as those versions of the platform
     * behaved.
     *
     * <p>Other additional default key handling may be performed
     * if configured with {@link #setDefaultKeyMode}.
     *
     * @return Return <code>true</code> to prevent this event from being propagated
     * further, or <code>false</code> to indicate that you have not handled
     * this event and it should continue to be propagated.
     * @see #onKeyUp
     * @see android.view.KeyEvent
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        LogUtil.V(CLASS_NAME, "onKeyDown() [I N] ");
        boolean ret = super.onKeyDown(keyCode, event);
        LogUtil.V(CLASS_NAME, "onKeyDown() [OUT] ");
        return ret;
    }

    /**
     * Default implementation of {@link KeyEvent.Callback#onKeyLongPress(int, KeyEvent)
     * KeyEvent.Callback.onKeyLongPress()}: always returns false (doesn't handle
     * the event).
     */
    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        LogUtil.V(CLASS_NAME, "onKeyLongPress() [I N] ");
        boolean ret = super.onKeyLongPress(keyCode, event);
        LogUtil.V(CLASS_NAME, "onKeyLongPress() [OUT] ");
        return ret;
    }

    /**
     * Called when a key was released and not handled by any of the views
     * inside of the activity. So, for example, key presses while the cursor
     * is inside a TextView will not trigger the event (unless it is a navigation
     * to another object) because TextView handles its own key presses.
     *
     * <p>The default implementation handles KEYCODE_BACK to stop the activity
     * and go back.
     *
     * @return Return <code>true</code> to prevent this event from being propagated
     * further, or <code>false</code> to indicate that you have not handled
     * this event and it should continue to be propagated.
     * @see #onKeyDown
     * @see KeyEvent
     */
    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        LogUtil.V(CLASS_NAME, "onKeyUp() [I N] ");
        boolean ret = super.onKeyUp(keyCode, event);
        LogUtil.V(CLASS_NAME, "onKeyUp() [OUT] ");
        return ret;
    }

    /**
     * Default implementation of {@link KeyEvent.Callback#onKeyMultiple(int, int, KeyEvent)
     * KeyEvent.Callback.onKeyMultiple()}: always returns false (doesn't handle
     * the event).
     */
    @Override
    public boolean onKeyMultiple(int keyCode, int repeatCount, KeyEvent event) {
        LogUtil.V(CLASS_NAME, "onKeyMultiple() [I N] ");
        boolean ret = super.onKeyMultiple(keyCode, repeatCount, event);
        LogUtil.V(CLASS_NAME, "onKeyMultiple() [OUT] ");
        return ret;
    }

    /**
     * Called when the activity has detected the user's press of the back
     * key.  The default implementation simply finishes the current activity,
     * but you can override this to do whatever you want.
     */
    @Override
    public void onBackPressed() {
        LogUtil.V(CLASS_NAME, "onBackPressed() [I N] ");
        super.onBackPressed();
        LogUtil.V(CLASS_NAME, "onBackPressed() [OUT] ");
    }

    /**
     * Called when a key shortcut event is not handled by any of the views in the Activity.
     * Override this method to implement global key shortcuts for the Activity.
     * Key shortcuts can also be implemented by setting the
     * {@link MenuItem#setShortcut(char, char) shortcut} property of menu items.
     *
     * @param keyCode The value in event.getKeyCode().
     * @param event   Description of the key event.
     * @return True if the key shortcut was handled.
     */
    @Override
    public boolean onKeyShortcut(int keyCode, KeyEvent event) {
        LogUtil.V(CLASS_NAME, "onKeyShortcut() [I N] ");
        boolean ret = super.onKeyShortcut(keyCode, event);
        LogUtil.V(CLASS_NAME, "onKeyShortcut() [OUT] ");
        return ret;
    }

    /**
     * Called when a touch screen event was not handled by any of the views
     * under it.  This is most useful to process touch events that happen
     * outside of your window bounds, where there is no view to receive it.
     *
     * @param event The touch screen event being processed.
     * @return Return true if you have consumed the event, false if you haven't.
     * The default implementation always returns false.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        LogUtil.V(CLASS_NAME, "onTouchEvent() [I N] ");
        boolean ret = super.onTouchEvent(event);
        LogUtil.V(CLASS_NAME, "onTouchEvent() [OUT] ");
        return ret;
    }

    /**
     * Called when the trackball was moved and not handled by any of the
     * views inside of the activity.  So, for example, if the trackball moves
     * while focus is on a button, you will receive a call here because
     * buttons do not normally do anything with trackball events.  The call
     * here happens <em>before</em> trackball movements are converted to
     * DPAD key events, which then get sent back to the view hierarchy, and
     * will be processed at the point for things like focus navigation.
     *
     * @param event The trackball event being processed.
     * @return Return true if you have consumed the event, false if you haven't.
     * The default implementation always returns false.
     */
    @Override
    public boolean onTrackballEvent(MotionEvent event) {
        LogUtil.V(CLASS_NAME, "onTrackballEvent() [I N] ");
        boolean ret = super.onTrackballEvent(event);
        LogUtil.V(CLASS_NAME, "onTrackballEvent() [OUT] ");
        return ret;
    }

    /**
     * Called when a generic motion event was not handled by any of the
     * views inside of the activity.
     * <p>
     * Generic motion events describe joystick movements, mouse hovers, track pad
     * touches, scroll wheel movements and other input events.  The
     * {@link MotionEvent#getSource() source} of the motion event specifies
     * the class of input that was received.  Implementations of this method
     * must examine the bits in the source before processing the event.
     * The following code example shows how this is done.
     * </p><p>
     * Generic motion events with source class
     * {@link android.view.InputDevice#SOURCE_CLASS_POINTER}
     * are delivered to the view under the pointer.  All other generic motion events are
     * delivered to the focused view.
     * </p><p>
     * See {@link View#onGenericMotionEvent(MotionEvent)} for an example of how to
     * handle this event.
     * </p>
     *
     * @param event The generic motion event being processed.
     * @return Return true if you have consumed the event, false if you haven't.
     * The default implementation always returns false.
     */
    @Override
    public boolean onGenericMotionEvent(MotionEvent event) {
        LogUtil.V(CLASS_NAME, "onGenericMotionEvent() [I N] ");
        boolean ret = super.onGenericMotionEvent(event);
        LogUtil.V(CLASS_NAME, "onGenericMotionEvent() [OUT] ");
        return ret;
    }

    /**
     * Called whenever a key, touch, or trackball event is dispatched to the
     * activity.  Implement this method if you wish to know that the user has
     * interacted with the device in some way while your activity is running.
     * This callback and {@link #onUserLeaveHint} are intended to help
     * activities manage status bar notifications intelligently; specifically,
     * for helping activities determine the proper time to cancel a notfication.
     *
     * <p>All calls to your activity's {@link #onUserLeaveHint} callback will
     * be accompanied by calls to {@link #onUserInteraction}.  This
     * ensures that your activity will be told of relevant user activity such
     * as pulling down the notification pane and touching an item there.
     *
     * <p>Note that this callback will be invoked for the touch down action
     * that begins a touch gesture, but may not be invoked for the touch-moved
     * and touch-up actions that follow.
     *
     * @see #onUserLeaveHint()
     */
    @Override
    public void onUserInteraction() {
        LogUtil.V(CLASS_NAME, "onUserInteraction() [I N] ");
        super.onUserInteraction();
        LogUtil.V(CLASS_NAME, "onUserInteraction() [OUT] ");
    }

    @Override
    public void onWindowAttributesChanged(WindowManager.LayoutParams params) {
        LogUtil.V(CLASS_NAME, "onWindowAttributesChanged() [I N] ");
        super.onWindowAttributesChanged(params);
        LogUtil.V(CLASS_NAME, "onWindowAttributesChanged() [OUT] ");
    }

    @Override
    public void onContentChanged() {
        LogUtil.V(CLASS_NAME, "onContentChanged() [I N] ");
        super.onContentChanged();
        LogUtil.V(CLASS_NAME, "onContentChanged() [OUT] ");
    }

    /**
     * Called when the current {@link Window} of the activity gains or loses
     * focus.  This is the best indicator of whether this activity is visible
     * to the user.  The default implementation clears the key tracking
     * state, so should always be called.
     *
     * <p>Note that this provides information about global focus state, which
     * is managed independently of activity lifecycles.  As such, while focus
     * changes will generally have some relation to lifecycle changes (an
     * activity that is stopped will not generally get window focus), you
     * should not rely on any particular order between the callbacks here and
     * those in the other lifecycle methods such as {@link #onResume}.
     *
     * <p>As a general rule, however, a resumed activity will have window
     * focus...  unless it has displayed other dialogs or popups that take
     * input focus, in which case the activity itself will not have focus
     * when the other windows have it.  Likewise, the system may display
     * system-level windows (such as the status bar notification panel or
     * a system alert) which will temporarily take window input focus without
     * pausing the foreground activity.
     *
     * @param hasFocus Whether the window of this activity has focus.
     * @see #hasWindowFocus()
     * @see #onResume
     * @see View#onWindowFocusChanged(boolean)
     */
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        LogUtil.V(CLASS_NAME, "onWindowFocusChanged() [I N] ");
        super.onWindowFocusChanged(hasFocus);
        LogUtil.V(CLASS_NAME, "onWindowFocusChanged() [OUT] ");
    }

    /**
     * Called when the main window associated with the activity has been
     * attached to the window manager.
     * See {@link View#onAttachedToWindow() View.onAttachedToWindow()}
     * for more information.
     *
     * @see View#onAttachedToWindow
     */
    @Override
    public void onAttachedToWindow() {
        LogUtil.V(CLASS_NAME, "onAttachedToWindow() [I N] ");
        super.onAttachedToWindow();
        LogUtil.V(CLASS_NAME, "onAttachedToWindow() [OUT] ");
    }

    /**
     * Called when the main window associated with the activity has been
     * detached from the window manager.
     * See {@link View#onDetachedFromWindow() View.onDetachedFromWindow()}
     * for more information.
     *
     * @see View#onDetachedFromWindow
     */
    @Override
    public void onDetachedFromWindow() {
        LogUtil.V(CLASS_NAME, "onDetachedFromWindow() [I N] ");
        super.onDetachedFromWindow();
        LogUtil.V(CLASS_NAME, "onDetachedFromWindow() [OUT] ");
    }

    /**
     * Default implementation of
     * {@link android.view.Window.Callback#onCreatePanelView}
     * for activities. This
     * simply returns null so that all panel sub-windows will have the default
     * menu behavior.
     */
    @Nullable
    @Override
    public View onCreatePanelView(int featureId) {
        LogUtil.V(CLASS_NAME, "onCreatePanelView() [I N] ");
        View view = super.onCreatePanelView(featureId);
        LogUtil.V(CLASS_NAME, "onCreatePanelView() [OUT] ");
        return view;
    }

    /**
     * Default implementation of
     * {@link android.view.Window.Callback#onCreatePanelMenu}
     * for activities.  This calls through to the new
     * {@link #onCreateOptionsMenu} method for the
     * {@link android.view.Window#FEATURE_OPTIONS_PANEL} panel,
     * so that subclasses of Activity don't need to deal with feature codes.
     */
    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        LogUtil.V(CLASS_NAME, "onCreatePanelMenu() [I N] ");
        boolean ret = super.onCreatePanelMenu(featureId, menu);
        LogUtil.V(CLASS_NAME, "onCreatePanelMenu() [OUT] ");
        return ret;
    }

    /**
     * Default implementation of
     * {@link android.view.Window.Callback#onPreparePanel}
     * for activities.  This
     * calls through to the new {@link #onPrepareOptionsMenu} method for the
     * {@link android.view.Window#FEATURE_OPTIONS_PANEL}
     * panel, so that subclasses of
     * Activity don't need to deal with feature codes.
     */
    @Override
    public boolean onPreparePanel(int featureId, View view, Menu menu) {
        LogUtil.V(CLASS_NAME, "onPreparePanel() [I N] ");
        boolean ret = super.onPreparePanel(featureId, view, menu);
        LogUtil.V(CLASS_NAME, "onPreparePanel() [OUT] ");
        return ret;
    }

    /**
     * {@inheritDoc}
     *
     * @return The default implementation returns true.
     */
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        LogUtil.V(CLASS_NAME, "onMenuOpened() [I N] ");
        boolean ret = super.onMenuOpened(featureId, menu);
        LogUtil.V(CLASS_NAME, "onMenuOpened() [OUT] ");
        return ret;
    }

    /**
     * Default implementation of
     * {@link android.view.Window.Callback#onMenuItemSelected}
     * for activities.  This calls through to the new
     * {@link #onOptionsItemSelected} method for the
     * {@link android.view.Window#FEATURE_OPTIONS_PANEL}
     * panel, so that subclasses of
     * Activity don't need to deal with feature codes.
     */
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        LogUtil.V(CLASS_NAME, "onMenuItemSelected() [I N] ");
        boolean ret = super.onMenuItemSelected(featureId, item);
        LogUtil.V(CLASS_NAME, "onMenuItemSelected() [OUT] ");
        return ret;
    }

    /**
     * Default implementation of
     * {@link android.view.Window.Callback#onPanelClosed(int, Menu)} for
     * activities. This calls through to {@link #onOptionsMenuClosed(Menu)}
     * method for the {@link android.view.Window#FEATURE_OPTIONS_PANEL} panel,
     * so that subclasses of Activity don't need to deal with feature codes.
     * For context menus ({@link Window#FEATURE_CONTEXT_MENU}), the
     * {@link #onContextMenuClosed(Menu)} will be called.
     */
    @Override
    public void onPanelClosed(int featureId, Menu menu) {
        LogUtil.V(CLASS_NAME, "onPanelClosed() [I N] ");
        super.onPanelClosed(featureId, menu);
        LogUtil.V(CLASS_NAME, "onPanelClosed() [OUT] ");
    }

    /**
     * Initialize the contents of the Activity's standard options menu.  You
     * should place your menu items in to <var>menu</var>.
     *
     * <p>This is only called once, the first time the options menu is
     * displayed.  To update the menu every time it is displayed, see
     * {@link #onPrepareOptionsMenu}.
     *
     * <p>The default implementation populates the menu with standard system
     * menu items.  These are placed in the {@link Menu#CATEGORY_SYSTEM} group so that
     * they will be correctly ordered with application-defined menu items.
     * Deriving classes should always call through to the base implementation.
     *
     * <p>You can safely hold on to <var>menu</var> (and any items created
     * from it), making modifications to it as desired, until the next
     * time onCreateOptionsMenu() is called.
     *
     * <p>When you add items to the menu, you can implement the Activity's
     * {@link #onOptionsItemSelected} method to handle them there.
     *
     * @param menu The options menu in which you place your items.
     * @return You must return true for the menu to be displayed;
     * if you return false it will not be shown.
     * @see #onPrepareOptionsMenu
     * @see #onOptionsItemSelected
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        LogUtil.V(CLASS_NAME, "onCreateOptionsMenu() [I N] ");
        boolean ret = super.onCreateOptionsMenu(menu);
        LogUtil.V(CLASS_NAME, "onCreateOptionsMenu() [OUT] ");
        return ret;
    }

    /**
     * Prepare the Screen's standard options menu to be displayed.  This is
     * called right before the menu is shown, every time it is shown.  You can
     * use this method to efficiently enable/disable items or otherwise
     * dynamically modify the contents.
     *
     * <p>The default implementation updates the system menu items based on the
     * activity's state.  Deriving classes should always call through to the
     * base class implementation.
     *
     * @param menu The options menu as last shown or first initialized by
     *             onCreateOptionsMenu().
     * @return You must return true for the menu to be displayed;
     * if you return false it will not be shown.
     * @see #onCreateOptionsMenu
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        LogUtil.V(CLASS_NAME, "onPrepareOptionsMenu() [I N] ");
        boolean ret = super.onPrepareOptionsMenu(menu);
        LogUtil.V(CLASS_NAME, "onPrepareOptionsMenu() [OUT] ");
        return ret;
    }

    /**
     * This hook is called whenever an item in your options menu is selected.
     * The default implementation simply returns false to have the normal
     * processing happen (calling the item's Runnable or sending a message to
     * its Handler as appropriate).  You can use this method for any items
     * for which you would like to do processing without those other
     * facilities.
     *
     * <p>Derived classes should call through to the base class for it to
     * perform the default menu handling.</p>
     *
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     * @see #onCreateOptionsMenu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        LogUtil.V(CLASS_NAME, "onOptionsItemSelected() [I N] ");
        boolean ret = super.onOptionsItemSelected(item);
        LogUtil.V(CLASS_NAME, "onOptionsItemSelected() [OUT] ");
        return ret;
    }

    /**
     * This method is called whenever the user chooses to navigate Up within your application's
     * activity hierarchy from the action bar.
     *
     * <p>If the attribute {@link android.R.attr#parentActivityName parentActivityName}
     * was specified in the manifest for this activity or an activity-alias to it,
     * default Up navigation will be handled automatically. If any activity
     * along the parent chain requires extra Intent arguments, the Activity subclass
     * should override the method {@link #onPrepareNavigateUpTaskStack(TaskStackBuilder)}
     * to supply those arguments.</p>
     *
     * <p>See <a href="{@docRoot}guide/components/tasks-and-back-stack.html">Tasks and Back
     * Stack</a>
     * from the developer guide and <a href="{@docRoot}design/patterns/navigation
     * .html">Navigation</a>
     * from the design guide for more information about navigating within your app.</p>
     *
     * <p>See the {@link TaskStackBuilder} class and the Activity methods
     * {@link #getParentActivityIntent()}, {@link #shouldUpRecreateTask(Intent)}, and
     * {@link #navigateUpTo(Intent)} for help implementing custom Up navigation.
     * The AppNavigation sample application in the Android SDK is also available for reference.</p>
     *
     * @return true if Up navigation completed successfully and this Activity was finished,
     * false otherwise.
     */
    @Override
    public boolean onNavigateUp() {
        LogUtil.V(CLASS_NAME, "onNavigateUp() [I N] ");
        boolean ret = super.onNavigateUp();
        LogUtil.V(CLASS_NAME, "onNavigateUp() [OUT] ");
        return ret;
    }

    /**
     * This is called when a child activity of this one attempts to navigate up.
     * The default implementation simply calls onNavigateUp() on this activity (the parent).
     *
     * @param child The activity making the call.
     */
    @Override
    public boolean onNavigateUpFromChild(Activity child) {
        LogUtil.V(CLASS_NAME, "onNavigateUpFromChild() [I N] ");
        boolean ret = super.onNavigateUpFromChild(child);
        LogUtil.V(CLASS_NAME, "onNavigateUpFromChild() [OUT] ");
        return ret;
    }

    /**
     * Define the synthetic task stack that will be generated during Up navigation from
     * a different task.
     *
     * <p>The default implementation of this method adds the parent chain of this activity
     * as specified in the manifest to the supplied {@link TaskStackBuilder}. Applications
     * may choose to override this method to construct the desired task stack in a different
     * way.</p>
     *
     * <p>This method will be invoked by the default implementation of {@link #onNavigateUp()}
     * if {@link #shouldUpRecreateTask(Intent)} returns true when supplied with the intent
     * returned by {@link #getParentActivityIntent()}.</p>
     *
     * <p>Applications that wish to supply extra Intent parameters to the parent stack defined
     * by the manifest should override {@link #onPrepareNavigateUpTaskStack(TaskStackBuilder)}.</p>
     *
     * @param builder An empty TaskStackBuilder - the application should add intents representing
     *                the desired task stack
     */
    @Override
    public void onCreateNavigateUpTaskStack(TaskStackBuilder builder) {
        LogUtil.V(CLASS_NAME, "onCreateNavigateUpTaskStack() [I N] ");
        super.onCreateNavigateUpTaskStack(builder);
        LogUtil.V(CLASS_NAME, "onCreateNavigateUpTaskStack() [OUT] ");
    }

    /**
     * Prepare the synthetic task stack that will be generated during Up navigation
     * from a different task.
     *
     * <p>This method receives the {@link TaskStackBuilder} with the constructed series of
     * Intents as generated by {@link #onCreateNavigateUpTaskStack(TaskStackBuilder)}.
     * If any extra data should be added to these intents before launching the new task,
     * the application should override this method and add that data here.</p>
     *
     * @param builder A TaskStackBuilder that has been populated with Intents by
     *                onCreateNavigateUpTaskStack.
     */
    @Override
    public void onPrepareNavigateUpTaskStack(TaskStackBuilder builder) {
        LogUtil.V(CLASS_NAME, "onPrepareNavigateUpTaskStack() [I N] ");
        super.onPrepareNavigateUpTaskStack(builder);
        LogUtil.V(CLASS_NAME, "onPrepareNavigateUpTaskStack() [OUT] ");
    }

    /**
     * This hook is called whenever the options menu is being closed (either by the user canceling
     * the menu with the back/menu button, or when an item is selected).
     *
     * @param menu The options menu as last shown or first initialized by
     *             onCreateOptionsMenu().
     */
    @Override
    public void onOptionsMenuClosed(Menu menu) {
        LogUtil.V(CLASS_NAME, "onOptionsMenuClosed() [I N] ");
        super.onOptionsMenuClosed(menu);
        LogUtil.V(CLASS_NAME, "onOptionsMenuClosed() [OUT] ");
    }

    /**
     * Called when a context menu for the {@code view} is about to be shown.
     * Unlike {@link #onCreateOptionsMenu(Menu)}, this will be called every
     * time the context menu is about to be shown and should be populated for
     * the view (or item inside the view for {@link AdapterView} subclasses,
     * this can be found in the {@code menuInfo})).
     * <p>
     * Use {@link #onContextItemSelected(android.view.MenuItem)} to know when an
     * item has been selected.
     * <p>
     * It is not safe to hold onto the context menu after this method returns.
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        LogUtil.V(CLASS_NAME, "onCreateContextMenu() [I N] ");
        super.onCreateContextMenu(menu, v, menuInfo);
        LogUtil.V(CLASS_NAME, "onCreateContextMenu() [OUT] ");
    }

    /**
     * This hook is called whenever an item in a context menu is selected. The
     * default implementation simply returns false to have the normal processing
     * happen (calling the item's Runnable or sending a message to its Handler
     * as appropriate). You can use this method for any items for which you
     * would like to do processing without those other facilities.
     * <p>
     * Use {@link MenuItem#getMenuInfo()} to get extra information set by the
     * View that added this menu item.
     * <p>
     * Derived classes should call through to the base class for it to perform
     * the default menu handling.
     *
     * @param item The context menu item that was selected.
     * @return boolean Return false to allow normal context menu processing to
     * proceed, true to consume it here.
     */
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        LogUtil.V(CLASS_NAME, "onContextItemSelected() [I N] ");
        boolean ret = super.onContextItemSelected(item);
        LogUtil.V(CLASS_NAME, "onContextItemSelected() [OUT] ");
        return ret;
    }

    /**
     * This hook is called whenever the context menu is being closed (either by
     * the user canceling the menu with the back/menu button, or when an item is
     * selected).
     *
     * @param menu The context menu that is being closed.
     */
    @Override
    public void onContextMenuClosed(Menu menu) {
        LogUtil.V(CLASS_NAME, "onContextMenuClosed() [I N] ");
        super.onContextMenuClosed(menu);
        LogUtil.V(CLASS_NAME, "onContextMenuClosed() [OUT] ");
    }

    /**
     * @deprecated Old no-arguments version of {@link #onCreateDialog(int, Bundle)}.
     */
    @Deprecated
    @Override
    protected Dialog onCreateDialog(int id) {
        LogUtil.V(CLASS_NAME, "onCreateDialog() [I N] ");
        Dialog dialog = super.onCreateDialog(id);
        LogUtil.V(CLASS_NAME, "onCreateDialog() [OUT] ");
        return dialog;
    }

    /**
     * Callback for creating dialogs that are managed (saved and restored) for you
     * by the activity.  The default implementation calls through to
     * {@link #onCreateDialog(int)} for compatibility.
     *
     * <em>If you are targeting {@link android.os.Build.VERSION_CODES#HONEYCOMB}
     * or later, consider instead using a {@link DialogFragment} instead.</em>
     *
     * <p>If you use {@link #showDialog(int)}, the activity will call through to
     * this method the first time, and hang onto it thereafter.  Any dialog
     * that is created by this method will automatically be saved and restored
     * for you, including whether it is showing.
     *
     * <p>If you would like the activity to manage saving and restoring dialogs
     * for you, you should override this method and handle any ids that are
     * passed to {@link #showDialog}.
     *
     * <p>If you would like an opportunity to prepare your dialog before it is shown,
     * override {@link #onPrepareDialog(int, Dialog, Bundle)}.
     *
     * @param id   The id of the dialog.
     * @param args The dialog arguments provided to {@link #showDialog(int, Bundle)}.
     * @return The dialog.  If you return null, the dialog will not be created.
     * @see #onPrepareDialog(int, Dialog, Bundle)
     * @see #showDialog(int, Bundle)
     * @see #dismissDialog(int)
     * @see #removeDialog(int)
     * @deprecated Use the new {@link DialogFragment} class with
     * {@link FragmentManager} instead; this is also
     * available on older platforms through the Android compatibility package.
     */
    @Nullable
    @Deprecated
    @Override
    protected Dialog onCreateDialog(int id, Bundle args) {
        LogUtil.V(CLASS_NAME, "onCreateDialog() [I N] ");
        Dialog dialog = super.onCreateDialog(id, args);
        LogUtil.V(CLASS_NAME, "onCreateDialog() [OUT] ");
        return dialog;
    }

    /**
     * @deprecated Old no-arguments version of
     * {@link #onPrepareDialog(int, Dialog, Bundle)}.
     */
    @Deprecated
    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        LogUtil.V(CLASS_NAME, "onPrepareDialog() [I N] ");
        super.onPrepareDialog(id, dialog);
        LogUtil.V(CLASS_NAME, "onPrepareDialog() [OUT] ");
    }

    /**
     * Provides an opportunity to prepare a managed dialog before it is being
     * shown.  The default implementation calls through to
     * {@link #onPrepareDialog(int, Dialog)} for compatibility.
     *
     * <p>
     * Override this if you need to update a managed dialog based on the state
     * of the application each time it is shown. For example, a time picker
     * dialog might want to be updated with the current time. You should call
     * through to the superclass's implementation. The default implementation
     * will set this Activity as the owner activity on the Dialog.
     *
     * @param id     The id of the managed dialog.
     * @param dialog The dialog.
     * @param args   The dialog arguments provided to {@link #showDialog(int, Bundle)}.
     * @see #onCreateDialog(int, Bundle)
     * @see #showDialog(int)
     * @see #dismissDialog(int)
     * @see #removeDialog(int)
     * @deprecated Use the new {@link DialogFragment} class with
     * {@link FragmentManager} instead; this is also
     * available on older platforms through the Android compatibility package.
     */
    @Deprecated
    @Override
    protected void onPrepareDialog(int id, Dialog dialog, Bundle args) {
        LogUtil.V(CLASS_NAME, "onPrepareDialog() [I N] ");
        super.onPrepareDialog(id, dialog, args);
        LogUtil.V(CLASS_NAME, "onPrepareDialog() [OUT] ");
    }

    /**
     * This hook is called when the user signals the desire to start a search.
     *
     * <p>You can use this function as a simple way to launch the search UI, in response to a
     * menu item, search button, or other widgets within your activity. Unless overidden,
     * calling this function is the same as calling
     * {@link #startSearch startSearch(null, false, null, false)}, which launches
     * search for the current activity as specified in its manifest, see {@link SearchManager}.
     *
     * <p>You can override this function to force global search, e.g. in response to a dedicated
     * search key, or to block search entirely (by simply returning false).
     *
     * <p>Note: when running in a {@link Configuration#UI_MODE_TYPE_TELEVISION}, the default
     * implementation changes to simply return false and you must supply your own custom
     * implementation if you want to support search.</p>
     *
     * @param searchEvent The {@link SearchEvent} that signaled this search.
     * @return Returns {@code true} if search launched, and {@code false} if the activity does
     * not respond to search.  The default implementation always returns {@code true}, except
     * when in {@link Configuration#UI_MODE_TYPE_TELEVISION} mode where it returns false.
     * @see android.app.SearchManager
     */
    @Override
    public boolean onSearchRequested(@Nullable SearchEvent searchEvent) {
        LogUtil.V(CLASS_NAME, "onSearchRequested() [I N] ");
        boolean ret = super.onSearchRequested(searchEvent);
        LogUtil.V(CLASS_NAME, "onSearchRequested() [OUT] ");
        return ret;
    }

    /**
     * @see #onSearchRequested(SearchEvent)
     */
    @Override
    public boolean onSearchRequested() {
        LogUtil.V(CLASS_NAME, "onSearchRequested() [I N] ");
        boolean ret = super.onSearchRequested();
        LogUtil.V(CLASS_NAME, "onSearchRequested() [OUT] ");
        return ret;
    }

    @Override
    protected void onApplyThemeResource(Resources.Theme theme, @StyleRes int resid,
            boolean first) {
        LogUtil.V(CLASS_NAME, "onApplyThemeResource() [I N] ");
        super.onApplyThemeResource(theme, resid, first);
        LogUtil.V(CLASS_NAME, "onApplyThemeResource() [OUT] ");
    }

    /**
     * Callback for the result from requesting permissions. This method
     * is invoked for every call on {@link #requestPermissions(String[], int)}.
     * <p>
     * <strong>Note:</strong> It is possible that the permissions request interaction
     * with the user is interrupted. In this case you will receive empty permissions
     * and results arrays which should be treated as a cancellation.
     * </p>
     *
     * @param requestCode  The request code passed in {@link #requestPermissions(String[], int)}.
     * @param permissions  The requested permissions. Never null.
     * @param grantResults The grant results for the corresponding permissions
     *                     which is either
     *                     {@link android.content.pm.PackageManager#PERMISSION_GRANTED}
     *                     or {@link android.content.pm.PackageManager#PERMISSION_DENIED}. Never
     *                     null.
     * @see #requestPermissions(String[], int)
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        LogUtil.V(CLASS_NAME, "onRequestPermissionsResult() [I N] ");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LogUtil.V(CLASS_NAME, "onRequestPermissionsResult() [OUT] ");
    }

    /**
     * Override to generate the desired referrer for the content currently being shown
     * by the app.  The default implementation returns null, meaning the referrer will simply
     * be the android-app: of the package name of this activity.  Return a non-null Uri to
     * have that supplied as the {@link Intent#EXTRA_REFERRER} of any activities started from it.
     */
    @Override
    public Uri onProvideReferrer() {
        LogUtil.V(CLASS_NAME, "onProvideReferrer() [I N] ");
        Uri uri = super.onProvideReferrer();
        LogUtil.V(CLASS_NAME, "onProvideReferrer() [OUT] ");
        return uri;
    }

    /**
     * Called when an activity you launched exits, giving you the requestCode
     * you started it with, the resultCode it returned, and any additional
     * data from it.  The <var>resultCode</var> will be
     * {@link #RESULT_CANCELED} if the activity explicitly returned that,
     * didn't return any result, or crashed during its operation.
     *
     * <p>You will receive this call immediately before onResume() when your
     * activity is re-starting.
     *
     * <p>This method is never invoked if your activity sets
     * {@link android.R.styleable#AndroidManifestActivity_noHistory noHistory} to
     * <code>true</code>.
     *
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode  The integer result code returned by the child activity
     *                    through its setResult().
     * @param data        An Intent, which can return result data to the caller
     *                    (various data can be attached to Intent "extras").
     * @see #startActivityForResult
     * @see #createPendingResult
     * @see #setResult(int)
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtil.V(CLASS_NAME, "onActivityResult() [I N] ");
        super.onActivityResult(requestCode, resultCode, data);
        LogUtil.V(CLASS_NAME, "onActivityResult() [OUT] ");
    }

    /**
     * Called when an activity you launched with an activity transition exposes this
     * Activity through a returning activity transition, giving you the resultCode
     * and any additional data from it. This method will only be called if the activity
     * set a result code other than {@link #RESULT_CANCELED} and it supports activity
     * transitions with {@link Window#FEATURE_ACTIVITY_TRANSITIONS}.
     *
     * <p>The purpose of this function is to let the called Activity send a hint about
     * its state so that this underlying Activity can prepare to be exposed. A call to
     * this method does not guarantee that the called Activity has or will be exiting soon.
     * It only indicates that it will expose this Activity's Window and it has
     * some data to pass to prepare it.</p>
     *
     * @param resultCode The integer result code returned by the child activity
     *                   through its setResult().
     * @param data       An Intent, which can return result data to the caller
     *                   (various data can be attached to Intent "extras").
     */
    @Override
    public void onActivityReenter(int resultCode, Intent data) {
        LogUtil.V(CLASS_NAME, "onActivityReenter() [I N] ");
        super.onActivityReenter(resultCode, data);
        LogUtil.V(CLASS_NAME, "onActivityReenter() [OUT] ");
    }

    @Override
    protected void onTitleChanged(CharSequence title, int color) {
        LogUtil.V(CLASS_NAME, "onTitleChanged() [I N] ");
        super.onTitleChanged(title, color);
        LogUtil.V(CLASS_NAME, "onTitleChanged() [OUT] ");
    }

    @Override
    protected void onChildTitleChanged(Activity childActivity, CharSequence title) {
        LogUtil.V(CLASS_NAME, "onChildTitleChanged() [I N] ");
        super.onChildTitleChanged(childActivity, title);
        LogUtil.V(CLASS_NAME, "onChildTitleChanged() [OUT] ");
    }

    /**
     * Standard implementation of
     * {@link android.view.LayoutInflater.Factory#onCreateView} used when
     * inflating with the LayoutInflater returned by {@link #getSystemService}.
     * This implementation does nothing and is for
     * pre-{@link android.os.Build.VERSION_CODES#HONEYCOMB} apps.  Newer apps
     * should use {@link #onCreateView(View, String, Context, AttributeSet)}.
     *
     * @see android.view.LayoutInflater#createView
     * @see android.view.Window#getLayoutInflater
     */
    @Nullable
    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        LogUtil.V(CLASS_NAME, "onCreateView() [I N] ");
        View view = super.onCreateView(name, context, attrs);
        LogUtil.V(CLASS_NAME, "onCreateView() [OUT] ");
        return view;
    }

    /**
     * Standard implementation of
     * {@link android.view.LayoutInflater.Factory2#onCreateView(View, String, Context, AttributeSet)}
     * used when inflating with the LayoutInflater returned by {@link #getSystemService}.
     * This implementation handles <fragment> tags to embed fragments inside
     * of the activity.
     *
     * @see android.view.LayoutInflater#createView
     * @see android.view.Window#getLayoutInflater
     */
    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        LogUtil.V(CLASS_NAME, "onCreateView() [I N] ");
        View view = super.onCreateView(parent, name, context, attrs);
        LogUtil.V(CLASS_NAME, "onCreateView() [OUT] ");
        return view;
    }

    /**
     * Called when a translucent activity over this activity is becoming opaque or another
     * activity is being launched. Activities that override this method must call
     * <code>super.onVisibleBehindCanceled()</code> or a SuperNotCalledException will be thrown.
     *
     * <p>When this method is called the activity has 500 msec to release any resources it may be
     * using while visible in the background.
     * If the activity has not returned from this method in 500 msec the system will destroy
     * the activity and kill the process in order to recover the resources for another
     * process. Otherwise {@link #onStop()} will be called following return.
     *
     * @see #requestVisibleBehind(boolean)
     * @deprecated This method's functionality is no longer supported as of
     * {@link android.os.Build.VERSION_CODES#O} and will be removed in a future release.
     */
    @Deprecated
    @CallSuper
    @Override
    public void onVisibleBehindCanceled() {
        LogUtil.V(CLASS_NAME, "onVisibleBehindCanceled() [I N] ");
        super.onVisibleBehindCanceled();
        LogUtil.V(CLASS_NAME, "onVisibleBehindCanceled() [OUT] ");
    }

    /**
     * Activities cannot draw during the period that their windows are animating in. In order
     * to know when it is safe to begin drawing they can override this method which will be
     * called when the entering animation has completed.
     */
    @Override
    public void onEnterAnimationComplete() {
        LogUtil.V(CLASS_NAME, "onEnterAnimationComplete() [I N] ");
        super.onEnterAnimationComplete();
        LogUtil.V(CLASS_NAME, "onEnterAnimationComplete() [OUT] ");
    }

    /**
     * Give the Activity a chance to control the UI for an action mode requested
     * by the system.
     *
     * <p>Note: If you are looking for a notification callback that an action mode
     * has been started for this activity, see {@link #onActionModeStarted(ActionMode)}.</p>
     *
     * @param callback The callback that should control the new action mode
     * @return The new action mode, or <code>null</code> if the activity does not want to
     * provide special handling for this action mode. (It will be handled by the system.)
     */
    @Nullable
    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        LogUtil.V(CLASS_NAME, "onWindowStartingActionMode() [I N] ");
        ActionMode actionMode = super.onWindowStartingActionMode(callback);
        LogUtil.V(CLASS_NAME, "onWindowStartingActionMode() [OUT] ");
        return actionMode;
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int type) {
        LogUtil.V(CLASS_NAME, "onWindowStartingActionMode() [I N] ");
        ActionMode actionMode = super.onWindowStartingActionMode(callback, type);
        LogUtil.V(CLASS_NAME, "onWindowStartingActionMode() [OUT] ");
        return actionMode;
    }

    /**
     * Notifies the Activity that an action mode has been started.
     * Activity subclasses overriding this method should call the superclass implementation.
     *
     * @param mode The new action mode.
     */
    @CallSuper
    @Override
    public void onActionModeStarted(ActionMode mode) {
        LogUtil.V(CLASS_NAME, "onActionModeStarted() [I N] ");
        super.onActionModeStarted(mode);
        LogUtil.V(CLASS_NAME, "onActionModeStarted() [OUT] ");
    }

    /**
     * Notifies the activity that an action mode has finished.
     * Activity subclasses overriding this method should call the superclass implementation.
     *
     * @param mode The action mode that just finished.
     */
    @CallSuper
    @Override
    public void onActionModeFinished(ActionMode mode) {
        LogUtil.V(CLASS_NAME, "onActionModeFinished() [I N] ");
        super.onActionModeFinished(mode);
        LogUtil.V(CLASS_NAME, "onActionModeFinished() [OUT] ");
    }
}
