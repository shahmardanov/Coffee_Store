package com.example.test.local

import ProductEntity
import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ProductEntity::class], version = 1)
abstract class CoffeeShopDB :RoomDatabase(){

    abstract fun productDao(): CoffeeShopDao
}