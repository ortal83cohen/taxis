package com.cohen.taxis.viewmodel

import androidx.lifecycle.*
import com.cohen.taxis.helpers.room.Persistent
import com.cohen.taxis.model.Taxi


class TaxiViewModel(private val persistent: Persistent) : ViewModel() {

    val taxis = liveData { emitSource(persistent.liveTaxis()) }
    private var filter = MutableLiveData("")
    private var filteredtaxis = MediatorLiveData<List<Taxi>>()

    init {

        filteredtaxis.addSource(taxis) {
            filterList()
        }
        filteredtaxis.addSource(filter) {
            filterList()
        }

    }

    private fun filterList() {
        taxis.value?.let {
            val list = ArrayList<Taxi>()
            list.addAll(taxis.value as ArrayList)
            filteredtaxis.value = list.filter {
                it.title?.toLowerCase()?.startsWith(filter.value?.toLowerCase() ?: "") ?: true
            }.sortedBy { it.eta }
        }
    }

    fun setFilter(string: String) {
        filter.postValue(string)
    }

    fun getFilteredTaxis(): LiveData<List<Taxi>> {
        return filteredtaxis

    }

}
