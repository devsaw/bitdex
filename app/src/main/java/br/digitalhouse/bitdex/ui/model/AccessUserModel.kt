package br.digitalhouse.bitdex.ui.model

import android.util.Patterns

class AccessUserModel {
    val listaEspeciais: List<String> =
        listOf(
            "!",
            "@",
            "#",
            "$",
            "¨",
            "&",
            "*",
            "(",
            ")",
            "'",
            "-",
            "=",
            "+",
            "_",
            "¹",
            "²",
            "³",
            "/",
            "|",
            "<",
            ">",
            ":",
            ";",
            ",",
            ".",
            "[",
            "]",
            "{",
            "}"
        )

    fun validPassSize(password: String) = password.length >= 6

    fun validPassChar(password: String): Boolean {
        var char = false
        for (element in listaEspeciais)
            if (!password.contains(element))
                char = true
        return char
    }

    fun validEmailChar (email: String) = Patterns.EMAIL_ADDRESS.matcher(email).matches()

}