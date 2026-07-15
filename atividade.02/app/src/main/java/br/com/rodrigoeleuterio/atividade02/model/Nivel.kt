package br.com.rodrigoeleuterio.atividade02.model

import kotlinx.serialization.Serializable

@Serializable
enum class Nivel {
    ADMIN,
    FINANCEIRO,
    GERENCIA,
    SUPORTE
}