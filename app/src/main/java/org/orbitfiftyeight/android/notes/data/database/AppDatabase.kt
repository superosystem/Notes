package org.orbitfiftyeight.android.notes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import org.orbitfiftyeight.android.notes.data.database.dao.ColorDao
import org.orbitfiftyeight.android.notes.data.database.dao.NoteDao
import org.orbitfiftyeight.android.notes.data.database.model.ColorDbModel
import org.orbitfiftyeight.android.notes.data.database.model.NoteDbModel

/**
 * App's database.
 *
 * It contains two tables: Note table and Color table.
 */
@Database(entities = [NoteDbModel::class, ColorDbModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

  companion object {
    const val DATABASE_NAME = "note-maker-database"
  }

  abstract fun noteDao(): NoteDao

  abstract fun colorDao(): ColorDao
}
