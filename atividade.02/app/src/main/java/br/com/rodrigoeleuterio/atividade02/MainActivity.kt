package br.com.rodrigoeleuterio.atividade02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.QuestionMark
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.com.rodrigoeleuterio.atividade02.navigation.CadastroRoute
import br.com.rodrigoeleuterio.atividade02.navigation.ListaColaboladoresRoute
import br.com.rodrigoeleuterio.atividade02.navigation.NavGraph
import br.com.rodrigoeleuterio.atividade02.navigation.SobreRoute
import br.com.rodrigoeleuterio.atividade02.ui.theme.Atividade02Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold()
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Scaffold() {
    val navController = rememberNavController()
    val telaAtual = navController.currentBackStackEntryAsState().value?.destination

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { TopAppBar(title = { Text("Cadastro de Colaboradores") }) },
        bottomBar = { BottomAppBar {
            NavigationBar() {
                NavigationBarItem(
                    selected = telaAtual?.hasRoute<CadastroRoute>() ?: false,
                    onClick = { navController.navigate(CadastroRoute("", "", "", "")) { launchSingleTop = true } },
                    icon = { Icon( imageVector = Icons.Default.AddBox, contentDescription = null ) },
                    label = { Text("Novo") }
                )

                NavigationBarItem(
                    selected = telaAtual?.hasRoute<ListaColaboladoresRoute>() ?: false,
                    onClick = { navController.navigate(ListaColaboladoresRoute) { launchSingleTop = true }},
                    icon = { Icon( imageVector = Icons.Default.FilterList, contentDescription = null ) },
                    label = { Text("Lista") }
                )

                NavigationBarItem(
                    selected = telaAtual?.hasRoute<SobreRoute>() ?: false,
                    onClick = { navController.navigate(SobreRoute) { launchSingleTop = true }},
                    icon = { Icon( imageVector = Icons.Default.QuestionMark, contentDescription = null ) },
                    label = { Text("Sobre") }
                )
            }
        } }
    ) { padding ->
        NavGraph(navController, Modifier.padding(padding))
    }
}
