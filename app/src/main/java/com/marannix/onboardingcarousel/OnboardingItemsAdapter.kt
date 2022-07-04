package com.marannix.onboardingcarousel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.marannix.onboardingcarousel.databinding.OnboardingItemContainerBinding

class OnboardingItemsAdapter :
    RecyclerView.Adapter<OnboardingItemsAdapter.OnboardingItemViewHolder>() {

    private var items: List<OnboardingItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return OnboardingItemViewHolder(
            OnboardingItemContainerBinding.inflate(
                inflater,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OnboardingItemViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setItems(items: List<OnboardingItem>) {
        this.items = items
        notifyDataSetChanged()
    }

    class OnboardingItemViewHolder(private val binding: OnboardingItemContainerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(onboardingItem: OnboardingItem) {
            binding.onboardingItemImage.setImageResource(onboardingItem.image)
            binding.onboardingItemTitle.text = onboardingItem.title
            binding.onboardingItemDescription.text = onboardingItem.description
        }
    }
}
