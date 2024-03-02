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

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val lgbtn:Button=findViewById(R.id.lgbtn)
        val sgnupoption:TextView=findViewById(R.id.sgp_option)

        auth = Firebase.auth

        sgnupoption.setOnClickListener {
            val intent=Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
        lgbtn.setOnClickListener {
            performLoginFunction()
        }
    }
    //login function
private fun performLoginFunction() {
    val username:EditText=findViewById(R.id.usermail)
        val password:EditText=findViewById(R.id.pwd)

        val usermail=username.text.toString().trim()
        val pwd=password.text.toString().trim()

        if(usermail.isEmpty()||pwd.isEmpty()){
            Toast.makeText(this,"Please fill all the fields",Toast.LENGTH_SHORT).show()
            return
        }

        auth.signInWithEmailAndPassword(usermail, pwd)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    Toast.makeText(this,"Successfully logged in",Toast.LENGTH_SHORT).show()

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }

    }
}