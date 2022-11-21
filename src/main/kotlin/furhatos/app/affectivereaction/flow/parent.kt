package furhatos.app.affectivereaction.flow

import furhatos.app.affectivereaction.flow.main.Idle
import furhatos.app.affectivereaction.flow.main.speakButton
import furhatos.app.affectivereaction.flow.main.turnButton
import furhatos.app.affectivereaction.setting.*
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures

val thanks = listOf("Super", "Merci", "OK !")
val Parent: State = state {

    onUserLeave(instant = true) {
        when {
            users.count == 0 -> goto(Idle)
            it == users.current -> furhat.attend(users.other)
        }
    }

    onUserEnter(instant = true) {
        furhat.glance(it)
        furhat.gesture(Gestures.Smile)
    }

    onButton(label = "RESET HEAD") {
        furhat.attend(location_FRONT)
    }

    onButton(turnButton.copy(label = "NAME LEFT")){
        with(furhat) {
            attend(location_LEFT)
            mapParticipants[location_LEFT]?.let { it1 -> say(it1) }
        }
    }

    onButton(turnButton.copy(label = "NAME RIGHT")){
        with(furhat) {
            attend(location_RIGHT)
            mapParticipants[location_RIGHT]?.let { it1 -> say(it1) }
        }
    }

    onButton(turnButton.copy(label = "NAME FRONT")){
        with(furhat) {
            attend(location_FRONT)
            mapParticipants[location_FRONT]?.let { it1 -> say(it1) }
        }
    }

    onButton(turnButton.copy(label = mapParticipants[location_LEFT].toString())) {

        with(furhat) {
            if ((0..2).random() == 0 ) {
                attend(location_LEFT)
                Thread.sleep(1000)
                mapParticipants[location_LEFT]?.let { it1 -> say(it1) }
            } else {
                if ((0..2).random() == 1 ){
                    attend(location_LEFT)
                    Thread.sleep(1000)
                    say(turn_question.nextQuestion())
                } else {
                    attend(location_LEFT)
                    Thread.sleep(1000)
                }
            }
        }
    }

    onButton(turnButton.copy(label = mapParticipants[location_RIGHT].toString())) {
        with(furhat) {
            if ((0..2).random() == 0 ) {
                attend(location_RIGHT)
                Thread.sleep(1000)
                mapParticipants[location_RIGHT]?.let { it1 -> say(it1) }
            } else {
                if ((0..2).random() == 1 ){
                    attend(location_RIGHT)
                    Thread.sleep(1000)
                    say(turn_question.nextQuestion())
                } else {
                    attend(location_RIGHT)
                    Thread.sleep(1000)
                }
            }
        }
    }

    onButton(turnButton.copy(label = mapParticipants[location_FRONT].toString())) {
        with(furhat) {
            if ((0..2).random() == 0 ) {
                attend(location_FRONT)
                Thread.sleep(1000)
                mapParticipants[location_FRONT]?.let { it1 -> say(it1) }
            } else {
                if ((0..2).random() == 1 ){
                    attend(location_FRONT)
                    Thread.sleep(1000)
                    say(turn_question.nextQuestion())
                } else {
                    attend(location_FRONT)
                    Thread.sleep(1000)
                }
            }
        }
    }

    onButton(speakButton.copy(label = "Avez-vous compris ?")){
        with(furhat){
//            glanceAll(furhat)
//            attendAll()
            say{
                +Gestures.Thoughtful(strength = 20.0, duration = 5000.0)
                +"Est-ce que les instructions sont claires ?"
            }
        }
    }

    onButton(speakButton.copy(label = "OK")){
        with(furhat){
//            glanceAll(furhat)
//            attendAll()
            say{
                +Gestures.BigSmile(strength = 20.0, duration = 2000.0)
                +thanks.random().toString()
            }
        }
    }

}