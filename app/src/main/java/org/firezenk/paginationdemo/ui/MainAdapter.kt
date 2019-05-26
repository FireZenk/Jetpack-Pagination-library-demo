package org.firezenk.paginationdemo.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.firezenk.paginationdemo.GlideApp
import org.firezenk.paginationdemo.R
import org.firezenk.paginationdemo.domain.PokemonModel

class MainAdapter : PagedListAdapter<PokemonModel, MainAdapter.ViewHolder>(ItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.view_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val pokeName = itemView.findViewById<TextView>(R.id.name)
        private val pokeImage = itemView.findViewById<ImageView>(R.id.image)

        fun bind(pokemon: PokemonModel?) = pokemon?.run {
            pokeName.text = name
            pokeImage.setImage(imageUrl)
        }
    }
}

fun ImageView.setImage(stringUrl: String?) = stringUrl?.run {
    GlideApp.with(this@setImage)
        .load(this)
        .into(this@setImage)
}

class ItemDiffCallback : DiffUtil.ItemCallback<PokemonModel>() {

    override fun areItemsTheSame(oldItem: PokemonModel, newItem: PokemonModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PokemonModel, newItem: PokemonModel): Boolean {
        return oldItem.name == newItem.name
    }
}