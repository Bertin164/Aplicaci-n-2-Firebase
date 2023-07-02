package com.example.myfirebaseproyect

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class detail : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val dataTextView: TextView = findViewById(R.id.datatextView)

        val db = FirebaseFirestore.getInstance()

        val collectionPath = "restaurantes"

        db.collection(collectionPath).get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val querySnapshot: QuerySnapshot? = task.result
                    if (querySnapshot != null) {
                        val stringBuilder = StringBuilder()
                        for (document in querySnapshot.documents) {
                            // Accede a los campos de cada documento
                            val campo1 = document.getString("dirreccion")
                            val campo2 = document.getString("imagen")
                            val campo3 = document.getString("nombre")
                            val campo4 = document.getString("tipo")
                            // Agrega los datos al StringBuilder
                            stringBuilder.append("Nombre: $campo1\n\n")
                            stringBuilder.append("Descripcion: $campo2\n\n")
                            stringBuilder.append("Precio: $campo3\n\n")
                            stringBuilder.append("Size: $campo4\n\n")
                        }
                        // Muestra los datos en el TextView
                        dataTextView.text = stringBuilder.toString()
                    }
                } else {
                    Log.e(TAG, "Error al obtener los datos: ", task.exception)
                }
            }
    }
}