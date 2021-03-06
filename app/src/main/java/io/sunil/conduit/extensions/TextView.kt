@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package io.sunil.conduit.extensions

import android.annotation.SuppressLint
import android.widget.TextView
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*



@SuppressLint("ConstantLocale")
val isoDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())

@SuppressLint("ConstantLocale")
val appDateFormat = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault())

var TextView.timeStamp: String
    set(value) {
        val date = isoDateFormat.parse(value)
        text = appDateFormat.format(date)
    }
    get() {
        val date = appDateFormat.parse(text.toString())
        return isoDateFormat.format(date)
    }