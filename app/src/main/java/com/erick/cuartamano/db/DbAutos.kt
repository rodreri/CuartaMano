package com.erick.cuartamano.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.erick.cuartamano.model.Auto

class DbAutos(private val context: Context): DbHelper(context) {
    //Aquí se van a implementar las operaciones CRUD (Create, Read, Update and Delete)

    fun insertGame(marca: String, año:String, modelo: String): Long{
        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase

        var id: Long = 0

        try{
            val values = ContentValues()

            values.put("marca", marca)
            values.put("año", año)
            values.put("modelo", modelo)

            id = db.insert("autos", null, values)

        }catch(e: Exception){
            //Manejo de excepción
        }finally {
            db.close()
        }

        return id
    }

    fun getGames(): ArrayList<Auto>{
        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase

        var listGames = ArrayList<Auto>()
        var gameTmp: Auto? = null
        var cursorGames: Cursor? = null

        cursorGames = db.rawQuery("SELECT * FROM AUTOS", null)

        if(cursorGames.moveToFirst()){
            do{
                gameTmp = Auto(cursorGames.getInt(0), cursorGames.getString(1), cursorGames.getString(2), cursorGames.getString(3))
                listGames.add(gameTmp)
            }while(cursorGames.moveToNext())
        }

        cursorGames.close()

        return listGames
    }

    fun getGame(id: Int): Auto?{
        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase

        var game: Auto? = null

        var cursorGames: Cursor? = null

        cursorGames = db.rawQuery("SELECT * FROM AUTOS WHERE id = $id LIMIT 1", null)

        if(cursorGames.moveToFirst()){
            game = Auto(cursorGames.getInt(0), cursorGames.getString(1), cursorGames.getString(2), cursorGames.getString(3))
        }

        cursorGames.close()

        return game
    }

    fun updateGame(id: Int, title: String, genre: String, developer: String): Boolean{
        var banderaCorrecto = false

        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase

        try{
            db.execSQL("UPDATE AUTOS SET marca = '$title', año = '$genre', modelo = '$developer' WHERE id = $id")
            banderaCorrecto = true
        }catch(e: Exception){
            //Manejo de la excepción
        }finally {
            db.close()
        }

        return banderaCorrecto
    }

    fun deleteGame(id: Int): Boolean{
        var banderaCorrecto = false

        val dbHelper = DbHelper(context)
        val db = dbHelper.writableDatabase

        try{
            db.execSQL("DELETE FROM AUTOS WHERE id = $id")
            banderaCorrecto = true
        }catch(e: Exception){

        }finally {
            db.close()
        }

        return banderaCorrecto
    }
}