package br.com.rodrigoeleuterio.atividade02.service

import br.com.rodrigoeleuterio.atividade02.model.Colaborador
import java.util.UUID

object ColaboradorService {

    private val repo = mutableListOf<Colaborador>();

    fun get(id: UUID): Colaborador? = repo.firstOrNull { it.id == id }

    fun remove(id: UUID): Boolean = repo.removeIf { filter -> filter.id == id }

    fun save(colaborador: Colaborador) {
        val update = get(colaborador.id)
        val isNew = update == null
        if (isNew) {
            repo.add(colaborador)
        } else {
            update.nome = colaborador.nome
            update.email = colaborador.email
            update.nivel = colaborador.nivel
        }
    }

    fun list(): List<Colaborador> = repo.toList()

}