package furhatos.app.affectivereaction.flow.main

import furhatos.app.affectivereaction.flow.Init
import furhatos.app.affectivereaction.flow.Parent
import furhatos.app.affectivereaction.setting.location_CENTER
import furhatos.app.affectivereaction.setting.location_LEFT
import furhatos.app.affectivereaction.setting.location_RIGHT
import furhatos.app.affectivereaction.util.iceBreaker
import furhatos.flow.kotlin.*
import furhatos.util.random


val IceBreaker : State = state(Parent) {
    val question = iceBreaker?.let { Question(it.Ukraine ) }

    onButton(label = "LEFT", section = Section.LEFT, color = Color.Blue) {

        with(furhat) {
            attend(location_LEFT)
            ask(turn_distribution.random().toString())
        }
    }

    onButton(label = "RIGHT", section = Section.LEFT, color = Color.Blue) {
        with(furhat) {
            attend(location_RIGHT)
            ask(turn_distribution.random().toString())
        }
    }

    onButton(label = "FRONT", section = Section.LEFT, color = Color.Blue) {
        with(furhat) {
            attend(location_CENTER)
            ask(turn_distribution.random().toString())
        }
    }

    onButton(label = "CONTEXT", section = Section.LEFT, color = Color.Yellow) {

        with(furhat) {

            attendAll()
            say("Je vais maintenant vous poser des questions concernant votre niveau de connaissance sur le sujet")
//            listPositions.forEach { furhat.attend(it) }
        }
    }

    onButton(label = "QUESTION", section = Section.LEFT, color = Color.Green) {

        with(furhat) {
            if (question != null) {
//                listOf("virtual-user-0","virtual-user-1","virtual-user-2").forEach {
//                    furhat.attend(it, eyesOnly = false)
//                say("")
//                }
                attendAll()
                say(question.nextQuestion())
            }
        }
    }



    onButton(label = "END ICE BREAKER", section = Section.RIGHT, color = Color.Red) {
        with(furhat) {
            attendAll()
            say("Maintenant que nous disposons de plus de contexte sur le sujet, passons à des questions plus tranchées.")
//            listPositions.forEach { furhat.attend(it) }
        }
        goto(Discussion)
    }

    onButton(label = "MAIN", section = Section.LEFT, color = Color.Yellow) {
        goto(Init)
    }
}