package furhatos.app.affectivereaction.flow.main

import furhatos.app.affectivereaction.flow.Menu
import furhatos.app.affectivereaction.flow.Parent
import furhatos.app.affectivereaction.flow.navigationButton
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.state
import furhatos.gestures.Gesture
import furhatos.gestures.Gestures

val Conclusion: State = state(Parent) {

    onButton(speakButton.copy(label = "THANKS")){
        with(furhat){
            gesture(Gestures.BigSmile)
            say("Nous avons terminé la session, je vous remercie d'avoir participé")
            Thread.sleep(1000)
            say("J'espère que cela vous a aidé à vous informer et à prendre conscience des différents opinions que nous pouvons avoir sur différents sujets.")
            Thread.sleep(2000)
            say("N'hésitez pas à revenir me voir, j'ai d'autres thématiques dont on pouura discuter si vous avez apprécié cette conversation")
            gesture(Gestures.Wink)
        }
    }

    onButton(navigationButton.copy(label = "MAIN")){
        goto(Menu)
    }

}