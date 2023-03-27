package org.orbitfiftyeight.android.notes.data.repository

import kotlinx.coroutines.flow.Flow
import org.orbitfiftyeight.android.notes.domain.model.ColorModel
import org.orbitfiftyeight.android.notes.domain.model.NoteModel

/**
 * Allows communication with the app's database.
 */
interface Repository {

  // notes
  fun getAllNotesNotInTrash(): Flow<List<NoteModel>>
  fun getAllNotesInTrash(): Flow<List<NoteModel>>
  fun getNote(id: Long): Flow<NoteModel>
  suspend fun insertNote(note: NoteModel)
  suspend fun deleteNote(id: Long)
  suspend fun deleteNotes(noteIds: List<Long>)
  suspend fun moveNoteToTrash(noteId: Long)
  suspend fun restoreNotesFromTrash(noteIds: List<Long>)

  // colors
  fun getAllColors(): Flow<List<ColorModel>>
  suspend fun getAllColorsSync(): List<ColorModel>
  fun getColor(id: Long): Flow<ColorModel>
  suspend fun getColorSync(id: Long): ColorModel
}