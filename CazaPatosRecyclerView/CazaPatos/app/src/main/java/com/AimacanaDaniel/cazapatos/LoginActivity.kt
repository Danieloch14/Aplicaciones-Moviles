package com.AimacanaDaniel.cazapatos

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.AimacanaDaniel.cazapatos.Interfaces.FileHandler
import com.AimacanaDaniel.cazapatos.R


class LoginActivity : AppCompatActivity() {
    lateinit var manejadorArchivo: FileHandler
    lateinit var editTextEmail:EditText
    lateinit var editTextPassword:EditText
    lateinit var buttonLogin:Button
    lateinit var buttonNewUser:Button
    lateinit var mediaPlayer:MediaPlayer
    lateinit var checkBoxRecordarme: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //Inicialización de variables
        manejadorArchivo = FileExternalManager(this) // Archivo externo
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        buttonLogin = findViewById(R.id.buttonLogin)
        buttonNewUser = findViewById(R.id.buttonNewUser)
        checkBoxRecordarme = findViewById(R.id.checkBoxRecordarme)
        // Leer datos
        leerDatosDePreferencias()
        //Eventos clic
        buttonLogin.setOnClickListener {
            val email = editTextEmail.text.toString()
            val clave = editTextPassword.text.toString()
            //Validaciones de datos requeridos y formatos
            if(!ValidarDatosRequeridos())
                return@setOnClickListener
            if(checkBoxRecordarme.isChecked){
                guardarDatosEnPreferencias()
            }
            //Si pasa validación de datos requeridos, ir a pantalla principal
            val intencion = Intent(this, MainActivity::class.java)
            intencion.putExtra(EXTRA_LOGIN, email)
            startActivity(intencion)
        }
        buttonNewUser.setOnClickListener{

        }
        mediaPlayer=MediaPlayer.create(this, R.raw.title_screen)
        mediaPlayer.start()
        mediaPlayer.isLooping = true
    }

    private fun ValidarEmail(email:String):Boolean{
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

    private fun ValidarDatosRequeridos():Boolean{
        val email = editTextEmail.text.toString()
        val clave = editTextPassword.text.toString()
        if (email.isEmpty()) {
            editTextEmail.setError("El email es obligatorio")
            editTextEmail.requestFocus()
            return false
        }
        if (clave.isEmpty()) {
            editTextPassword.setError("La clave es obligatoria")
            editTextPassword.requestFocus()
            return false
        }
        if(!ValidarEmail(email)){
            editTextEmail.setError("El email ingresado no es válido")
            editTextEmail.requestFocus()
            return false
        }
        if (clave.length < 8) {
            editTextPassword.setError("La clave debe tener al menos 8 caracteres")
            editTextPassword.requestFocus()
            return false
        }
        return true
    }

    private fun leerDatosDePreferencias(){
        val listadoLeido = manejadorArchivo.ReadInformation()
        if (listadoLeido.first != ""){
            checkBoxRecordarme.isChecked = true
        }
        editTextEmail.setText(listadoLeido.first)
        editTextPassword.setText(listadoLeido.second)
    }

    private fun guardarDatosEnPreferencias() {
        val email = editTextEmail.text.toString()
        val password = editTextPassword.text.toString()
        val listadoAGrabar:Pair<String, String>
        listadoAGrabar = email to password
        manejadorArchivo.SaveInformation(listadoAGrabar)
    }

    override fun onDestroy() {
        mediaPlayer.release()
        super.onDestroy()
    }
}
