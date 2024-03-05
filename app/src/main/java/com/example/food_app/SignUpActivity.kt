package com.example.food_app

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        // Initialize Firebase Auth
        auth = Firebase.auth

        val lgnoption: TextView = findViewById(R.id.lgnoption)
        lgnoption.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java);
            startActivity(intent)

        }
        val signupbtn: Button = findViewById(R.id.signupbtn)
        signupbtn.setOnClickListener{
            performSignup()
        }

    }
//lets get signup
private fun performSignup() {
    val email: EditText = findViewById(R.id.s_email)
    val username: EditText = findViewById(R.id.s_username)
    val pwd1: EditText = findViewById(R.id.s_pwd)
    val pwd2: EditText = findViewById(R.id.s_pwd2)

    val inputMail=email.text.toString().trim()
    val userName=username.text.toString().trim()
    val pwd=pwd1.text.toString().trim()
    val rpwd=pwd2.text.toString().trim()


    if (pwd != rpwd) {
        Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
        return
    }

    auth.createUserWithEmailAndPassword(inputMail, pwd)
        .addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                // Sign-in success, move to the next activity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // Optional: finish current activity to prevent going back
            } else {
                // If sign-in fails, display a message to the user.
                Log.w(TAG, "createUserWithEmail:failure", task.exception)
                Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                // You may want to handle specific error cases here based on task.exception
            }
        }
            .addOnFailureListener{
                Toast.makeText(this,"error occured",Toast.LENGTH_SHORT).show()
            }
    }
}

