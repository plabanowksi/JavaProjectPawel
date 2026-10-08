package pd5;

import pd5.model.ConsultantNote;
import pd5.model.EmailNotification;
import pd5.model.Notification;
import pd5.model.SmsNotification;
import pd5.service.NotificationService;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        String onehundredsixtyChars = "x".repeat(160);
        LocalDateTime localDateTime = LocalDateTime.now();
        Notification[] notifications = {
                new EmailNotification(2, "blablabla", localDateTime, "Wojciech Pborodo", "Zupa"),
                new SmsNotification(6, onehundredsixtyChars, localDateTime, "666777888")
        };

        NotificationService service = new NotificationService();
        service.sentNotifications(notifications);
        ConsultantNote consultantNote = new ConsultantNote(5, "blablabla", localDateTime, "Paweł");
        consultantNote.editNote(consultantNote);

        System.out.println("Trying to edit previous note:");
        consultantNote.editNote(consultantNote);
    }
}