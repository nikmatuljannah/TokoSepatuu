package com.example.tokosepatu.repository

import com.example.tokosepatu.dao.ShoeDao
import com.example.tokosepatu.model.Shoe
import kotlinx.coroutines.flow.Flow

class ShoeRepository(private val shoeDao: ShoeDao) {
    val allShoes: Flow<List<Shoe>> = shoeDao.getAllShoe()
    suspend fun insertShoe(shoe: Shoe){
        shoeDao.insertShoe(shoe)
    }

    suspend fun deleteShoe(shoe: Shoe){
        shoeDao.deleteShoe(shoe)
    }

    suspend fun updateShoe(shoe: Shoe){
        shoeDao.updateShoe(shoe)
    }

}