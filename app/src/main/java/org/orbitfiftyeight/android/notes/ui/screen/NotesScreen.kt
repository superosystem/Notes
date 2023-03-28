package org.orbitfiftyeight.android.notes.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import org.orbitfiftyeight.android.notes.domain.model.NoteModel
import org.orbitfiftyeight.android.notes.ui.components.Note
import org.orbitfiftyeight.android.notes.ui.components.TopBarApp
import org.orbitfiftyeight.android.notes.viewmodel.MainViewModel

@Composable
fun NotesScreen(viewModel: MainViewModel) {
    val notes: List<NoteModel> by viewModel.notesNotInTrash.observeAsState(listOf())

    Column {
        TopBarApp(
            title = "Notes",
            icon = Icons.Filled.List,
            onIconClick = {}
        )
        LazyColumn {
            items(count = notes.size) { noteIndex ->
                val note = notes[noteIndex]
                Note(
                    note = note,
                    onNoteClick = {
                        viewModel.onNoteClick(it)
                    },
                    onNoteCheckedChange = {
                        viewModel.onNoteCheckedChage(it)
                    }
                )
            }
        }
    }
}

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
                onNoteCheckedChange = onNoteCheckedChange
            )
        }
    }
}

@Preview
@Composable
private fun NotesListPreview() {
    NotesList(
        notes = listOf(
            NoteModel(1, "Note 1", "Content Note 1", null),
            NoteModel(2, "Note 2", "Content Note 2", null),
            NoteModel(3, "Note 3", "Content Note 3", false),
            NoteModel(4, "Note 4", "Content Note 4", null),
            NoteModel(5, "Note 5", "Content Note 5", true),
        ),
        onNoteCheckedChange = {},
        onNoteClick = {}
    )
}