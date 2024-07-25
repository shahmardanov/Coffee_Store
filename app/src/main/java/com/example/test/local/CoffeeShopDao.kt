package com.example.test.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.test.model.CoffeeResponseItem
import com.example.test.model.ProductEntity

@Dao
interface CoffeeShopDao {

    @Insert
    suspend fun addProduct(vararg product: ProductEntity): List<Long>

    @Query("select * from product_table")
    suspend fun getAllProducts(): List<ProductEntity>

    @Query("select *from product_table where id = :id")
    suspend fun getProductById(id: Long): ProductEntity?

    @Delete
    suspend fun deleteProduct(product: ProductEntity)

    @Insert
    suspend fun insertBasket(coffeeItem: CoffeeResponseItem)

    @Query("Select * from basket_table")
    suspend fun readAllBasket():List<CoffeeResponseItem>

    @Update
    suspend fun updateBasket(productResponseModel: CoffeeResponseItem)

    @Query("DELETE FROM basket_table")
    suspend fun deleteBasket()
}