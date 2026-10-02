
package com.example.hackquestion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HackQuestionTheme {
                LifeHackOrMythApp()
            }
        }
    }
}

/*
 * DATA MODEL
 *
 * Each question contains:
 * - the statement
 * - whether it is a real hack or myth
 * - an explanation shown after answering
 */
data class HackQuestion(
    val statement: String,
    val isHack: Boolean,
    val explanation: String
)

/*
 * QUIZ QUESTIONS
 */
val quizQuestions = listOf(

    HackQuestion(
        statement = "A damp cloth underneath a chopping board can help stop it from sliding.",
        isHack = true,
        explanation = "The damp cloth increases friction between the board and the work surface."
    ),

    HackQuestion(
        statement = "Drinking coffee immediately makes a person sober after drinking alcohol.",
        isHack = false,
        explanation = "Coffee may make someone feel more alert, but it does not remove alcohol from the body."
    ),

    HackQuestion(
        statement = "A wooden spoon across a boiling pot can help reduce bubbling over.",
        isHack = true,
        explanation = "A wooden spoon can disrupt bubbles at the surface and may reduce foaming."
    ),

    HackQuestion(
        statement = "Eating sugar quickly makes a person sober after drinking alcohol.",
        isHack = false,
        explanation = "Sugar does not reverse alcohol's effects or remove alcohol from the bloodstream."
    ),

    HackQuestion(
        statement = "A banana peel can sometimes be used to polish certain leather shoes.",
        isHack = true,
        explanation = "The inside of a banana peel can provide a temporary polish for some leather surfaces."
    ),

    HackQuestion(
        statement = "Putting all bread in the refrigerator always keeps it fresher for longer.",
        isHack = false,
        explanation = "Refrigeration can cause bread to stale faster. Freezing is generally better for longer storage."
    ),

    HackQuestion(
        statement = "Freezing suitable foods can extend their storage life.",
        isHack = true,
        explanation = "Freezing slows processes that cause many foods to spoil, although storage times vary."
    ),

    HackQuestion(
        statement = "Toothpaste permanently removes every type of scratch from glass.",
        isHack = false,
        explanation = "Toothpaste cannot permanently remove every type of glass scratch and is not suitable for all surfaces."
    ),

    HackQuestion(
        statement = "Keeping a damp cloth under a bowl can help prevent the bowl from sliding.",
        isHack = true,
        explanation = "The cloth can increase friction between the bowl and the surface."
    ),

    HackQuestion(
        statement = "Putting a metal spoon in a microwave is always a safe way to heat food.",
        isHack = false,
        explanation = "Metal can cause arcing in some microwaves and should only be used when the appliance allows it."
    )
)

/*
 * MAIN APPLICATION
 */
@Composable
fun LifeHackOrMythApp() {

    var currentScreen by remember {
        mutableStateOf("welcome")
    }

    var currentQuestion by remember {
        mutableIntStateOf(0)
    }

    var score by remember {
        mutableIntStateOf(0)
    }

    var selectedAnswer by remember {
        mutableStateOf<Boolean?>(null)
    }

    var answerSubmitted by remember {
        mutableStateOf(false)
    }

    when (currentScreen) {

        "welcome" -> {

            WelcomeScreen(
                onStart = {
                    currentQuestion = 0
                    score = 0
                    selectedAnswer = null
                    answerSubmitted = false
                    currentScreen = "quiz"
                }
            )
        }

        "quiz" -> {

            QuizScreen(
                question = quizQuestions[currentQuestion],
                questionNumber = currentQuestion + 1,
                totalQuestions = quizQuestions.size,
                selectedAnswer = selectedAnswer,
                answerSubmitted = answerSubmitted,

                onAnswerSelected = { answer ->

                    if (!answerSubmitted) {

                        selectedAnswer = answer
                        answerSubmitted = true

                        if (answer == quizQuestions[currentQuestion].isHack) {
                            score++
                        }
                    }
                },

                onNext = {

                    if (currentQuestion < quizQuestions.lastIndex) {

                        currentQuestion++
                        selectedAnswer = null
                        answerSubmitted = false

                    } else {

                        currentScreen = "score"
                    }
                }
            )
        }

        "score" -> {

            ScoreScreen(
                score = score,
                total = quizQuestions.size,

                onReview = {
                    currentScreen = "review"
                },

                onRestart = {
                    currentQuestion = 0
                    score = 0
                    selectedAnswer = null
                    answerSubmitted = false
                    currentScreen = "quiz"
                }
            )
        }

        "review" -> {

            ReviewScreen(
                questions = quizQuestions,

                onBack = {
                    currentScreen = "score"
                },

                onRestart = {
                    currentQuestion = 0
                    score = 0
                    selectedAnswer = null
                    answerSubmitted = false
                    currentScreen = "quiz"
                }
            )
        }
    }
}

/*
 * WELCOME SCREEN
 */
@Composable
fun WelcomeScreen(
    onStart: () -> Unit
) {

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "💡",
                fontSize = 72.sp
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = "Life Hack or\nUrban Myth?",
                fontSize = 34.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = "Test your common sense and discover which popular life hacks are useful shortcuts and which are simply urban myths.",
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            Button(
                onClick = onStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(14.dp)
            ) {

                Text(
                    text = "START QUIZ",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = "10 questions • Hack or Myth",
                fontSize = 14.sp
            )
        }
    }
}

/*
 * QUIZ SCREEN
 */
@Composable
fun QuizScreen(
    question: HackQuestion,
    questionNumber: Int,
    totalQuestions: Int,
    selectedAnswer: Boolean?,
    answerSubmitted: Boolean,
    onAnswerSelected: (Boolean) -> Unit,
    onNext: () -> Unit
) {

    val isCorrect =
        selectedAnswer != null &&
                selectedAnswer == question.isHack

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "Question $questionNumber of $totalQuestions",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 5.dp
            )
        ) {

            Column(
                modifier = Modifier.padding(24.dp)
            ) {

                Text(
                    text = "HACK OR MYTH?",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(18.dp)
                )

                Text(
                    text = question.statement,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        Button(
            onClick = {
                onAnswerSelected(true)
            },
            enabled = !answerSubmitted,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(14.dp)
        ) {

            Text(
                text = "✓  HACK (TRUE)",
                fontSize = 17.sp
            )
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Button(
            onClick = {
                onAnswerSelected(false)
            },
            enabled = !answerSubmitted,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            shape = RoundedCornerShape(14.dp)
        ) {

            Text(
                text = "✕  MYTH (FALSE)",
                fontSize = 17.sp
            )
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        if (answerSubmitted) {

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp)
            ) {

                Column(
                    modifier = Modifier.padding(18.dp)
                ) {

                    Text(
                        text = if (isCorrect) {
                            "🎉 Correct!"
                        } else {
                            "❌ Incorrect!"
                        },
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(8.dp)
                    )

                    Text(
                        text = if (isCorrect) {
                            "Well done! You spotted it correctly."
                        } else {
                            if (question.isHack) {
                                "This statement is a real life hack."
                            } else {
                                "This statement is an urban myth."
                            }
                        },
                        fontSize = 16.sp
                    )

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Text(
                        text = question.explanation,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Button(
                onClick = onNext,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(14.dp)
            ) {

                Text(
                    text = if (questionNumber == totalQuestions) {
                        "VIEW SCORE"
                    } else {
                        "NEXT QUESTION"
                    },
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

/*
 * SCORE SCREEN
 */
@Composable
fun ScoreScreen(
    score: Int,
    total: Int,
    onReview: () -> Unit,
    onRestart: () -> Unit
) {

    val percentage =
        (score.toDouble() / total.toDouble()) * 100

    val feedback = when {

        percentage >= 80 ->
            "🏆 Master of Life Hacks!"

        percentage >= 60 ->
            "🌟 Great job!"

        percentage >= 40 ->
            "👍 Good effort!"

        else ->
            "📚 Keep practising!"
    }

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "QUIZ COMPLETE!",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(25.dp)
            )

            Text(
                text = "Your Score",
                fontSize = 18.sp
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(
                text = "$score / $total",
                fontSize = 60.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(
                text = "${percentage.toInt()}%",
                fontSize = 22.sp
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            Text(
                text = feedback,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Text(
                text = when {

                    percentage >= 80 ->
                        "You have a great eye for spotting useful hacks!"

                    percentage >= 60 ->
                        "You have a good understanding of everyday life hacks."

                    percentage >= 40 ->
                        "You got some right. A little more practice will help."

                    else ->
                        "Review the explanations and try the quiz again."
                },
                textAlign = TextAlign.Center,
                fontSize = 16.sp
            )

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            Button(
                onClick = onReview,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(14.dp)
            ) {

                Text(
                    text = "REVIEW ANSWERS",
                    fontSize = 17.sp
                )
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            OutlinedButton(
                onClick = onRestart,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(14.dp)
            ) {

                Text(
                    text = "TRY AGAIN",
                    fontSize = 17.sp
                )
            }
        }
    }
}

/*
 * REVIEW SCREEN
 */
@Composable
fun ReviewScreen(
    questions: List<HackQuestion>,
    onBack: () -> Unit,
    onRestart: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = "Review Answers",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(6.dp)
        )

        Text(
            text = "Review each statement and learn why it is a hack or myth.",
            fontSize = 15.sp
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            itemsIndexed(questions) { index, question ->

                ReviewCard(
                    number = index + 1,
                    question = question
                )
            }
        }

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {

            OutlinedButton(
                onClick = onBack,
                modifier = Modifier.weight(1f)
            ) {

                Text("BACK")
            }

            Spacer(
                modifier = Modifier.width(10.dp)
            )

            Button(
                onClick = onRestart,
                modifier = Modifier.weight(1f)
            ) {

                Text("TRY AGAIN")
            }
        }
    }
}

/*
 * REVIEW CARD
 */
@Composable
fun ReviewCard(
    number: Int,
    question: HackQuestion
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {

        Column(
            modifier = Modifier.padding(18.dp)
        ) {

            Text(
                text = "Question $number",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = question.statement,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(
                text = if (question.isHack) {
                    "✓ HACK"
                } else {
                    "✕ MYTH"
                },
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = question.explanation,
                fontSize = 14.sp
            )
        }
    }
}

/*
 * THEME
 *
 * This name is intentionally different from the default
 * Android Studio theme to avoid a redeclaration conflict.
 */
@Composable
fun HackQuestionTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        content = content
    )
}


