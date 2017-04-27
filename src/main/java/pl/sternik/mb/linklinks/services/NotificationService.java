package pl.sternik.mb.linklinks.services;

import java.util.List;

import pl.sternik.mb.linklinks.services.NotificationServiceImpl.NotificationMessage;

public interface NotificationService {
    void addInfoMessage(String msg);
    void addErrorMessage(String msg);
    List<NotificationMessage> getNotificationMessages();
}