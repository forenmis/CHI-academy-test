package com.example.chiacademytest.screens.home.entity

import com.example.chiacademytest.R

sealed class Pages(val position: Int, val menuId: Int) {
    object All : Pages(0, R.id.menuAll)
    object Favorite : Pages(1, R.id.menuFavorite)

    companion object {
        fun findPageByPosition(position: Int): Pages {
            return when (position) {
                All.position -> All
                Favorite.position -> Favorite
                else -> throw IllegalArgumentException()
            }
        }

        fun findPageByMenuId(menuId: Int): Pages {
            return when (menuId) {
                All.menuId -> All
                Favorite.menuId -> Favorite
                else -> throw IllegalArgumentException()
            }
        }
    }
}