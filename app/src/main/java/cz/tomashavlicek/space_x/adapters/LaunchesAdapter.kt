package cz.tomashavlicek.space_x.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cz.tomashavlicek.space_x.vo.Launch
import cz.tomashavlicek.space_x.databinding.ListItemLaunchBinding

class LaunchesAdapter
    : ListAdapter<Launch, LaunchesAdapter.LaunchViewHolder>(
        LaunchDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder {
        return LaunchViewHolder(
            ListItemLaunchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class LaunchViewHolder(
        private val binding: ListItemLaunchBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Launch) {
            binding.apply {
                launch = item
                executePendingBindings()
            }
        }
    }
}

private class LaunchDiffCallback : DiffUtil.ItemCallback<Launch>() {
    override fun areItemsTheSame(oldItem: Launch, newItem: Launch): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Launch, newItem: Launch): Boolean {
        return oldItem.name == newItem.name
    }
}
