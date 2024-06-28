package br.com.alura.forum.model

object TopicoTest {
    fun build() = Topico(
        id = 1,
        titulo = "Teste unitario",
        mensagem = "Este é um teste",
        curso = CursoTeste.build(),
        autor = UsuarioTest.build()
    )
}
