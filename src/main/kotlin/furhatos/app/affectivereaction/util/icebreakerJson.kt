package furhatos.app.affectivereaction.util

import furhatos.app.affectivereaction.AffectivereactionSkill
import com.beust.klaxon.*

private val klaxon = Klaxon()
const val file_icebreaker = "/content/IceBreaker.json"

data class IceBreaker (
    val Ukraine: List<String>
) {
    companion object {
        public fun fromJson(json: String) = klaxon.parse<IceBreaker>(json)
    }
}

val jsonString = AffectivereactionSkill::class.java.getResource(file_icebreaker).readText()
val iceBreaker = IceBreaker.fromJson(jsonString)