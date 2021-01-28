package cz.tomashavlicek.space_x.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cz.tomashavlicek.space_x.vo.Rocket
import cz.tomashavlicek.space_x.databinding.ListItemRocketBinding
import cz.tomashavlicek.space_x.ui.rockets.RocketsFragmentDirections

class RocketsAdapter : ListAdapter<Rocket, RocketsAdapter.RocketViewHolder>(RocketDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RocketViewHolder {
        return RocketViewHolder(
            ListItemRocketBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class RocketViewHolder(
        private val binding: ListItemRocketBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener { view ->
                view.findNavController().navigate(RocketsFragmentDirections.actionNavRocketsToRocketDetailFragment(binding.rocket!!.id))
            }
        }

        fun bind(item: Rocket) {
            binding.apply {
                rocket = item
                executePendingBindings()
            }
        }
    }
}

private class RocketDiffCallback : DiffUtil.ItemCallback<Rocket>() {

    override fun areItemsTheSame(oldItem: Rocket, newItem: Rocket) : Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Rocket, newItem: Rocket) : Boolean {
        return oldItem.name == newItem.name
    }
}
