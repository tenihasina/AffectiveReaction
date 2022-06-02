package furhatos.app.affectivereaction.flow.main

import furhatos.app.affectivereaction.flow.Parent
import furhatos.app.affectivereaction.util.dialogueCues
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.util.random

val Discussion : State = state(Parent) {
    onButton(label = "ICE BREAKER", section = Section.LEFT, color = Color.Blue) {
        goto(IceBreaker)
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

    onButton(label = "SUMMARY", section = Section.LEFT, color = Color.Yellow) {
        goto(Summary)
    }
}