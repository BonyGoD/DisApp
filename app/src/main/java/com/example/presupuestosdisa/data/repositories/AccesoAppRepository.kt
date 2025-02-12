package com.example.presupuestosdisa.data.repositories

import android.content.Context
import com.example.presupuestosdisa.data.network.FirebaseClient
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import javax.inject.Inject

class AccesoAppRepository @Inject constructor(
    private val context: Context,
    private val firebaseClient: FirebaseClient
) {

    companion object {
        const val MIN_VERSION = "min_version"
    }

    val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig.apply {
        setConfigSettingsAsync(remoteConfigSettings { minimumFetchIntervalInSeconds = 3600 })
        fetchAndActivate()
    }

    suspend fun getMinVersion(): List<Int> {
        val response: List<Int> = firebaseClient.getMinVersion()
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