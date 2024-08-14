package com.example.test.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.test.model.CoffeeResponseItem
import com.example.test.model.ProductEntity
import com.example.test.util.Converters

@Database(entities = [CoffeeResponseItem::class], version = 6,exportSchema = false)
@TypeConverters(Converters::class)
abstract class CoffeeShopDB :RoomDatabase(){

    abstract fun productDao(): CoffeeShopDao
}