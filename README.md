# HackQuestion - Life Hack or Urban Myth Quiz

## ST10499880

**Name:** Lelethu Rasmeni

## Project Description

HackQuestion is a Kotlin Android application developed using Jetpack Compose. The application presents users with a series of statements and challenges them to decide whether each statement is a genuine life hack or an urban myth.

The quiz provides immediate feedback, keeps track of the user's score, and allows users to review all answers after completing the quiz.

---

## Features

### Welcome Screen
- Introduction to the quiz
- Start Quiz button

### Quiz Screen
- 10 Life Hack or Urban Myth questions
- Hack (True) and Myth (False) answer options
- Immediate answer feedback
- Explanations for each answer

### Score Screen
- Displays final score
- Percentage calculation
- Personalized feedback message

### Review Screen
- View all quiz questions
- Correct answers displayed
- Explanations for each question
- Option to restart the quiz

### Restart Functionality
- Users can retake the quiz at any time

---

## Technologies Used

- Kotlin
- Jetpack Compose
- Android Studio
- Material 3
- Git
- GitHub
- GitHub Actions

---

## Automated Testing and Continuous Integration

GitHub Actions has been configured to automatically run whenever changes are pushed to the repository.

The workflow performs the following tasks:

1. Checks out the repository
2. Sets up Java 17
3. Configures Gradle
4. Runs automated unit tests
5. Builds the Android application

Commands executed:

```bash
./gradlew test
./gradlew assembleDebug
```

This ensures the project is automatically tested and validated after every push.

---

## Project Structure

```text
MainActivity
│
├── WelcomeScreen
├── QuizScreen
├── ScoreScreen
├── ReviewScreen
│
├── HackQuestion Data Model
├── Question List
└── GitHub Actions CI Pipeline
```

---

## Screenshots

### Welcome Screen


### Quiz Screen


### Score Screen


### Review Screen


### GitHub Actions Workflow


## GitHub Repository

Repository Link:

https://github.com/lelerasmeni-pixel/HackQuestion1

---

## How to Run the Project

1. Clone the repository

```bash
git clone https://github.com/lelerasmeni-pixel/HackQuestion1.git
```

2. Open the project in Android Studio

3. Allow Gradle to sync

4. Run the application using an Android Emulator or Android device

---

## Future Improvements

- Larger question bank
- Randomized questions
- Dark mode support
- Difficulty levels
- High score tracking
- Improved user interface design

---

## Author

Lelethu Rasmeni
