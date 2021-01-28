package cz.tomashavlicek.space_x.ui.rockets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import cz.tomashavlicek.space_x.R
import cz.tomashavlicek.space_x.databinding.FragmentRocketDetailBinding
import cz.tomashavlicek.space_x.util.autoCleared
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RocketDetailFragment : Fragment() {

    private val args: RocketDetailFragmentArgs by navArgs()

    private val rocketDetailViewModel: RocketDetailViewModel by viewModels()

    var binding by autoCleared<FragmentRocketDetailBinding>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRocketDetailBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.lifecycleOwner = viewLifecycleOwner

        binding.rocket = rocketDetailViewModel.rocket
    }
}
