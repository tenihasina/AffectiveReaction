package furhatos.app.affectivereaction.flow.main

import furhatos.app.affectivereaction.flow.Parent

import furhatos.app.affectivereaction.util.welcome7
import furhatos.flow.kotlin.*
import furhatos.flow.kotlin.voice.AcapelaVoice
import furhatos.flow.kotlin.voice.PollyNeuralVoice
import furhatos.flow.kotlin.voice.cereproc.William
import furhatos.gestures.Gestures
import furhatos.nlu.common.No
import furhatos.nlu.common.Yes
import furhatos.records.Location
import furhatos.util.random

val turn_distribution = listOf("Qu'en pensez vous ?", "Est-ce que cela vous parle ?", "")

val WoZ : State = state(Parent) {

    onButton(label = "RIGHT", section = Section.LEFT, color = Color.Blue) {
        val location = Location(0.8, 0.0, 1.0)
        with(furhat) {
            attend(location)
            ask(turn_distribution.random().toString())
        }
    }

    onButton(label = "LEFT", section = Section.LEFT, color = Color.Blue) {
        val location = Location(-0.9, 0.0, 1.0)
        with(furhat) {
            attend(location)
            ask(turn_distribution.random().toString())
        }
    }

    onButton(label = "FRONT", section = Section.LEFT, color = Color.Blue) {
        val location = Location(0.0, 0.0, 2.0)
        with(furhat) {
            attend(location)
            ask(turn_distribution.random().toString())
        }
    }

    onResponse<Yes> {
        if (welcome7 != null) {
            furhat.say(welcome7.happyEmpatheticPhrases.random().toString())
        }
    }

    onResponse<No> {
        if (welcome7 != null) {
            furhat.say(welcome7.sadEmpatheticPhrases.random().toString())
        }
    }

    onResponse {
//        furhat.glance(it.userId)
        furhat.say {
            +"${William.Vocal.HMM_YES}, je vous ai entendu."
            +Gestures.Wink
            +"vous avez dit ${it.text}"
        }
    }

    onButton(label = "SOCIAL", section = Section.RIGHT, color = Color.Red) {
        furhat.say {
            +Gestures.Smile
            if (welcome7 != null) {
                +welcome7.happyEmpatheticPhrases.random().toString()
            }
        }
    }

    onButton(label = "HAPPY", section = Section.RIGHT, color = Color.Red) {
        furhat.say {
            +Gestures.BigSmile(duration = 2.0)
            if (welcome7 != null) {
                +welcome7.happyEmpatheticPhrases.random().toString()
            }
        }
    }

    onButton(label = "SAD", section = Section.RIGHT, color = Color.Red) {
        furhat.say {
            +Gestures.ExpressSad(duration = 2.0)
            if (welcome7 != null) {
                +welcome7.sadEmpatheticPhrases.random().toString()
            }
        }
    }

    onButton(label = "ENCOURAGE", section = Section.RIGHT, color = Color.Red) {
        furhat.say {
            +Gestures.Thoughtful
            if (welcome7 != null) {
                +welcome7.encouragingEmpatheticPhrases.random().toString()
            }
        }
    }

    onButton(label = "INTEREST", section = Section.RIGHT, color = Color.Red) {
        furhat.say {
            +Gestures.Surprise
            if (welcome7 != null) {
                +welcome7.showInterestEmpatheticPrases.random().toString()
            }
        }
    }

    onButton(label = "GRATITUDE", section = Section.RIGHT, color = Color.Red) {
        furhat.say {
            +Gestures.Nod
            if (welcome7 != null) {
                +welcome7.gratitudeEmpatheticPhrases.random().toString()
            }
        }
    }
}
