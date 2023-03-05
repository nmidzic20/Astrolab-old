package endava.project.starstruck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import endava.project.starstruck.adapters.MainPagerAdapter
import endava.project.starstruck.databinding.ActivityMainBinding
import endava.project.starstruck.fragments.LessonsOverviewFragment
import endava.project.starstruck.fragments.PhotoGalleryFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var tabLayout: TabLayout
    lateinit var viewPager2: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setFragments()
    }

    private fun setFragments()
    {
        tabLayout = binding.tabs
        viewPager2 = binding.viewpager

        val mainPagerAdapter = MainPagerAdapter(supportFragmentManager, lifecycle)

        mainPagerAdapter.addFragment(
            MainPagerAdapter.FragmentItem(
                R.string.lessons_overview,
                R.drawable.ic_baseline_assignment_24,
                LessonsOverviewFragment::class
            )
        )
        mainPagerAdapter.addFragment(
            MainPagerAdapter.FragmentItem(
                R.string.photo_gallery,
                R.drawable.ic_baseline_photo_library_24,
                PhotoGalleryFragment::class
            )
        )
        viewPager2.adapter = mainPagerAdapter

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.setText(mainPagerAdapter.fragmentItems[position].titleRes)
            tab.setIcon(mainPagerAdapter.fragmentItems[position].iconRes)
        }.attach()
    }
}