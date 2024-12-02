package com.example.mariejosekhalil_pet_adoption.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mariejosekhalil_pet_adoption.PetDatabase
import com.example.mariejosekhalil_pet_adoption.model.Pet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PetViewModel(application: Application) : AndroidViewModel(application) {

    private val petDao = PetDatabase.getDatabase(application).petDao()
    val allPets: LiveData<List<Pet>> = petDao.getAllPets() // LiveData for observing the pet list

    // Optional: Add a MutableLiveData for dynamic UI changes
    private val _operationStatus = MutableLiveData<String>()
    val operationStatus: LiveData<String> get() = _operationStatus

    /**
     * Add a new pet to the database.
     */
    fun addPet(pet: Pet) {
        viewModelScope.launch {
            try {
                petDao.insertPet(pet)
                _operationStatus.postValue("Pet added successfully!")
            } catch (e: Exception) {
                _operationStatus.postValue("Error adding pet: ${e.message}")
            }
        }
    }


    fun deletePet(pet: Pet) {
        viewModelScope.launch {
            try {
                petDao.deletePet(pet)
                _operationStatus.postValue("Pet removed successfully!")
            } catch (e: Exception) {
                _operationStatus.postValue("Error removing pet: ${e.message}")
            }
        }
    }



}
