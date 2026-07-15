package br.com.rodrigoeleuterio.atividade02.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import br.com.rodrigoeleuterio.atividade02.screens.TelaCadastro
import br.com.rodrigoeleuterio.atividade02.screens.TelaListaColaboradores
import br.com.rodrigoeleuterio.atividade02.screens.TelaSobre


@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier) {

    NavHost(
        navController = navController,
        startDestination = ListaColaboladoresRoute,
        modifier = modifier
    ) {
        composable<ListaColaboladoresRoute> { TelaListaColaboradores(navController) }
        composable<SobreRoute> { TelaSobre() }
        composable<CadastroRoute> { entry ->
            val dto = entry.toRoute<CadastroRoute>()
            TelaCadastro(navController, dto)
        }
    }

}