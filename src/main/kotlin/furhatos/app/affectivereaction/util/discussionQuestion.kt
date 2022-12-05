package furhatos.app.affectivereaction.util

import furhatos.app.affectivereaction.AffectivereactionSkill
import com.beust.klaxon.*

private val klaxon = Klaxon()
const val file_discussion = "/content/DiscussionQuestion.json"

data class DiscussionQuestion(
    val Ukraine:List<String>,
    val Covid:List<String>,
    val Elections:List<String>,
    val SocialNetwork:List<String>,
    val Climat:List<String>,
    val Sport:List<String>,
    val Prison:List<String>,
    val INTRO_ACTIVE_LISTENING:List<String>
) {
    companion object {
        public fun fromJson(json: String) = klaxon.parse<DiscussionQuestion>(json)
    }
}

//val discussionQ = DiscussionQuestion.fromJson(AffectivereactionSkill::class.java.getResource(file_discussion).readText())

val jsonDQ = AffectivereactionSkill::class.java.getResource(file_discussion).readText()
val discussionQ = DiscussionQuestion.fromJson(jsonDQ)