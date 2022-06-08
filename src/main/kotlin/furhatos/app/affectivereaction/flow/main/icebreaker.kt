package furhatos.app.affectivereaction.flow.main

import furhatos.app.affectivereaction.flow.Menu
import furhatos.app.affectivereaction.flow.Parent
import furhatos.app.affectivereaction.flow.backToMenuButton
import furhatos.app.affectivereaction.flow.navigationButton
import furhatos.app.affectivereaction.setting.listPositions
import furhatos.app.affectivereaction.setting.location_CENTER
import furhatos.app.affectivereaction.setting.location_LEFT
import furhatos.app.affectivereaction.setting.location_RIGHT
import furhatos.app.affectivereaction.util.iceBreaker
import furhatos.flow.kotlin.*
import furhatos.util.random

val turnButton = Button(label = "turn management", section = Section.RIGHT, color = Color.Green)
val speakButton = Button(label = "speech", section = Section.RIGHT, color = Color.Red)
val question = iceBreaker?.let { Question(it.Ukraine ) }

fun glanceAll(furhat:Furhat){
    listPositions.forEach { 
        furhat.glance(it, 1000)
    }
}

val IceBreaker : State = state(Parent) {

    onButton(turnButton.copy(label = "LEFT")) {

        with(furhat) {
            attend(location_LEFT)
            ask(Question(turn_distribution).nextQuestion())
        }
    }

    onButton(turnButton.copy(label = "RIGHT")) {
        with(furhat) {
            attend(location_RIGHT)
            ask(Question(turn_distribution).nextQuestion())
        }
    }

    onButton(turnButton.copy(label = "FRONT")) {
        with(furhat) {
            attend(location_CENTER)
            ask(Question(turn_distribution).nextQuestion())
        }
    }

    onButton(speakButton.copy(label = "CONTEXT")) {

        with(furhat) {

            glanceAll(furhat)
            say("Je vais maintenant vous poser des questions concernant votre niveau de connaissance sur le sujet")
//            listPositions.forEach { furhat.attend(it) }
        }
    }

    onButton(speakButton.copy(label = "QUESTION")) {

        with(furhat) {
            if (question != null) {
//                listOf("virtual-user-0","virtual-user-1","virtual-user-2").forEach {
//                    furhat.attend(it, eyesOnly = false)
//                say("")
//                }
                glanceAll(furhat)
                say(question.nextQuestion())
            }
        }
    }



    onButton(navigationButton.copy(label = "END ICE BREAKER")) {
        with(furhat) {
            glanceAll(furhat)
            say("Maintenant que nous disposons de plus de contexte sur le sujet, passons à des questions plus tranchées.")
//            listPositions.forEach { furhat.attend(it) }
        }
        goto(Discussion)
    }

    onButton(backToMenuButton.copy(label = "MAIN")) {
        goto(Menu)
    }
}