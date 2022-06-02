package furhatos.app.affectivereaction.flow.main

import furhatos.flow.kotlin.Color
import furhatos.flow.kotlin.Section
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.state


val Summary : State = state {
    onButton(label = "DISCUSSION", section = Section.RIGHT, color = Color.Blue){
        goto(Discussion)
    }
}