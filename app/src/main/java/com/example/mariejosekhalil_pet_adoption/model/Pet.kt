package com.example.mariejosekhalil_pet_adoption.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
/*we are going to ensure that inmageurl is correctly sstored and retreived in firebase stoorage*/
@Entity(tableName = "pet_table")
data class Pet(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val breed: String,
    val age: String,
    val location: String,
    val description: String,
    val imageUrl: String,
    val userId: String,
   /* val ownerEmail: String*/ // New field for owner's email*/
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
      /*  parcel.readString().toString(),*/

    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(breed)
        parcel.writeString(age)
        parcel.writeString(location)
        parcel.writeString(description)
        parcel.writeString(imageUrl)
        parcel.writeString(userId)
   /*     parcel.writeString(ownerEmail)*/
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Pet> {
        override fun createFromParcel(parcel: Parcel): Pet {
            return Pet(parcel)
        }

        override fun newArray(size: Int): Array<Pet?> {
            return arrayOfNulls(size)
        }
    }
}
