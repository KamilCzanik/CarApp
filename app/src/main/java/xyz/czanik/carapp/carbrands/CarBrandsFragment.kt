package xyz.czanik.carapp.carbrands

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import xyz.czanik.carapp.BaseFragment
import xyz.czanik.carapp.CarAppViewModelFactory
import xyz.czanik.carapp.databinding.FragmentCarBrandsBinding

class CarBrandsFragment : BaseFragment() {

    private val carBrandsViewModel by viewModels<CarBrandsViewModel> { CarAppViewModelFactory(container) }
    private val carBrandsAdapter by lazy(::CarBrandsAdapter)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentCarBrandsBinding.inflate(inflater, container, false).let { binding ->
        binding.lifecycleOwner = viewLifecycleOwner
        binding.carBrandsRecycler.adapter = carBrandsAdapter
        carBrandsViewModel.viewState.observe(viewLifecycleOwner, Observer(::render))
        binding.root
    }

    private fun render(viewState: CarBrandsContract.ViewState) {
        carBrandsAdapter.submitList(viewState.carBrands)
    }

    override fun onResume() {
        super.onResume()
        carBrandsViewModel.accept(CarBrandsContract.Event.Init)
    }
}
