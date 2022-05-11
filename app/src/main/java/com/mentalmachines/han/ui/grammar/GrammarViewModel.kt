package com.mentalmachines.han.ui.grammar

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mentalmachines.han.data.repository.GrammarRepository
import io.reactivex.disposables.CompositeDisposable

class GrammarViewModel : ViewModel() {
    private val mGrammarItems = MutableLiveData<List<String>>()
    private val mDataLoading = MutableLiveData<Boolean>()
    private val grammarRepository: GrammarRepository? = null
    val disposables: CompositeDisposable? = null

//    private fun loadGrammar(grammars: List<String>) {
//        val loadGreetingUseCase
//        disposables?.add(loadGreetingUseCase.execute()
//            .subscribeOn()
//            .observeOn()
//            .doOnSubscribe({ __ -> {} })
//            .subscribe(
//                { grammar -> {} }
//            )
//        )
//    }
//
//    fun execute(): Single<String> {
//        return grammarRepository.getGrammar()
//    }

}