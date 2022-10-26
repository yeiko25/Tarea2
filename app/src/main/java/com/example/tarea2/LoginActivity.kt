package com.example.tarea2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.tarea2.databinding.ActivityLoginBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityLoginBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth


        binding.btLogin.setOnClickListener() {

                Login()

        }

            binding.btRegister.setOnClickListener() {
                val RegisterIntent = Intent(this, RegisterActivity::class.java).apply {
                }
                startActivity(RegisterIntent)
            }

        }


    public override fun onStart() {
        super.onStart()
        val usuario = auth.currentUser
        actualiza(usuario)
    }

    private fun actualiza(user: FirebaseUser?) {
        if (user != null) {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }


    private fun Login() {

        title = "Inicio de Seción"

        val  email = binding.etEmail.text.toString()
        val clave = binding.etClave.text.toString()

        auth.signInWithEmailAndPassword(email,clave).addOnCompleteListener(this) {

                task -> if(task.isSuccessful){
            Log.d("Login", "Autenticando")
            val user = auth.currentUser
            if(user != null ){
                actualiza(user)
            }
        }else{
            Log.d("Login", "error")
            Toast.makeText(baseContext, "error", Toast.LENGTH_LONG).show()
        }
        }
    }

}