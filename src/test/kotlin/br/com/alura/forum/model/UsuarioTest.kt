package br.com.alura.forum.model

object UsuarioTest {
    fun  build() =  Usuario(
        id = 1,
        nome = "Thales",
        email = "medeiros.tn@gmail.com",
        password = "123456",
        role = listOf()
    )

    fun buildToToken() = Usuario(nome = "Ana da Silva", email = "ana@email.com", password = "123456")
}