package io.drdroid.tipcalculator.enums

enum class Rating(val rate: String, var level: Safety) {
    G("G - All Ages", Safety.SFW),
    PG("PG - Children", Safety.SFW),
    PG13("PG-13 - Teens 13 or older", Safety.SFW),
    R("R - 17+ (violence & profanity)", Safety.NSFW),
    RP("R+ - Mild Nudity", Safety.NSFW),
    RX("Rx - Hentai", Safety.NSFW);

    companion object{
        fun access(rate: String): Rating {
            return Rating.values().first { it.rate == rate }
        }
    }
}

