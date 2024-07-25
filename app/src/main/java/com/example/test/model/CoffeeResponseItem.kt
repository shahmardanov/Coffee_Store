package com.example.test.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "coffee_table")
@Parcelize
data class CoffeeResponseItem(
    @PrimaryKey val id: Int,
    val name: String,
    val flavor_profile: List<String>,
    val _id: String,
    val description: String,
    val grind_option: List<String>,
    val image_url: String,
    val price: Double,
    val region: String,
    val roast_level: Int,
    val weight: Int
) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    var dbId:Int=0
    var count:Int=0
}