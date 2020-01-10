package com.seven.mindorks.ui.feed.opensource

import androidx.databinding.ObservableField

/**
 * at 2019/12/3
 * at 14:16
 * summary:
 */
class OpenSourceItemViewModel(
    imageUrl: String,
    title: String,
    content: String,
    projectUrl: String
) {
    val content = ObservableField<String>().apply {
        this.set(content)
    }

    val imageUrl = ObservableField<String>().apply {
        this.set(imageUrl)
    }

    val projectUrl = ObservableField<String>().also {
        it.set(projectUrl)
    }

    val title = ObservableField<String>().also {
        it.set(title)
    }
}