package furhatos.app.affectivereaction.flow.main

import furhatos.app.affectivereaction.flow.Init
import furhatos.app.affectivereaction.flow.Parent
import furhatos.app.affectivereaction.setting.*

import furhatos.app.affectivereaction.util.dialogueCues
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.records.Location
import furhatos.util.random

val turn_distribution = listOf("Qu'en pensez vous ?", "Est-ce que cela vous parle ?", "")

val WoZ : State = state(Parent) {

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

    onResponse<Yes> {
        if (dialogueCues != null) {
            furhat.say(dialogueCues.happyEmpatheticPhrases.random().toString())
        }
    }

    onResponse<No> {
        if (dialogueCues != null) {
            furhat.say(dialogueCues.sadEmpatheticPhrases.random().toString())
        }
    }

    onResponse {
        furhat.attend(it.userId)
        furhat.say {
            +"je vous ai entendu."
            +Gestures.Wink
//            +"vous avez dit ${it.text}"
        }
    }

    onButton(label = "SOCIAL", section = Section.RIGHT, color = Color.Red) {
        furhat.say {
            +Gestures.Smile
            if (dialogueCues != null) {
                +dialogueCues.happyEmpatheticPhrases.random().toString()
            }
        }
    }

    onButton(label = "HAPPY", section = Section.RIGHT, color = Color.Red) {
        furhat.say {
            +Gestures.BigSmile(duration = 2.0)
            if (dialogueCues != null) {
                +dialogueCues.happyEmpatheticPhrases.random().toString()
            }
        }
    }

    onButton(label = "SAD", section = Section.RIGHT, color = Color.Red) {
        furhat.say {
            +Gestures.ExpressSad(duration = 2.0)
            if (dialogueCues != null) {
                +dialogueCues.sadEmpatheticPhrases.random().toString()
            }
        }
    }

    onButton(label = "ENCOURAGE", section = Section.RIGHT, color = Color.Red) {
        furhat.say {
            +Gestures.Thoughtful
            if (dialogueCues != null) {
                +dialogueCues.encouragingEmpatheticPhrases.random().toString()
            }
        }
    }

    onButton(label = "INTEREST", section = Section.RIGHT, color = Color.Red) {
        furhat.say {
            +Gestures.Surprise
            if (dialogueCues != null) {
                +dialogueCues.showInterestEmpatheticPhrases.random().toString()
            }
        }
    }

    onButton(label = "GRATITUDE", section = Section.RIGHT, color = Color.Red) {
        furhat.say {
            +Gestures.Nod
            if (dialogueCues != null) {
                +dialogueCues.gratitudeEmpatheticPhrases.random().toString()
            }
        }
    }

    onButton(label = "FEAR", section = Section.RIGHT, color = Color.Red) {
        furhat.say {
            +Gestures.ExpressFear
            if (dialogueCues != null) {
                +dialogueCues.showFearEmpatheticPhrases.random().toString()
            }
        }
    }

    onButton(label = "ANGER", section = Section.RIGHT, color = Color.Red) {
        furhat.say {
            +Gestures.ExpressAnger
            if (dialogueCues != null) {
                +dialogueCues.showAngerEmpatheticPhrases.random().toString()
            }
        }
    }

    onButton(label = "DISGUST", section = Section.RIGHT, color = Color.Red) {
        furhat.say {
            +Gestures.ExpressDisgust
            if (dialogueCues != null) {
                +dialogueCues.showDisgustEmpatheticPhrases.random().toString()
            }
        }
    }

    onButton(label = "SURPRISE", section = Section.RIGHT, color = Color.Red) {
        furhat.say {
            +Gestures.Surprise
            if (dialogueCues != null) {
                +dialogueCues.showSurpriseEmpatheticPhrases.random().toString()
            }
        }
    }

    onButton(label = "MAIN", section = Section.LEFT, color = Color.Yellow) {
        goto(Init)
    }
}
