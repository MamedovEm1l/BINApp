package com.example.bankcardapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Entity
import androidx.room.PrimaryKey


@Database(entities = [BinEntity::class], version = 2)
abstract class BinDatabase : RoomDatabase() {
    abstract fun binDao(): BinDao
}

@Entity
data class BinEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val bin: String,
    val info: String
)




@Dao
interface BinDao {
    @Insert
    suspend fun insertBin(binEntity: BinEntity)

    @Query("SELECT * FROM BinEntity")
    suspend fun getAllBins(): List<BinEntity>
}
