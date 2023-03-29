package org.orbitfiftyeight.android.notes.data.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteDbModel(
  @PrimaryKey(autoGenerate = true) val id: Long = 0,
  @ColumnInfo(name = "title") val title: String,
  @ColumnInfo(name = "content") val content: String,
  @ColumnInfo(name = "can_be_checked_off") val canBeCheckedOff: Boolean,
  @ColumnInfo(name = "is_checked_off") val isCheckedOff: Boolean,
  @ColumnInfo(name = "color_id") val colorId: Long,
  @ColumnInfo(name = "in_trash") val isInTrash: Boolean
) {
}
