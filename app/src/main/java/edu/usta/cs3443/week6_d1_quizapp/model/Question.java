package edu.usta.cs3443.week6_d1_quizapp.model;

public class Question {
    private final String question;
    private final boolean answer;

    public Question(String question, boolean answer){
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion(){
        return question;
    }

    public boolean getAnswer(){
        return answer;
    }
}
