package furhatos.app.affectivereaction.flow

import furhatos.app.affectivereaction.setting.location_FRONT
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state

val ResetHead : State = state {

    onButton(label = "RESET HEAD") {
        furhat.attend(location_FRONT)
    }

}