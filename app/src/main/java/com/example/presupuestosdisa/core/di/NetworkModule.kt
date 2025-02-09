package com.example.presupuestosdisa.core.di

import com.example.presupuestosdisa.data.network.FirebaseClient
import com.example.presupuestosdisa.data.network.FirebaseClientImpl
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideFirebaseFirestoreDB(): FirebaseFirestore {
        val db = Firebase.firestore
        return db
    }

    @Singleton
    @Provides
    fun provideFirebaseClient(firebaseFirestore: FirebaseFirestore): FirebaseClient {
        return FirebaseClientImpl(firebaseFirestore)
    }
}