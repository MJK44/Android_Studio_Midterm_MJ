package com.example.mariejosekhalil_pet_adoption.views

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mariejosekhalil_pet_adoption.adapter.PetAdapter
import com.example.mariejosekhalil_pet_adoption.databinding.ActivityMainBinding
import com.example.mariejosekhalil_pet_adoption.viewmodel.PetViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var petViewModel: PetViewModel
    private lateinit var petAdapter: PetAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize the RecyclerView and Adapter
        petAdapter = PetAdapter(mutableListOf()) { pet ->
            // Handle remove pet callback
            petViewModel.deletePet(pet) // Remove the pet from the database
            Toast.makeText(this, "${pet.name} removed", Toast.LENGTH_SHORT).show()
        }
        binding.addPetButton.setOnClickListener {
            val intent = Intent(this, AddPetActivity::class.java)
            startActivity(intent)
        }

        // Set up RecyclerView
        binding.sfRecyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity) // Set layout manager
            adapter = petAdapter // here I attached the adapter with the recyclerview
        }

        // Initialize ViewModel
        petViewModel = ViewModelProvider(this).get(PetViewModel::class.java)

        // Observe the pet list LiveData
        petViewModel.allPets.observe(this) { pets ->
            // Update the adapter whenever the pet list changes
            petAdapter.updatePetList(pets)
        }

        // Optional: Set up the SearchView for filtering
        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                petAdapter.filter(query ?: "") // Filter based on query
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                petAdapter.filter(newText ?: "") // Update filter as user types
                return true
            }
        })
    }
}
