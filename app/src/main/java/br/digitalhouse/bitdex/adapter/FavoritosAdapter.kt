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

class FavoritosAdapter(val context : Context, val listFavoritos : List<ModelDataCLass>) :
    RecyclerView.Adapter<FavoritosAdapter.VerticalHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalHolder {
        val layoutInflater = LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false)
        return VerticalHolder (layoutInflater)
    }


    override fun onBindViewHolder(holder: VerticalHolder, position: Int) {
        holder.imagem.setImageResource(listFavoritos[position].image)
        holder.titulo.text = listFavoritos[position].title
        holder.mensagem.text = listFavoritos[position].message
        holder.valor.text = listFavoritos[position].valor
    }

    override fun getItemCount(): Int {
        return listFavoritos.size
    }

    inner class VerticalHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imagem = itemView.findViewById<ImageView>(R.id.imageRecycler)
        var titulo = itemView.findViewById<TextView>(R.id.nomeCrypto)
        var mensagem = itemView.findViewById<TextView>(R.id.variarPreco)
        var valor = itemView.findViewById<TextView>(R.id.variarValor)

    }
}