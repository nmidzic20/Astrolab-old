package endava.project.starstruck.helpers

import endava.project.starstruck.database.LessonsDatabase
import endava.project.starstruck.entities.Lesson
import endava.project.starstruck.entities.LessonCompletionStatus

object MockDataLoader {
    fun loadMockData() {
        val lessonsDao = LessonsDatabase.getInstance().getLessonsDAO()
        val lessonCompletionStatusesDao = LessonsDatabase.getInstance().getLessonCompletionStatusesDAO()

        if (lessonsDao.getAllLessons().isEmpty() && lessonCompletionStatusesDao.getAllCompletionStatuses().isEmpty())
        {
            val completionStatuses = arrayOf(
                LessonCompletionStatus(1, false, "#c1241a"),
                LessonCompletionStatus(2, true, "#377328")
            )
            lessonCompletionStatusesDao.insertCompletionStatus(*completionStatuses)
            val dbCompletionStatuses = lessonCompletionStatusesDao.getAllCompletionStatuses()
            val lessons = arrayOf(
                Lesson(1,"History of Astronomy", dbCompletionStatuses[1].id),
                Lesson(2,"The Moon", dbCompletionStatuses[0].id),
                Lesson(3,"The Sun", dbCompletionStatuses[1].id),
                Lesson(4,"Kepler's Laws", dbCompletionStatuses[0].id)
                )
            lessonsDao.insertLesson(*lessons)
        }
    }
}