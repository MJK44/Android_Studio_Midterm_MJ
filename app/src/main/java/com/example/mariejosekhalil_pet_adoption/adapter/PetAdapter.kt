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

class   PetAdapter(
    private var petList: MutableList<Pet>, // Make this mutable to allow modification
    private val onPetRemoved: (Pet) -> Unit // Callback for removal
) : RecyclerView.Adapter<PetAdapter.PetViewHolder>() {

    private var filteredPetList: MutableList<Pet> = ArrayList(petList) // Copy for filtering

    inner class PetViewHolder(private val binding: ItemPetBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pet: Pet) {
            binding.petName.text = pet.name
            binding.petBreed.text = pet.breed
            binding.petCity.text = pet.location.split(",")[0] // Extract city from location
            binding.petDistrict.text = pet.location.split(",").getOrNull(1) ?: "Unknown" // Extract district if available

            // Load pet image using Picasso
            if (pet.imageUrl.isNotEmpty()) {
                Picasso.get().load(pet.imageUrl).placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_error).into(binding.petImage)
            } else {
                binding.petImage.setImageResource(R.drawable.ic_placeholder)
            }

            // Set click listener for the item
            binding.root.setOnClickListener {
                val intent = Intent(binding.root.context, PetDetailsActivity::class.java).apply {
                    putExtra("PET", pet)
                }
                binding.root.context.startActivity(intent)
            }

            // Handle remove button click
            binding.removeButton.setOnClickListener {
                onPetRemoved(pet) // Call the callback to handle removal
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

    // Method to filter the list
    fun filter(query: String) {
        filteredPetList = if (query.isEmpty()) {
            ArrayList(petList) // Show all pets
        } else {
            petList.filter { pet ->
                pet.name.contains(query, ignoreCase = true) ||
                        pet.breed.contains(query, ignoreCase = true) ||
                        pet.location.contains(query, ignoreCase = true)
            }.toMutableList()
        }
        notifyDataSetChanged()
    }

    // Method to remove a pet
    fun removePet(pet: Pet) {
        petList.remove(pet) // Remove from original list
        filteredPetList.remove(pet) // Remove from filtered list
        notifyDataSetChanged()
    }
}
