package furhatos.app.affectivereaction.util

import furhatos.app.affectivereaction.AffectivereactionSkill
import com.beust.klaxon.*

private val klaxon = Klaxon()
const val file_icebreaker = "/content/IceBreaker.json"

data class IceBreaker (
    val Ukraine: List<String>,
    val Covid: List<String>,
    val Elections: List<String>,
    val INTRO_Ukraine: List<String>,
    val INTRO_Covid: List<String>,
    val INTRO_Elections:List<String>,
    val INTRO_GENERAL: List<String>,
    val CONSIGNE: List<String>
) {
    companion object {
        public fun fromJson(json: String) = klaxon.parse<IceBreaker>(json)
    }
}

val jsonString = AffectivereactionSkill::class.java.getResource(file_icebreaker).readText()
val iceBreaker = IceBreaker.fromJson(jsonString)