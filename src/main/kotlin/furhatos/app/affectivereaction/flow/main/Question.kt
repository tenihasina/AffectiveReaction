package furhatos.app.affectivereaction.flow.main

class Question(questions: List<String>) {
    private val listQ = questions
    private var poolAskedQuestions = mutableSetOf<String>()
    private var poolNextQuestions = listQ.toMutableSet()
    private var question = ""

    fun nextQuestion(): String{
        if (poolNextQuestions.isNotEmpty()){
            question = poolNextQuestions.random().toString()
        } else {
            poolNextQuestions.addAll(poolAskedQuestions)
            question = poolNextQuestions.random().toString()
            poolAskedQuestions = mutableSetOf()
        }
        updatePools()
        return question
    }

    private fun updatePools() {
        poolNextQuestions.remove(question)
        poolAskedQuestions.add(question)
    }
}