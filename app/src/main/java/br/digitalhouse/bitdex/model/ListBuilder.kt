package br.digitalhouse.bitdex.model

class ListBuilder {
    var listaDeTopicos = mutableListOf<ModelDataCLass>()

    fun add(image: Int, message: String, title: String, valor: String) {
        var itens = ModelDataCLass(image, title, message, valor)
        listaDeTopicos.add(itens)
    }
}