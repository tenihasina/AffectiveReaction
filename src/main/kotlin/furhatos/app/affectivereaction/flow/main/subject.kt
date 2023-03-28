package furhatos.app.affectivereaction.flow.main
import furhatos.app.affectivereaction.flow.Parent
import furhatos.app.affectivereaction.flow.navigationButton
import furhatos.app.affectivereaction.util.discussionQ
import furhatos.app.affectivereaction.util.iceBreaker
import furhatos.flow.kotlin.State
import furhatos.flow.kotlin.state


var question = iceBreaker?.let { Question(it.SocialNetwork ) }
var randomQ = discussionQ?.let { Question(it.SocialNetwork) }
var intro = iceBreaker?.INTRO_SocialNetwork

data class ChatInfo(val iceQChat: Question, val randomQChat: Question, val introChat: List<String>)

val socialNetworkChat =
    iceBreaker?.let { Question(it.SocialNetwork) }
        ?.let { discussionQ?.let { it1 -> Question(it1.SocialNetwork) }
            ?.let { it2 -> ChatInfo(it, it2, iceBreaker.INTRO_SocialNetwork) } }
val sportChat = iceBreaker?.let { Question(it.Sport) }
    ?.let { discussionQ?.let { it1 -> Question(it1.Sport) }?.let { it2 -> ChatInfo(it, it2, iceBreaker.INTRO_Sport) } }
val climatChat = iceBreaker?.let { Question(it.Climat) }
    ?.let { discussionQ?.let { it1 -> Question(it1.Climat) }?.let { it2 -> ChatInfo(it, it2, iceBreaker.INTRO_Climat) } }
val prisonChat = iceBreaker?.let { Question(it.Prison) }
    ?.let { discussionQ?.let { it1 -> Question(it1.Prison) }?.let { it2 -> ChatInfo(it, it2, iceBreaker.INTRO_Prison) } }

val Subject: State = state(Parent) {
    onButton(navigationButton.copy(label = "SocialNetwork")) {
        if (socialNetworkChat != null) {
            gotoChat(socialNetworkChat)
        }
        goto(IceBreaker)
    }

    onButton(navigationButton.copy(label = "Sport")) {
        if (sportChat != null) {
            gotoChat(sportChat)
        }
        goto(IceBreaker)
    }

    onButton(navigationButton.copy(label = "Climat")) {
        if (climatChat != null) {
            gotoChat(climatChat)
        }
        goto(IceBreaker)
    }

    onButton(navigationButton.copy(label = "Prison")) {
        if (prisonChat != null) {
            gotoChat(prisonChat)
        }
        goto(IceBreaker)
    }
}

fun gotoChat(chatInfo: ChatInfo) {
    val (iceQChat, randomQChat, introChat) = chatInfo
    question = iceQChat
    randomQ = randomQChat
    intro = introChat
}