package xyz.czanik.carapp.carbrands

import androidx.recyclerview.widget.DiffUtil

object CarBrandDiff : DiffUtil.ItemCallback<CarBrand>() {
    override fun areItemsTheSame(oldItem: CarBrand, newItem: CarBrand) = oldItem == newItem

    override fun areContentsTheSame(oldItem: CarBrand, newItem: CarBrand) = oldItem == newItem
}