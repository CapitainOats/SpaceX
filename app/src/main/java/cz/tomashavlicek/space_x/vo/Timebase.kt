package cz.tomashavlicek.space_x.vo

enum class Timebase(val string: String) {
    PAST("past"),
    UPCOMING("upcoming"),
    LATEST("latest"),
    NEXT("next"),
    ALL("")
}
