package com.example.test.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverter
import androidx.room.Update
import com.example.test.model.CoffeeResponseItem
import java.util.Arrays


@Dao
interface CoffeeShopDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addProducts(products: CoffeeResponseItem)

    @Query("SELECT * FROM coffee_table")
    suspend fun getAllProducts(): List<CoffeeResponseItem>

    @Query("Select * from coffee_table where id = :id")
    suspend fun getProductById(id: String): CoffeeResponseItem?

    @Query("DELETE FROM coffee_table")
    suspend fun deleteBasket()

    @Update
    suspend fun updateBasket(coffeeResponseItem: CoffeeResponseItem)

    @Query("Delete from coffee_table")
    suspend fun deleteAll()


}