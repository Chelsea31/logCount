package com.example.logCount.models.constants;

/*
 * @author Shubham Bansal
 */

public enum NotificationChannels {
    SMS {
        @Override
        public void sendNotification() {

        }
    }, EMAIL {
        @Override
        public void sendNotification() {

        }
    }, WHATSAPP {
        @Override
        public void sendNotification() {

        }
    };

    public abstract void sendNotification();
}
