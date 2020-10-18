package com.example.androidarchitecturedemo.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer


class PublishLiveData<T> : LiveData<T>() {

    private var internalLiveData = MutableLiveData<T>()

    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        internalLiveData = MutableLiveData<T>()
        internalLiveData.observe(owner, observer)
    }

    public override fun setValue(value: T?) {
        super.setValue(value)
        internalLiveData.value = value
    }

    public override fun getValue(): T? {
        return internalLiveData.value
    }

    public override fun postValue(value: T?) {
        internalLiveData.postValue(value)
    }
}