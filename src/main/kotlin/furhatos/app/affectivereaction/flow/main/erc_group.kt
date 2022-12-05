package furhatos.app.affectivereaction.flow.main

import furhatos.app.affectivereaction.flow.ResetHead
import furhatos.app.affectivereaction.flow.navigationButton
import furhatos.app.affectivereaction.setting.location_FRONT
import furhatos.app.affectivereaction.setting.location_LEFT
import furhatos.app.affectivereaction.setting.location_RIGHT
import furhatos.app.affectivereaction.setting.mapParticipants
import furhatos.app.affectivereaction.util.groupSummary
import furhatos.flow.kotlin.*

val transitionStatement = listOf<String>("Et", "Quant à", "Ensuite")

val Summary : State = state(ResetHead) {
    onButton(navigationButton.copy(label = "REACTIONS")){
        goto(Discussion)
    }

    onButton(navigationButton.copy(label = "CONFLICT")){
        goto(ActiveListening)
    }

    onButton(speakButton.copy(label = "POS")){
        with(furhat){
            say {
                if (groupSummary != null) {
                    +groupSummary?.let { Question(it.PPP) }?.nextQuestion()
                }
            }
        }
    }
    onButton(speakButton.copy(label = "NEU")){
        with(furhat){
            say {
                if (groupSummary != null) {
                    +groupSummary?.let { Question(it.OOO) }?.nextQuestion()
                }
            }
        }
    }
    onButton(speakButton.copy(label = "NEG")){
        with(furhat){
            say {
                if (groupSummary != null) {
                    +groupSummary?.let { Question(it.NNN) }?.nextQuestion()
                }
            }
        }
    }
    onButton(speakButton.copy(label = "PON")){
        with(furhat){
            say {
                if (groupSummary != null) {
                    +groupSummary?.let { Question(it.PON) }?.nextQuestion()
                }
            }
        }
    }

    onButton(turnButton.copy(label = "${mapParticipants[location_LEFT].toString()}-${mapParticipants[location_RIGHT].toString()}")) {
        with(furhat) {
            attend(location_LEFT)
            say("${mapParticipants[location_LEFT]}")
            Thread.sleep(1000)
            attend(location_RIGHT)
            say("${mapParticipants[location_RIGHT]}")
        }
        goto(StatementMajority)
    }

    onButton(turnButton.copy(label = "${mapParticipants[location_LEFT].toString()}-${mapParticipants[location_FRONT].toString()}")) {
        with(furhat) {
            attend(location_LEFT)
            say("${mapParticipants[location_LEFT]}")
            Thread.sleep(1000)
            attend(location_FRONT)
            say("${mapParticipants[location_FRONT]}")
        }
        goto(StatementMajority)
    }

    onButton(turnButton.copy(label = "${mapParticipants[location_RIGHT].toString()}-${mapParticipants[location_FRONT].toString()}")) {
        with(furhat) {
            attend(location_RIGHT)
            say("${mapParticipants[location_RIGHT]}")
            Thread.sleep(1000)
            attend(location_RIGHT)
            say("${mapParticipants[location_FRONT]}")
        }
        goto(StatementMajority)
    }

    onButton(turnButton.copy(label = mapParticipants[location_LEFT].toString())){
        with(furhat) {
            attend(location_LEFT)
            say(transitionStatement.random().toString()+" ${mapParticipants[location_LEFT]}")
        }
        goto(StatementMinority)
    }

    onButton(turnButton.copy(label = mapParticipants[location_RIGHT].toString())){
        with(furhat) {
            attend(location_RIGHT)
            say(transitionStatement.random().toString()+" ${mapParticipants[location_RIGHT]}")
        }
        goto(StatementMinority)
    }

    onButton(turnButton.copy(label = mapParticipants[location_FRONT].toString())){
        with(furhat) {
            attend(location_FRONT)
            say(transitionStatement.random().toString()+" ${mapParticipants[location_FRONT]}")
        }
        goto(StatementMinority)
    }

}

val StatementMajority : State = state(ResetHead) {
    onButton(speakButton.copy(label = "POS")){
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

    onButton(speakButton.copy(label = "NEU")){
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

    onButton(speakButton.copy(label = "NEG")){
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

    onButton(speakButton.copy(label = "POS")) {
        with(furhat){
            if (groupSummary != null) {
                say(
                    Question(groupSummary.P).nextQuestion()
                )
            }
        }
        goto(Summary)
    }

    onButton(speakButton.copy(label = "NEU")) {
        with(furhat){
            if (groupSummary != null) {
                say(
                    Question(groupSummary.O).nextQuestion()
                )
            }
        }
        goto(Summary)
    }

    onButton(speakButton.copy(label = "NEG")) {
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