package com.mentalmachines.han.ui.base

import android.content.Context
import android.os.Bundle
import android.support.annotation.StringDef
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.trello.rxlifecycle2.LifecycleTransformer
import io.reactivex.subjects.BehaviorSubject
import org.reactivestreams.Subscription
import timber.log.Timber
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

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
    fun onAttach(context: Context?) {
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