package com.rodrigoeleuterio.atividadejunho.ui.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rodrigoeleuterio.atividadejunho.ui.model.Colaborador
import com.rodrigoeleuterio.atividadejunho.ui.model.Nivel


@Composable
fun TelaCadastro() {

    val niveis = listOf(Nivel.ADMIN, Nivel.FINANCEIRO, Nivel.GERENCIA, Nivel.SUPORTE)
    val repositorio = remember { mutableStateListOf<Colaborador>() }
    var edicao by remember { mutableStateOf(Colaborador()) }
    var alerta by remember { mutableStateOf("") }


    fun limpar() {
        edicao = Colaborador()
        alerta = ""
    }

    fun salvar() {
        if (edicao.nome.isBlank() || edicao.email.isBlank()) {
            alerta = "Preencha todos os campos!"
            return
        }

        repositorio.removeIf { it.id == edicao.id }
        repositorio.add(edicao.copy())
        repositorio.sortBy { it.nome }
        limpar()
    }

    @Composable
    fun CampoNome() {
        OutlinedTextField(
            value = edicao.nome,
            onValueChange = { edicao = edicao.copy(nome = it) },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth()
        )
    }

    @Composable
    fun CampoEmail() {
        OutlinedTextField(
            value = edicao.email,
            onValueChange = { edicao = edicao.copy(email = it) },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
    }

    @Composable
    fun SeletorNivel() {
        Text("Nível:")
        Spacer(Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            niveis.forEach { nivel ->
                val selecionado = nivel == edicao.nivel
                Box(
                    modifier = Modifier
                        .background(if (selecionado) Color.Blue else Color.LightGray)
                        .clickable { edicao = edicao.copy(nivel = nivel) }
                        .padding(12.dp)
                ) {
                    Text(
                        text = nivel.name,
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

    @Composable
    fun BotaoEditarItem(colaborador: Colaborador) {
        IconButton(onClick = {
            edicao = colaborador.copy()
        }) {
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = "Editar"
            )
        }
    }

    @Composable
    fun BotaoRemoverItem(colaborador: Colaborador) {
        IconButton(onClick = {
            repositorio.remove(colaborador)
        }) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Excluir"
            )
        }
    }

    @Composable
    fun CardColaborador(colaborador: Colaborador) {
        Card(
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text("Nome: ${colaborador.nome}")
                    Text("Email: ${colaborador.email}")
                    Text("Nível: ${colaborador.nivel}")
                }
                Row {
                    BotaoEditarItem(colaborador)
                    BotaoRemoverItem(colaborador)
                }
            }
        }
    }

    @Composable
    fun ListaDeColaboradores() {
        if (repositorio.count() == 0) return

        Text("Lista de usuários:")
        Spacer(Modifier.height(8.dp))
        LazyColumn {
            items(repositorio, key = { it.id }) { colaborador ->
                CardColaborador(colaborador)
            }
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

        Spacer(Modifier.height(24.dp))
        ListaDeColaboradores()
    }

}
