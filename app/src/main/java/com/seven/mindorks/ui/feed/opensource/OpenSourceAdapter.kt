package com.seven.mindorks.ui.feed.opensource

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.seven.mindorks.databinding.ItemOpenSourceEmptyViewBinding
import com.seven.mindorks.databinding.ItemOpenSourceViewBinding
import com.seven.mindorks.ui.base.BaseViewHolder

/**
 * at 2019/12/3
 * at 14:14
 * summary:
 */
class OpenSourceAdapter :
    RecyclerView.Adapter<BaseViewHolder>() {

    private val mOpenSourceResponseList: MutableList<OpenSourceItemViewModel>
    private lateinit var mListener: OpenSourceAdapterListener

    init {
        mOpenSourceResponseList = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            VIEW_TYPE_EMPTY -> {
                val emptyViewBinding = ItemOpenSourceEmptyViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                EmptyViewHolder(emptyViewBinding)
            }
            VIEW_TYPE_NORMAL -> {
                val openSourceViewBinding = ItemOpenSourceViewBinding.inflate(
                    LayoutInflater.from(
                        parent.context
                    ), parent, false
                )
                OpenSourceViewHolder(openSourceViewBinding)
            }
            else -> {
                val emptyViewBinding = ItemOpenSourceEmptyViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )
                EmptyViewHolder(emptyViewBinding)
            }
        }
    }

    override fun getItemViewType(position: Int): Int =
        if (mOpenSourceResponseList.isNotEmpty()) VIEW_TYPE_NORMAL else VIEW_TYPE_EMPTY

    override fun getItemCount(): Int =
        if (mOpenSourceResponseList.isNotEmpty()) mOpenSourceResponseList.size else 0

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun addItems(repoList: List<OpenSourceItemViewModel>?) {
        repoList?.let {
            mOpenSourceResponseList.addAll(repoList)
            notifyDataSetChanged()
        }
    }

    fun clearItems(){
        mOpenSourceResponseList.clear()
    }

    fun setListener(listener: OpenSourceAdapterListener) {
        mListener = listener
    }

    interface OpenSourceAdapterListener {
        fun onRetryClick()
    }

    inner class EmptyViewHolder(private val openSourceEmptyViewBinding: ItemOpenSourceEmptyViewBinding) :
        BaseViewHolder(openSourceEmptyViewBinding.root),
        OpenSourceEmptyItemViewModel.OpenSourceEmptyItemViewModelListener {
        override fun onBind(position: Int) {
            val emptyItemViewModel = OpenSourceEmptyItemViewModel(this)
            openSourceEmptyViewBinding.viewModel = emptyItemViewModel
        }

        override fun onRetryClick() {
            mListener.onRetryClick()
        }

    }

    inner class OpenSourceViewHolder(private val openSourceViewBinding: ItemOpenSourceViewBinding) :
        BaseViewHolder(openSourceViewBinding.root), View.OnClickListener {
        override fun onBind(position: Int) {
            val mOpenSourceItemViewModel = mOpenSourceResponseList[position]
            openSourceViewBinding.viewModel = mOpenSourceItemViewModel
            openSourceViewBinding.executePendingBindings()
        }

        override fun onClick(v: View?) {
            mOpenSourceResponseList[0].projectUrl.get()?.let {
                val intent = Intent()
                intent.action = Intent.ACTION_VIEW
                intent.addCategory(Intent.CATEGORY_BROWSABLE)
                intent.data = Uri.parse(it)
                itemView.context.startActivity(intent)
            }
        }

    }

    companion object {
        const val VIEW_TYPE_EMPTY = 0

        const val VIEW_TYPE_NORMAL = 1
    }
}