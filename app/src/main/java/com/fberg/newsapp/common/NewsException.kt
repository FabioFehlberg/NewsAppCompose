package com.fberg.newsapp.common

sealed class NewsException(val errorMessage: String) : Exception(errorMessage) {
    class DataException(message: String) : NewsException(message)
    class DomainException(message: String) : NewsException(message)
}
