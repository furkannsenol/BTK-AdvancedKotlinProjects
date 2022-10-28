package com.atilsamancioglu.besinlerkitabigradlework.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.atilsamancioglu.besinlerkitabigradlework.R
import com.atilsamancioglu.besinlerkitabigradlework.databinding.BesinRecyclerRowBinding
import com.atilsamancioglu.besinlerkitabigradlework.model.Besin
import com.atilsamancioglu.besinlerkitabigradlework.util.gorselIndır
import com.atilsamancioglu.besinlerkitabigradlework.util.placeholderYap
import com.atilsamancioglu.besinlerkitabigradlework.view.BesinListesiFragmentDirections
import kotlinx.android.synthetic.main.besin_recycler_row.view.*

class BesinRecyclerAdapter(val besinListesi: ArrayList<Besin>) :
    RecyclerView.Adapter<BesinRecyclerAdapter.BesinViewHolder>(), BesinClickListener {
    class BesinViewHolder(var view: BesinRecyclerRowBinding) :
        RecyclerView.ViewHolder(view.root) {//viewbinding ile baglama

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BesinViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.besin_recycler_row, parent, false)
        val view = DataBindingUtil.inflate<BesinRecyclerRowBinding>(
            inflater,
            R.layout.besin_recycler_row,
            parent,
            false
        )
        return BesinViewHolder(view)
    }

    override fun onBindViewHolder(holder: BesinViewHolder, position: Int) {
        /*
        holder.itemView.isim.text=besinListesi.get(position).besinIsim
        holder.itemView.kalori.text=besinListesi.get(position).besinKalori

        holder.itemView.setOnClickListener {
            val action= BesinListesiFragmentDirections.actionBesinListesiFragmentToBesinDetayFragment(besinListesi.get(position).uuid)
            Navigation.findNavController(it).navigate(action)
        }

        holder.itemView.ImageView.gorselIndır(besinListesi.get(position).besinGorsel, placeholderYap(holder.itemView.context))*/
        holder.view.besin = besinListesi[position]
        holder.view.besinListener = this
    }

    override fun getItemCount(): Int {
        return besinListesi.size
    }

    fun besinListesiGuncelle(yeniBesinListesi: List<Besin>) {
        besinListesi.clear()
        besinListesi.addAll(yeniBesinListesi)
        notifyDataSetChanged()

    }

    override fun besinTiklandi(view: View) {
        val uuid = view.besin_uuid.text.toString().toIntOrNull()
        uuid?.let {
            val action =
                BesinListesiFragmentDirections.actionBesinListesiFragmentToBesinDetayFragment(it)
            Navigation.findNavController(view).navigate(action)
        }

    }
}