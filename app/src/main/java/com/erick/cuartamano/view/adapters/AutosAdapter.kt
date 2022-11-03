package com.erick.cuartamano.view.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.erick.cuartamano.databinding.ListElementBinding
import com.erick.cuartamano.model.Auto
import com.erick.cuartamano.view.activities.MainActivity

class AutosAdapter(private val context: Context, val games: ArrayList<Auto>): RecyclerView.Adapter<AutosAdapter.ViewHolder>(){

    private val layoutInflater = LayoutInflater.from(context)

    class ViewHolder(view: ListElementBinding): RecyclerView.ViewHolder(view.root){
        val tvTitle = view.tvTitle
        val tvGenre = view.tvGenre
        val tvDeveloper = view.tvDeveloper
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ListElementBinding.inflate(layoutInflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.tvTitle.text = games[position].marca
        holder.tvGenre.text = games[position].año
        holder.tvDeveloper.text = games[position].modelo


        //Para los clicks de cada elemento viewholder

        holder.itemView.setOnClickListener {
            //Manejar el click
            if(context is MainActivity) context.selectedGame(games[position])
        }

    }

    override fun getItemCount(): Int {
        return games.size
    }

}