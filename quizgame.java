import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class quizgame {

    public static int checkAnswer(String userAnswer, String correctAnswer) {
        if (userAnswer != null && userAnswer.equals(correctAnswer)) {
            System.out.println("Answer is correct!");
            return 1;
        } else {
            System.out.println("Your answer is wrong! The correct answer is " + correctAnswer + ".");
            return 0;
        }
    }

    public static int updateScore(int score, int result) {
        return (result == 1) ? score + 1 : score;
    }

    public static ArrayList<String[]> getJavaQuestions() {
        ArrayList<String[]> questions = new ArrayList<>();

        questions.add(createQuestion("Which keyword is used to create a subclass in Java?", "A) implements", "B) extends", "C) inherits", "D) override", "B"));
        questions.add(createQuestion("What is the output of: System.out.println(10 + 20 + \"Java\")?", "A) 30Java", "B) Java1020", "C) Java30", "D) 1020Java", "A"));
        questions.add(createQuestion("Which of these is a valid way to create an object in Java?", "A) MyClass obj = new MyClass();", "B) obj = new MyClass();", "C) class obj = MyClass();", "D) new MyClass obj();", "A"));
        questions.add(createQuestion("What is the output of: System.out.println(10 > 5 ? \"Yes\" : \"No\");", "A) Yes", "B) No", "C) true", "D) false", "A"));
        questions.add(createQuestion("What will be the output of: System.out.println(\"Java\".charAt(2));", "A) a", "B) v", "C) J", "D) Exception", "B"));
        questions.add(createQuestion("Which component is used to compile, debug and execute Java programs?", "A) JRE", "B) JIT", "C) JDK", "D) JVM", "C"));
        questions.add(createQuestion("What is the size of 'int' variable in Java?", "A) 16 bit", "B) 32 bit", "C) 64 bit", "D) 8 bit", "B"));
        questions.add(createQuestion("Which of these is not a access modifier?", "A) protected", "B) private", "C) public", "D) internal", "D"));
        questions.add(createQuestion("Which method can be defined only once in a program?", "A) main method", "B) finalize method", "C) static method", "D) private method", "A"));
        questions.add(createQuestion("Which package contains the Random class?", "A) java.lang", "B) java.io", "C) java.util", "D) java.net", "C"));

        return questions;
    }

    public static String[] createQuestion(String text, String optA, String optB, String optC, String optD, String correctAns) {
        return new String[]{text, optA, optB, optC, optD, correctAns};
    }

   public static void countdownTimer() {
        System.out.print("You have 10 seconds to think...");
        try {
            for (int i = 10; i >= 0; i--) {
                System.out.print("\rTime left: " + i + " seconds   ");
                System.out.flush();
                if (i > 0) Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println();
    }

    public static void displayQuestion(String[] question, int questionNumber) {
        System.out.println("\n====================");
        System.out.println("Question " + questionNumber + ": " + question[0]);
        for (int i = 1; i <= 4; i++) {
            System.out.println(question[i]);
        }
    }

    public static String getUserAnswer(Scanner sc) {
        while (true) {
            System.out.print("Enter your answer (A, B, C, D): ");
            String answer = sc.nextLine().trim().toUpperCase();
            if (answer.matches("[A-D]")) return answer;
            System.out.println("Invalid input! Please choose A, B, C, or D.");
        }
    }

    public static void playQuiz() {
        System.out.println("Welcome to Java Quiz!");
        ArrayList<String[]> questions = getJavaQuestions();
        Collections.shuffle(questions);
        
        Scanner sc = new Scanner(System.in);
        int score = 0;

        for (int i = 0; i < questions.size(); i++) {
            String[] q = questions.get(i);
            displayQuestion(q, i + 1);
            countdownTimer();
            score = updateScore(score, checkAnswer(getUserAnswer(sc), q[5]));
        }

        System.out.println("\nYour final score is: " + score + " / " + questions.size());
        System.out.println("Thanks for playing!");
    }

    public static void main(String[] args) {
        playQuiz();
    }
}