package furhatos.app.affectivereaction.util

import furhatos.app.affectivereaction.AffectivereactionSkill
import com.beust.klaxon.*

private val klaxon = Klaxon()
const val file_groupJson = "/content/GroupSummary.json"

data class GroupJson(
    val PPP: List<String>,
    val OOO: List<String>,
    val NNN: List<String>,
    val PON: List<String>,
    val PP: List<String>,
    val OO: List<String>,
    val NN: List<String>,
    val P: List<String>,
    val O: List<String>,
    val N: List<String>
) {
    companion object {
        public fun fromJson(json: String) = klaxon.parse<GroupJson>(json)
    }
}

val groupSummary = GroupJson.fromJson(AffectivereactionSkill::class.java.getResource(file_groupJson).readText())
