package com.example.myfirebaseproyect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.myfirebaseproyect.databinding.ActivityDataBinding
import com.google.firebase.firestore.FirebaseFirestore

const val TAG = "FIRESTORE"


class DataActivity : AppCompatActivity() {

    private var binding: ActivityDataBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_data)


        binding = ActivityDataBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        uploadData()
        //readData()
        val button = findViewById<Button>(R.id.btnReadData)
        button.setOnClickListener{
            val intent = Intent(this, detail::class.java)
            startActivity(intent)
        }

    }


    class firebaseUtils{
        val fireStoreDatabase = FirebaseFirestore.getInstance()
    }
    private fun uploadData(){
        binding!!.btnUploadData.setOnClickListener{
            val hashMap = hashMapOf<String, Any>(
                "dirrecion" to "5 de Febrero",
                "nombre" to "Sushi Unipoli",
                "tipo" to "Japones",
                "imagen" to "URL"
            )
            firebaseUtils().fireStoreDatabase.collection("restaurantes")
                .add(hashMap)
                .addOnSuccessListener{
                    Log.d(TAG, "Se añadio el documento con ID {$it.id}")
                }
                .addOnFailureListener{
                    Log.d(TAG, "El documento no se pudo agregar")
                }
        }
    }
    /*private fun readData(){

        binding!!.btnReadData.setOnClickListener {
            firebaseUtils().fireStoreDatabase.collection("restaurantes")
                .get()
                .addOnSuccessListener { QuerySnapshot ->
                    QuerySnapshot.forEach { Document ->
                        Log.d(TAG, "Documento recuperado con ID {${Document.data}}")
                        val nombre = findViewById<TextView>(R.id.name)
                        val direccion = findViewById<TextView>(R.id.address)
                        //val imagen = findViewById<TextView>(R.id.)
                        val tipo = findViewById<TextView>(R.id.type)
                        //info = Document.get("Nombre") as String
                        //inf = Document.get("Nombre") as String
                        // Log.d(TAG, "Documento recuperado con ID {${Document.get("Nombre")}}")
                        nombre.text = Document.get("nombre") as String
                        direccion.text = Document.get("dirreccion") as String
                        tipo.text = Document.get("tipo") as String
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Hubo un error recuperand la informacion", Toast.LENGTH_LONG).show()
                }
        }

    }*/
}