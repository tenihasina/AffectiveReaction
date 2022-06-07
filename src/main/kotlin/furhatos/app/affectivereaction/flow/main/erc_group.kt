package furhatos.app.affectivereaction.flow.main

import furhatos.app.affectivereaction.flow.navigationButton
import furhatos.flow.kotlin.Color
import furhatos.flow.kotlin.Section
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.state


val Summary : State = state {
    onButton(navigationButton.copy(label = "REACTIONS")){
        goto(Discussion)
    }
}