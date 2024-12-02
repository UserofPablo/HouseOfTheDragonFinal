package com.example.houseofthedragon2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast

class ElectionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_elction)

        //obtener una referencia a la etiqueta de la actividad
        val tvGreeting = findViewById<TextView>(R.id.tvGreeting)
        val checkboxRaenira = findViewById<CheckBox>(R.id.raenira)
        val checkboxAegon = findViewById<CheckBox>(R.id.aegon)
        val tvInfo = findViewById<TextView>(R.id.info)
        val btnRod = findViewById<Button>(R.id.rodilla)

        //recogemos el nombre del intent
        val nombre = intent.getStringExtra("NAME")
        //creamos la cadena del saludo
        tvGreeting.apply {
            //toma el valor de la cadena "greeting" y le incorpora el valor del nombre.
            text = getString(R.string.greeting,nombre)
        }
        val updateMessage={
        when {
            checkboxRaenira.isChecked && checkboxAegon.isChecked ->
                tvInfo.text = getString(R.string.ambos)
            checkboxRaenira.isChecked ->
                tvInfo.text = getString(R.string.primer)
            checkboxAegon.isChecked ->
                tvInfo.text = getString(R.string.segundo)
            else ->
                tvInfo.text = getString(R.string.advertencia)
        }}
        checkboxRaenira.setOnCheckedChangeListener { _, _ -> updateMessage() }
        checkboxAegon.setOnCheckedChangeListener { _, _ -> updateMessage() }

        btnRod.setOnClickListener {
            val choiceMessage = when {
                checkboxRaenira.isChecked && checkboxAegon.isChecked ->
                    "mbos bandos."

                checkboxRaenira.isChecked ->
                    " Rhaenira."

                checkboxAegon.isChecked ->
                    " Aegon."

                else -> {
                    // Mostrar un mensaje si no se ha tomado ninguna decisión
                    Toast.makeText(this, getString(R.string.advertencia), Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
            }

            val intent = Intent(this, FinalActivity::class.java)
            intent.putExtra("CHOICE", choiceMessage)
            startActivity(intent)
        }
    }
}