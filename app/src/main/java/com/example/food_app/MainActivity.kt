package com.example.food_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val username:EditText=findViewById(R.id.username)
        val password:EditText=findViewById(R.id.pwd);
        val lgbtn:Button=findViewById(R.id.lgbtn)
        val sgnupoption:TextView=findViewById(R.id.sgp_option)

        sgnupoption.setOnClickListener {
            val intent=Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }
        lgbtn.setOnClickListener {
            val username=username.text.toString().trim();
            val password=password.text.toString().trim()
            if(username=="user" && password=="1234"){
                Toast.makeText(this@MainActivity, username, Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this@MainActivity,"Login failed",Toast.LENGTH_SHORT).show()
            }


        }
    }
}