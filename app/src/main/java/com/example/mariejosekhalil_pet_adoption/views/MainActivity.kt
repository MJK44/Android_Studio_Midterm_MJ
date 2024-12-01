package com.example.mariejosekhalil_pet_adoption.views

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mariejosekhalil_pet_adoption.R
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

        // Set padding to adjust for system bars
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize the RecyclerView and Adapter
        petAdapter = PetAdapter(mutableListOf()) { pet ->
            // Handle item click here (optional)
            Toast.makeText(this, "Clicked on ${pet.name}", Toast.LENGTH_SHORT).show()
        }

        binding.sfRecyclerview.apply {
            layoutManager = LinearLayoutManager(this@MainActivity) // Set LinearLayoutManager
            adapter = petAdapter // Attach the adapter
        }

        // Initialize ViewModel
        petViewModel = ViewModelProvider(this).get(PetViewModel::class.java)

        // Observe LiveData and update RecyclerView
      /*  petViewModel.allPets.observe(this) { pets ->
            // Update the adapter with the new pet list
            petAdapter.updatePetList(pets)
        }*/
    }
}
