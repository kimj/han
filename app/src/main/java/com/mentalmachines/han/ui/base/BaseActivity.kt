package com.mentalmachines.han.ui.base

import android.R
import android.app.Activity
import android.os.Bundle
import android.view.MenuItem
import butterknife.ButterKnife
import timber.log.Timber

/**
 * Created by jkim11 on 1/31/17.
 */
abstract class BaseActivity : Activity() {
    abstract val layout: Int

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.v("LIFECYCLE::onCreate(): %s", this)
        setContentView(layout)
        ButterKnife.bind(this)
    }

    override fun onStart() {
        super.onStart()
        Timber.v("LIFECYCLE::onStart(): %s", this)
    }

    override fun onResume() {
        super.onResume()
        Timber.v("LIFECYCLE::onResume(): %s", this)
    }

    override fun onPause() {
        super.onPause()
        Timber.v("LIFECYCLE::onPause(): %s", this)
    }

    override fun onStop() {
        super.onStop()
        Timber.v("LIFECYCLE::onStop(): %s", this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.v("LIFECYCLE::onDestroy(): %s", this)
    }
}