package endava.project.starstruck.helpers

import endava.project.starstruck.entities.Lesson
import endava.project.starstruck.entities.LessonCompletionStatus

object MockDataLoader {
    fun getDemoData(): List<Lesson> = listOf(
        Lesson("Kepler's Laws", LessonCompletionStatus(false, "#c1241a")),
        Lesson("History of Astronomy", LessonCompletionStatus(true, "#377328")),
        Lesson("The Moon", LessonCompletionStatus(true, "#377328")),
        Lesson("The Sun", LessonCompletionStatus(false, "#c1241a"))
    )
}