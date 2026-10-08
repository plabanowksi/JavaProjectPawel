package pd5.model;

import java.time.LocalDateTime;

public class EmailNotification extends Notification {
    private final String recipient;
    private final String subject;

    public EmailNotification(long id, String content, LocalDateTime createdAt, String recipient, String subject) {
        super(id, content, createdAt);
        this.recipient = recipient;
        this.subject = subject;
    }

    @Override
    protected boolean sendNotification() {
        System.out.println("Sending notification by e-mail to: " + recipient + ", with subject: " + subject);
        return true;
    }
}