package org.orbitfiftyeight.android.notes.data.database.dbmapper

import org.orbitfiftyeight.android.notes.data.database.model.ColorDbModel
import org.orbitfiftyeight.android.notes.data.database.model.NoteDbModel
import org.orbitfiftyeight.android.notes.domain.model.ColorModel
import org.orbitfiftyeight.android.notes.domain.model.NoteModel

interface DbMapper {
  // NoteDbModel -> NoteModel
  fun mapNotes(
    noteDbModels: List<NoteDbModel>,
    colorDbModels: Map<Long, ColorDbModel>
  ): List<NoteModel>
  fun mapNote(noteDbModel: NoteDbModel, colorDbModel: ColorDbModel): NoteModel

  // ColorDbModel -> ColorModel
  fun mapColors(colorDbModels: List<ColorDbModel>): List<ColorModel>
  fun mapColor(colorDbModel: ColorDbModel): ColorModel

  // NoteModel -> NoteDbModel
  fun mapDbNote(note: NoteModel): NoteDbModel

  // ColorModel -> ColorDbModel
  fun mapDbColors(colors: List<ColorModel>): List<ColorDbModel>
  fun mapDbColor(color: ColorModel): ColorDbModel
}
