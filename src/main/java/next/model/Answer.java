package next.model;

import java.sql.Timestamp;

public class Answer {
    private int answerId;
    private String writer;
    private String contents;
    private Timestamp createdDate;
    private int questionId;

    public Answer(String writer, String contents, Timestamp createdDate, int questionId) {
        this.writer = writer;
        this.contents = contents;
        this.createdDate = createdDate;
        this.questionId = questionId;
    }

    public Answer(int answerId, String writer, String contents, Timestamp createdDate, int questionId) {
        this.answerId = answerId;
        this.writer = writer;
        this.contents = contents;
        this.createdDate = createdDate;
        this.questionId = questionId;
    }


    public int getAnswerId() {
        return answerId;
    }

    public int getQuestionId() {
        return questionId;
    }
    public String getWriter() {
        return writer;
    }
    public String getContents() {
        return contents;
    }
    public Timestamp getCreatedDate() {
        return createdDate;
    }
}
