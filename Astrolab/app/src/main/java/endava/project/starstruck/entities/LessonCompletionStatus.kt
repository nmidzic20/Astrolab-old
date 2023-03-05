package endava.project.starstruck.entities

data class LessonCompletionStatus(val completed: Boolean, val color: String,
                                  val text: String = if (completed) "Completed" else "")