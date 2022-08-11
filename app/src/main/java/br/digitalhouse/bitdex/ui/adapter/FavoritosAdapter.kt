package br.digitalhouse.bitdex.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.digitalhouse.bitdex.R
import br.digitalhouse.bitdex.data.dto.cryptos.Cryptos

class FavoritosAdapter(val listFavoritos: MutableList<Cryptos>) :
    RecyclerView.Adapter<FavoritosAdapter.FavoritosHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritosHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_delete, parent, false)
        return FavoritosHolder(layoutInflater)
    }


    override fun onBindViewHolder(holder: FavoritosHolder, position: Int) {
        holder.bind(listFavoritos[position])
    }

    override fun getItemCount(): Int {
        return listFavoritos.size
    }

    fun add(listagem: Cryptos) {
        this.listFavoritos.add(listagem)
        this.notifyDataSetChanged()
    }

    inner class FavoritosHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageDel = itemView.findViewById<ImageView>(R.id.imageRecyclerDel)
        val titleDel = itemView.findViewById<TextView>(R.id.nomeCryptoDel)
        val symbolDel = itemView.findViewById<TextView>(R.id.symbolDel)
        val rankDel = itemView.findViewById<TextView>(R.id.posRankDel)
        val delBtn = itemView.findViewById<ImageButton>(R.id.btnDell)
        fun bind(list : Cryptos) {

        }
    }
}