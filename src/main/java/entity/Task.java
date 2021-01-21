package entity;

import java.time.LocalDate;

public class Task {

    private int id;
    private String comment;
    private LocalDate editedAt;
    private Boolean status;

    public Task() {
    }

    public Task(int id, String comment, LocalDate createdAt, Boolean status) {
        this.id = id;
        this.comment = comment;
        this.editedAt = createdAt;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getEditedAt() {
        return editedAt;
    }

    public void setEditedAt(LocalDate editedAt) {
        this.editedAt = editedAt;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", editedAt=" + editedAt +
                ", status=" + status +
                '}';
    }
}
