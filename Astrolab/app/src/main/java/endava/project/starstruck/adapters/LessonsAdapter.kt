package endava.project.starstruck.adapters

import android.view.LayoutInflater
import android.view.SurfaceView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import endava.project.starstruck.R
import endava.project.starstruck.entities.Lesson

class LessonsAdapter(private val lessonsList : List<Lesson>) : RecyclerView.Adapter<LessonsAdapter.LessonViewHolder>() {
    inner class LessonViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val lessonName: TextView
        private val lessonCompletionStatusColor: SurfaceView
        private val lessonCompletionStatusText: TextView

        init {
            lessonName = view.findViewById(R.id.tv_lesson_name)
            lessonCompletionStatusColor = view.findViewById(R.id.sv_lesson_completion_status_color)
            lessonCompletionStatusText = view.findViewById(R.id.tv_lesson_completion_status_text)
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
}