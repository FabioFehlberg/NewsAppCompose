package com.fberg.newsapp.common

sealed class Resource<T> {
    class Success<T>(val data: T, val shouldResetList: Boolean = false) : Resource<T>()
    class Error<T>(val exception: NewsException, val data: T? = null) : Resource<T>()
}
