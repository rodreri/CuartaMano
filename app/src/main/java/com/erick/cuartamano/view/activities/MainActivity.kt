package com.erick.cuartamano.view.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.erick.cuartamano.R
import com.erick.cuartamano.databinding.ActivityMainBinding
import com.erick.cuartamano.db.DbAutos
import com.erick.cuartamano.model.Auto
import com.erick.cuartamano.view.adapters.AutosAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var listGames: ArrayList<Auto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /*
        //Código para probar que se generó correctamente la BD
        val dbHelper = DbHelper(this)
        val db = dbHelper.writableDatabase
        if(db!=null){
            Toast.makeText(this, "La BD fue creada exitosamente", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Error al crear la BD", Toast.LENGTH_SHORT).show()
        }*/

        val dbGames = DbAutos(this)

        listGames = dbGames.getGames()

        val gamesAdapter = AutosAdapter(this, listGames)
        binding.rvGames.layoutManager = LinearLayoutManager(this)
        binding.rvGames.adapter = gamesAdapter

        if(listGames.size == 0) binding.tvSinRegistros.visibility = View.VISIBLE
        else binding.tvSinRegistros.visibility = View.INVISIBLE

    }

    fun click(view: View) {
        startActivity(Intent(this, InsertActivity::class.java))
        finish()
    }

    fun selectedGame(game: Auto){
        //Manejamos el click del elemento en el recycler view
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("ID", game.id)
        startActivity(intent)
        finish()
    }
}