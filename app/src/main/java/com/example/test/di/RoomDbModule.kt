package com.example.test.di

import android.content.Context
import androidx.room.Room
import com.example.test.local.CoffeeShopDB
import com.example.test.local.CoffeeShopDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDbModule {

    @Provides
    @Singleton
    fun provideRoomDb(@ApplicationContext context: Context): CoffeeShopDB {
        return Room.databaseBuilder(context, CoffeeShopDB::class.java, "CoffeeShopDB")
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideDataBase(coffeeShopDB: CoffeeShopDB): CoffeeShopDao {
        return coffeeShopDB.productDao()
    }
}