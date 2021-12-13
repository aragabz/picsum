package com.ragabz.picsum.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ragabz.picsum.models.PictureList
import com.ragabz.picsum.models.PictureModel
import kotlinx.coroutines.flow.Flow

@Dao
interface PictureDao {

    /**
     * Insert all
     *
     * @param list
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg list: PictureModel)

    /**
     * Select all pictures
     *
     * @return [PictureList]
     */
    @Query("select * from picturemodel")
    suspend fun selectAllPictures(): PictureList
}