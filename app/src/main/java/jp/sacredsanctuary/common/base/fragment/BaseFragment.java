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
package jp.sacredsanctuary.common.base.fragment;

import android.animation.Animator;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import jp.sacredsanctuary.common.util.LogUtil;

/**
 * @braif A wrapper over {@link Fragment} which allows to override some methods.
 * @class BaseFragment
 */
public abstract class BaseFragment extends Fragment {
    private final String CLASS_NAME;

    /**
     * Default constructor.  <strong>Every</strong> fragment must have an
     * empty constructor, so it can be instantiated when restoring its
     * activity's state.  It is strongly recommended that subclasses do not
     * have other constructors with parameters, since these constructors
     * will not be called when the fragment is re-instantiated; instead,
     * arguments can be supplied by the caller with {@link #setArguments}
     * and later retrieved by the Fragment with {@link #getArguments}.
     *
     * <p>Applications should generally not implement a constructor. Prefer
     * {@link #onAttach(Context)} instead. It is the first place application code can run where
     * the fragment is ready to be used - the point where the fragment is actually associated with
     * its context. Some applications may also want to implement {@link #onInflate} to retrieve
     * attributes from a layout resource, although note this happens when the fragment is attached.
     */
    public BaseFragment() {
        super();
        this.CLASS_NAME = "BaseFragment";
    }

    /**
     * Default constructor.  <strong>Every</strong> fragment must have an
     * empty constructor, so it can be instantiated when restoring its
     * activity's state.  It is strongly recommended that subclasses do not
     * have other constructors with parameters, since these constructors
     * will not be called when the fragment is re-instantiated; instead,
     * arguments can be supplied by the caller with {@link #setArguments}
     * and later retrieved by the Fragment with {@link #getArguments}.
     *
     * <p>Applications should generally not implement a constructor. Prefer
     * {@link #onAttach(Context)} instead. It is the first place application code can run where
     * the fragment is ready to be used - the point where the fragment is actually associated with
     * its context. Some applications may also want to implement {@link #onInflate} to retrieve
     * attributes from a layout resource, although note this happens when the fragment is attached.
     */
    public BaseFragment(String className) {
        super();
        this.CLASS_NAME = className + "Base";
    }

    /**
     * Called when the hidden state (as returned by {@link #isHidden()} of
     * the fragment has changed.  Fragments start out not hidden; this will
     * be called whenever the fragment changes state from that.
     *
     * @param hidden True if the fragment is now hidden, false otherwise.
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        LogUtil.V(CLASS_NAME, "onHiddenChanged() [I N] ");
        super.onHiddenChanged(hidden);
        LogUtil.V(CLASS_NAME, "onHiddenChanged() [OUT] ");
    }

    /**
     * Receive the result from a previous call to
     * {@link #startActivityForResult(Intent, int)}.  This follows the
     * related Activity API as described there in
     * {@link Activity#onActivityResult(int, int, Intent)}.
     *
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode  The integer result code returned by the child activity
     *                    through its setResult().
     * @param data        An Intent, which can return result data to the caller
     *                    (various data can be attached to Intent "extras").
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtil.V(CLASS_NAME, "onActivityResult() [I N] ");
        super.onActivityResult(requestCode, resultCode, data);
        LogUtil.V(CLASS_NAME, "onActivityResult() [OUT] ");
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
        /* callback - do nothing */
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LogUtil.V(CLASS_NAME, "onRequestPermissionsResult() [OUT] ");
    }

    /**
     * Returns the LayoutInflater used to inflate Views of this Fragment. The default
     * implementation will throw an exception if the Fragment is not attached.
     *
     * @return The LayoutInflater used to inflate Views of this Fragment.
     */
    @Override
    public LayoutInflater onGetLayoutInflater(Bundle savedInstanceState) {
        LogUtil.V(CLASS_NAME, "onGetLayoutInflater() [I N] ");
        LayoutInflater inflater = super.onGetLayoutInflater(savedInstanceState);
        LogUtil.V(CLASS_NAME, "onGetLayoutInflater() [OUT] ");
        return inflater;
    }

    /**
     * @deprecated Use {@link #onInflate(Context, AttributeSet, Bundle)} instead.
     */
    @Deprecated
    @CallSuper
    @Override
    public void onInflate(AttributeSet attrs, Bundle savedInstanceState) {
        LogUtil.V(CLASS_NAME, "onInflate() [I N] ");
        super.onInflate(attrs, savedInstanceState);
        LogUtil.V(CLASS_NAME, "onInflate() [OUT] ");
    }

    /**
     * Called when a fragment is being created as part of a view layout
     * inflation, typically from setting the content view of an activity.  This
     * may be called immediately after the fragment is created from a <fragment>
     * tag in a layout file.  Note this is <em>before</em> the fragment's
     * {@link #onAttach(Activity)} has been called; all you should do here is
     * parse the attributes and save them away.
     *
     * <p>This is called every time the fragment is inflated, even if it is
     * being inflated into a new instance with saved state.  It typically makes
     * sense to re-parse the parameters each time, to allow them to change with
     * different configurations.</p>
     *
     * <p>Here is a typical implementation of a fragment that can take parameters
     * both through attributes supplied here as well from {@link #getArguments()}:</p>
     *
     * {@sample development/samples/ApiDemos/src/com/example/android/apis/app/FragmentArguments.java
     * fragment}
     *
     * <p>Note that parsing the XML attributes uses a "styleable" resource.  The
     * declaration for the styleable used here is:</p>
     *
     * {@sample development/samples/ApiDemos/res/values/attrs.xml fragment_arguments}
     *
     * <p>The fragment can then be declared within its activity's content layout
     * through a tag like this:</p>
     *
     * {@sample development/samples/ApiDemos/res/layout/fragment_arguments.xml from_attributes}
     *
     * <p>This fragment can also be created dynamically from arguments given
     * at runtime in the arguments Bundle; here is an example of doing so at
     * creation of the containing activity:</p>
     *
     * {@sample development/samples/ApiDemos/src/com/example/android/apis/app/FragmentArguments.java
     * create}
     *
     * @param context            The Context that is inflating this fragment.
     * @param attrs              The attributes at the tag where the fragment is
     *                           being created.
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @CallSuper
    @Override
    public void onInflate(Context context, AttributeSet attrs, Bundle savedInstanceState) {
        LogUtil.V(CLASS_NAME, "onInflate(Context) [I N] ");
        super.onInflate(context, attrs, savedInstanceState);
        LogUtil.V(CLASS_NAME, "onInflate(Context) [OUT] ");
    }

    /**
     * @deprecated Use {@link #onInflate(Context, AttributeSet, Bundle)} instead.
     */
    @Deprecated
    @CallSuper
    @Override
    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        LogUtil.V(CLASS_NAME, "onInflate(Activity) [I N] ");
        super.onInflate(activity, attrs, savedInstanceState);
        LogUtil.V(CLASS_NAME, "onInflate(Activity) [OUT] ");
    }

    /**
     * Called when a fragment is attached as a child of this fragment.
     *
     * <p>This is called after the attached fragment's <code>onAttach</code> and before
     * the attached fragment's <code>onCreate</code> if the fragment has not yet had a previous
     * call to <code>onCreate</code>.</p>
     *
     * @param childFragment child fragment being attached
     */
    @Override
    public void onAttachFragment(Fragment childFragment) {
        LogUtil.V(CLASS_NAME, "onAttachFragment(childFragment) [I N] ");
        super.onAttachFragment(childFragment);
        LogUtil.V(CLASS_NAME, "onAttachFragment(childFragment) [OUT] ");
    }

    /**
     * Called when a fragment is first attached to its context.
     * {@link #onCreate(Bundle)} will be called after this.
     */
    @CallSuper
    @Override
    public void onAttach(Context context) {
        LogUtil.V(CLASS_NAME, "onAttach(Context) [I N] ");
        super.onAttach(context);
        LogUtil.V(CLASS_NAME, "onAttach(Context) [OUT] ");
    }

    /**
     * @deprecated Use {@link #onAttach(Context)} instead.
     */
    @Deprecated
    @CallSuper
    @Override
    public void onAttach(Activity activity) {
        LogUtil.V(CLASS_NAME, "onAttach(Activity) [I N] ");
        super.onAttach(activity);
        LogUtil.V(CLASS_NAME, "onAttach(Activity) [OUT] ");
    }

    /**
     * Called when a fragment loads an animation.
     */
    @Override
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {
        LogUtil.V(CLASS_NAME, "onCreateAnimator() [I N] ");
        Animator animator = super.onCreateAnimator(transit, enter, nextAnim);
        LogUtil.V(CLASS_NAME, "onCreateAnimator() [OUT] ");
        return animator;
    }

    /**
     * Called to do initial creation of a fragment.  This is called after
     * {@link #onAttach(Activity)} and before
     * {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}, but is not called if the fragment
     * instance is retained across Activity re-creation (see {@link #setRetainInstance(boolean)}).
     *
     * <p>Note that this can be called while the fragment's activity is
     * still in the process of being created.  As such, you can not rely
     * on things like the activity's content view hierarchy being initialized
     * at this point.  If you want to do work once the activity itself is
     * created, see {@link #onActivityCreated(Bundle)}.
     *
     * <p>If your app's <code>targetSdkVersion</code> is {@link android.os.Build.VERSION_CODES#M}
     * or lower, child fragments being restored from the savedInstanceState are restored after
     * <code>onCreate</code> returns. When targeting {@link android.os.Build.VERSION_CODES#N} or
     * above and running on an N or newer platform version
     * they are restored by <code>Fragment.onCreate</code>.</p>
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @CallSuper
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        LogUtil.V(CLASS_NAME, "onCreate() [I N] ");
        super.onCreate(savedInstanceState);
        LogUtil.V(CLASS_NAME, "onCreate() [OUT] ");
    }

    /**
     * Called to have the fragment instantiate its user interface view.
     * This is optional, and non-graphical fragments can return null (which
     * is the default implementation).  This will be called between
     * {@link #onCreate(Bundle)} and {@link #onActivityCreated(Bundle)}.
     *
     * <p>If you return a View from here, you will later be called in
     * {@link #onDestroyView} when the view is being released.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view
     *                           itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
            Bundle savedInstanceState) {
        LogUtil.V(CLASS_NAME, "onCreateView() [I N] ");
        View view = super.onCreateView(inflater, container, savedInstanceState);
        LogUtil.V(CLASS_NAME, "onCreateView() [OUT] ");
        return view;
    }

    /**
     * Called immediately after {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     * has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to initialize themselves once
     * they know their view hierarchy has been completely created.  The fragment's
     * view hierarchy is not however attached to its parent at this point.
     *
     * @param view               The View returned by
     *                           {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        LogUtil.V(CLASS_NAME, "onViewCreated() [I N] ");
        super.onViewCreated(view, savedInstanceState);
        LogUtil.V(CLASS_NAME, "onViewCreated() [OUT] ");
    }

    /**
     * Called when the fragment's activity has been created and this
     * fragment's view hierarchy instantiated.  It can be used to do final
     * initialization once these pieces are in place, such as retrieving
     * views or restoring state.  It is also useful for fragments that use
     * {@link #setRetainInstance(boolean)} to retain their instance,
     * as this callback tells the fragment when it is fully associated with
     * the new activity instance.  This is called after {@link #onCreateView}
     * and before {@link #onViewStateRestored(Bundle)}.
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @CallSuper
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        LogUtil.V(CLASS_NAME, "onActivityCreated() [I N] ");
        super.onActivityCreated(savedInstanceState);
        LogUtil.V(CLASS_NAME, "onActivityCreated() [OUT] ");
    }

    /**
     * Called when all saved state has been restored into the view hierarchy
     * of the fragment.  This can be used to do initialization based on saved
     * state that you are letting the view hierarchy track itself, such as
     * whether check box widgets are currently checked.  This is called
     * after {@link #onActivityCreated(Bundle)} and before
     * {@link #onStart()}.
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @CallSuper
    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        LogUtil.V(CLASS_NAME, "onViewStateRestored() [I N] ");
        super.onViewStateRestored(savedInstanceState);
        LogUtil.V(CLASS_NAME, "onViewStateRestored() [OUT] ");
    }

    /**
     * Called when the Fragment is visible to the user.  This is generally
     * tied to {@link Activity#onStart() Activity.onStart} of the containing
     * Activity's lifecycle.
     */
    @CallSuper
    @Override
    public void onStart() {
        LogUtil.V(CLASS_NAME, "onStart() [I N] ");
        super.onStart();
        LogUtil.V(CLASS_NAME, "onStart() [OUT] ");
    }

    /**
     * Called when the fragment is visible to the user and actively running.
     * This is generally
     * tied to {@link Activity#onResume() Activity.onResume} of the containing
     * Activity's lifecycle.
     */
    @CallSuper
    @Override
    public void onResume() {
        LogUtil.V(CLASS_NAME, "onStart() [I N] ");
        super.onResume();
        // When creating again after fragment discarding, sometime onResume() called twice.
        // For that reason, confirm whether it is attached.
        if (!isAdded()) return;
        LogUtil.V(CLASS_NAME, "onStart() [OUT] ");
    }

    /**
     * Called to ask the fragment to save its current dynamic state, so it
     * can later be reconstructed in a new instance of its process is
     * restarted.  If a new instance of the fragment later needs to be
     * created, the data you place in the Bundle here will be available
     * in the Bundle given to {@link #onCreate(Bundle)},
     * {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}, and
     * {@link #onActivityCreated(Bundle)}.
     *
     * <p>This corresponds to {@link Activity#onSaveInstanceState(Bundle)
     * Activity.onSaveInstanceState(Bundle)} and most of the discussion there
     * applies here as well.  Note however: <em>this method may be called
     * at any time before {@link #onDestroy()}</em>.  There are many situations
     * where a fragment may be mostly torn down (such as when placed on the
     * back stack with no UI showing), but its state will not be saved until
     * its owning activity actually needs to save its state.
     *
     * @param outState Bundle in which to place your saved state.
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        LogUtil.V(CLASS_NAME, "onSaveInstanceState() [I N] ");
        super.onSaveInstanceState(outState);
        LogUtil.V(CLASS_NAME, "onSaveInstanceState() [OUT] ");
    }

    /**
     * Called when the Fragment's activity changes from fullscreen mode to multi-window mode and
     * visa-versa. This is generally tied to {@link Activity#onMultiWindowModeChanged} of the
     * containing Activity. This method provides the same configuration that will be sent in the
     * following {@link #onConfigurationChanged(Configuration)} call after the activity enters this
     * mode.
     *
     * @param isInMultiWindowMode True if the activity is in multi-window mode.
     * @param newConfig           The new configuration of the activity with the state
     *                            {@param isInMultiWindowMode}.
     */
    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode, Configuration newConfig) {
        LogUtil.V(CLASS_NAME, "onMultiWindowModeChanged() [I N] ");
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig);
        LogUtil.V(CLASS_NAME, "onMultiWindowModeChanged() [OUT] ");
    }

    /**
     * Called when the Fragment's activity changes from fullscreen mode to multi-window mode and
     * visa-versa. This is generally tied to {@link Activity#onMultiWindowModeChanged} of the
     * containing Activity.
     *
     * @param isInMultiWindowMode True if the activity is in multi-window mode.
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
     * Called by the system when the activity changes to and from picture-in-picture mode. This is
     * generally tied to {@link Activity#onPictureInPictureModeChanged} of the containing Activity.
     * This method provides the same configuration that will be sent in the following
     * {@link #onConfigurationChanged(Configuration)} call after the activity enters this mode.
     *
     * @param isInPictureInPictureMode True if the activity is in picture-in-picture mode.
     * @param newConfig                The new configuration of the activity with the state
     *                                 {@param isInPictureInPictureMode}.
     */
    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode,
            Configuration newConfig) {
        LogUtil.V(CLASS_NAME, "onPictureInPictureModeChanged() [I N] ");
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);
        LogUtil.V(CLASS_NAME, "onPictureInPictureModeChanged() [OUT] ");
    }

    /**
     * Called by the system when the activity changes to and from picture-in-picture mode. This is
     * generally tied to {@link Activity#onPictureInPictureModeChanged} of the containing Activity.
     *
     * @param isInPictureInPictureMode True if the activity is in picture-in-picture mode.
     * @deprecated Use {@link #onPictureInPictureModeChanged(boolean, Configuration)} instead.
     */
    @Deprecated
    @Override
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        LogUtil.V(CLASS_NAME, "onPictureInPictureModeChanged() [I N] ");
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);
        LogUtil.V(CLASS_NAME, "onPictureInPictureModeChanged() [OUT] ");
    }

    @CallSuper
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        LogUtil.V(CLASS_NAME, "onConfigurationChanged() [I N] ");
        super.onConfigurationChanged(newConfig);
        LogUtil.V(CLASS_NAME, "onConfigurationChanged() [OUT] ");
    }

    /**
     * Called when the Fragment is no longer resumed.  This is generally
     * tied to {@link Activity#onPause() Activity.onPause} of the containing
     * Activity's lifecycle.
     */
    @CallSuper
    @Override
    public void onPause() {
        LogUtil.V(CLASS_NAME, "onPause() [I N] ");
        super.onPause();
        LogUtil.V(CLASS_NAME, "onPause() [OUT] ");
    }

    /**
     * Called when the Fragment is no longer started.  This is generally
     * tied to {@link Activity#onStop() Activity.onStop} of the containing
     * Activity's lifecycle.
     */
    @CallSuper
    @Override
    public void onStop() {
        LogUtil.V(CLASS_NAME, "onStop() [I N] ");
        super.onStop();
        LogUtil.V(CLASS_NAME, "onStop() [OUT] ");
    }

    @CallSuper
    @Override
    public void onLowMemory() {
        LogUtil.V(CLASS_NAME, "onLowMemory() [I N] ");
        super.onLowMemory();
        LogUtil.V(CLASS_NAME, "onLowMemory() [OUT] ");
    }

    @CallSuper
    @Override
    public void onTrimMemory(int level) {
        LogUtil.V(CLASS_NAME, "onTrimMemory() [I N] ");
        super.onTrimMemory(level);
        LogUtil.V(CLASS_NAME, "onTrimMemory() [OUT] ");
    }

    /**
     * Called when the view previously created by {@link #onCreateView} has
     * been detached from the fragment.  The next time the fragment needs
     * to be displayed, a new view will be created.  This is called
     * after {@link #onStop()} and before {@link #onDestroy()}.  It is called
     * <em>regardless</em> of whether {@link #onCreateView} returned a
     * non-null view.  Internally it is called after the view's state has
     * been saved but before it has been removed from its parent.
     */
    @CallSuper
    @Override
    public void onDestroyView() {
        LogUtil.V(CLASS_NAME, "onDestroyView() [I N] ");
        super.onDestroyView();
        LogUtil.V(CLASS_NAME, "onDestroyView() [OUT] ");
    }

    /**
     * Called when the fragment is no longer in use.  This is called
     * after {@link #onStop()} and before {@link #onDetach()}.
     */
    @CallSuper
    @Override
    public void onDestroy() {
        LogUtil.V(CLASS_NAME, "onDestroy() [I N] ");
        super.onDestroy();
        LogUtil.V(CLASS_NAME, "onDestroy() [OUT] ");
    }

    /**
     * Called when the fragment is no longer attached to its activity.  This is called after
     * {@link #onDestroy()}, except in the cases where the fragment instance is retained across
     * Activity re-creation (see {@link #setRetainInstance(boolean)}), in which case it is called
     * after {@link #onStop()}.
     */
    @CallSuper
    @Override
    public void onDetach() {
        LogUtil.V(CLASS_NAME, "onDetach() [I N] ");
        super.onDetach();
        LogUtil.V(CLASS_NAME, "onDetach() [OUT] ");
    }

    /**
     * Initialize the contents of the Activity's standard options menu.  You
     * should place your menu items in to <var>menu</var>.  For this method
     * to be called, you must have first called {@link #setHasOptionsMenu}.  See
     * {@link Activity#onCreateOptionsMenu(Menu) Activity.onCreateOptionsMenu}
     * for more information.
     *
     * @param menu The options menu in which you place your items.
     * @see #setHasOptionsMenu
     * @see #onPrepareOptionsMenu
     * @see #onOptionsItemSelected
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        LogUtil.V(CLASS_NAME, "onCreateOptionsMenu() [I N] ");
        super.onCreateOptionsMenu(menu, inflater);
        LogUtil.V(CLASS_NAME, "onCreateOptionsMenu() [OUT] ");
    }

    /**
     * Prepare the Screen's standard options menu to be displayed.  This is
     * called right before the menu is shown, every time it is shown.  You can
     * use this method to efficiently enable/disable items or otherwise
     * dynamically modify the contents.  See
     * {@link Activity#onPrepareOptionsMenu(Menu) Activity.onPrepareOptionsMenu}
     * for more information.
     *
     * @param menu The options menu as last shown or first initialized by
     *             onCreateOptionsMenu().
     * @see #setHasOptionsMenu
     * @see #onCreateOptionsMenu
     */
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        LogUtil.V(CLASS_NAME, "onPrepareOptionsMenu() [I N] ");
        super.onPrepareOptionsMenu(menu);
        LogUtil.V(CLASS_NAME, "onPrepareOptionsMenu() [OUT] ");
    }

    /**
     * Called when this fragment's option menu items are no longer being
     * included in the overall options menu.  Receiving this call means that
     * the menu needed to be rebuilt, but this fragment's items were not
     * included in the newly built menu (its {@link #onCreateOptionsMenu(Menu, MenuInflater)}
     * was not called).
     */
    @Override
    public void onDestroyOptionsMenu() {
        LogUtil.V(CLASS_NAME, "onDestroyOptionsMenu() [I N] ");
        super.onDestroyOptionsMenu();
        LogUtil.V(CLASS_NAME, "onDestroyOptionsMenu() [OUT] ");
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
     * perform the default menu handling.
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
        LogUtil.V(CLASS_NAME, "onOptionsItemSelected() [OUT] ret:" + ret);
        return ret;
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
     * Unlike {@link #onCreateOptionsMenu}, this will be called every
     * time the context menu is about to be shown and should be populated for
     * the view (or item inside the view for {@link AdapterView} subclasses,
     * this can be found in the {@code menuInfo})).
     * <p>
     * Use {@link #onContextItemSelected(android.view.MenuItem)} to know when an
     * item has been selected.
     * <p>
     * The default implementation calls up to
     * {@link Activity#onCreateContextMenu Activity.onCreateContextMenu}, though
     * you can not call this implementation if you don't want that behavior.
     * <p>
     * It is not safe to hold onto the context menu after this method returns.
     * {@inheritDoc}
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
        LogUtil.V(CLASS_NAME, "onContextItemSelected() [OUT] ret:" + ret);
        return ret;
    }
}
