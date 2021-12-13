package com.ragabz.picsum.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ragabz.picsum.data.local.dao.PictureDao
import com.ragabz.picsum.models.PictureModel

const val DATABASE_NAME = "picsum_app_db"

@Database(entities = [PictureModel::class], version = 1)
abstract class PicsumDataBase : RoomDatabase() {

    abstract fun pictureDao(): PictureDao

    companion object {
        fun getDataBase(context: Context) = Room
            .databaseBuilder(
                context,
                PicsumDataBase::class.java,
                DATABASE_NAME
            ).build()
    }
}