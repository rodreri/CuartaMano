package com.erick.cuartamano.view.activities

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.erick.cuartamano.R
import com.erick.cuartamano.databinding.ActivityDetailsBinding
import com.erick.cuartamano.db.DbAutos
import com.erick.cuartamano.model.Auto

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    private lateinit var dbGames: DbAutos
    var game: Auto? = null
    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras

        if (bundle != null) {
            id = bundle.getInt("ID", 0)
        }

        dbGames = DbAutos(this)

        game = dbGames.getGame(id)

        game?.let { game ->
            with(binding) {
//                tietTitle.setText(game.marca)
                tietGenre.setText(game.año)
                tietDeveloper.setText(game.modelo)

                //para que se desactive el teclado en los edittext
//                tietTitle.inputType = InputType.TYPE_NULL
                tietGenre.inputType = InputType.TYPE_NULL
                tietDeveloper.inputType = InputType.TYPE_NULL
            }
        }
    }

    fun click(view: View) {
        when (view.id) {
            R.id.btnEdit -> {
                val intent = Intent(this, EditActivity::class.java)
                intent.putExtra("ID", id)
                startActivity(intent)
                finish()
            }
            R.id.btnDelete -> {
                //Aquí iría el código para borrar el registro

                AlertDialog.Builder(this)
                    .setTitle("Confirmación")
                    .setMessage("¿Realmente deseas eliminar el auto ${game?.modelo}?")
                    .setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, which ->
                        if (dbGames.deleteGame(id)) {
                            Toast.makeText(
                                this@DetailsActivity,
                                "Registro eliminado exitosamente",
                                Toast.LENGTH_SHORT
                            ).show()
                            startActivity(Intent(this@DetailsActivity, MainActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(
                                this@DetailsActivity,
                                "No se pudo realizar la eliminación",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })
                    .setNegativeButton(
                        "Cancelar",
                        DialogInterface.OnClickListener { dialog, which ->
                            dialog.dismiss()
                        })
                    .show()

            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }
}