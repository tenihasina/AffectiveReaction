package furhatos.app.affectivereaction.flow

import furhatos.app.affectivereaction.flow.main.IceBreaker
import furhatos.app.affectivereaction.flow.main.Idle
import furhatos.app.affectivereaction.flow.main.WoZ
import furhatos.app.affectivereaction.setting.activate
import furhatos.app.affectivereaction.setting.distanceToEngage
import furhatos.app.affectivereaction.setting.mainPersona
import furhatos.app.affectivereaction.setting.maxNumberOfUsers
import furhatos.flow.kotlin.*
import furhatos.flow.kotlin.voice.Voice
import furhatos.util.Gender
import furhatos.util.Language

val Init : State = state() {
    init {
        /** Set our default interaction parameters */
        users.setSimpleEngagementPolicy(distanceToEngage, maxNumberOfUsers)
//        furhat.setMask("anime [legacy]")
//        furhat.voice = Voice("Lea-Neural")
        activate(mainPersona)
        /** start the interaction */
        goto(IceBreaker)
//        goto(WoZ)
    }
}
