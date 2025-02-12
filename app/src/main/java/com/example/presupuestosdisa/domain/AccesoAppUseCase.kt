package com.example.presupuestosdisa.domain

import com.example.presupuestosdisa.data.repositories.AccesoAppRepository
import javax.inject.Inject

class AccesoAppUseCase @Inject constructor(
    private val accesoAppRepository: AccesoAppRepository
) {

    suspend operator fun invoke(): Boolean {

        val currentVersion = accesoAppRepository.getCurrentVersion()
        val minVersion = accesoAppRepository.getMinVersion()

        for ((currentPart, minVersionPart) in currentVersion.zip(minVersion)) {
            if (currentPart != minVersionPart) {
                return currentPart > minVersionPart
            }
        }
        return true
    }
}