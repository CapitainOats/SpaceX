package cz.tomashavlicek.space_x.ui.launches

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import cz.tomashavlicek.space_x.R
import cz.tomashavlicek.space_x.adapters.LaunchesAdapter
import cz.tomashavlicek.space_x.databinding.FragmentLaunchesBinding
import cz.tomashavlicek.space_x.util.autoCleared
import cz.tomashavlicek.space_x.vo.Status
import cz.tomashavlicek.space_x.vo.Timebase
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class LaunchesFragment : Fragment() {

    private val viewModel: LaunchesViewModel by viewModels()

    var binding by autoCleared<FragmentLaunchesBinding>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLaunchesBinding.inflate(
            inflater,
            container,
            false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner

        val layoutManager = LinearLayoutManager(binding.upcomingLaunchesList.context)
        val itemDecoration = DividerItemDecoration(
            binding.upcomingLaunchesList.context,
            layoutManager.orientation
        )
        val adapter = LaunchesAdapter()
        binding.upcomingLaunchesList.adapter = adapter
        binding.upcomingLaunchesList.layoutManager = layoutManager
        binding.upcomingLaunchesList.addItemDecoration(itemDecoration)

        viewModel.launches.observe(viewLifecycleOwner, {
            if (it.status == Status.SUCCESS) {
                Timber.d("success")
                adapter.submitList(it.data)
            }

            if (it.status == Status.LOADING) {
                Timber.d("loading")
            }
        })

        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.launches_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.show_past_menu -> {
                viewModel.updateFilter(Timebase.PAST)
                true
            }
            R.id.show_upcoming_menu -> {
                viewModel.updateFilter(Timebase.UPCOMING)
                true
            }
            R.id.show_latest_menu -> {
                viewModel.updateFilter(Timebase.LATEST)
                true
            }
            R.id.show_next_menu -> {
                viewModel.updateFilter(Timebase.NEXT)
                true
            }
            R.id.show_all_menu -> {
                viewModel.updateFilter(Timebase.ALL)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
