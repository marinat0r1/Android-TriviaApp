package com.example.trivia.model;

import java.util.ArrayList;

public class Question {

    private String question;
    private String id;
    private String category;
    private String type;
    private String difficulty;
    private ArrayList<String> possibleAnswers;
    private ArrayList<String> incorrectAnswers;
    private String correctAnswer;
    private int version;


    public Question() {

    }

    public Question(String question,
                    String id,
                    String category,
                    String type,
                    String difficulty,
                    ArrayList<String> possibleAnswers,
                    ArrayList<String> incorrectAnswers,
                    String correctAnswer,
                    int version) {
        this.question = question;
        this.id = id;
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
        this.possibleAnswers = possibleAnswers;
        this.incorrectAnswers = incorrectAnswers;
        this.correctAnswer = correctAnswer;
        this.version = version;
    }

    public Question(String question,
                    ArrayList<String> possibleAnswers,
                    ArrayList<String> incorrectAnswers,
                    String correctAnswer) {
        this.question = question;
        this.possibleAnswers = possibleAnswers;
        this.incorrectAnswers = incorrectAnswers;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public ArrayList<String> getPossibleAnswers() {
        return possibleAnswers;
    }

    public void setPossibleAnswers(ArrayList<String> possibleAnswers) {
        this.possibleAnswers = possibleAnswers;
    }

    public ArrayList<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(ArrayList<String> incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", possibleAnswers=" + possibleAnswers +
                ", incorrectAnswers=" + incorrectAnswers +
                ", correctAnswer='" + correctAnswer + '\'' +
                ", version=" + version +
                '}';
    }
}
