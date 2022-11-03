package com.erick.cuartamano.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.erick.cuartamano.R
import com.erick.cuartamano.databinding.ActivityInsertBinding
import com.erick.cuartamano.db.DbAutos

class InsertActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityInsertBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val spinner: Spinner = binding.marcaSpinner
        spinner.onItemSelectedListener = this
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this, R.array.marcas_array, android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }
    }

    fun click(view: View) {
        val dbGames = DbAutos(this)
        with(binding){
            Toast.makeText(this@InsertActivity, marcaSpinner.selectedItem.toString(), Toast.LENGTH_SHORT).show()
            when{
                marcaSpinner.selectedItem.toString().isEmpty() -> {
                    Toast.makeText(this@InsertActivity, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show()
                }
                tietGenre.text.toString().isEmpty() -> {
                    tietGenre.error = "No puede quedar vacío"
                    Toast.makeText(this@InsertActivity, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show()
                }
                tietDeveloper.text.toString().isEmpty() -> {
                    tietDeveloper.error = "No puede quedar vacío"
                    Toast.makeText(this@InsertActivity, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    //Realizamos la inserción
                    val id = dbGames.insertGame(marcaSpinner.selectedItem.toString(), tietGenre.text.toString(), tietDeveloper.text.toString())

                    if(id>0){
                        Toast.makeText(this@InsertActivity, "Registro guardado exitosamente", Toast.LENGTH_SHORT).show()
//                            marcaSpinner.set
                        tietGenre.setText("")
                        tietDeveloper.setText("")
                        marcaSpinner.requestFocus()
                    }else{
                        Toast.makeText(this@InsertActivity, "Error al guardar el registro", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }


    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}