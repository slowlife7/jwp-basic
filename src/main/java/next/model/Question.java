package next.model;


import java.util.Date;

public class Question {
    private long    questionId;
    private String  writer;
    private String  title;
    private String  contents;
    private Date    createdDate;
    private int     countOfAnswer;

    public Question(Long questionId, String writer, String title, String contents, Date createdDate, Integer countOfAnswer) {
        this.questionId = questionId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }

    public long getQuestionId() {
        return questionId;
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

    public Integer getCountOfAnswer() {
        return countOfAnswer;
    }

    public String getWriter() {
        return writer;
    }

    @Override
    public String toString() {
        return "Question [questionId = " + questionId + "writer = " + writer + "title = "+ title + "contents = " + contents + "createdDate = "
        + createdDate + "countOfAnswer = " + countOfAnswer;
    }
}
