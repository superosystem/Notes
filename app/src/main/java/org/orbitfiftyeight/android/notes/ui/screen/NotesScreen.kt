package org.orbitfiftyeight.android.notes.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import org.orbitfiftyeight.android.notes.domain.model.NoteModel
import org.orbitfiftyeight.android.notes.ui.components.Note
import org.orbitfiftyeight.android.notes.viewmodel.MainViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NotesScreen(
  viewModel: MainViewModel,
  onOpenNavigationDrawer: () -> Unit = {},
  onNavigateToSaveNote: () -> Unit = {}
) {
  val notes: List<NoteModel> by viewModel
    .notesNotInTrash
    .observeAsState(listOf())

  val scaffoldState: ScaffoldState = rememberScaffoldState()

  Scaffold(
    scaffoldState = scaffoldState,
    topBar = {
      TopAppBar(
        title = {
          Text(
            text = "Notes",
            color = MaterialTheme.colors.onPrimary
          )
        },
        navigationIcon = {
          IconButton(onClick = {
            onOpenNavigationDrawer.invoke()
          }) {
            Icon(
              imageVector = Icons.Filled.List,
              contentDescription = "Drawer Button"
            )
          }
        }
      )
    },
    floatingActionButtonPosition = FabPosition.End,
    floatingActionButton = {
      FloatingActionButton(
        onClick = {
          viewModel.onCreateNewNoteClick()
          onNavigateToSaveNote.invoke()
        },
        contentColor = MaterialTheme.colors.background,
        content = {
          Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add Note Button"
          )
        }
      )
    },
    content = {
      if (notes.isNotEmpty()) {
        NotesList(
          notes = notes,
          onNoteCheckedChange = {
            viewModel.onNoteCheckedChange(it)
          },
          onNoteClick = {
            viewModel.onNoteClick(it)
            onNavigateToSaveNote.invoke()
          }
        )
      }
    }
  )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun NotesList(
  notes: List<NoteModel>,
  onNoteCheckedChange: (NoteModel) -> Unit,
  onNoteClick: (NoteModel) -> Unit
) {
  LazyColumn {
    items(count = notes.size) { noteIndex ->
      val note = notes[noteIndex]
      Note(
        note = note,
        onNoteClick = onNoteClick,
        onNoteCheckedChange = onNoteCheckedChange,
        isSelected = false
      )
    }
  }
}

@Preview
@Composable
private fun NotesListPreview() {
  NotesList(
    notes = listOf(
      NoteModel(1, "Note 1", "Content 1", null),
      NoteModel(2, "Note 2", "Content 2", false),
      NoteModel(3, "Note 3", "Content 3", true)
    ),
    onNoteCheckedChange = {},
    onNoteClick = {}
  )
}
