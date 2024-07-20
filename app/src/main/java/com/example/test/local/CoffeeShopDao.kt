package com.example.test.local

import ProductEntity
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

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
}