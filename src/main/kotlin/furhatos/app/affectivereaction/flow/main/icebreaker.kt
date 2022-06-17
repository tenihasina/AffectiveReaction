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

fun glanceAll(furhat:Furhat){
    listPositions.forEach { 
        furhat.glance(it, 1000)
    }
}

val IceBreaker : State = state(Parent) {

    onButton(speakButton.copy(label = "CONTEXT")) {

        with(furhat) {
//            attendAll()
            intro?.forEach {
//                attend(location_LEFT)
//                Thread.sleep(1000)
                furhat.say(it)
                Thread.sleep(1000)
//                attend(location_RIGHT)
        //                    glanceAll(furhat)
            }
            Thread.sleep(1000)
//            attend(location_FRONT)
//            glanceAll(furhat)
            say("Je vais maintenant vous poser des questions concernant votre niveau de connaissance sur le sujet")
//            listPositions.forEach { furhat.attend(it) }
        }
    }

    onButton(speakButton.copy(label = "QUESTION")) {

        with(furhat) {
            if (question != null) {
//                glanceAll(furhat)
//                attendAll()
                say(question.nextQuestion())
            }
        }
    }



    onButton(navigationButton.copy(label = "END ICE BREAKER")) {
        with(furhat) {
//            glanceAll(furhat)
//            attendAll()
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

val Introduction : State = state(Parent) {

    onButton(speakButton.copy(label = "introduction")){

        if (iceBreaker != null) {
            with(furhat){
                gesture(Gestures.Thoughtful(duration = 5000.0))
//                glanceAll(furhat)
//                say("Je vais maintenant présenter le déroulé de la session")
                iceBreaker.INTRO_GENERAL.forEach {
//                    attend(listPositions.random())
//                    Thread.sleep(1000)
                    furhat.say(it)
                    Thread.sleep(1000)
//                    attend(listPositions.random())
                }
            }
        }
    }

    onButton(speakButton.copy(label = "consigne")){
        with(furhat){
            gesture(Gestures.Thoughtful(strength = 20.0, duration = 5000.0))
            say("Je vais maintenant présenter les consignes à respecter")
            Thread.sleep(1000)
            iceBreaker?.CONSIGNE?.forEach {
                furhat.say(it)
                Thread.sleep(1000)
                //                    attend(listPositions.random())
            }
        }

    }

    var count = 0
    onButton(turnButton.copy(label = "NAME ?")){

        with(furhat){
            if (count == 0){
                say{
                    +Gestures.Thoughtful
                    +"Pouvez vous me donner votre prénom ?"
                }
                count += 1
            } else {
                say ("Et vous")
            }
        }
    }

    onResponse<TellName>{
        val name = it.intent.name.toString()
        if (name != null) {
            listParticipants.add(name)
            with(furhat){
                say{
                    +Gestures.BigSmile(duration = 3000.0)
                    +"Enchanté $name"
                }
                saveParticipant(name)
            }
        }

    }

    onButton(speakButton.copy(label = "MEMORIZE ALL NAMES")){
        with(furhat){
            if (listParticipants.isNotEmpty()){
//                gesture(Gestures.CloseEyes)
                say("Pour l'instant, j'ai retenu ${listParticipants.size%4} noms ")
                mapParticipants.forEach{
                    furhat.attend(it.key)
                    Thread.sleep(1000)
                    furhat.say(it.value)
                }
            } else
                say("Je n'ai pas retenu de noms désolé")

        }
    }

        onButton(speakButton.copy(label = "RESET NAMES")){
        listParticipants = mutableListOf()
        with(furhat){
//            gesture(Gestures.CloseEyes)
            say("Je vais effacer les noms de ma mémoire")
        }
    }

    onButton(navigationButton.copy(label = "ICE BREAKER")) {
        goto(IceBreaker)
    }

}

private fun saveParticipant(name : String){
    mapParticipants[listPositions[(listParticipants.size-1)%3]] = name
}