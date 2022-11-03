package com.erick.cuartamano.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.erick.cuartamano.databinding.ActivityEditBinding
import com.erick.cuartamano.db.DbAutos
import com.erick.cuartamano.model.Auto

class EditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditBinding

    private lateinit var dbGames: DbAutos
    var game: Auto? = null
    var id = 0
    var aux = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras

        if(bundle!=null){
            id = bundle.getInt("ID",0)
        }

        dbGames = DbAutos(this)

        game = dbGames.getGame(id)

        game?.let{ game ->
            with(binding){
                aux = game.marca
                tietGenre.setText(game.año)
                tietDeveloper.setText(game.modelo)

            }
        }
    }

    fun click(view: View) {
        val dbGames = DbAutos(this)
        with(binding){
//            Toast.makeText(this@EditActivity, marcaSpinner.selectedItem.toString(), Toast.LENGTH_SHORT).show()
            when{
//                marcaSpinner.selectedItem.toString().isEmpty() -> {
//                    Toast.makeText(this@EditActivity, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show()
//                }
                tietGenre.text.toString().isEmpty() -> {
                    tietGenre.error = "No puede quedar vacío"
                    Toast.makeText(this@EditActivity, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show()
                }
                tietDeveloper.text.toString().isEmpty() -> {
                    tietDeveloper.error = "No puede quedar vacío"
                    Toast.makeText(this@EditActivity, "Por favor llene todos los campos", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    if(dbGames.updateGame(id, aux, tietGenre.text.toString(), tietDeveloper.text.toString())){
                        Toast.makeText(this@EditActivity, "Registro actualizado exitosamente", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@EditActivity, DetailsActivity::class.java)
                        intent.putExtra("ID", id)
                        startActivity(intent)
                        finish()
                    }else{
                        Toast.makeText(this@EditActivity, "Error al actualizar el registro", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }
    }


    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, DetailsActivity::class.java))
    }
}