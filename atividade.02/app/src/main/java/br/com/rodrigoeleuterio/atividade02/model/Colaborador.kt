package br.com.rodrigoeleuterio.atividade02.model

import java.util.UUID

data class Colaborador(
    var id: UUID = UUID.randomUUID(),
    var nome: String = "",
    var email: String = "",
    var nivel: Nivel = Nivel.ADMIN
)