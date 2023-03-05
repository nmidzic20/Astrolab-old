package endava.project.starstruck.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lesson_completion_statuses")
data class LessonCompletionStatus(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val completed: Boolean,
    val color: String,
    val text: String = if (completed) "Completed" else ""
) {
    override fun toString(): String {
        return text
    }
}
