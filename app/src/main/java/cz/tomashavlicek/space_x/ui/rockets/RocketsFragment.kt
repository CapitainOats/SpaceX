package cz.tomashavlicek.space_x.ui.rockets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import cz.tomashavlicek.space_x.adapters.RocketsAdapter
import cz.tomashavlicek.space_x.databinding.FragmentRocketsBinding
import cz.tomashavlicek.space_x.ui.common.RetryCallback
import cz.tomashavlicek.space_x.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RocketsFragment : Fragment() {

    private val viewModel: RocketsViewModel by viewModels()

    var binding by autoCleared<FragmentRocketsBinding>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRocketsBinding.inflate(
            inflater,
            container,
            false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = RocketsAdapter()
        binding.rocketList.apply {
            val layoutManager = LinearLayoutManager(context)
            val itemDecoration = DividerItemDecoration(
                context,
                layoutManager.orientation
            )

            this.adapter = adapter
            this.layoutManager = layoutManager
            addItemDecoration(itemDecoration)
        }

        viewModel.rockets.observe(viewLifecycleOwner, {
            adapter.submitList(it?.data)
        })

        binding.rockets = viewModel.rockets

        binding.callback = object : RetryCallback {
            override fun retry() {
                viewModel.retry()
            }
        }
    }
}
