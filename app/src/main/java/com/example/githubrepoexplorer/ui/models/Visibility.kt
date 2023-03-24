package com.example.githubrepoexplorer.ui.models

import androidx.annotation.StringRes
import com.example.githubrepoexplorer.R

enum class Visibility(private val apiName: String?, @StringRes val displayTextResource: Int?) {
    PUBLIC("public", R.string.txt_visibility_public),
    PRIVATE("private", R.string.txt_visibility_private),
    INTERNAL("internal", R.string.txt_visibility_internal),
    UNKWOWN(null, null);

    companion object {
        fun fromApiName(name: String?): Visibility {
            return Visibility.values().find { name == it.apiName } ?: UNKWOWN
        }
    }
}