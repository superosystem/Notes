package org.orbitfiftyeight.android.notes.routing

/**
 * Class defining all possible screens in the app.
 */
sealed class Screen(val route: String) {

  companion object {
    fun fromRoute(route: String?): Screen {
      return when(route) {
        Notes.route -> Notes
        SaveNote.route -> SaveNote
        Trash.route -> Trash
        else -> Notes
      }
    }
  }

  object Notes : Screen("Notes")
  object SaveNote : Screen("SaveNote")
  object Trash : Screen("Trash")
}
