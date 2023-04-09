package com.example.app2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.app2.databinding.ActivityLogInBinding
import com.google.firebase.auth.FirebaseAuth

class logInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_log_in)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.loginButton.setOnClickListener {
            val email = binding.email.text.trim().toString()
            val pass = binding.password.text?.trim().toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val user = firebaseAuth.currentUser
                        if (user?.isEmailVerified == true) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this, "Vui lòng xác thực tài khoản!", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "Email hoặc mật khẩu không đúng!", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Không được để trống !", Toast.LENGTH_SHORT).show()

            }
        }
        binding.signupButton.setOnClickListener {
            val intent = Intent(this, signUpActivity::class.java)
            startActivity(intent)
        }
        binding.forgotPasswordText.setOnClickListener {
            val intent = Intent(this, forgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }
    override fun onStart() {
        super.onStart()
        /*val user = firebaseAuth.currentUser
        if (user != null) {
            if (user.isEmailVerified){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
        }*/
    }
}