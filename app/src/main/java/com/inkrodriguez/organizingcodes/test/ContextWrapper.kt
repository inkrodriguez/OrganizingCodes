package com.inkrodriguez.organizingcodes.test

import android.content.Context
import com.inkrodriguez.organizingcodes.R

class ContextWrapper(private val context: Context) {

    fun getLogin(): String {
        return context.getString(R.string.login)
    }

}