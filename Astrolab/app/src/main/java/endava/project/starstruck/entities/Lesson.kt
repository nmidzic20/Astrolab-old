package endava.project.starstruck.entities

import androidx.room.*
import endava.project.starstruck.database.LessonsDatabase

@Entity(
    tableName = "lessons",
    foreignKeys = [ForeignKey(
        entity = LessonCompletionStatus::class,
        parentColumns = ["id"],
        childColumns = ["completionStatusId"],
        onDelete = ForeignKey.RESTRICT
    )]
)
data class Lesson(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    @ColumnInfo(name = "completionStatusId", index = true) var completionStatusId: Int
) {
    @delegate:Ignore
    val completionStatus: LessonCompletionStatus by
    lazy {
        LessonsDatabase.getInstance().getLessonCompletionStatusesDAO().getCompletionStatusById(completionStatusId)
    }
}
