package br.digitalhouse.bitdex.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.digitalhouse.bitdex.R
import br.digitalhouse.bitdex.ui.model.Cryptos

class CryptosAdapter(val listCryptos: MutableList<Cryptos>) :
    RecyclerView.Adapter<CryptosAdapter.CryptoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_favorites, parent, false)
        return CryptoHolder(layoutInflater)
    }


    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {
        holder.bind(listCryptos[position])
    }

    override fun getItemCount(): Int {
        return listCryptos.size
    }

    fun add(listagem: Cryptos) {
        this.listCryptos.add(listagem)
        this.notifyDataSetChanged()
    }

    inner class CryptoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imagem = itemView.findViewById<ImageView>(R.id.imageRecyclerFav)
        val titulo = itemView.findViewById<TextView>(R.id.nomeCryptoFav)
        val variacao = itemView.findViewById<TextView>(R.id.variarPrecoFav)
        val valor = itemView.findViewById<TextView>(R.id.variarValorFav)
        val addButton = itemView.findViewById<ImageButton>(R.id.btnStar)
        fun bind(list : Cryptos) {

        }
    }
}