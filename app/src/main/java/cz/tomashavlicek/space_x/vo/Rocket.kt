package cz.tomashavlicek.space_x.vo

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import cz.tomashavlicek.space_x.db.Converters

@Entity
@TypeConverters(Converters::class)
data class Rocket(
    @PrimaryKey
    @field:SerializedName(value = "id")
    val id: String,
    @field:SerializedName(value = "name")
    val name: String,
    @field:SerializedName(value = "flickr_images")
    val images: List<String>
) {
    val firstImage: String
        get() = images[0]
}
