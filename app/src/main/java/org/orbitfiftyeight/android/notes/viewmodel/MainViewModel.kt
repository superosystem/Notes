package org.orbitfiftyeight.android.notes.viewmodel

import androidx.lifecycle.ViewModel
import org.orbitfiftyeight.android.notes.data.repository.Repository

/**
 * View model used for storing the global app state.
 *
 * This view model is used for all screens.
 */
class MainViewModel(private val repository: Repository) : ViewModel() {

}
