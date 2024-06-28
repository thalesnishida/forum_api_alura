package br.com.alura.forum.service

import br.com.alura.forum.model.Usuario
import br.com.alura.forum.repository.UsuarioRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Service
class UsuarioService (
    private val repository: UsuarioRepository,
    private val logger: Logger = LoggerFactory.getLogger(UsuarioService::class.java)
    ) : UserDetailsService {

    fun buscarPorId(id: Long): Usuario {
        return repository.getOne(id)
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        logger.info("Tentativa de login com username: {}", username)
        val usuario = repository.findByEmail(username) ?: throw UsernameNotFoundException("Usuário não encontrado: $username")
        logger.info("Usuário encontrado: {}", usuario)
        return UserDetail(usuario)
    }
}
