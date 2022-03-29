package os.abuyahya.photogallery.model

import com.google.gson.annotations.SerializedName

data class SearchResponseApi(
    @SerializedName("total")
    var total: Int,
    @SerializedName("total_pages")
    var total_pages: Int,
    @SerializedName("results")
    var results: List<Photo>
)
