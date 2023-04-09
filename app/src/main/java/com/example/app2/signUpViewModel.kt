package com.example.app2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

enum class Error {
    ERROR_EMAIL,
    ERROR_PASSWORD,
}

class Resp(val isSuccess: Boolean, val error: Error?)

class signUpViewModel : ViewModel() {

    private var _isSuccessEvent: MutableLiveData<Boolean> = MutableLiveData()
    val isSuccessEvent: LiveData<Boolean>
        get() = _isSuccessEvent

    private var _isErrorEvent: MutableLiveData<String> = MutableLiveData()
    val isErrorEvent: LiveData<String>
        get() = _isErrorEvent

    fun checkEmailAndPassword(user:String, email: String, password: String, repassword: String, check: Boolean) {
        //kiem tra format email

        if (user.isEmpty() || email.isEmpty() || password.isEmpty() || repassword.isEmpty()){
            _isErrorEvent.postValue("Không được để trống!")
            return
        }

        val isValidEmail = isEmailValid(email)
        if (!isValidEmail) {
            _isErrorEvent.postValue("Tài khoản Email không hợp lệ!")
            return
        }
        //password length > 8 && < 10
        val isValidPassword = isPasswordValid(password)
        if (!isValidPassword) {
            _isErrorEvent.postValue("Mật khẩu không hợp lệ!")
            return
        }
        //check password and repassword
        val isCorrectPassword = isPasswordCorrect(password,repassword)
        if (!isCorrectPassword) {
            _isErrorEvent.postValue("Mật khẩu không trùng khớp!")
            return
        }
        //check read term
        if (!check){
            _isErrorEvent.postValue("Vui lòng .....")
            return
        }

        //call API login
        //val isSuccess = DataRepo.login(email = email, pass = password)

        _isSuccessEvent.postValue(true)
    }

    private fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    private fun isPasswordValid(password: String): Boolean {
        val passwordREGEX = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")
        return passwordREGEX.matcher(password).matches()
    }

    private fun isPasswordCorrect(password: String,repassword: String):Boolean {
        if (password == repassword)
            return true
        return false
    }


}