package com.example.app2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.app2.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class forgotPasswordActivity : AppCompatActivity() {
    private lateinit var binding:ActivityForgotPasswordBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgot_password)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.ForgotPassButton.setOnClickListener {
            firebaseAuth.sendPasswordResetEmail(binding.email.text.trim().toString())
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(
                            this,
                            "Kiểm tra Email để thực hiện đặt lại mât khẩu!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }else{
                        Toast.makeText(
                            this,
                            "Lỗi khi thực hiện gửi Mail!",
                            Toast.LENGTH_SHORT
                        ).show()                    }                    }
        }
        binding.DescriptionSignInText.setOnClickListener {
            val intent = Intent(this, logInActivity::class.java)
            startActivity(intent)
        }
    }
}