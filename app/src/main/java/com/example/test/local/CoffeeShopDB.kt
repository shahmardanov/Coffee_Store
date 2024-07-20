package com.example.test.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.test.model.ProductEntity

@Database(entities = [ProductEntity::class], version = 2)
abstract class CoffeeShopDB :RoomDatabase(){

    abstract fun productDao(): CoffeeShopDao
}