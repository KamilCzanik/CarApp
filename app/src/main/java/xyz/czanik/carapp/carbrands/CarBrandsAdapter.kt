package xyz.czanik.carapp.carbrands

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import xyz.czanik.carapp.carbrands.CarBrandsAdapter.CarBrandViewHolder
import xyz.czanik.carapp.databinding.ItemCarBrandBinding

class CarBrandsAdapter : ListAdapter<CarBrand, CarBrandViewHolder>(CarBrandDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarBrandViewHolder = ItemCarBrandBinding.inflate(
        LayoutInflater.from(parent.context), parent, false
    ).let(::CarBrandViewHolder)

    override fun onBindViewHolder(holder: CarBrandViewHolder, position: Int) = holder.bind(getItem(position))

    class CarBrandViewHolder(private val binding: ItemCarBrandBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(carBrand: CarBrand) {
            binding.viewModel = carBrand
        }
    }
}