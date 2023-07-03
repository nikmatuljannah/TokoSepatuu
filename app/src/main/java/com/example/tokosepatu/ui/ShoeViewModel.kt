package com.example.tokosepatu.ui


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.tokosepatu.model.Shoe
import com.example.tokosepatu.repository.ShoeRepository
import kotlinx.coroutines.launch

class ShoeViewModel(private val repository: ShoeRepository): ViewModel(){
    val allShoes: LiveData<List<Shoe>> = repository.allShoes.asLiveData()

    fun insert(shoe: Shoe) = viewModelScope.launch {
        repository.insertShoe(shoe)
    }

    fun delete(shoe: Shoe) = viewModelScope.launch {
        repository.deleteShoe(shoe)
    }

    fun update(shoe: Shoe) = viewModelScope.launch {
        repository.updateShoe(shoe)
    }
}

class ShoeViewModelFactory(private val repository: ShoeRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom((ShoeViewModel::class.java))) {
            return ShoeViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}