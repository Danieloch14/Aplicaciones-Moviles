package com.danielAiamacana.circularview

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.squareup.picasso.Picasso

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val imageView = findViewById<ImageView>(R.id.imageView)
        Picasso.get().load("https://i.imgur.com/DvpvklR.png").into(imageView);
    }
}