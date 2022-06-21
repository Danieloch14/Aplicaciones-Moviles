package com.DanielAimacana.holamundo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ScroolingEmptyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrooling_empty)
        val editTextDatos = findViewById<EditText>(R.id.editTextDatos)
        val textViewMostrarTexto = findViewById<TextView>(R.id.textViewMostrarTexto)
        val buttonGuardar = findViewById<Button>(R.id.buttonGuardar)
        val buttonLimpiar = findViewById<Button>(R.id.buttonLimpiar)
        textViewMostrarTexto.movementMethod = ScrollingMovementMethod()
        buttonGuardar.setOnClickListener {
            textViewMostrarTexto.text = editTextDatos.text
        }
        buttonLimpiar.setOnClickListener {
            textViewMostrarTexto.text = ""
            editTextDatos.setText("")
        }
    }
}