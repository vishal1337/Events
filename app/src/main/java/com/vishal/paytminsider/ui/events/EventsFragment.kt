package com.vishal.paytminsider.ui.events

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.vishal.paytminsider.R
import com.vishal.paytminsider.common.base.BaseFragment
import com.vishal.paytminsider.common.utils.viewModelProvider
import com.vishal.paytminsider.databinding.FragmentEventsBinding
import kotlinx.android.synthetic.main.fragment_events.*
import kotlinx.android.synthetic.main.fragment_events.view.*
import javax.inject.Inject

class EventsFragment @Inject constructor() : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var eventsViewModel: EventsViewModel

    private val eventAdapter by lazy { EventAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        eventsViewModel = viewModelProvider(viewModelFactory)

        // Inflate the layout for this fragment
        val binding = FragmentEventsBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = eventsViewModel
        }

        eventsViewModel.events.observe(viewLifecycleOwner, Observer { events ->
            eventAdapter.submitList(events)
        })

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initToolbar()
        initRecyclerView()

        // Load Data
        fetchData()

    }


    private fun initToolbar() {
        appbar.toolbar.let {
            it.inflateMenu(R.menu.menu_events)
            it.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_reload -> fetchData()
                }
                true
            }
        }
    }

    private fun initRecyclerView() {
        rvEvents.adapter = eventAdapter
    }

    private fun fetchData() {

        if (isConnected().not()) {
            eventsViewModel.setOfflineState()
            return
        }

        eventsViewModel.loadEvents()

    }

    private fun isConnected(): Boolean {

        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkCapabilities = connectivityManager.activeNetwork
            ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(networkCapabilities)
            ?: return false
        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }


}
