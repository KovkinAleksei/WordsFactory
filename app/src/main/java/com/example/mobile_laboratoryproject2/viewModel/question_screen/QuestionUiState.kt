package com.example.mobile_laboratoryproject2.viewModel.question_screen

data class QuestionUiState(
    val currentQuestion: Int = 0,
    val questionsAmount: Int = 0,
    val definition: String = "The practice or skill of preparing food by combining, mixing, and heating ingredients.",
    var answerOptions: List<AnswerOption> = listOf(
        AnswerOption(
            false,
            "Cooking",
            "A."
        ),
        AnswerOption(
            false,
            "Smiling",
            "B."
        ),
        AnswerOption(
            false,
            "Freezing",
            "C."
        )
    )
    /*val answerOptions: List<String> = listOf(
        "Cooking",
        "Smiling",
        "Freezing"
    ),*/
    /*val correctAnswer: String? = null,
    val chosenAnswer: String? = null*/
)
