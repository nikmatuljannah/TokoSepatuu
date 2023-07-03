package com.example.tokosepatu.application

import android.app.Application
import com.example.tokosepatu.repository.ShoeRepository

class ShoeApp: Application() {
    val database by lazy { ShoeDatabase.getDatabase(this) }
    val repository by lazy { ShoeRepository(database.ShoeDao()) }

}