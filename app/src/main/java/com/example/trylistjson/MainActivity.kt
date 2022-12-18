package com.example.trylistjson


import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.window.SplashScreen
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var button2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.supportActionBar!!.hide()
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);

        button = findViewById(R.id.button)
        button.setOnClickListener{
            val intent:Intent = Intent(this, AddbookActivity::class.java)
            startActivity(intent)
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            val intent = Intent(this, ShowBookActivity::class.java)
            val options =
                ActivityOptions.makeCustomAnimation(this, R.anim.slide_out_bottom, R.anim.slide_in_bottom)
            startActivity(intent)



        }
    }

}