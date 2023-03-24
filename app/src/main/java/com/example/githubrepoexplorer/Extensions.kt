package com.example.githubrepoexplorer

import timber.log.Timber

fun Any.logDebug(message: String? = null) {
    Timber.d(message)
}

fun Any.logError(message: String? = null, throwable: Throwable? = null) {
    Timber.e(throwable, message)
}