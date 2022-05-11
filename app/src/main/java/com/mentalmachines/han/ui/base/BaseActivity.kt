package com.mentalmachines.han.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import butterknife.ButterKnife;
import timber.log.Timber;


/**
 * Created by jkim11 on 1/31/17.
 */

public abstract class BaseActivity extends Activity {
    public abstract int getLayout();

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.v("LIFECYCLE::onCreate(): %s", this);

        setContentView(getLayout());
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Timber.v("LIFECYCLE::onStart(): %s", this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.v("LIFECYCLE::onResume(): %s", this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Timber.v("LIFECYCLE::onPause(): %s", this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Timber.v("LIFECYCLE::onStop(): %s", this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.v("LIFECYCLE::onDestroy(): %s", this);
    }
}
