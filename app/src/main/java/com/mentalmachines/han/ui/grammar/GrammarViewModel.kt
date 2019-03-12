package com.mentalmachines.han.ui.grammar

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.graphics.drawable.Drawable
import com.google.android.gms.tasks.Task
import com.mentalmachines.han.data.repository.GrammarRepository


class GrammarViewModel : ViewModel() {
    private val mItems = MutableLiveData<List<Task<*>>>()

    private val mDataLoading = MutableLiveData<Boolean>()

    private val mCurrentFilteringLabel = MutableLiveData<String>()

    private val mNoTasksLabel = MutableLiveData<String>()

    private val mNoTaskIconRes = MutableLiveData<Drawable>()

    private val mTasksAddViewVisible = MutableLiveData<Boolean>()

    private val mTasksRepository: GrammarRepository? = null

}