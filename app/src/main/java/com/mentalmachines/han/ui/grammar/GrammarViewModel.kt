package com.mentalmachines.han.ui.grammar

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.google.android.gms.tasks.Task
import com.mentalmachines.han.data.repository.GrammarRepository
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class GrammarViewModel : ViewModel() {
    private val mGrammarItems = MutableLiveData<List<Task<*>>>()
    private val mDataLoading = MutableLiveData<Boolean>()
    private val grammarRepository: GrammarRepository? = null
    val disposables : CompositeDisposable? = null

    private fun loadGrammar(grammars: List<String>) {
        disposables.add(loadGreetingUseCase.execute()
            .subscribeOn()
            .observeOn()
            .doOnSubscribe({ __ -> {} })
            .subscribe(
                { grammar -> {} }
            )
        )
    }

    fun execute(): Single<String> {
        return grammarRepository.getGrammar()
    }

}