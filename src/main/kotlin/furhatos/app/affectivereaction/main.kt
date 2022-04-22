package furhatos.app.affectivereaction

import furhatos.app.affectivereaction.flow.*
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class AffectivereactionSkill : Skill() {
    override fun start() {
        Flow().run(Init)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
