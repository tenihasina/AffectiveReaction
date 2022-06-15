package furhatos.app.affectivereaction.flow

import furhatos.app.affectivereaction.flow.main.Idle
import furhatos.app.affectivereaction.flow.main.turnButton
import furhatos.app.affectivereaction.flow.main.turn_question
import furhatos.app.affectivereaction.setting.location_FRONT
import furhatos.app.affectivereaction.setting.location_LEFT
import furhatos.app.affectivereaction.setting.location_RIGHT
import furhatos.app.affectivereaction.setting.mapParticipants
import furhatos.flow.kotlin.*
import furhatos.gestures.Gesture
import furhatos.gestures.Gestures

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

    onButton(turnButton.copy(label = "LEFT")) {

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

    onButton(turnButton.copy(label = "RIGHT")) {
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

    onButton(turnButton.copy(label = "FRONT")) {
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

}