package xyz.czanik.carapp.carbrands

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.util.Consumer
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import xyz.czanik.carapp.carbrands.CarBrandsAdapter.CarBrandViewHolder
import xyz.czanik.carapp.carbrands.CarBrandsContract.Event
import xyz.czanik.carapp.databinding.ItemCarBrandBinding

class CarBrandsAdapter(private val eventConsumer: Consumer<Event>) : ListAdapter<CarBrand, CarBrandViewHolder>(CarBrandDiff) {

    var selected: CarBrand? = null
        set(value) {
            val deselected = currentList.indexOf(field)
            val selected = currentList.indexOf(value)
            field = value
            notifyItemChanged(deselected)
            notifyItemChanged(selected)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarBrandViewHolder = ItemCarBrandBinding.inflate(
        LayoutInflater.from(parent.context), parent, false
    ).let { binding -> CarBrandViewHolder(binding, eventConsumer) }

    override fun onBindViewHolder(holder: CarBrandViewHolder, position: Int) {
        val carBrand = getItem(position)
        holder.bind(carBrand, carBrand == selected)
    }

    class CarBrandViewHolder(
        private val binding: ItemCarBrandBinding,
        private val eventConsumer: Consumer<Event>
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(carBrand: CarBrand, isSelected: Boolean) {
            binding.viewModel = carBrand
            binding.root.setOnClickListener { eventConsumer.accept(Event.CarBrandClicked(adapterPosition)) }
            binding.root.isSelected = isSelected
            binding.executePendingBindings()
        }
    }
}