package os.abuyahya.photogallery.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import os.abuyahya.photogallery.databinding.RowPhotoBinding
import os.abuyahya.photogallery.model.Photo
import javax.inject.Inject

class PhotoAdapter @Inject constructor(
    private val glide: RequestManager
) : RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {

    var listPhotos = emptyList<Photo>()

    var onIconFavClickListener: ((photo :Photo, isFavorite: Boolean) -> Unit)? = null

    class ViewHolder(val rowBinding: RowPhotoBinding) :
        RecyclerView.ViewHolder(rowBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RowPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        glide.load(listPhotos[position].urls.regular).into(holder.rowBinding.imageView)
        holder.rowBinding.tvTitle.text = listPhotos[position].id
        holder.rowBinding.tvDescription.text = listPhotos[position].description
        holder.rowBinding.icFavorite.isSelected = listPhotos[position].isFavorite

        holder.rowBinding.icFavorite.setOnClickListener {
            holder.rowBinding.icFavorite.isSelected = !it.isSelected
            listPhotos[position].isFavorite = it.isSelected
            onIconFavClickListener?.invoke(
                listPhotos[position],
                it.isSelected
            )
        }
    }

    override fun getItemCount(): Int {
        return listPhotos.size
    }

    fun setList(newListPhoto: List<Photo>){
        val photoDiffUtil = PhotoDiffUtil(listPhotos, newListPhoto)
        val photoDiffResult = DiffUtil.calculateDiff(photoDiffUtil)
        this.listPhotos = newListPhoto
        photoDiffResult.dispatchUpdatesTo(this)
    }

    fun setIconFavClickListener(listener: (photo :Photo, isFavorite: Boolean) -> Unit) {
        this.onIconFavClickListener = listener
    }

}
