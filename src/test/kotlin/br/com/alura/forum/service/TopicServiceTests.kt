package br.com.alura.forum.service

import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicoFormMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.model.TopicViewTeste
import br.com.alura.forum.model.TopicoTest
import br.com.alura.forum.repository.TopicoRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.Optional

class TopicServiceTests {

    val topicos = PageImpl(listOf(TopicoTest.build()))

    val pagination: Pageable = mockk()

    val topicoRepository: TopicoRepository = mockk{
        every { findByCursoNome(any(), any()) } returns topicos
        every { findAll(pagination) } returns topicos
    }
    val topicoViewMapper : TopicoViewMapper = mockk{
        every { map(any()) } returns TopicViewTeste.build()
    }
    val topicoFormMapper : TopicoFormMapper = mockk()

    val topicService = TopicoService(
        topicoRepository, topicoViewMapper, topicoFormMapper
    )

    @Test
    fun `deve retornar uma lista de topicos dado o nome do curso`(){
        topicService.listar("Kotlin avançado", pagination)

        verify(exactly = 1) { topicoRepository.findByCursoNome(any(), any()) }
        verify(exactly = 1) { topicoViewMapper.map(any()) }
        verify(exactly = 0) { topicoRepository.findAll() }
    }

    @Test
    fun `deve listar os topicos quando o nome do curso for igual a nulo`(){
        topicService.listar(null, pagination)

        verify(exactly = 0) { topicoRepository.findByCursoNome(any(), any()) }
        verify(exactly = 1) { topicoViewMapper.map(any()) }
        verify(exactly = 1) { topicoRepository.findAll(pagination) }
    }

    @Test
    fun `deve lancar excecao se nao achar topico por id`() {
        every { topicoRepository.findById(any()) } returns Optional.empty()

        val actual = assertThrows<NotFoundException> {
            topicService.buscarPorId(2)
        }

        assertThat(actual.message).isEqualTo("Topico nao encontrado!")
    }
}