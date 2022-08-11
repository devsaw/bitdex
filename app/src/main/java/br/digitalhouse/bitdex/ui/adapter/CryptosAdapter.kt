package br.digitalhouse.bitdex.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.digitalhouse.bitdex.R
import br.digitalhouse.bitdex.data.dto.cryptos.Coins
import br.digitalhouse.bitdex.data.dto.cryptos.Cryptos
import com.bumptech.glide.Glide

class CryptosAdapter(val listData: MutableList<Coins> = mutableListOf()) :
    RecyclerView.Adapter<CryptosAdapter.CryptoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_favorites, parent, false)
        return CryptoHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    fun add(list: Cryptos) {
        this.listData.clear()
        this.listData.addAll(list)
        this.notifyDataSetChanged()
    }

    inner class CryptoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageFav = itemView.findViewById<ImageView>(R.id.imageRecyclerFav)
        val titleFav = itemView.findViewById<TextView>(R.id.nomeCryptoFav)
        val symbolFav = itemView.findViewById<TextView>(R.id.symbolFav)
        val rankFav = itemView.findViewById<TextView>(R.id.posRankFav)
        val favBtn = itemView.findViewById<ImageButton>(R.id.btnFav)
        fun bind(cryptos: Coins) {
            symbolFav.text = cryptos.symbol
            titleFav.text = cryptos.name
            rankFav.text = cryptos.price.toString()
            Glide.with(itemView)
                .load(cryptos.image)
                .fitCenter()
                .circleCrop()
                .into(imageFav)
        }

    }
}