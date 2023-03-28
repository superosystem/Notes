package org.orbitfiftyeight.android.notes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.orbitfiftyeight.android.notes.data.repository.Repository
import org.orbitfiftyeight.android.notes.domain.model.NoteModel

/**
 * View model used for storing the global app state.
 *
 * This view model is used for all screens.
 */
class MainViewModel(
    private val repository: Repository
) : ViewModel() {
    val notesNotInTrash: LiveData<List<NoteModel>> by lazy {
        repository.getAllNotesNotInTrash().asLiveData()
    }

    fun onCreateNewNoteClick() {

    }

    fun onNoteClick(note: NoteModel) {

    }

    fun onNoteCheckedChage(note: NoteModel) {
        viewModelScope.launch(Dispatchers.Default) {
            repository.insertNote(note)
        }
    }
}
