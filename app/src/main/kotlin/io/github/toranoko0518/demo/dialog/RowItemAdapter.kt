package io.github.toranoko0518.demo.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.toranoko0518.demo.dialog.databinding.ItemRowBinding

class RowItemAdapter : ListAdapter<Row, RecyclerView.ViewHolder>(
    object : DiffUtil.ItemCallback<Row>() {
        override fun areItemsTheSame(
            oldItem: Row,
            newItem: Row
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: Row,
            newItem: Row
        ): Boolean = oldItem == newItem
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(ItemRowBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ItemViewHolder)?.bind(getItem(position))
    }

    inner class ItemViewHolder(
        private val binding: ItemRowBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(row: Row) {
            binding.row = row
            binding.executePendingBindings()
        }
    }
}
