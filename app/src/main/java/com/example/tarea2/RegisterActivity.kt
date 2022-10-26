package com.example.tarea2


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.tarea2.databinding.ActivityRegisterBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegisterBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        auth = Firebase.auth




        binding.btRegisterR.setOnClickListener() {

                Registro()


        }

            binding.btLoginR.setOnClickListener() {
                val LoginIntent = Intent(this, LoginActivity::class.java).apply {
                }
                startActivity(LoginIntent)
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


    private fun Registro() {

        title = "Registro"

        val  email = binding.etEmailR.text.toString()
        val clave = binding.etClaveR.text.toString()

        auth.createUserWithEmailAndPassword(email,clave).addOnCompleteListener(this) {

                task -> if(task.isSuccessful){
            Log.d("Registro", "Registrando")
            val user = auth.currentUser
            if(user != null ){
                actualiza(user)
            }
        }else{
            Log.d("Registro", "error")
            Toast.makeText(baseContext, "error", Toast.LENGTH_LONG).show()
        }
        }
    }

}


