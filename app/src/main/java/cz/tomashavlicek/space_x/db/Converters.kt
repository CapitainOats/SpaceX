package cz.tomashavlicek.space_x.db

import androidx.room.TypeConverter

class Converters {
    @TypeConverter fun stringToStringList(string: String): List<String> = string.split("@@@")

    @TypeConverter fun stringListToString(list: List<String>): String = list.joinToString("@@@")
}
