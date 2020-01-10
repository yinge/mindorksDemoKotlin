package com.seven.mindorks.utils

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mindorks.placeholderview.SwipePlaceHolderView
import com.seven.mindorks.data.model.api.BlogResponse
import com.seven.mindorks.data.model.others.QuestionCardData
import com.seven.mindorks.ui.feed.blogs.BlogAdapter
import com.seven.mindorks.ui.feed.opensource.OpenSourceAdapter
import com.seven.mindorks.ui.feed.opensource.OpenSourceItemViewModel
import com.seven.mindorks.ui.main.MainViewModel
import com.seven.mindorks.ui.main.QuestionCard

/**
 * at 2019/12/10
 * at 9:23
 * summary:
 */
object BindingUtils {
    @BindingAdapter("adapter")
    @JvmStatic
    fun addBlogItems(recyclerView: RecyclerView, blogs: List<BlogResponse.Blog>?) {
        val adapter = recyclerView.adapter
        adapter?.let {
            (it as BlogAdapter).clearItems()
            it.addItems(blogs)
        }
    }

    @BindingAdapter("adapter")
    @JvmStatic
    fun addOpenSourceItems(recyclerView: RecyclerView, openSources: List<OpenSourceItemViewModel>?) {
        val adapter = recyclerView.adapter
        adapter?.let {
            (it as OpenSourceAdapter).clearItems()
            it.addItems(openSources)
        }
    }

    @BindingAdapter("adapter", "action")
    @JvmStatic
    fun addQuestionItems(
        swipeView: SwipePlaceHolderView,
        cardDates: List<QuestionCardData>,
        action: Int
    ) {
        if (action == MainViewModel.ACTION_ADD_ALL) {
            cardDates?.let {
                swipeView.removeAllViews()
                for (cardDate in cardDates) {
                    swipeView.addView(QuestionCard(cardDate))
                }
                ViewAnimationUtils.scaleAnimateView(swipeView)
            }
        }
    }

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun setImageUrl(imageView: ImageView, imageUrl: String) {
        val context = imageView.context
        Glide.with(context).load(imageUrl).into(imageView)
    }
}