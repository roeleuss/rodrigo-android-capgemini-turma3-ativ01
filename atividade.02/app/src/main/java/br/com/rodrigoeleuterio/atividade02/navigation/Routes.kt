package br.com.rodrigoeleuterio.atividade02.navigation

import br.com.rodrigoeleuterio.atividade02.model.Nivel
import kotlinx.serialization.Serializable

@Serializable
object SobreRoute

@Serializable
object ListaColaboladoresRoute

@Serializable
data class CadastroRoute(val id: String = "",
                         val nome: String = "",
                         val email: String = "",
                         val nivel: String = "")