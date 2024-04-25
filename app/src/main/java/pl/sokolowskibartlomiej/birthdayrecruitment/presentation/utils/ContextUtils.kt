package pl.sokolowskibartlomiej.birthdayrecruitment.presentation.utils

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("DiscouragedApi")
fun Context.getDrawableId(name:String): Int {
    return this.resources.getIdentifier(name , "drawable", this.packageName)
}