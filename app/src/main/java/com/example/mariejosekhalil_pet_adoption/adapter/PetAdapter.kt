package com.example.mariejosekhalil_pet_adoption.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mariejosekhalil_pet_adoption.R
import com.example.mariejosekhalil_pet_adoption.databinding.ItemPetBinding
import com.example.mariejosekhalil_pet_adoption.model.Pet
import com.example.mariejosekhalil_pet_adoption.views.PetDetailsActivity
import com.squareup.picasso.Picasso

class PetAdapter(
    private var petList: MutableList<Pet>, // Main list of pets
    private val onPetRemoved: (Pet) -> Unit // Callback for remove functionality
) : RecyclerView.Adapter<PetAdapter.PetViewHolder>() {

    private var filteredPetList: MutableList<Pet> = ArrayList(petList) // Filtered list for search

    inner class PetViewHolder(private val binding: ItemPetBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pet: Pet) {
            binding.petName.text = pet.name
            binding.petBreed.text = pet.breed
            binding.petCity.text = pet.location.split(",")[0]
            binding.petDistrict.text = pet.location.split(",").getOrNull(1) ?: "Unknown"

            // Load the pet's image using Picasso
            if (pet.imageUrl.isNotEmpty()) {
                Picasso.get().load(pet.imageUrl)
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_error)
                    .into(binding.petImage)
            } else {
                binding.petImage.setImageResource(R.drawable.ic_placeholder)
            }

            // Click listener for the item
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, PetDetailsActivity::class.java).apply {
                    putExtra("PET", pet)
                }
                binding.root.context.startActivity(intent)
            }

            // Remove button listener
            binding.removeButton.setOnClickListener {
                onPetRemoved(pet) // Call the remove callback
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PetViewHolder {
        val binding = ItemPetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PetViewHolder, position: Int) {
        holder.bind(filteredPetList[position])
    }

    override fun getItemCount() = filteredPetList.size

    // Update the pet list
    fun updatePetList(newList: List<Pet>) {
        petList.clear()
        petList.addAll(newList)
        filteredPetList = ArrayList(petList)
        notifyDataSetChanged()
    }

    // Filter method for search functionality
    fun filter(query: String) {
        filteredPetList = if (query.isEmpty()) {
            ArrayList(petList)
        } else {
            petList.filter { pet ->
                pet.name.contains(query, ignoreCase = true) ||
                        pet.breed.contains(query, ignoreCase = true) ||
                        pet.location.contains(query, ignoreCase = true)
            }.toMutableList()
        }
        notifyDataSetChanged()
    }
}
