package com.example.androidarchitecturedemo.repository

abstract class ApiResult<T> {
    abstract fun success(result: T)
    abstract fun failed(exception: Exception)
}