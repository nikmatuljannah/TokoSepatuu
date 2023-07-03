package com.example.tokosepatu.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.tokosepatu.model.Shoe
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoeDao {
    @Query("SELECT * FROM 'Shoe_table' ORDER BY name ASC")
    fun getAllShoe(): Flow<List<Shoe>>

    @Insert
    suspend fun insertShoe(shoe: Shoe)

    @Delete
    suspend fun deleteShoe(shoe: Shoe)

    @Update
    fun updateShoe(shoe: Shoe)
}