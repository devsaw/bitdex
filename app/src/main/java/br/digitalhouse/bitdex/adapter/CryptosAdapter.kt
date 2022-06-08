package br.digitalhouse.bitdex.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.digitalhouse.bitdex.R
import br.digitalhouse.bitdex.model.ModelDataCLass

class CryptosAdapter(val context : Context, val listCryptos : List<ModelDataCLass>) :
    RecyclerView.Adapter<CryptosAdapter.VerticalHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalHolder {
        val layoutInflater = LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false)
        return VerticalHolder (layoutInflater)
    }


    override fun onBindViewHolder(holder: VerticalHolder, position: Int) {
        holder.imagem.setImageResource(listCryptos[position].image)
        holder.titulo.text = listCryptos[position].title
        holder.mensagem.text = listCryptos[position].message
        holder.valor.text = listCryptos[position].valor
    }

    override fun getItemCount(): Int {
        return listCryptos.size
    }

    inner class VerticalHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imagem = itemView.findViewById<ImageView>(R.id.imageRecycler)
        var titulo = itemView.findViewById<TextView>(R.id.nomeCrypto)
        var mensagem = itemView.findViewById<TextView>(R.id.variarPreco)
        var valor = itemView.findViewById<TextView>(R.id.variarValor)

    }
}