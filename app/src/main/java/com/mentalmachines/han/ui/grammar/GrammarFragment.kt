package com.mentalmachines.han.ui.grammar

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mentalmachines.han.ui.base.BaseFragment
import io.reactivex.Observable

class GrammarFragment : BaseFragment(){
    var viewModel : GrammarViewModel()
    override val layout: Int
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(GrammarViewModel::class.java)

    }
}