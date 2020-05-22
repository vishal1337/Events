package com.vishal.paytminsider.ui.events

import com.vishal.paytminsider.common.base.BaseViewState


/**
 * Different states for [EventsFragment].
 *
 * @see BaseViewState
 */
sealed class EventsListViewState : BaseViewState {

    /**
     * Loaded events list.
     */
    object Loaded : EventsListViewState()

    /**
     * Loading events list.
     */
    object Loading : EventsListViewState()

    /**
     * Internet Not Available
     */
    object Offline : EventsListViewState()

    /**
     * Empty events list.
     */
    object Empty : EventsListViewState()

    /**
     * Error on loading events list.
     */
    object Error : EventsListViewState()


    // ============================================================================================
    //  Public helpers methods
    // ============================================================================================


    /**
     * Check if current view state is [Loaded].
     *
     * @return True if is loaded state, otherwise false.
     */
    fun isLoaded() = this is Loaded

    /**
     * Check if current view state is [Loading].
     *
     * @return True if is loading state, otherwise false.
     */
    fun isLoading() = this is Loading

    /**
     * Check if device is [Offline].
     *
     * @return True if device is Offline, otherwise false.
     */
    fun isOffline() = this is Offline

    /**
     * Check if current view state is [Empty].
     *
     * @return True if is empty state, otherwise false.
     */
    fun isEmpty() = this is Empty

    /**
     * Check if current view state is [Error].
     *
     * @return True if is error state, otherwise false.
     */
    fun isError() = this is Error

}
