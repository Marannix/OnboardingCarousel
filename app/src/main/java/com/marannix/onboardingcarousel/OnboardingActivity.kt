package com.marannix.onboardingcarousel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.marannix.onboardingcarousel.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var adapter: OnboardingItemsAdapter
    private lateinit var indicatorsContainerLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        indicatorsContainerLayout = binding.indicatorsContainer
        setOnboardingItems()
        setupIndicators()
        setCurrentIndicator(0)
        setViewPagerListener()
        setClickListeners()
    }

    private fun setOnboardingItems() = with(binding) {
        adapter = OnboardingItemsAdapter()
        adapter.setItems(
            listOf(
                OnboardingItem(
                    image = R.drawable.ic_undraw_tutorial,
                    title = "Start of Onboarding Screen",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus venenatis viverra interdum."
                ),
                OnboardingItem(
                    image = R.drawable.ic_undraw_learning,
                    title = "Middle of Onboarding Screen",
                    description = "Vestibulum condimentum est hendrerit interdum euismod."
                ),
                OnboardingItem(
                    image = R.drawable.ic_undraw_finish_line,
                    title = "End of Onboarding Screen",
                    description = "Duis venenatis interdum tincidunt."
                )
            )
        )
        onboardingViewPager.adapter = adapter
    }

    private fun setClickListeners() = with(binding) {
        onboardingSkipButton.setOnClickListener {
            navigateToHomeActivity()
        }
        onboardingNextButton.setOnClickListener {
            if (onboardingViewPager.currentItem + 1 < adapter.itemCount) {
                onboardingViewPager.currentItem += 1
            } else {
                navigateToHomeActivity()
            }
        }
    }

    private fun navigateToHomeActivity() {
        startActivity(HomeActivity.getCallingIntent(this@OnboardingActivity))
        finish()
    }

    private fun setViewPagerListener() = with(binding) {
        onboardingViewPager.adapter = adapter
        onboardingViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })

        (onboardingViewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER
    }

    //We will now iterate through the size of the adapter and create multiple inactive background states
    private fun setupIndicators() = with(binding) {
        indicatorsContainerLayout = indicatorsContainer
        val indicators = arrayOfNulls<ImageView>(adapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8, 8, 8, 0)

        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
                it.layoutParams = layoutParams
                indicatorsContainerLayout.addView(it)
            }
        }
    }

    private fun setCurrentIndicator(position: Int) = with(binding) {
        val childCount = indicatorsContainerLayout.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorsContainerLayout.getChildAt(i) as ImageView
            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_background
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_background
                    )
                )
            }
        }
    }
}
