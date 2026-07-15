package br.com.rodrigoeleuterio.atividade02.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TelaSobre() {

    Column(
        modifier = Modifier.fillMaxSize().padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Autor: Rodrigo Eleuterio da Silva Santos", fontSize = 18.sp)
        Text(text = "App: Cadastro de Colaboradores", fontSize = 18.sp)
        Text(text = "Versão: 1.0.0", fontSize = 18.sp)
    }
}