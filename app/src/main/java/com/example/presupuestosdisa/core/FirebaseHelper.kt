package com.example.presupuestosdisa.core

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object FirebaseHelper {
    fun getFirebase(): FirebaseFirestore {
        val db = Firebase.firestore
        return db
    }
}