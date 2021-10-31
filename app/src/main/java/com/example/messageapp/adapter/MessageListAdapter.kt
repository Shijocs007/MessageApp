package com.example.messageapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.messageapp.databinding.MessageListItemBinding
import com.example.messageapp.models.Message

class MessageListAdapter  : PagingDataAdapter<Message, MessageListAdapter.MessageViewHolder>(NEWS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MessageViewHolder {
        val binding = MessageListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class MessageViewHolder(private val binding: MessageListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(message: Message) {
            binding.apply {
                if(!message.isHeader) {
                    binding.message.text = message.body
                }

                itemView.setOnClickListener {
//                    itemView.context.startActivity(
////                        Intent(itemView.context, NewsDetailsActivity::class.java)
////                            .putExtra("data", Gson().toJson(news))
//                    )
                }
            }
        }
    }

    companion object {
        private val NEWS_COMPARATOR = object : DiffUtil.ItemCallback<Message>() {
            override fun areItemsTheSame(oldItem: Message, newItem: Message) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Message, newItem: Message) =
                oldItem == newItem
        }
    }
}