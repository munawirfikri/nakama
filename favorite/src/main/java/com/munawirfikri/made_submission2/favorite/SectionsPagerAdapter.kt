package com.munawirfikri.made_submission2.favorite

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.munawirfikri.made_submission2.R
import com.munawirfikri.made_submission2.favorite.anime.FavoriteAnimeFragment
import com.munawirfikri.made_submission2.favorite.manga.FavoriteMangaFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITLES = intArrayOf(R.string.favorite_anime, R.string.favorite_manga)
    }

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> FavoriteAnimeFragment()
            1 -> FavoriteMangaFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence = mContext.resources.getString(
        TAB_TITLES[position])

    override fun getCount(): Int = 2

}