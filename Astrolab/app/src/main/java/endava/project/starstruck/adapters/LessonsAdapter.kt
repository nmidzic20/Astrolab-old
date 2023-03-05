package endava.project.starstruck.adapters

import android.view.LayoutInflater
import android.view.SurfaceView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import endava.project.starstruck.R
import endava.project.starstruck.database.LessonsDatabase
import endava.project.starstruck.entities.Lesson

class LessonsAdapter(
    private val lessonsList : MutableList<Lesson>,
    private val onLessonModified: ((lessonId: Int) -> Unit)
) : RecyclerView.Adapter<LessonsAdapter.LessonViewHolder>() {

    inner class LessonViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val lessonName: TextView
        private val lessonCompletionStatusColor: SurfaceView
        private val lessonCompletionStatusText: TextView

        init {
            lessonName = view.findViewById(R.id.tv_lesson_name)
            lessonCompletionStatusColor = view.findViewById(R.id.sv_lesson_completion_status_color)
            lessonCompletionStatusText = view.findViewById(R.id.tv_lesson_completion_status_text)

            view.setOnLongClickListener {
                val currentLesson = lessonsList[adapterPosition]

                val alertDialogBuilder = AlertDialog.Builder(view.context)
                    .setTitle(lessonName.text)
                    .setNegativeButton(view.context.getString(R.string.cancel)) { dialog, _ ->
                        dialog.cancel();
                    }

                if (!currentLesson.completionStatus.completed)
                {
                    setPositiveButton(alertDialogBuilder, view.context.getString(R.string.mark_lesson_completed))
                }
                else
                {
                    setPositiveButton(alertDialogBuilder, view.context.getString(R.string.mark_lesson_incomplete))
                }

                alertDialogBuilder.show()

                return@setOnLongClickListener true
            }
        }

        private fun setPositiveButton(alertDialogBuilder : AlertDialog.Builder, text : String)
        {
            alertDialogBuilder.setPositiveButton(text) { _, _ ->
                val modifiedLesson = lessonsList[adapterPosition]

                modifiedLesson.completionStatusId =
                    when (text) {
                        view.context.getString(R.string.mark_lesson_incomplete) -> 1
                        else -> 2
                    }
                LessonsDatabase.getInstance().getLessonsDAO().insertLesson(modifiedLesson)
                onLessonModified.invoke(modifiedLesson.id)

            }

        }


        fun bind(lesson: Lesson) {
            lessonName.text = lesson.name
            lessonCompletionStatusColor.setBackgroundColor(lesson.completionStatus.color.toColorInt())
            lessonCompletionStatusText.text = lesson.completionStatus.text
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val lessonView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.lesson_list_item, parent, false)
        return LessonViewHolder(lessonView)
    }

    override fun getItemCount(): Int = lessonsList.size

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.bind(lessonsList[position])
    }

    fun modifyLesson(modifiedLesson: Lesson) {
        val lessonIndexInList = modifiedLesson.id - 1
        lessonsList.set(lessonIndexInList, modifiedLesson)
        notifyItemChanged(lessonIndexInList)
    }
}