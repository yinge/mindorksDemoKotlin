package com.seven.mindorks.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * at 2019/12/3
 * at 10:49
 * summary:
 */
abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(position: Int)
}