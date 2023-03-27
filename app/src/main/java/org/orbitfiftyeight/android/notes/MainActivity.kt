package org.orbitfiftyeight.android.notes

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
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
        setTheme(org.orbitfiftyeight.android.notes.R.style.Theme_Notes)

        super.onCreate(savedInstanceState)

        setContent {

        }
    }
}
