package furhatos.app.affectivereaction.flow.main

import furhatos.app.affectivereaction.flow.Parent
import furhatos.app.affectivereaction.flow.navigationButton
import furhatos.app.affectivereaction.util.discussionQ
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.gestures.Gestures

val end = listOf("Ok, nous en avons fini avec cette question.", "Je pense que nous avons fait le tour de la question")
val guide = Question(listOf("Je vous laisse commencer.", "Si ça vous convient de commencer.", "Commençons par vous."))
val keep = Question(listOf("N'hésitez pas à rebondir sur ce qui a été dit avant", "N'hésitez pas à prendre la parole directement", "N'hésitez pas à discuter ensemble de ce qui s'est dit"))


val ActiveListening : State = state(Parent){
    onButton(speakButton.copy(label = "START")){
        with(furhat){
            say {
                +Gestures.BigSmile
                +"Maintenant, ce serait intéressant d'avoir une discussion plus ouverte sur le sujet"
            }
        }
    }

    onButton(speakButton.copy(label = "Guide")){
        with(furhat){
            say{
                +Gestures.BigSmile
                +guide.nextQuestion()
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

    onButton(speakButton.copy(label = "KEEP TALKING")){
        with(furhat){
            say{
                +Gestures.Thoughtful
                +keep.nextQuestion()
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