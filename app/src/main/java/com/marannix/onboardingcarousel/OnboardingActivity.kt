package com.marannix.onboardingcarousel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.marannix.onboardingcarousel.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private lateinit var adapter: OnboardingItemsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setOnboardingItems()
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
}
