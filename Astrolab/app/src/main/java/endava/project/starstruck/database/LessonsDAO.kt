package endava.project.starstruck.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import endava.project.starstruck.entities.Lesson

@Dao
interface LessonsDAO {
    @Query("SELECT * FROM lessons WHERE id = :id")
    fun getLesson(id: Int): Lesson

    @Query("SELECT * FROM lessons")
    fun getAllLessons(): List<Lesson>

    @Insert(onConflict = REPLACE)
    fun insertLesson(vararg lesson: Lesson): List<Long>

    @Delete
    fun removeLesson(vararg lesson: Lesson)
}