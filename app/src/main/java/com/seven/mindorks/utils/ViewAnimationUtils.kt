package com.seven.mindorks.utils

import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation

/**
 * at 2019/12/10
 * at 10:01
 * summary:
 */
object ViewAnimationUtils {
    fun scaleAnimateView(view: View) {
        val animation = ScaleAnimation(
            1.15f, 1f, 1.15f, 1f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        ).apply { duration = 100 }
        view.animation = animation
        animation.start()
    }
}