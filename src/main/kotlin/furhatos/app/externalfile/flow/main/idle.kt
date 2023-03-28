package furhatos.app.externalfile.flow.main

import furhatos.app.externalfile.util.getText
import furhatos.flow.kotlin.*

val Idle: State = state {
    onEntry {
        furhat.say(getText("Greeting"))
        furhat.say(getText("HowAreYou"))
    }
//    onUserEnter {
//        furhat.attend(it)
//        goto(Greeting)
//    }
}
