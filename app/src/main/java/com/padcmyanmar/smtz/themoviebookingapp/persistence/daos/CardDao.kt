package com.padcmyanmar.smtz.themoviebookingapp.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcmyanmar.smtz.themoviebookingapp.data.vos.CardVO

@Dao
interface CardDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCardList(cardList: List<CardVO>)

    @Query("SELECT * FROM cards")
    fun getAllCards() : List<CardVO>

    @Query("DELETE FROM cards")
    fun deleteAllCards()
}