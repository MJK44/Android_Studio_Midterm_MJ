package com.example.mariejosekhalil_pet_adoption.views

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mariejosekhalil_pet_adoption.databinding.ActivityPetDetailsBinding
import com.example.mariejosekhalil_pet_adoption.model.Pet
import com.squareup.picasso.Picasso

class PetDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPetDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPetDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get Pet object from Intent
        val pet = intent.getParcelableExtra<Pet>("PET")
        if (pet != null) {
            // Bind data to the views
            binding.petName.text = pet.name
            binding.petBreed.text = pet.breed
            binding.petAge.text = pet.age
            binding.petLocation.text = pet.location
            binding.petDescription.text = pet.description
            Picasso.get().load(pet.imageUrl).into(binding.petImage)

            // Handle Contact Owner Button
           /* binding.contactButton.setOnClickListener {
                sendEmail(pet.ownerEmail, pet.name)
            }*/
        } else {
            Toast.makeText(this, "Pet details not available", Toast.LENGTH_SHORT).show()
            finish() // Close the activity if no pet data is provided
        }
    }

  /*  private fun sendEmail(ownerEmail: String, petName: String) {
        if (ownerEmail.isNotEmpty()) {
            val emailIntent = Intent(Intent.ACTION_SEND).apply {
                type = "message/rfc822"
                putExtra(Intent.EXTRA_EMAIL, arrayOf(ownerEmail))
                putExtra(Intent.EXTRA_SUBJECT, "Adoption Inquiry for $petName")
                putExtra(Intent.EXTRA_TEXT, "Hello,\n\nI'm interested in adopting $petName. Please let me know how we can proceed.")
            }
            try {
                startActivity(Intent.createChooser(emailIntent, "Send Email"))
            } catch (e: Exception) {
                Toast.makeText(this, "No email clients installed", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Owner's email is not available", Toast.LENGTH_SHORT).show()
        }
    }*/
}
