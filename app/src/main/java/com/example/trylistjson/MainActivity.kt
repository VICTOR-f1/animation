package com.example.trylistjson


import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import android.widget.Button
import android.widget.TextView
import android.window.SplashScreen
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var button2: Button
    private lateinit var textv: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.supportActionBar!!.hide()
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
        val  mEnlargeAnimation = AnimationUtils.loadAnimation(this, R.anim.enlarge);
        val  mEnlargeAnimation2 = AnimationUtils.loadAnimation(this, R.anim.enlarge2);
        val  mRotateAnimation = AnimationUtils.loadAnimation(this, R.anim.rotate);

        button = findViewById(R.id.button)
        textv = findViewById(R.id.textv)
        textv.startAnimation(mRotateAnimation)
        button.startAnimation(mEnlargeAnimation2)
        button.setOnClickListener{
            val intent:Intent = Intent(this, AddbookActivity::class.java)
            startActivity(intent)
        }

        button2 = findViewById(R.id.button2)
        button2.startAnimation(mEnlargeAnimation);

        button2.setOnClickListener {
            val intent = Intent(this, ShowBookActivity::class.java)
            startActivity(intent)



        }
    }


}