package com.example.messageapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.messageapp.databinding.MessageListItemBinding
import com.example.messageapp.databinding.SectionListItemBinding
import com.example.messageapp.models.Message

class MessageListAdapter  : PagingDataAdapter<Message, RecyclerView.ViewHolder>(NEWS_COMPARATOR) {

    private val VIEW_ITEM = 1
    private val VIEW_HEADER = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        lateinit var vh: RecyclerView.ViewHolder
        if (viewType == VIEW_ITEM) {
            val binding =
                MessageListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            vh = MessageViewHolder(binding)
        } else {
            val binding =
                SectionListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            vh = HeaderViewHolder(binding)
        }

        return vh
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null && !currentItem.isHeader) {
            (holder as MessageViewHolder).bind(currentItem)
        } else {
            (holder as HeaderViewHolder).bind(currentItem!!)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position)!!.isHeader) VIEW_HEADER else VIEW_ITEM
    }

    class HeaderViewHolder(private val binding: SectionListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(message: Message) {
            binding.titleSection.text = message.header
        }
    }

    class MessageViewHolder(private val binding: MessageListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(message: Message) {
            binding.apply {

                binding.name.text = message.address
                binding.description.text = message.body

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