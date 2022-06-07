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
    val PPO: List<String>,
    val PPN: List<String>,
    val OOP: List<String>,
    val OON: List<String>,
    val NNO: List<String>,
    val NNP: List<String>
) {
    companion object {
        public fun fromJson(json: String) = klaxon.parse<GroupJson>(json)
    }
}

val groupSummary = GroupJson.fromJson(AffectivereactionSkill::class.java.getResource(file_groupJson).readText())
