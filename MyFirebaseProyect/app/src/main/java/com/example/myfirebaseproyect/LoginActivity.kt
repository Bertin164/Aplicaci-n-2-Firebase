package com.example.myfirebaseproyect

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    lateinit var editTextLogin : EditText
    lateinit var editPassLogion: EditText
    //lateinit var editConfLogin : EditText
    lateinit var btnLogin :Button
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        editTextLogin = findViewById(R.id.emialLogin)
        editPassLogion = findViewById(R.id.passLogin)
        btnLogin = findViewById(R.id.btnLogin)
        auth = Firebase.auth

        btnLogin.setOnClickListener{
            login()
        }
    }
    private fun login(){
        val email = editTextLogin.text.toString()
        val pass = editPassLogion.text.toString()

        auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this){
            if(it.isSuccessful){
                Toast.makeText(this, "Login exitoso", Toast.LENGTH_LONG).show()

                val intent = Intent(this, DataActivity::class.java)
                startActivity(intent)
            }
            else{
                Toast.makeText(this, "Verifica email o contraseña", Toast.LENGTH_LONG).show()
            }
        }

    }
}