package next.model;

import java.sql.Timestamp;
import java.util.Date;

public class Question {
    private int questionId;
    private String writer;
    private String title;
    private String contents;
    private Timestamp createdDate;
    private int countOfAnswer;

    public Question(String writer, String title, String contents, Timestamp createdDate, int countOfAnswer) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }

    public Question(int questionId, String writer, String title, String contents, Timestamp createdDate, int countOfAnswer) {
        this.questionId = questionId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }
    public int getQuestionId() {
        return questionId;
    }
    public String getWriter() {
        return writer;
    }
    public String getTitle() {
        return title;
    }
    public String getContents() {
        return contents;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public int getCountOfAnswer() {
        return countOfAnswer;
    }

    public String toString() {
        return String.format("Question [questionId=%s, writer=%s, title=%s, contents=%s, createdDate=%s, countOfAnswer=%s]", questionId, writer, title, contents, createdDate, countOfAnswer);
    }
}
