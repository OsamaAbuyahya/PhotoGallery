package os.abuyahya.photogallery.adapter

import androidx.recyclerview.widget.DiffUtil
import os.abuyahya.photogallery.model.Photo

class PhotoDiffUtil(
    private val oldList: List<Photo>,
    private val newList: List<Photo>,
): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].hashCode() == newList[newItemPosition].hashCode()
    }
}
