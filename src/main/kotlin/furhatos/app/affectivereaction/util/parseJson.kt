package furhatos.app.affectivereaction.util

import com.google.gson.Gson
import com.google.gson.JsonParser
import furhatos.app.affectivereaction.AffectivereactionSkill
import java.io.InputStreamReader
import com.beust.klaxon.*

private val klaxon = Klaxon()

data class Welcome7 (
    val socialEmpatheticPhrases: List<String>,
    val happyEmpatheticPhrases: List<String>,
    val sadEmpatheticPhrases: List<String>,
    val encouragingEmpatheticPhrases: List<String>,
    val showInterestEmpatheticPrases: List<String>,
    val showAngerEmpatheticPhrases: List<String>,
    val showDisgustEmpatheticPhrases: List<String>,
    val showFearEmpatheticPhrases: List<String>,
    val showSurpriseEmpatheticPhrases: List<String>,
    val gratitudeEmpatheticPhrases: List<String>
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<Welcome7>(json)
    }
}

//val jsonString = AffectivereactionSkill::class.java.getResource("/content/EmpatheticPhrases.json").readText()
val jsonString = AffectivereactionSkill::class.java.getResource("/content/EmpatheticPhrases.json").readText()
val welcome7 = Welcome7.fromJson(jsonString)

