package com.example.shopgo.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.shopgo.Models.Rating
import com.example.shopgo.Models.ShopGoModel
import com.example.shopgo.R
import com.example.shopgo.databinding.HorizontalrowBinding


class HorizontalAdapter(val liste: ArrayList<ShopGoModel>,val clickedCategoriListener:(String)->Unit) : RecyclerView.Adapter<HorizontalAdapter.HorizontalAdapterHolder>() {
    private lateinit var rating: Rating
    class HorizontalAdapterHolder(val binding: HorizontalrowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalAdapterHolder {
        val binding = HorizontalrowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HorizontalAdapterHolder(binding)
    }

    override fun getItemCount(): Int {
        return liste.size
    }

    override fun onBindViewHolder(holder: HorizontalAdapterHolder, position: Int) {
        holder.binding.toggleButton.text = liste[position].category
        rating = Rating(liste.get(0).rating.rate, liste.get(0).rating.count)
        holder.binding.toggleButton.textOn = liste[position].category
        holder.binding.toggleButton.textOff = liste[position].category
        holder.binding.toggleButton.setOnCheckedChangeListener{copmpoundButton,checked->
            if(checked){
                clickedCategoriListener(liste[position].category)
                val color = ContextCompat.getColor(holder.itemView.context, com.example.shopgo.R.color.dark_orange)
                holder.binding.toggleButton.setBackgroundColor(color)
            }else{
            clickedCategoriListener("")
                val color = ContextCompat.getColor(holder.itemView.context, com.example.shopgo.R.color.orange)
                holder.binding.toggleButton.setBackgroundColor(color)
            }
        }

    }
//listeyi update yap

    fun updateShopGoList(yeniListe: List<ShopGoModel>) {
        liste.clear()
        val uniqeKategoriListesi = yeniListe.distinctBy { it.category }
        println(uniqeKategoriListesi.size)
        uniqeKategoriListesi.forEach {
            liste.add(ShopGoModel(id = it.id, category = it.category, image = it.image, price = it.price, title = it.title, description = it.description, rating = it.rating))
        }
        notifyDataSetChanged()
    }
}