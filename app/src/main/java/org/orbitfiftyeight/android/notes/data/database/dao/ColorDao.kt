package org.orbitfiftyeight.android.notes.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.orbitfiftyeight.android.notes.data.database.model.ColorDbModel

/**
 * Dao for managing Color table in the database.
 */
@Dao
interface ColorDao {

  @Query("SELECT * FROM ColorDbModel")
  fun getAll(): Flow<List<ColorDbModel>>

  @Query("SELECT * FROM ColorDbModel")
  suspend fun getAllSync(): List<ColorDbModel>

  @Query("SELECT * FROM ColorDbModel WHERE id LIKE :id")
  fun findById(id: Long): Flow<ColorDbModel>

  @Query("SELECT * FROM ColorDbModel WHERE id LIKE :id")
  suspend fun findByIdSync(id: Long): ColorDbModel

  @Insert
  suspend fun insertAll(vararg colorDbModels: ColorDbModel)
}