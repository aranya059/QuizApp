package edu.usta.cs3443.week6_d1_quizapp.model;

import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;


public class QuizBank {
    private ArrayList<Question> questions;
    private int qIndex;
    private Question currentQuestion;

    public QuizBank(){
        questions = new ArrayList<Question>();
        qIndex = 0;
        currentQuestion = null;
    }

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public int getIndex() {
        return qIndex;
    }

    public void setIndex(int qIndex) {
        this.qIndex = qIndex;
    }

    public Question getCurrentQuestion() {
        int index = getIndex();
        if (index>= 0 && index < questions.size()){
            currentQuestion = questions.get(index);
            setIndex(index + 1);
        }else{
            setIndex(0);
            currentQuestion = questions.get(index);
        }
        return currentQuestion;
    }

    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }


    public String getCurrentQuestionText(){
        return currentQuestion.getQuestion();
    }

    public boolean getCurrentQuestionAnswer(){
        return currentQuestion.getAnswer();
    }

    public void addQuestion(Question question){
        if (question != null){
            questions.add(question);
        }
    }

    public void loadQuestion(InputStream inputStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String questionText = parts[0];
                    boolean answer = Boolean.parseBoolean(parts[1].trim());
                    addQuestion(new Question(questionText, answer));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
