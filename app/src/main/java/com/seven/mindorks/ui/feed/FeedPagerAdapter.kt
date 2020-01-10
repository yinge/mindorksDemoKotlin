package com.seven.mindorks.ui.feed

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.seven.mindorks.ui.feed.blogs.BlogFragment
import com.seven.mindorks.ui.feed.opensource.OpenSourceFragment

/**
 * at 2019/12/3
 * at 10:39
 * summary:
 */
class FeedPagerAdapter(fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {

    var mTabCount: Int = 0

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> BlogFragment.newInstance()
            1 -> OpenSourceFragment.newInstance()
            else -> BlogFragment.newInstance()//这块儿没法返回空
        }
    }

    fun setCount(count: Int) {
        mTabCount = count
    }

    override fun getCount(): Int = mTabCount
}