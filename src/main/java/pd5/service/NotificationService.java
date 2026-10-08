package pd5.service;

import pd5.model.Notification;

public class NotificationService {
    public void sentNotifications(Notification[] notifications) {
        for (Notification notification : notifications) {
            notification.send();
        }
    }
}