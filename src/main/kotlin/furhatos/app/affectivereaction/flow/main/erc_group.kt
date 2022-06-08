package furhatos.app.affectivereaction.flow.main

import furhatos.app.affectivereaction.flow.navigationButton
import furhatos.app.affectivereaction.setting.location_CENTER
import furhatos.app.affectivereaction.setting.location_LEFT
import furhatos.app.affectivereaction.setting.location_RIGHT
import furhatos.app.affectivereaction.util.groupSummary
import furhatos.flow.kotlin.*

val transitionStatement = listOf<String>("Et", "Quant à", "Ensuite")

val Summary : State = state {
    onButton(navigationButton.copy(label = "REACTIONS")){
        goto(Discussion)
    }

    onButton(turnButton.copy(label = "LEFT-RIGHT")) {
        with(furhat) {
            glance(location_LEFT, 1000)
            say("Vous à gauche")
            glance(location_RIGHT, 1000)
            say("Et vous à droite")
        }
        goto(StatementMajority)
    }

    onButton(turnButton.copy(label = "LEFT-FRONT")) {
        with(furhat) {
            glance(location_LEFT, 1000)
            say("Vous à gauche")
            glance(location_RIGHT, 1000)
            say("Et vous au milieu")
        }
        goto(StatementMajority)
    }

    onButton(turnButton.copy(label = "RIGHT-FRONT")) {
        with(furhat) {
            glance(location_LEFT, 1000)
            say("Vous à droite")
            glance(location_RIGHT, 1000)
            say("Et vous au milieu")
        }
        goto(StatementMajority)
    }

    onButton(turnButton.copy(label = "LEFT")){
        with(furhat) {
            glance(location_LEFT, duration = 1000)
            say(transitionStatement.random().toString()+" vous à gauche")
        }
        goto(StatementMinority)
    }

    onButton(turnButton.copy(label = "RIGHT")){
        with(furhat) {
            glance(location_RIGHT, duration = 1000)
            say(transitionStatement.random().toString()+" vous à droite")
        }
        goto(StatementMinority)
    }

    onButton(turnButton.copy(label = "FRONT")){
        with(furhat) {
            glance(location_CENTER, duration = 1000)
            say(transitionStatement.random().toString()+" vous au milieu")
        }
        goto(StatementMinority)
    }

}

val StatementMajority : State = state {
    onButton(speakButton.copy(label = "PP")){
        with(furhat){
            glanceAll(furhat)
            if (groupSummary != null) {
                say(
                    Question(groupSummary.PP).nextQuestion()
                )
            }
        }
        goto(Summary)
    }

    onButton(speakButton.copy(label = "OO")){
        with(furhat){
            glanceAll(furhat)
            if (groupSummary != null) {
                say(
                    Question(groupSummary.OO).nextQuestion()
                )
            }
        }
        goto(Summary)
    }

    onButton(speakButton.copy(label = "NN")){
        with(furhat){
            glanceAll(furhat)
            if (groupSummary != null) {
                say(
                    Question(groupSummary.NN).nextQuestion()
                )
            }
        }
        goto(Summary)
    }
}

val StatementMinority: State = state {

    onButton(speakButton.copy(label = "P")) {
        with(furhat){
            if (groupSummary != null) {
                say(
                    Question(groupSummary.P).nextQuestion()
                )
            }
        }
        goto(Summary)
    }

    onButton(speakButton.copy(label = "O")) {
        with(furhat){
            if (groupSummary != null) {
                say(
                    Question(groupSummary.O).nextQuestion()
                )
            }
        }
        goto(Summary)
    }

    onButton(speakButton.copy(label = "P")) {
        with(furhat){
            if (groupSummary != null) {
                say(
                    Question(groupSummary.N).nextQuestion()
                )
            }
        }
        goto(Summary)
    }
}