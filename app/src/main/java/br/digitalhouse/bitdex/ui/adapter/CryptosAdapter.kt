package br.digitalhouse.bitdex.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.digitalhouse.bitdex.R
import br.digitalhouse.bitdex.data.dto.*

class CryptosAdapter(val listData: MutableList<Cryptos>) :
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
        this.listData.add(list)
        this.notifyDataSetChanged()
    }

    inner class CryptoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val symbol = itemView.findViewById<TextView>(R.id.symbol)
        val price = itemView.findViewById<TextView>(R.id.price)
        val volatility = itemView.findViewById<TextView>(R.id.variacao)
        val nameCrypto = itemView.findViewById<TextView>(R.id.nameCrypto)
        val addButton = itemView.findViewById<ImageButton>(R.id.btnStar)
        fun bind(cryptos: Cryptos) {
            symbol.text = cryptos.data[0].symbol
            nameCrypto.text = cryptos.data[0].name
            price.text = cryptos.data[0].quote.usd.price.toString()
            volatility.text = cryptos.data[0].quote.usd.volatility.toString()
        }

        fun addIten(){

        }
    }
}