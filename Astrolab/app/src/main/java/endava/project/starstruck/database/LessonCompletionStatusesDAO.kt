package endava.project.starstruck.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import endava.project.starstruck.entities.LessonCompletionStatus

@Dao
interface LessonCompletionStatusesDAO {
    @Query("SELECT * FROM lesson_completion_statuses")
    fun getAllCompletionStatuses(): List<LessonCompletionStatus>

    @Query("SELECT * FROM lesson_completion_statuses WHERE id = :id")
    fun getCompletionStatusById(id: Int): LessonCompletionStatus

    @Insert
    fun insertCompletionStatus(vararg category: LessonCompletionStatus)
}
