package org.orbitfiftyeight.android.notes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.orbitfiftyeight.android.notes.data.repository.Repository
import org.orbitfiftyeight.android.notes.domain.model.ColorModel
import org.orbitfiftyeight.android.notes.domain.model.NoteModel

/**
 * View model used for storing the global app state.
 *
 * This view model is used for all screens.
 */
class MainViewModel(private val repository: Repository) : ViewModel() {

  val notesNotInTrash: LiveData<List<NoteModel>> by lazy {
    repository.getAllNotesNotInTrash().asLiveData()
  }

  private var _noteEntry = MutableStateFlow<NoteModel>(NoteModel())
  val noteEntry: LiveData<NoteModel> = _noteEntry.asLiveData()

  val colors: LiveData<List<ColorModel>> by lazy {
    repository.getAllColors().asLiveData()
  }

  val notesInTrash: LiveData<List<NoteModel>> by lazy {
    repository.getAllNotesInTrash().asLiveData()
  }

  private var _selectedNotes = MutableStateFlow<List<NoteModel>>(listOf())
  val selectedNotes: LiveData<List<NoteModel>> = _selectedNotes.asLiveData()

  fun onCreateNewNoteClick() {
    _noteEntry.value = NoteModel()
  }

  fun onNoteClick(note: NoteModel) {
    _noteEntry.value = note
  }

  fun onNoteCheckedChange(note: NoteModel) {
    viewModelScope.launch(Dispatchers.Default) {
      repository.insertNote(note)
    }
  }

  fun onNoteSelected(note: NoteModel) {
    _selectedNotes.value = _selectedNotes.value!!.toMutableList().apply {
      if (contains(note)) {
        remove(note)
      } else {
        add(note)
      }
    }
  }

  fun restoreNotes(notes: List<NoteModel>) {
    viewModelScope.launch(Dispatchers.Default) {
      repository.restoreNotesFromTrash(notes.map { it.id })
      withContext(Dispatchers.Main) {
        _selectedNotes.value = listOf()
      }
    }
  }

  fun permanentlyDeleteNotes(notes: List<NoteModel>) {
    viewModelScope.launch(Dispatchers.Default) {
      repository.deleteNotes(notes.map { it.id })
      withContext(Dispatchers.Main) {
        _selectedNotes.value = listOf()
      }
    }
  }

  fun onNoteEntryChange(note: NoteModel) {
    _noteEntry.value = note
  }

  fun saveNote(note: NoteModel) {
    viewModelScope.launch(Dispatchers.Default) {
      repository.insertNote(note)

      withContext(Dispatchers.Main) {
        _noteEntry.value = NoteModel()
      }
    }
  }

  fun moveNoteToTrash(note: NoteModel) {
    viewModelScope.launch(Dispatchers.Default) {
      repository.moveNoteToTrash(note.id)
    }
  }
}
