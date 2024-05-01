package com.example.loginandjoin

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginandjoin.databinding.ActivityPayPageBinding
import org.w3c.dom.Text


class PayPage : AppCompatActivity() {
    private lateinit var binding: ActivityPayPageBinding
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPayPageBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_pay_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
            val costTable = findViewById<TextView>(R.id.ProductPrice)
            val costOrigin : Int = 100000
            costTable.text = costOrigin.toString()
            val totalTable = findViewById<TextView>(R.id.TotalPriceInt)
            totalTable.text = costOrigin.toString()

            //쿠폰 및 포인트
            val items = arrayOf("쿠폰","10% 할인 테스트","5,000원 할인 테스트")
            val selectAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item, items)
            val select = findViewById<Spinner>(R.id.CouponeSelect)
            select.adapter = selectAdapter
            val pointHave = findViewById<TextView>(R.id.CouponePointHave)
            val points : Int = 5000
            pointHave.text = points.toString() + " 포인트 보유중"

            //쿠폰 스피너 선택
            val totalcouponeTable = findViewById<TextView>(R.id.TotalCouponeInt)
            select.onItemSelectedListener= object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when(position){
                        0->{
                            //아무것도 선택안함
                        }
                        1->{
                            //10% 할인 선택
                            totalcouponeTable.text =  (costOrigin/10).toString()
                        }
                        2->{
                            //5천원 할인 선택
                            totalcouponeTable.text = 5000.toString()
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }
            //포인트 등록
            val totalpointTable = findViewById<TextView>(R.id.TotalPointInt)
            val pointbutton = findViewById<Button>(R.id.CouponePointInput)
            val pointInput = findViewById<EditText>(R.id.CouponePointText)
            pointbutton.setOnClickListener {
                if (pointInput.text.toString().toInt()>points){
                    Toast.makeText(this,"${points} 포인트까지 사용할 수 있습니다",Toast.LENGTH_SHORT).show()
                    totalpointTable.text = "TotalPointInt"
                }else{
                    totalpointTable.text = pointInput.text.toString()
                }
            }

            //배달비
            val deliverTable = findViewById<TextView>(R.id.TotalDeliverInt)
            deliverTable.text = 3000.toString()

            //총합
            val totalAllTable = findViewById<TextView>(R.id.TotalAllInt)
            val totalAllCost : Int = costOrigin -
                    try{ totalcouponeTable.text.toString().toInt() }
                     catch (e: NumberFormatException){
                         0
                     } -
                    try{ totalpointTable.text.toString().toInt() }
                    catch (e: NumberFormatException){
                        0
                    } +
                    deliverTable.text.toString().toInt()
            totalAllTable.text = totalAllCost.toString()


    }
}