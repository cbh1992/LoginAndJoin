package com.example.loginandjoin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isGone

class LoginAfter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login_after)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val payment = findViewById<Button>(R.id.Payment)
        val logout = findViewById<Button>(R.id.Logout)
        val text = findViewById<TextView>(R.id.HowtoLogin)

        if(intent.hasExtra("LoginType")){
            text.text = intent.getStringExtra("LoginType")
        }else{
            text.isGone
        }

        payment.setOnClickListener {
            val pay = Intent(this,PayPage::class.java)
            startActivity(pay)
        }
        logout.setOnClickListener { 
            //로그아웃 코드 필요
        }
    }
}