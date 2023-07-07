package com.example.megagifs.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.megagifs.BuildConfig
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/*****
 * Proyect: MegaGifs
 * Package: com.example.megagifs.ui
 *
 * Created by Rafael Barbeyto Torrellas on 03/07/2023 at 22:36
 * More info: https://www.linkedin.com/in/rafa-barbeyto/
 *
 * All rights reserved 2023.
 *****/
@HiltViewModel
class MainViewModel @Inject constructor(
    private val database: FirebaseDatabase
) : ViewModel() {

    private lateinit var databaseReference: DatabaseReference

    private val _newVersion = MutableLiveData<Boolean>()
    val newVersion: LiveData<Boolean> = _newVersion

    fun checkVersion() {

        var versionCode: Long = 0
        var versionName = ""
        val currentVersionCode = BuildConfig.VERSION_CODE.toLong()
        val currentVersionName = BuildConfig.VERSION_NAME

        databaseReference = database.getReference("VERSION")
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // Este método se llama cada vez que los datos en la base de datos cambian
                // Verifica si hay datos disponibles en la ubicación
                if (snapshot.exists()) {
                    // Recorre los datos y realiza las operaciones necesarias
                    for (childSnapshot in snapshot.children) {
                        val key = childSnapshot.key
                        val value = childSnapshot.value
                        // Aquí puedes realizar acciones con los datos
                        when (key) {
                            "versionCode" -> {
                                versionCode = value as Long
                            }
                            "versionName" -> {
                                versionName = value.toString()
                            }
                        }
                    }
                    _newVersion.value = (versionCode > currentVersionCode || versionName != currentVersionName)
                } else {
                    // No hay datos disponibles en la ubicación
                    Log.i("DEVELOPRAFA", "Sin Datos accesibles en RealTime Database")
                }
            }
            override fun onCancelled(error: DatabaseError) {
                // Este método se llama si hay algún error en la operación
                // Puedes agregar manejo de errores aquí si lo deseas
                Log.i("DEVELOPRAFA", "Error acceso a REALTIME Database : ${error.message}")
            }
        })
    }


}