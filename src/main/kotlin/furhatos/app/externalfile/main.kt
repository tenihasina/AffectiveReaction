package furhatos.app.externalfile

import furhatos.app.externalfile.flow.*
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class ExternalfileSkill : Skill() {
    override fun start() {
        Flow().run(Init)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)
}
