package com.example.loginandjoin

import android.annotation.SuppressLint
import android.content.Intent
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
    @SuppressLint("CommitPrefEdits")
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
            if(isChecked){
                val agree = 1
                val pref = getSharedPreferences("pref",0)
                val edit = pref.edit()
                edit.putInt("agree",agree)
                edit.apply()

            }
            else{
                val agree = 0
                val pref = getSharedPreferences("pref",0)
                val edit = pref.edit()
                edit.putInt("agree",agree)
                edit.apply()
            }
        }
        //회원가입
        binding.SignInPass.setOnClickListener {
            //약관동의 받아오기
            val pref = getSharedPreferences("pref",0)
            //이름확인
            if(binding.SignInName.text.trim().isNotEmpty()){
                //이메일 확인
                if(binding.SignInEmail.text.trim().isNotEmpty()){
                    //아이디 확인
                    if(binding.SignInId.text.toString().trim().isNotEmpty()){
                        //비밀번호 입력
                        if(binding.SignInPw1.text.toString().trim().isNotEmpty()){
                            //비밀번호 확인
                            if(binding.SignInPw2.text.toString().trim().isNotEmpty()){
                                //비밀번호 일치확인
                                if(binding.SignInPw1.text.toString()==binding.SignInPw2.text.toString()){
                                    //약관동의
                                    if (pref.getInt("agree", 0)==1){
                                        //회원가입 준비완료
                                        val login = Intent(this,LoginAfter::class.java)
                                        startActivity(login)
                                    }
                                    else{
                                        //약관 거부
                                        Toast.makeText(this,"약관동의를 하지 않으셨습니다", Toast.LENGTH_SHORT).show()
                                    }
                                }
                                else{
                                    Toast.makeText(this,"비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
                                }

                            }
                            else{
                                Toast.makeText(this,"비밀번호를 확인해 주세요", Toast.LENGTH_SHORT).show()
                            }
                        }
                        else{
                            Toast.makeText(this,"비밀번호를 입력해 주세요", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        Toast.makeText(this,"아이디를 입력해 주세요", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this,"이메일을 입력해 주세요", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this,"이름을 입력해 주세요", Toast.LENGTH_SHORT).show()
            }
        }
        //취소버튼
        binding.SignInCancel.setOnClickListener { finish() }
    }
}