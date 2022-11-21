package furhatos.app.affectivereaction.flow.main

import furhatos.app.affectivereaction.flow.Menu
import furhatos.app.affectivereaction.flow.Parent
import furhatos.app.affectivereaction.flow.backToMenuButton
import furhatos.app.affectivereaction.flow.navigationButton
import furhatos.app.affectivereaction.setting.*
import furhatos.app.affectivereaction.util.dialogueCues
import furhatos.app.affectivereaction.util.discussionQ
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.util.random


val reactionButton = Button(label = "reaction button", section = Section.RIGHT, color = Color.Red)

var current_question = ""

val Discussion : State = state(Parent) {

    onButton(navigationButton.copy(label = "CONCLUSION")){
        goto(Conclusion)
    }

    onButton(navigationButton.copy(label = "CONFLICT")){
        goto(ActiveListening)
    }


    onButton(speakButton.copy(label = "QUESTION", color = Color.Yellow)){
        with(furhat){
//            attend(location_FRONT)
//            attendAll()
            if (randomQ != null) {
                current_question = randomQ.nextQuestion()
                say(current_question)
            }
        }
    }

    onButton(speakButton.copy(label = "REPEAT")){
        with(furhat){
            say{
                +"la question était la suivante :"
                +Gestures.Thoughtful
                +current_question
            }
        }
    }

    onButton(speakButton.copy(label = " MORE?")){
        with(furhat){
//            glanceAll(furhat)
//            attendAll()
            gesture(Gestures.Thoughtful(strength = 20.0, duration = 5000.0))
            say("Avez-vous encore quelque chose à rajouter ?")
        }
    }

    onButton(speakButton.copy(label = "CUT", color = Color.Yellow)){
        with(furhat){
            gesture(Gestures.Thoughtful)
            say("Je vais devoir couper la discussion sur ce sujet. Nous avons passé pas mal de temps sur cette question. ")
        }

    }

    onButton(navigationButton.copy(label = "ICE BREAKER")) {
        goto(IceBreaker)
    }

    onButton(navigationButton.copy(label = "SUMMARY")) {
        goto(Summary)
    }

    onButton(backToMenuButton.copy(label = "MAIN")) {
        goto(Menu)
    }

    onButton(reactionButton.copy(label = "HAPPY")) {
        furhat.say {
            +Gestures.BigSmile(strength = 20.0)
            if (dialogueCues != null) {
                +dialogueCues.happyEmpatheticPhrases.random().toString()
            }
        }
    }

    onButton(reactionButton.copy(label = "SAD")) {
        furhat.say {
            +Gestures.ExpressSad(strength = 20.0)
            if (dialogueCues != null) {
                +dialogueCues.sadEmpatheticPhrases.random().toString()
            }
        }
    }

    onButton(reactionButton.copy(label = "ENCOURAGE")) {
        furhat.say {
            +Gestures.Thoughtful
            if (dialogueCues != null) {
                +dialogueCues.encouragingEmpatheticPhrases.random().toString()
            }
        }
    }

    onButton(reactionButton.copy(label = "INTEREST")) {
        furhat.say {
            +Gestures.Surprise
            if (dialogueCues != null) {
                +dialogueCues.showInterestEmpatheticPhrases.random().toString()
            }
        }
    }

    onButton(reactionButton.copy(label = "NEUTRE")) {
        furhat.say {
            +Gestures.Nod
            if (dialogueCues != null) {
                +dialogueCues.showInterestEmpatheticPhrases.random().toString()
            }
        }
    }

    onButton(reactionButton.copy(label = "FEAR")) {
        furhat.say {
            +Gestures.ExpressFear
            if (dialogueCues != null) {
                +dialogueCues.showFearEmpatheticPhrases.random().toString()
            }
        }
    }

    onButton(reactionButton.copy(label = "ANGER")) {
        furhat.say {
            +Gestures.Thoughtful(strength = 20.0, duration = 5000.0)
            if (dialogueCues != null) {
                +dialogueCues.showAngerEmpatheticPhrases.random().toString()
            }
        }
    }

    onButton(reactionButton.copy(label = "DISGUST")) {
        furhat.say {
            +Gestures.ExpressDisgust
            if (dialogueCues != null) {
                +dialogueCues.showDisgustEmpatheticPhrases.random().toString()
            }
        }
    }

    onButton(reactionButton.copy(label = "SURPRISE")) {
        furhat.say {
            +Gestures.Surprise
            if (dialogueCues != null) {
                +dialogueCues.showSurpriseEmpatheticPhrases.random().toString()
            }
        }
    }
}