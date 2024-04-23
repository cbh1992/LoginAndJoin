package com.example.loginandjoin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener
import com.example.loginandjoin.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //이름
        val name = binding.SignInName.text
        //이메일
        val email = binding.SignInEmail.text
        //아이디
        val id = binding.SignInId.text
        //이메일 사용
        binding.SignInEailtoId.setOnCheckedChangeListener{buttonView, isChecked ->
            if(isChecked){val checkid = binding.SignInEmail.text
                binding.SignInId.text = checkid}
            else{binding.SignInId.text = null}
        }
        //비밀번호 입력
        val pw1 = binding.SignInPw1.text
        binding.SignInPw1.addTextChangedListener(object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.SignInPw1.text.toString().trim().isNotEmpty()){
                    binding.SignInPw1Text.visibility = View.GONE
                }
                else{binding.SignInPw1Text.visibility = View.VISIBLE}
            }
            override fun afterTextChanged(s: Editable?) {
            }
        })
        //비밀번호 확인
        val pw2 = binding.SignInPw2.text
        binding.SignInPw2.addTextChangedListener (object:TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (binding.SignInPw2.text.toString().trim().isNotEmpty()){
                    binding.SignInPw2Text.visibility = View.GONE
                }
                else{binding.SignInPw2Text.visibility = View.VISIBLE}

            }
            override fun afterTextChanged(s: Editable?) {

            }
        })

        //약관 동의
        binding.SignInAgree.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){Toast.makeText(this,"동의 확인",Toast.LENGTH_SHORT).show()}
        }
        //회원가입

        //취소버튼
        binding.SignInCancel.setOnClickListener { finish() }
    }
}