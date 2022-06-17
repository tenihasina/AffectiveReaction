package furhatos.app.affectivereaction.flow.main

import furhatos.app.affectivereaction.flow.Parent
import furhatos.app.affectivereaction.flow.navigationButton
import furhatos.app.affectivereaction.util.discussionQ
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.gestures.Gestures

val end = listOf("Ok, nous en avons fini avec cette question", "Je pense que nous avons fait le tout de la question")
val ActiveListening : State = state(Parent){

    onButton(speakButton.copy(label = "INTRO_ACTIVE")){
        with(furhat){
            discussionQ?.INTRO_ACTIVE_LISTENING?.forEach {
                this.say {
                    +Gestures.Thoughtful
                    +it
                }
                Thread.sleep(1000)
            }

        }
    }

    onButton(speakButton.copy(label = "START")){
        with(furhat){
            say {
                +Gestures.BigSmile
                +"Maintenant, je voudrais que vous pratiquiez l'écoute active lorsque vos coéquipiers décrivent leurs opinions"
            }
        }
    }

    onButton(speakButton.copy(label = "Guide")){
        with(furhat){
            say{
                +Gestures.BigSmile
                +"Pouvez-vous nours dire ce que vous pensez de l'opinion des autres participants ?"
            }
        }
    }

    onButton(speakButton.copy(label = "CONFLICT ?")){
        with(furhat){
            say{
                +Gestures.Thoughtful
                +"Voyez-vous un conflit dans les opinions présentées pour cette question ?"
            }
        }
    }

    onButton(navigationButton.copy(label = "END ACTIVE LISTENING")){
        with(furhat){
            say {
                +Gestures.Nod
                +end.random().toString()
            }
        }
        goto(Discussion)
    }
}