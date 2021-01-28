package cz.tomashavlicek.space_x.vo

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Launch(
    @PrimaryKey @field: SerializedName(value = "id") val id :String,
    @field: SerializedName(value = "name") val name: String,
    var timebase: String
)
