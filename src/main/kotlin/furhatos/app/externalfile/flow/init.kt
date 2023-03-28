package furhatos.app.externalfile.flow

import furhatos.app.externalfile.flow.main.Idle
import furhatos.app.externalfile.setting.distanceToEngage
import furhatos.app.externalfile.setting.maxNumberOfUsers
import furhatos.app.externalfile.util.parseCsv
import furhatos.flow.kotlin.*
import furhatos.flow.kotlin.voice.Voice

val Init : State = state() {
    init {
        /** Set our default interaction parameters */
        users.setSimpleEngagementPolicy(distanceToEngage, maxNumberOfUsers)
        furhat.voice = Voice("Matthew")
        /** start the interaction */

        // parse the csv file
        parseCsv()

        goto(Idle)
    }
}
