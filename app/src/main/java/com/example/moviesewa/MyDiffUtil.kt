package com.example.moviesewa

import androidx.recyclerview.widget.DiffUtil
import com.example.moviesewa.data_classes.MovieData

class MyDiffUtil(val oldList : List<MovieData> , val newList : List<MovieData>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int= oldList.size

    override fun getNewListSize(): Int  = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return oldList[oldItemPosition].movieId == newList[newItemPosition].movieId
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        when {
            oldList[oldItemPosition].title != newList[newItemPosition].title ->
            {
                return  false
            }

            oldList[oldItemPosition].posterPath != newList[newItemPosition].posterPath ->
            {
                return false
            }

            oldList[oldItemPosition].releaseData != newList[newItemPosition].releaseData ->
            {
                return false
            }

            else ->
            {
                return true
            }

        }
    }

}