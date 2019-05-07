package com.man.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.man.myapplication.api.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val keywords: MutableLiveData<List<String>> by lazy {
        MutableLiveData<List<String>>().also {
            fetchKeywords()
        }
    }
    private val error: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getKeywords(): LiveData<List<String>> {
        return keywords
    }

    fun getError(): LiveData<String> {
        return error
    }

    private fun fetchKeywords() {
        compositeDisposable.addAll(
            ApiService.getInstance()
                .getKeywords()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    keywords.postValue(it)
                }, {
                    error.postValue(it.message)
                })
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }
}