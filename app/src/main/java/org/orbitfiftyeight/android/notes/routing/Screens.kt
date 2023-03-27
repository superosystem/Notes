package org.orbitfiftyeight.android.notes.routing

/**
 * Class defining all possible screens in the app.
 */
sealed class Screen(val route: String) {
  object Notes : Screen("Notes")
  object SaveNote : Screen("SaveNote")
  object Trash : Screen("Trash")
}