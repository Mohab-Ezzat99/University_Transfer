package mrandroid.app.model;

import java.util.List;

public class QuestionModel {

    private String question;
    private boolean is4Option;
    private boolean isCode;
    private List<AnswerModel> answers;

    public QuestionModel() {

    }

    public QuestionModel(String question, boolean is4Option, boolean isCode, List<AnswerModel> answers) {
        this.question = question;
        this.is4Option = is4Option;
        this.isCode = isCode;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isIs4Option() {
        return is4Option;
    }

    public void setIs4Option(boolean is4Option) {
        this.is4Option = is4Option;
    }

    public boolean isCode() {
        return isCode;
    }

    public void setCode(boolean code) {
        isCode = code;
    }

    public List<AnswerModel> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerModel> answers) {
        this.answers = answers;
    }
}
