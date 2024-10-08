package com.example.test.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "coffee_table")
@Parcelize
data class CoffeeResponseItem(
    @PrimaryKey
    val id: String,
    val name: String,
    val flavor_profile: List<String>,
    val description: String,
    val grind_option: List<String>,
    val image_url: String?,
    val price: Double,
    val region: String,
    val roast_level: Int,
    val weight: Int,
    var dbId: Int = 0,
    var count: Int = 0

) : Parcelable
