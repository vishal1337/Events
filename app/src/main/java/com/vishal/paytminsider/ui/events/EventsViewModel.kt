package com.vishal.paytminsider.ui.events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishal.paytminsider.data.network.repository.PaytmRepository
import com.vishal.paytminsider.model.EventDto
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * View model responsible for preparing and managing the data for Events.
 *
 * @see ViewModel
 */
class EventsViewModel @Inject constructor(
    private val paytmRepository: PaytmRepository
) : ViewModel() {

    private val _events = MutableLiveData<List<EventDto.Event>>()
    val events: MutableLiveData<List<EventDto.Event>>
        get() = _events

    private val _state = MutableLiveData<EventsListViewState>()
    val stateList: LiveData<EventsListViewState>
        get() = _state

    // ============================================================================================
    //  Public methods
    // ============================================================================================

    /**
     * Load Events
     */
    fun loadEvents() {

        //Show Loading State in UI
        _state.value = EventsListViewState.Loading

        //Fetch data and revert
        viewModelScope.launch {
            try {

                // Get Events Hash Map
                val events = paytmRepository.getEvents(CITY)

                // Set State to Loaded to hide loader.
                _state.value = EventsListViewState.Loaded

                //Only Posting back the Events. Not using their Keys.
                _events.postValue(events.map { it.value })

            } catch (e: Exception) {
                _state.value = EventsListViewState.Error
            }
        }
    }

    /**
     * Set Current View State
     */
    fun setOfflineState() {
        _state.value = EventsListViewState.Offline
    }

    companion object {

        /**
         * Making it variable so, in future, this can be provided by user using a drop down list
         */
        const val CITY = "mumbai"
    }
}
