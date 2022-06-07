package furhatos.app.affectivereaction.flow

import furhatos.app.affectivereaction.flow.main.*
import furhatos.app.affectivereaction.setting.activate
import furhatos.app.affectivereaction.setting.distanceToEngage
import furhatos.app.affectivereaction.setting.mainPersona
import furhatos.app.affectivereaction.setting.maxNumberOfUsers
import furhatos.flow.kotlin.*
import furhatos.flow.kotlin.voice.Voice
import furhatos.util.Gender
import furhatos.util.Language

val navigationButton = Button(label = "navigation button", section = Section.LEFT, color = Color.Blue)
val backToMenuButton = Button(label = "back to menu", section = Section.LEFT, color = Color.Yellow)
val Menu : State = state() {
    init {
        /** Set our default interaction parameters */
        users.setSimpleEngagementPolicy(distanceToEngage, maxNumberOfUsers)
//        furhat.setMask("anime [legacy]")
//        furhat.voice = Voice("Lea-Neural")
        activate(mainPersona)
        /** start the interaction */
        //        goto(WoZ)
    }

    onButton(navigationButton.copy(label = "ICE BREAKER")) {
        goto(IceBreaker)
    }

    onButton(navigationButton.copy(label = "REACTIONS")) {
        goto(Discussion)
    }

    onButton(navigationButton.copy(label = "SUMMARY")) {
        goto(Summary)
    }
}
