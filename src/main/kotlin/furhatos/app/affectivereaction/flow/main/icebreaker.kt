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
val turn_distribution = listOf("Qu'en pensez vous ?", "Est-ce que cela vous parle ?", "Et vous ?")
val turn_question = Question(turn_distribution)
fun glanceAll(furhat:Furhat){
    listPositions.forEach { 
        furhat.glance(it, 1000)
    }
}

val IceBreaker : State = state(Parent) {

    onButton(speakButton.copy(label = "CONTEXT")) {

        with(furhat) {
            attend(location_FRONT)
            iceBreaker?.INTRO_Ukraine?.forEach {
                furhat.say(it)
                Thread.sleep(1000)
                glanceAll(furhat)
            }
            Thread.sleep(1000)
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

    onButton(speakButton.copy(label = "introduction")){

        if (iceBreaker != null) {
            with(furhat){
                gesture(Gestures.BigSmile(duration = 4000.0))
                glanceAll(furhat)
                iceBreaker.INTRO_GENERAL.forEach {
                    furhat.say(it)
                    Thread.sleep(1000)
                    glanceAll(furhat)
                }
                Thread.sleep(2000)
                gesture(Gestures.Thoughtful(duration = 4000.0))
                iceBreaker.CONSIGNE.forEach {
                    furhat.say(it)
                    Thread.sleep(1000)
                    glanceAll(furhat)
                }
            }
        }
    }

    onButton(speakButton.copy(label = "ALREADY KNOW YOU")){
        with(furhat){
            glanceAll(furhat)
            say("On m'a donné à l'avance les noms des participants au session, si jamais il y a une erreur, n'hésitez pas à m'en faire part")
        }
    }

    onButton(turnButton.copy(label = "NAME ?")){

        with(furhat){
            gesture(Gestures.Thoughtful)
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
                gesture(Gestures.BigSmile(duration = 3000.0))
                say("Enchanté $name")
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