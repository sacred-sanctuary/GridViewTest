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

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import jp.sacredsanctuary.common.base.activity.BaseAppCompatActivity;
import jp.sacredsanctuary.common.util.LogUtil;
import jp.sacredsanctuary.gridviewtest.R;
import jp.sacredsanctuary.gridviewtest.view.adapter.ImageListAdapter;
import jp.sacredsanctuary.gridviewtest.view.ui.preference.PreferenceActivity;
import jp.sacredsanctuary.gridviewtest.viewModel.ImageItemViewModel;

/**
 * GridView Test Activity
 */
public class GridViewTestActivity extends BaseAppCompatActivity implements View.OnClickListener {
    private static final String ClassName = GridViewTestActivity.class.getSimpleName();

    private RecyclerView mRecyclerView;
    private ImageListAdapter mImageListAdapter;

    public GridViewTestActivity() {
        super(ClassName);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({"unchecked", "deprecation"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LogUtil.V(ClassName, "onCreate() ");
        if (RequestPermissionsActivity.startPermissionActivity(this)) {
            finish();
        }

        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

        setContentView(R.layout.activity_grid_view_test);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = (RecyclerView) findViewById(R.id.grid_list);
        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        int orientation = getResources().getConfiguration().orientation;
        int spanCount = (orientation == Configuration.ORIENTATION_LANDSCAPE) ? 5 : 3; // 3 columns
        int spacing = 40; // 50px
        boolean includeEdge = true;

        // use a linear layout manager
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, spanCount));

        mRecyclerView.addItemDecoration(
                new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        mImageListAdapter = new ImageListAdapter(this, this.getLoaderManager());
        mRecyclerView.setAdapter(mImageListAdapter);
        mImageListAdapter.clearItems();

        ImageItemViewModel model = ViewModelProviders.of(this).get(ImageItemViewModel.class);
        model.getImageItemList().observe(this, mImageItemList -> {
            LogUtil.V(ClassName, "observe() [INF] mImageItemList:" + mImageItemList);
            mImageListAdapter.setItems(mImageItemList);
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onResume() {
        LogUtil.V(ClassName, "onResume() ");
        if (RequestPermissionsActivity.startPermissionActivity(this)) {
            finish();
        }
        super.onResume();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        LogUtil.V(ClassName, "onCreateOptionsMenu() ");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        LogUtil.V(ClassName, "onPrepareOptionsMenu() ");
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean ret = true;
        int id = item.getItemId();
        if (R.id.action_settings == id) {
            LogUtil.V(ClassName, "onOptionsItemSelected() [INF] settings ");
            startSettingsActivity();
        } else {
            LogUtil.V(ClassName, "onOptionsItemSelected() [INF] default ");
            ret = super.onOptionsItemSelected(item);
        }
        return ret;
    }

    public void startSettingsActivity() {
        Intent intent = new Intent().setClass(this, PreferenceActivity.class);
        startActivity(intent);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClick(View view) {
        String path = (String) view.getTag();
        LogUtil.E(ClassName, "onClick() [INF] path:" + path);
    }
}
