package pd5.model;

import java.time.LocalDateTime;

public class ConsultantNote {
    private final long id;
    private String content;
    private final LocalDateTime createdAt;
    private final String author;
    private boolean isArchived;

    public ConsultantNote(long id, String content, LocalDateTime createdAt, String author) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.author = author;
        this.isArchived = false;
    }

    private boolean isArchived() {
        return isArchived;
    }

    public void editNote(String newContent) {
        if (this.isArchived()) {
            System.out.println("This note has been already archived and cannot be changed!");
        } else {
            archive();
            this.content = newContent;
            System.out.println("This note has been archived successfully");
        }
    }

    private void archive() {
        if (!this.isArchived) {
            this.isArchived = true;
        }
    }
}