package gr.softweb.goldenhome.data.network

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import com.squareup.moshi.ToJson


/**
 * Converters for Moshi
 * Don't know if they will be used or not
 * The Moshi Library provides an all-in-one converter that works for this project
 * Although we can keep this file for special occasions else we delete it.
 */
class MoshiNullStringAdapter {
    @FromJson
    fun fromJson(reader: JsonReader): String {
        return if (reader.peek() != JsonReader.Token.NULL) {
            String()
        } else {
            reader.nextString()
        }
    }
}

class MoshiNullDoubleAdapter {
    @FromJson
    fun fromJson(reader: JsonReader): Double {
        return if (reader.peek() != JsonReader.Token.NULL) {
            0.0
        } else {
            reader.nextDouble()
        }
    }
}

class MoshiNullIntAdapter {
    @FromJson
    fun fromJson(reader: JsonReader): Int {
        return if (reader.peek() != JsonReader.Token.NULL) {
            0
        } else {
            reader.nextInt()
        }
    }
}


class MoshiNullLongAdapter {
    @FromJson
    fun fromJson(reader: JsonReader): Long {
        return if (reader.peek() != JsonReader.Token.NULL) {
            0L
        } else {
            reader.nextLong()
        }
    }
}

class MoshiNullBooleanAdapter {
    @FromJson
    fun fromJson(reader: JsonReader): Boolean {
        return if (reader.peek() != JsonReader.Token.NULL) {
            false
        } else {
            reader.nextBoolean()
        }
    }
}

class MoshiStatusAdapter {
    @FromJson
    fun fromJson(status: Int): String {
        return when (status) {
            200 -> "success"
            else -> "error"
        }
    }

    @ToJson
    fun toJson(status: String): Int {
        return when (status) {
            "success" -> 200
            else -> -1
        }
    }
}


class MoshiStringOrIntAdapter {
    @FromJson
    fun fromJson(reader: JsonReader): String {
        return if (reader.peek() == JsonReader.Token.NUMBER) {
            reader.nextInt().toString()
        } else {
            reader.nextString()
        }
    }
}









