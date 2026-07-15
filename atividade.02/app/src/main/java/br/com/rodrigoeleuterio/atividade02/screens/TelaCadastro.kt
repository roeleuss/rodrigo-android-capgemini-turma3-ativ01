package br.com.rodrigoeleuterio.atividade02.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.rodrigoeleuterio.atividade02.model.Colaborador
import br.com.rodrigoeleuterio.atividade02.model.Nivel
import br.com.rodrigoeleuterio.atividade02.navigation.CadastroRoute
import br.com.rodrigoeleuterio.atividade02.navigation.ListaColaboladoresRoute
import br.com.rodrigoeleuterio.atividade02.service.ColaboradorService
import java.util.UUID

@Composable
fun TelaCadastro(navController: NavController, colaborador: CadastroRoute) {

    val niveis = listOf(Nivel.ADMIN, Nivel.FINANCEIRO, Nivel.GERENCIA, Nivel.SUPORTE)
    var alerta by remember { mutableStateOf("") }
    var id by remember { mutableStateOf(colaborador.id) }
    var nome by remember { mutableStateOf(colaborador.nome) }
    var email by remember { mutableStateOf(colaborador.email) }
    var nivel by remember { mutableStateOf(colaborador.nivel) }

    fun limpar() {
        nome = ""
        email = ""
        nivel = Nivel.ADMIN.name
        alerta = ""
    }

    fun salvar() {
        val uuid = if (id.isBlank()) UUID.randomUUID() else UUID.fromString(id)

        if (nome.isBlank() || email.isBlank() || nivel.isBlank()) {
            alerta = "Preencha todos os campos!"
            return
        }

        ColaboradorService.save(
            Colaborador(uuid, nome, email, Nivel.valueOf(nivel))
        )

        navController.navigate(ListaColaboladoresRoute) { launchSingleTop = true }
    }

    @Composable
    fun CampoNome() {
        OutlinedTextField(
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )
    }

    @Composable
    fun CampoEmail() {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
    }

    @Composable
    fun SeletorNivel() {
        Text("Nível:")
        Spacer(Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            niveis.forEach { item ->
                val selecionado = item.name == nivel
                Box(
                    modifier = Modifier
                        .background(if (selecionado) Color.Blue else Color.LightGray)
                        .clickable { nivel = item.name }
                        .padding(12.dp)
                ) {
                    Text(
                        text = item.name,
                        color = if (selecionado) Color.White else Color.Black
                    )
                }
            }
        }
    }

    @Composable
    fun Alerta() {
        if (alerta.isNotEmpty()) {
            Text(text = alerta, color = Color.Red)
        }
    }

    @Composable
    fun BotaoSalvar() {
        Button(onClick = {
            salvar()
        }) {
            Text("Salvar")
        }
    }

    @Composable
    fun BotaoLimpar() {
        OutlinedButton(onClick = {
            limpar()
        }) {
            Text("Limpar")
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Spacer(Modifier.height(16.dp))
        CampoNome()
        Spacer(Modifier.height(8.dp))
        CampoEmail()
        Spacer(Modifier.height(16.dp))
        SeletorNivel()
        Spacer(Modifier.height(16.dp))
        Alerta()
        Spacer(Modifier.height(16.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            BotaoSalvar()
            BotaoLimpar()
        }
    }
}