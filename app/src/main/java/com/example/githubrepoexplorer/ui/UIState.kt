package com.example.githubrepoexplorer.ui

sealed class UIState<out R> {
    object Loading: UIState<Nothing>()
    data class Error(val exception: Throwable) : UIState<Nothing>()
    data class Success<out R>(val data: R): UIState<R>()
}