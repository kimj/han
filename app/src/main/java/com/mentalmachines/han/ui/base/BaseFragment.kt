package com.mentalmachines.han.ui.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.support.annotation.StringDef
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.trello.rxlifecycle2.LifecycleTransformer

import org.reactivestreams.Subscription

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

import butterknife.ButterKnife
import butterknife.Unbinder
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber

/**
 * Created by jkim11 on 1/31/17.
 */

/**
 * Abstract Fragment that every other Fragment in this application must implement.
 */
// RxLifecycle, LifeCycle logging, presenter attachment
abstract class BaseFragment : Fragment() {
    private val lifeCycleSubject = BehaviorSubject.create<LifecycleEvent>()
    private val lifecycleLoggingSubsbcription: Subscription? = null

    @get:LayoutRes
    abstract val layout: Int

    @CallSuper
    override fun onAttach(context: Context?) {
        Timber.v("Lifecycle onAttach: %s to %s", this, context)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(layout, container, false)
        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onStart() {
        super.onStart()
        // lifeCycleSubject.onNext(LifecycleEvent.START);
    }

    override fun onResume() {
        super.onResume()
        // lifeCycleSubject.onNext(LifecycleEvent.RESUME);
    }

    override fun onPause() {
        super.onPause()
        // lifeCycleSubject.onNext(LifecycleEvent.PAUSE);
    }

    override fun onStop() {
        super.onStop()
        // lifeCycleSubject.onNext(LifecycleEvent.STOP);
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    fun <T> bindLifecycle(): LifecycleTransformer<T>? {
        // return RxLifecycle.bind(lifecycle());
        return null
    }

    @StringDef("ATTACH", "DETACH", "CREATE", "START", "RESUME", "PAUSE", "STOP", "DESTROY")
    @Retention(RetentionPolicy.SOURCE)
    annotation class LifecycleEvent
    /*public final Observable<LifecycleEvent> getLifecycle() {
        return lifecycle;
    }
    public final <T> LifecycleTransformer<T> bindUntilDestroy() {
        return RxLifecycle.bind(lifecycle(), event -> LifecycleMvpDelegate.LifecycleEvent.DESTROY);
    }*/
}