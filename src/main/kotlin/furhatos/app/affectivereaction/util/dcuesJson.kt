package furhatos.app.affectivereaction.util

import furhatos.app.affectivereaction.AffectivereactionSkill
import com.beust.klaxon.*

private val klaxon = Klaxon()
const val file_dCues = "/content/EmpatheticPhrases.json"

data class DialogueCues (
    val socialEmpatheticPhrases: List<String>,
    val happyEmpatheticPhrases: List<String>,
    val sadEmpatheticPhrases: List<String>,
    val encouragingEmpatheticPhrases: List<String>,
    val showInterestEmpatheticPhrases: List<String>,
    val showAngerEmpatheticPhrases: List<String>,
    val showDisgustEmpatheticPhrases: List<String>,
    val showFearEmpatheticPhrases: List<String>,
    val showSurpriseEmpatheticPhrases: List<String>,
    val gratitudeEmpatheticPhrases: List<String>
) {
    companion object {
        public fun fromJson(json: String) = klaxon.parse<DialogueCues>(json)
    }
}

//val jsonString = AffectivereactionSkill::class.java.getResource("/content/EmpatheticPhrases.json").readText()
val dialogueCues = DialogueCues.fromJson(AffectivereactionSkill::class.java.getResource(file_dCues).readText())



