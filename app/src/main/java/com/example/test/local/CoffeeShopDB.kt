package com.example.test.local

import ProductEntity
import androidx.room.Database

@Database(entities = [ProductEntity::class], version = 1)
abstract class CoffeeShopDB {

    abstract fun productDao(): CoffeeShopDao
}