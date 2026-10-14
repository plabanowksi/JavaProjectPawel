package pd5.model;

import java.time.LocalDateTime;

public class SmsNotification extends Notification {
    private final String phoneNumber;

    public SmsNotification(long id, String content, LocalDateTime createdAt, String phoneNumber) {
        super(id, content, createdAt);
        if (content.length() > 160) {
            throw new IllegalArgumentException("SMS Content cannot have more than 160 characters");
        }
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void sendNotification() {
        System.out.println("Sending notification by SMS on number: " + phoneNumber);
    }
}