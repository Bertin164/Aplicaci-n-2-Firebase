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


class MainActivity : AppCompatActivity() {
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPass: EditText
    private lateinit var editTextConf: EditText
    private lateinit var btnSignUp: Button
    private lateinit var btnLoginRe: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextEmail = findViewById(R.id.email)
        editTextPass = findViewById(R.id.pass)
        editTextConf = findViewById(R.id.pasconf)
        btnSignUp  = findViewById(R.id.regis)
        btnLoginRe = findViewById(R.id.login)

        auth = Firebase.auth

        btnSignUp.setOnClickListener{
            signUpUser()
        }
        btnLoginRe.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
    private fun signUpUser(){
        val email = editTextEmail.text.toString()
        val pass = editTextPass.text.toString()
        val confirm = editTextConf.text.toString()

        if(email.isBlank() || pass.isBlank()){
            Toast.makeText(this, "Email y password no pueden estar vacios", Toast.LENGTH_LONG).show()
            return
        }
        if(pass!=confirm){
            Toast.makeText(this, "La contraseña no coincide", Toast.LENGTH_LONG).show()
            return
        }
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this){
            if(it.isSuccessful){
                Toast.makeText(this, "Se ha registrado correctamente", Toast.LENGTH_LONG).show()
                //finish()
            }else{
                Toast.makeText(this, "El registr no se a podido realizar", Toast.LENGTH_LONG).show()
            }
        }
    }
}