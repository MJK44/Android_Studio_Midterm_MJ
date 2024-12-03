package com.example.mariejosekhalil_pet_adoption.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mariejosekhalil_pet_adoption.R

class BreedAdapter(
    private val breeds: List<String>,
    private val onItemClick: (String) -> Unit
) : RecyclerView.Adapter<BreedAdapter.BreedViewHolder>() {

    class BreedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val breedName: TextView = itemView.findViewById(R.id.breedName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_breed, parent, false)
        return BreedViewHolder(view)
    }

    override fun onBindViewHolder(holder: BreedViewHolder, position: Int) {
        val breed = breeds[position]
        holder.breedName.text = breed
        holder.itemView.setOnClickListener { onItemClick(breed) }
    }

    override fun getItemCount(): Int = breeds.size
}
