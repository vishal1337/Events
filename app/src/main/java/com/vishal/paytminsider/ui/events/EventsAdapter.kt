package com.vishal.paytminsider.ui.events

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.vishal.paytminsider.databinding.ItemEventBinding
import com.vishal.paytminsider.model.EventDto

class EventAdapter : ListAdapter<EventDto.Event, EventViewHolder>(EventDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder =
        EventViewHolder(
            ItemEventBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class EventViewHolder(private val binding: ItemEventBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(event: EventDto.Event) {
        binding.event = event
        binding.executePendingBindings()
    }
}

private class EventDiffCallback : DiffUtil.ItemCallback<EventDto.Event>() {
    override fun areItemsTheSame(
        oldItem: EventDto.Event,
        newItem: EventDto.Event
    ): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: EventDto.Event,
        newItem: EventDto.Event
    ): Boolean =
        oldItem.toString() == newItem.toString()

}