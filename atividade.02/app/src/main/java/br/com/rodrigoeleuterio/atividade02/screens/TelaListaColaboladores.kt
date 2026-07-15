package br.com.rodrigoeleuterio.atividade02.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.rodrigoeleuterio.atividade02.model.Colaborador
import br.com.rodrigoeleuterio.atividade02.navigation.CadastroRoute
import br.com.rodrigoeleuterio.atividade02.service.ColaboradorService

@Composable
fun TelaListaColaboradores(navController: NavController) {

    val colaboradores = remember { mutableStateListOf<Colaborador>() }
    colaboradores.clear()
    colaboradores.addAll(ColaboradorService.list());

    @Composable
    fun BotaoEditarItem(colaborador: Colaborador) {
        IconButton(onClick = {
            navController.navigate(CadastroRoute(colaborador.id.toString(), colaborador.nome, colaborador.email, colaborador.nivel.name))
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
            ColaboradorService.remove(colaborador.id)
            colaboradores.clear()
            colaboradores.addAll(ColaboradorService.list());
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
        if (colaboradores.isEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize().padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Lista de colaboradores está vazia", fontSize = 18.sp)
            }

            return
        }

        Text("Lista de usuários:")
        Spacer(Modifier.height(8.dp))
        LazyColumn {
            items(colaboradores, key = { it.id }) { colaborador ->
                CardColaborador(colaborador)
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Spacer(Modifier.height(24.dp))
        ListaDeColaboradores()
    }


}