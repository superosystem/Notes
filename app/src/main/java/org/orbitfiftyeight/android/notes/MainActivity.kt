package org.orbitfiftyeight.android.notes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import org.orbitfiftyeight.android.notes.routing.Screen
import org.orbitfiftyeight.android.notes.theme.NotesTheme
import org.orbitfiftyeight.android.notes.ui.components.AppDrawer
import org.orbitfiftyeight.android.notes.ui.screen.NotesScreen
import org.orbitfiftyeight.android.notes.ui.screen.SaveNoteScreen
import org.orbitfiftyeight.android.notes.ui.screen.TrashScreen
import org.orbitfiftyeight.android.notes.viewmodel.MainViewModel
import org.orbitfiftyeight.android.notes.viewmodel.MainViewModelFactory

/**
 * Main activity for the app.
 */
class MainActivity : AppCompatActivity() {

  private val viewModel: MainViewModel by viewModels(factoryProducer = {
    MainViewModelFactory(
      this,
      (application as NotesApplication).dependencyInjector.repository
    )
  })

  @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
  override fun onCreate(savedInstanceState: Bundle?) {
    // Switch to AppTheme for displaying the activity
    setTheme(R.style.Theme_Notes)

    super.onCreate(savedInstanceState)

    setContent {
      NotesTheme {
        val coroutineScope = rememberCoroutineScope()
        val scaffoldState: ScaffoldState = rememberScaffoldState()
        val navController = rememberNavController()
        val navBackStackEntry
          by navController.currentBackStackEntryAsState()

        Scaffold(
          scaffoldState = scaffoldState,
          drawerContent = {
            AppDrawer(
              currentScreen = Screen.fromRoute(
                navBackStackEntry?.destination?.route
              ),
              onScreenSelected = { screen ->
                navController.navigate(screen.route) {
                  popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                  }
                  launchSingleTop = true
                  restoreState = true
                }
                coroutineScope.launch {
                  scaffoldState.drawerState.close()
                }
              }
            )
          },
          content = {
            MainActivityScreen(
              navController = navController,
              viewModel = viewModel,
              openNavigationDrawer = {
                coroutineScope.launch {
                  scaffoldState.drawerState.open()
                }
              }
            )
          }
        )
      }
    }
  }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun MainActivityScreen(
  navController: NavHostController,
  viewModel: MainViewModel,
  openNavigationDrawer: () -> Unit
) {
  NavHost(
    navController = navController,
    startDestination = Screen.Notes.route
  ) {
    composable(Screen.Notes.route) {
      NotesScreen(
        viewModel,
        openNavigationDrawer,
        { navController.navigate(Screen.SaveNote.route) }
      )
    }
    composable(Screen.SaveNote.route) {
      SaveNoteScreen(
        viewModel,
        { navController.popBackStack() }
      )
    }
    composable(Screen.Trash.route) {
      TrashScreen(viewModel, openNavigationDrawer)
    }
  }
}
