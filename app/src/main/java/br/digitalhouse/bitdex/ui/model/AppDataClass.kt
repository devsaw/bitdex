package br.digitalhouse.bitdex.ui.model

data class Cryptos(
    var image: String,
    var title: String,
    var valor: String,
    var variacao: String,
)

data class User (var id : String,
                 var email : String,
                 var password : String?,
)