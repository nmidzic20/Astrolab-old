package endava.project.starstruck.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import endava.project.starstruck.R
import endava.project.starstruck.adapters.LessonsAdapter
import endava.project.starstruck.database.LessonsDatabase
import endava.project.starstruck.helpers.MockDataLoader

class LessonsOverviewFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private val lessonsDao = LessonsDatabase.getInstance().getLessonsDAO()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lessons_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.rv_lessons_overview)
        recyclerView.adapter = LessonsAdapter(lessonsDao.getAllLessons().toMutableList()) { lessonId ->
            val lessonsAdapter = recyclerView.adapter as LessonsAdapter
            lessonsAdapter.modifyLesson(lessonsDao.getLesson((lessonId)))
        }
        recyclerView.layoutManager = LinearLayoutManager(view.context)
    }
}