package org.orbitfiftyeight.android.notes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch
import org.orbitfiftyeight.android.notes.routing.Screen
import org.orbitfiftyeight.android.notes.theme.NotesTheme
import org.orbitfiftyeight.android.notes.ui.components.AppDrawer
import org.orbitfiftyeight.android.notes.ui.components.Note
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
                Scaffold(
                    scaffoldState = scaffoldState,
                    drawerContent = {
                        AppDrawer(
                            currentScreen = Screen.Notes,
                            onScreenSelected = {
                                /* TODO */
                                coroutineScope.launch {
                                    scaffoldState.drawerState.close()
                                }
                            })
                    },
                    content = {
                        Note()
                    }
                )
            }
        }
    }
}
