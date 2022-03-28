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

    var onIconFavClickListener: ((photo :Photo, isFavorite: Boolean) -> Unit)? = null


    class ViewHolder(val rowBinding: RowPhotoBinding) :
        RecyclerView.ViewHolder(rowBinding.root)

    private val diffCallback = object: DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
             return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    var listPhotos: List<Photo>
    get() = differ.currentList
    set(value) = differ.submitList(value)

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

    fun setIconFavClickListener(listener: (photo :Photo, isFavorite: Boolean) -> Unit) {
        this.onIconFavClickListener = listener
    }

    fun removeItem(photo: Photo) {
        listPhotos.toMutableList().remove(photo)
        differ.submitList(listPhotos.toList())
    }
}
