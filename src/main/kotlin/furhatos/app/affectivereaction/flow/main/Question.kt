package furhatos.app.affectivereaction.flow.main

class Question(questions: List<String>) {
    private val listQ = questions
    private var poolAskedQuestions = mutableSetOf<String>()
    private var poolNextQuestions = listQ.toMutableSet()

    fun nextQuestion(): String{
        val question = listQ.random().toString()

        if (poolNextQuestions.contains(question)) {

            poolNextQuestions.filter { it == question }.forEach { poolNextQuestions.remove(it) }
            poolAskedQuestions.add(question)

        } else if (poolNextQuestions.isEmpty()) {

            poolNextQuestions.addAll(poolAskedQuestions)
            poolAskedQuestions = mutableSetOf()

        }

        return question
    }
}