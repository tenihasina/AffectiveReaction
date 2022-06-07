package furhatos.app.affectivereaction.flow.main

import furhatos.app.affectivereaction.flow.Menu
import furhatos.app.affectivereaction.flow.Parent
import furhatos.app.affectivereaction.flow.backToMenuButton
import furhatos.app.affectivereaction.flow.navigationButton
import furhatos.app.affectivereaction.setting.location_CENTER
import furhatos.app.affectivereaction.setting.location_LEFT
import furhatos.app.affectivereaction.setting.location_RIGHT
import furhatos.app.affectivereaction.util.dialogueCues
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.util.random


val reactionButton = Button(label = "reaction button", section = Section.RIGHT, color = Color.Red)
val turn_distribution = listOf("Qu'en pensez vous ?", "Est-ce que cela vous parle ?", "")


val Discussion : State = state(Parent) {


    onButton(turnButton.copy(label = "LEFT")) {

        with(furhat) {
            attend(location_LEFT)
            ask(turn_distribution.random().toString())
        }
    }

    onButton(turnButton.copy(label = "RIGHT")) {
        with(furhat) {
            attend(location_RIGHT)
            ask(turn_distribution.random().toString())
        }
    }

    onButton(turnButton.copy(label = "FRONT")) {
        with(furhat) {
            attend(location_CENTER)
            ask(turn_distribution.random().toString())
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
            +Gestures.BigSmile(duration = 2.0)
            if (dialogueCues != null) {
                +dialogueCues.happyEmpatheticPhrases.random().toString()
            }
        }
    }

    onButton(reactionButton.copy(label = "SAD")) {
        furhat.say {
            +Gestures.ExpressSad(duration = 2.0)
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

    onButton(reactionButton.copy(label = "GRATITUDE")) {
        furhat.say {
            +Gestures.Nod
            if (dialogueCues != null) {
                +dialogueCues.gratitudeEmpatheticPhrases.random().toString()
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
            +Gestures.ExpressAnger
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