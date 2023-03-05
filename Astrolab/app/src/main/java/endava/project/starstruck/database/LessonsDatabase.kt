package endava.project.starstruck.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import endava.project.starstruck.entities.Lesson
import endava.project.starstruck.entities.LessonCompletionStatus

@Database(version = 1, entities = [Lesson::class, LessonCompletionStatus::class], exportSchema = false)
abstract class LessonsDatabase : RoomDatabase() {
    abstract fun getLessonsDAO(): LessonsDAO
    abstract fun getLessonCompletionStatusesDAO(): LessonCompletionStatusesDAO

    companion object {
        @Volatile
        private var implementedInstance: LessonsDatabase? = null
        fun getInstance(): LessonsDatabase {
            if (implementedInstance == null) {
                throw NullPointerException("Database instance has not yet been created!")
            }
            return implementedInstance!!
        }
        fun buildInstance(context: Context) {
            if (implementedInstance == null) {
                val instanceBuilder = Room.databaseBuilder(
                    context,
                    LessonsDatabase::class.
                    java,
                    "tasks.db"
                )
                instanceBuilder.fallbackToDestructiveMigration()
                instanceBuilder.allowMainThreadQueries()
                instanceBuilder.build()
                implementedInstance = instanceBuilder.build()
            }
        }
    }
}