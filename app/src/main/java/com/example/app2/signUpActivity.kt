package com.example.app2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.app2.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class signUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var viewModel: signUpViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        viewModel = ViewModelProvider(this).get(signUpViewModel::class.java)

        binding.SignUpButton.setOnClickListener {
            val user = binding.username.text.trim().toString()
            val email = binding.email.text.trim().toString()
            val pass = binding.password.text?.trim().toString()
            val repass = binding.ConfirmPassword.text?.trim().toString()
            val check = binding.ReadTermText.isChecked

            viewModel.checkEmailAndPassword(user,email, pass, repass,check)
        }
        binding.DescriptionSignInText.setOnClickListener {
            val intent = Intent(this, logInActivity::class.java)
            startActivity(intent)
        }
        listenerSuccessEvent()
        listenerErrorEvent()
    }
    private fun listenerSuccessEvent() {
        viewModel.isSuccessEvent.observe(this) { isSuccess ->
            if (isSuccess) {
                firebaseAuth = FirebaseAuth.getInstance()
                val email = binding.email.text.trim().toString()
                val pass = binding.password.text?.trim().toString()

                firebaseAuth.fetchSignInMethodsForEmail(email).addOnCompleteListener { it ->
                    if (it.result.signInMethods?.size  == 0) {
                        firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { it ->
                            if (it.isSuccessful) {
                                firebaseAuth.currentUser?.sendEmailVerification()?.addOnCompleteListener {
                                    if (it.isSuccessful){
                                        Toast.makeText(this, "Đăng ký thành công, vui lòng kiểm tra email để xác thực tài khoản!", Toast.LENGTH_SHORT).show()
                                    }
                                }
                                val intent = Intent(this, logInActivity::class.java)
                                startActivity(intent)
                            }
                        }
                    }
                    else{
                        Toast.makeText(this, "Tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun listenerErrorEvent() {
        viewModel.isErrorEvent.observe(this) { errMsg ->
            Toast.makeText(this, errMsg, Toast.LENGTH_SHORT).show()
        }
    }
}