package com.example.mariejosekhalil_pet_adoption.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mariejosekhalil_pet_adoption.PetDatabase
import com.example.mariejosekhalil_pet_adoption.model.Pet
import kotlinx.coroutines.launch

class PetViewModel(application: Application) : AndroidViewModel(application) {

    private val petDao = PetDatabase.getDatabase(application).petDao()
    val allPets: LiveData<List<Pet>> = petDao.getAllPets()


    fun addPet(pet: Pet) {
        viewModelScope.launch {
            petDao.insertPet(pet)
        }
    }


    fun deletePet(pet: Pet) {
        viewModelScope.launch {
            petDao.deletePet(pet)
        }
    }
}
