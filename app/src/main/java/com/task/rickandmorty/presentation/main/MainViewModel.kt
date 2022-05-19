package com.task.rickandmorty.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.task.rickandmorty.data.networck.dto.CharacterDto
import com.task.rickandmorty.data.networck.dto.DataDto
import com.task.rickandmorty.data.networck.retrofit.APIService
import com.task.rickandmorty.presentation.details.CharacterDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { CharacterDetailsViewModel(get()) }
}

class MainViewModel(val apiService: APIService) : ViewModel() {

    val itemsLiveData: LiveData<List<CharacterDto>> = MutableLiveData()

    private lateinit var itemsCall: Call<DataDto>
    private var nextPageNumber: String? = "0"

    fun getData() {
        if (nextPageNumber == null) {
            return
        }
        itemsCall = apiService.getCharacters(nextPageNumber!!)
        itemsCall.enqueue(object : Callback<DataDto> {
            override fun onResponse(
                call: Call<DataDto>,
                response: Response<DataDto>
            ) {
                if (response.isSuccessful) {
                    val taskDto = response.body()
                    taskDto?.let {
                        it.results?.let { items ->
                            (itemsLiveData as MutableLiveData).value = items
                        }
                        val params = it.info?.next?.split("=")
                        nextPageNumber = if (params != null && params.size > 1) {
                            params[1]
                        } else {
                            null
                        }
                    }
                }
            }

            override fun onFailure(call: Call<DataDto>, t: Throwable) {
            }
        })
    }

    override fun onCleared() {
        itemsCall.cancel()
    }

    fun getNewPage() {
        getData()
    }
}