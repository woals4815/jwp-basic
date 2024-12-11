package next.model;

public class Answer {
    private int answerId;
    private String writer;
    private String contents;
    private String createdDate;
    private int questionId;

    public Answer(String writer, String contents, String createdDate, int questionId) {
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
    public String getCreatedDate() {
        return createdDate;
    }
}
