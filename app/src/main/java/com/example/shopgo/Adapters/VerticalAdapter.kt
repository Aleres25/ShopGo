package com.example.shopgo.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shopgo.Models.ShopGoModel
import com.example.shopgo.Util.getImageFromUrl
import com.example.shopgo.Util.placeHolderProgressBar
import com.example.shopgo.Views.FeedFragmentDirections
import com.example.shopgo.databinding.VerticalrowBinding

class VerticalAdapter(var liste: ArrayList<ShopGoModel>) : RecyclerView.Adapter<VerticalAdapter.VerticalAdapterHolder>() {
    class VerticalAdapterHolder(var binding: VerticalrowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalAdapterHolder {
        val binding = VerticalrowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VerticalAdapterHolder(binding)
    }

    override fun getItemCount(): Int {
        return liste.size
    }

    override fun onBindViewHolder(holder: VerticalAdapterHolder, position: Int) {
        holder.binding.urunAdiRecyclerRow.text = liste[position].title
        holder.binding.urunAciklamaRecyclerRow.text = liste[position].description
        holder.binding.urunFotorecyclerRow.getImageFromUrl(liste[position].image, placeHolderProgressBar(holder.itemView.context))
        holder.itemView.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToDetailsFragment(liste[position].rating,liste[position])
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateList(yeniListe: List<ShopGoModel>) {
        liste.clear()
        liste.addAll(yeniListe)
        notifyDataSetChanged()
    }
}