package com.devware.disapp.data.repositories

import android.content.Context
import com.devware.disapp.data.network.FireBaseService
import javax.inject.Inject

class AccesoAppRepository @Inject constructor(
    private val context: Context,
    private val fireBaseService: FireBaseService
) {

    companion object {
        const val MIN_VERSION = "min_version"
    }

    suspend fun getMinVersion(): List<Int> {
        val response: List<Int> = fireBaseService.getMinVersion()
        return response
    }

    fun getCurrentVersion(): List<Int> {
        return try {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            packageInfo.versionName?.split(".")?.map { it.toInt() }
            listOf(1, 0, 0)
        } catch (e: Exception) {
            listOf(0, 0, 0)
        }
    }

}