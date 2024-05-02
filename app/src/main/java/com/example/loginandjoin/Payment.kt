package com.example.loginandjoin

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tosspayments.paymentsdk.view.PaymentMethod

class Payment : AppCompatActivity() {


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_payment)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }
        val card = findViewById<Button>(R.id.PayCard)
        card.setOnClickListener {
            val cardpay = Intent(this,PaymentCard::class.java)
            startActivity(cardpay)
        }
    }
    companion object {
        private const val TEST_KEY = "test_ck_DLJOpm5Qrldyn50kjaoPrPNdxbWn"
        private const val TEST_CUSTOMER_KEY = "test_sk_KNbdOvk5rk1zKEnNLOl43n07xlzm"
    }
}