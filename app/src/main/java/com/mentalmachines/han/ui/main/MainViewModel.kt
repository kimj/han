package com.mentalmachines.han.ui.main

import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.graphics.drawable.Drawable
import com.google.android.gms.tasks.Task


class MainViewModel : ViewModel(){

    private val mItems = MutableLiveData<List<Task<*>>>()

    private val mDataLoading = MutableLiveData<Boolean>()

    private val mCurrentFilteringLabel = MutableLiveData<String>()

    private val mNoTasksLabel = MutableLiveData<String>()

    private val mNoTaskIconRes = MutableLiveData<Drawable>()

    private val mTasksAddViewVisible = MutableLiveData<Boolean>()

}