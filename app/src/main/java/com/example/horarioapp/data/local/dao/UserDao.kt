package com.example.horarioapp.data.local.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.horarioapp.data.local.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    // INSERT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    // UPDATE
    @Update
    suspend fun updateUser(user: UserEntity)

    // DELETE
    @Delete
    suspend fun deleteUser(user: UserEntity)

    @Query("DELETE FROM users WHERE uid = :uid")
    suspend fun deleteUserById(uid: String)

    // SELECT
    @Query("SELECT * FROM users WHERE uid = :uid LIMIT 1")
    fun getUserById(uid: String): Flow<UserEntity?>

    // Obtener el usuario actualmente logueado (debería haber solo uno)
    @Query("SELECT * FROM users LIMIT 1")
    fun getCurrentUser(): Flow<UserEntity?>

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    suspend fun getUserByEmail(email: String): UserEntity?

    // Verificar si existe un usuario
    @Query("SELECT EXISTS(SELECT 1 FROM users WHERE uid = :uid)")
    suspend fun userExists(uid: String): Boolean

    // Limpiar todos los usuarios
    @Query("DELETE FROM users")
    suspend fun clearAllUsers()
}