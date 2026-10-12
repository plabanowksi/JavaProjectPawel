package pd5.model;

import java.time.LocalDateTime;

public abstract class Notification {
    private final long id;
    private String content;
    private final LocalDateTime createdAt;
    private boolean sent = false;

    public Notification(long id, String content, LocalDateTime createdAt) {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Content cannot be null");
        }
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
    }

    public abstract void sendNotification();
}