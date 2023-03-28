package furhatos.app.affectivereaction.setting

import furhatos.flow.kotlin.FlowControlRunner
import furhatos.flow.kotlin.furhat
import furhatos.flow.kotlin.voice.AcapelaVoice
import furhatos.flow.kotlin.voice.PollyNeuralVoice
import furhatos.flow.kotlin.voice.PollyVoice
import furhatos.flow.kotlin.voice.Voice
import furhatos.util.Gender
import furhatos.util.Language

class Persona(val name: String, val mask: String = "adult", val face: List<String>, val voice: List<Voice>) {
}

fun FlowControlRunner.activate(persona: Persona) {
    for (voice in persona.voice) {
        if (voice.isAvailable) {
            furhat.voice = voice
            break
        }
    }

    for (face in persona.face) {
        if (furhat.faces[persona.mask]?.contains(face)!!){
            furhat.character = face
            break
        }
    }
}


val mainPersona = Persona(
    name = "Host",
    face = listOf("Titan"),
//    voice = listOf(PollyVoice.Lea()).shuffled() as List<Voice> // randomize what voice to select
    voice = listOf(AcapelaVoice(language = Language.FRENCH, gender = Gender.FEMALE)) as List<Voice>
)