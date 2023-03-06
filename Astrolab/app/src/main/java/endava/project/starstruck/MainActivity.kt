package endava.project.starstruck

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import endava.project.starstruck.adapters.MainPagerAdapter
import endava.project.starstruck.database.LessonsDatabase
import endava.project.starstruck.databinding.ActivityMainBinding
import endava.project.starstruck.fragments.DailyPhotoFragment
import endava.project.starstruck.fragments.LessonsOverviewFragment
import endava.project.starstruck.fragments.PhotoGalleryFragment
import endava.project.starstruck.helpers.MockDataLoader

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var tabLayout: TabLayout
    lateinit var viewPager2: ViewPager2

    lateinit var navDrawerLayout: DrawerLayout
    lateinit var navDrawerView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val mainPagerAdapter = MainPagerAdapter(supportFragmentManager, lifecycle)

        setFragments(mainPagerAdapter)
        setNavigationDrawer(mainPagerAdapter)

        LessonsDatabase.buildInstance(applicationContext)
        MockDataLoader.loadMockData()
    }

    private fun setFragments(mainPagerAdapter : MainPagerAdapter)
    {
        tabLayout = binding.tabs
        viewPager2 = binding.viewpager

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
        mainPagerAdapter.addFragment(
            MainPagerAdapter.FragmentItem(
                R.string.daily_photo,
                R.drawable.baseline_star_purple500_24,
                DailyPhotoFragment::class
            )
        )
        viewPager2.adapter = mainPagerAdapter

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.setText(mainPagerAdapter.fragmentItems[position].titleRes)
            tab.setIcon(mainPagerAdapter.fragmentItems[position].iconRes)
        }.attach()
    }

    private fun setNavigationDrawer(mainPagerAdapter : MainPagerAdapter)
    {
        navDrawerLayout = binding.navDrawerLayout
        navDrawerView = binding.navView

        mainPagerAdapter.fragmentItems.withIndex().forEach { (index, fragmentItem) ->
            navDrawerView.menu
                .add(fragmentItem.titleRes)
                .setIcon(fragmentItem.iconRes)
                .setCheckable(true)
                .setChecked((index == 0))
                .setOnMenuItemClickListener {
                    viewPager2.setCurrentItem(index, true)
                    navDrawerLayout.closeDrawers()
                    return@setOnMenuItemClickListener true
                }
        }

        //update checked item in nav menu as soon as page is changed
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                navDrawerView.menu.getItem(position).isChecked = true
            }
        })
    }
}