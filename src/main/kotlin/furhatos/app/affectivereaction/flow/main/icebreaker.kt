package furhatos.app.affectivereaction.flow.main

import furhatos.app.affectivereaction.flow.Menu
import furhatos.app.affectivereaction.flow.Parent
import furhatos.app.affectivereaction.flow.backToMenuButton
import furhatos.app.affectivereaction.flow.navigationButton
import furhatos.app.affectivereaction.setting.*
import furhatos.app.affectivereaction.util.iceBreaker
import furhatos.flow.kotlin.*
import furhatos.gestures.Gestures
import furhatos.nlu.common.TellName

val turnButton = Button(label = "turn management", section = Section.RIGHT, color = Color.Green)
val speakButton = Button(label = "speech", section = Section.RIGHT, color = Color.Red)
val question = iceBreaker?.let { Question(it.Ukraine ) }

fun glanceAll(furhat:Furhat){
    listPositions.forEach { 
        furhat.glance(it, 1000)
    }
}

val IceBreaker : State = state(Parent) {

    onButton(turnButton.copy(label = "LEFT")) {

        with(furhat) {
            attend(location_LEFT)
            ask(Question(turn_distribution).nextQuestion())
        }
    }

    onButton(turnButton.copy(label = "RIGHT")) {
        with(furhat) {
            attend(location_RIGHT)
            ask(Question(turn_distribution).nextQuestion())
        }
    }

    onButton(turnButton.copy(label = "FRONT")) {
        with(furhat) {
            attend(location_CENTER)
            ask(Question(turn_distribution).nextQuestion())
        }
    }

    onButton(speakButton.copy(label = "CONTEXT")) {

        with(furhat) {

            glanceAll(furhat)
            say("Je vais maintenant vous poser des questions concernant votre niveau de connaissance sur le sujet")
//            listPositions.forEach { furhat.attend(it) }
        }
    }

    onButton(speakButton.copy(label = "QUESTION")) {

        with(furhat) {
            if (question != null) {
                glanceAll(furhat)
                say(question.nextQuestion())
            }
        }
    }



    onButton(navigationButton.copy(label = "END ICE BREAKER")) {
        with(furhat) {
            glanceAll(furhat)
            say("Maintenant que nous disposons de plus de contexte sur le sujet, passons à des questions plus tranchées.")
//            listPositions.forEach { furhat.attend(it) }
        }
        goto(Discussion)
    }

    onButton(backToMenuButton.copy(label = "MAIN")) {
        goto(Menu)
    }

    onButton(navigationButton.copy(label = "INTRO")){
        goto(Introduction)
    }
}

val Introduction : State = state {

    onButton(turnButton.copy(label = "NAME ?")){

        with(furhat){
            Gestures.Thoughtful
            if (listParticipants.isEmpty()){
                ask("Pouvez vous me donner votre prénom ?")
            } else {
                ask ("Et vous")
            }
        }
    }

    onResponse<TellName>{
        val name = it.intent.name.toString()
        if (name != null) {
            listParticipants.add(name)
            with(furhat){
                Gestures.BigSmile(duration = 3000.0)
                say("Enchanté $name")
                mapParticipants.put(listPositions[listParticipants.size-1], name)
            }
        }

    }

    onButton(speakButton.copy(label = "MEMORIZE ALL NAMES")){
        with(furhat){
            if (listParticipants.isNotEmpty()){
                Gestures.Roll
                say("Pour l'instant, j'ai retenu ${listParticipants.size} noms ")
                mapParticipants.forEach{
                    furhat.attend(it.key)
                    furhat.say(it.value)
                }
            } else
                say("Je n'ai pas retenu de noms désolé")

        }
    }

        onButton(speakButton.copy(label = "RESET NAMES")){
        listParticipants = mutableListOf()
        with(furhat){
            Gestures.CloseEyes(duration = 3000.0)
            say("Je vais effacer les noms de ma mémoire")
        }
    }

    onButton(navigationButton.copy(label = "ICE BREAKER")) {
        goto(IceBreaker)
    }

}