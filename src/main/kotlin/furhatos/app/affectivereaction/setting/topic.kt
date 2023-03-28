package furhatos.app.affectivereaction.setting

import furhatos.app.affectivereaction.flow.main.Question

import furhatos.app.affectivereaction.util.discussionQ
import furhatos.app.affectivereaction.util.iceBreaker

//val question = iceBreaker?.let { Question(it.SocialNetwork ) }
val turn_distribution = listOf("Qu'en pensez vous ?", "Est-ce que cela vous parle ?", "Et vous ?")
val turn_question = Question(turn_distribution)
//val randomQ = discussionQ?.let { Question(it.SocialNetwork) }
//val intro = iceBreaker?.INTRO_SocialNetwork