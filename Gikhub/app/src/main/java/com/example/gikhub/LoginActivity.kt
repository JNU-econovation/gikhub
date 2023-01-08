package com.example.gikhub

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val goSignUp = Intent(this, SignUpActivity::class.java)
        signup_msg2.setOnClickListener{startActivity(goSignUp)}

        val goHomeIntent = Intent(this, MainActivity::class.java)
        login_btn.setOnClickListener{startActivity(goHomeIntent)}
    }
}