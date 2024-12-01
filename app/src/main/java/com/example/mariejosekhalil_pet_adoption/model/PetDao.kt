package com.example.mariejosekhalil_pet_adoption.model

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mariejosekhalil_pet_adoption.model.Pet

@Dao
interface PetDao {
    // Insert a new pet into the database
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPet(pet: Pet)

    // Fetch all pets from the database
    @Query("SELECT * FROM pet_table ORDER BY id DESC")
    fun getAllPets(): LiveData<List<Pet>>

    // Delete a specific pet from the database
    @Delete
    suspend fun deletePet(pet: Pet)

    @Query("SELECT * FROM pet_table WHERE userId = :userId ORDER BY id DESC")
    fun getUserPets(userId: String): LiveData<List<Pet>>

}
