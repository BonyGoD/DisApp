package com.example.presupuestosdisa.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

fun navegarPlayStore(context: Context) {
    val appPackage = context.packageName
    try {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$appPackage")))
    } catch (e: Exception) {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackage")))
    }
}