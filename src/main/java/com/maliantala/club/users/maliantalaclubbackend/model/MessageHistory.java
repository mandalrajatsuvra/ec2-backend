package com.maliantala.club.users.maliantalaclubbackend.model;

public class MessageHistory {
    private String messageHistoryHtmlBody;

    public MessageHistory() {
    }
    public MessageHistory(String messageHistoryHtmlBody) {
        this.messageHistoryHtmlBody = messageHistoryHtmlBody;
    }
    public String getMessageHistoryHtmlBody()  {
        return messageHistoryHtmlBody;
    }
    public void setMessageHistoryHtmlBody(String messageHistoryHtmlBody) {
        this.messageHistoryHtmlBody = messageHistoryHtmlBody;
    }
}
