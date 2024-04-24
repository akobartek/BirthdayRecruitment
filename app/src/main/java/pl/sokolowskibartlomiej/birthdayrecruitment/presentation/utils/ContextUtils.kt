package pl.sokolowskibartlomiej.birthdayrecruitment.presentation.utils

import android.content.Context

fun Context.getDrawableId(name:String): Int {
    return this.resources.getIdentifier(name , "drawable", this.packageName)
}