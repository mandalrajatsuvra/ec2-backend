package com.maliantala.club.users.maliantalaclubbackend.model;

public class AcknowledgementForSendMessage {
    private Integer messageId;
    private String from;
    private String to;
    private boolean isAcknowledgementSend;

    public AcknowledgementForSendMessage() {

    }
    public AcknowledgementForSendMessage(String from, String to, boolean isAcknowledgementSend, Integer messageId) {
        this.from = from;
        this.to = to;
        this.isAcknowledgementSend = isAcknowledgementSend;
        this.messageId = messageId;
    }

    @Override
    public String toString() {
        return "AcknowledgementForSendMessage{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", isAcknowledgementSend=" + isAcknowledgementSend +
                '}';
    }

    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }
    public boolean isAcknowledgementSend() {
        return isAcknowledgementSend;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public void setAcknowledgementSend(boolean acknowledgementSend) {
        isAcknowledgementSend = acknowledgementSend;
    }
    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }
    public Integer getMessageId() {
        return messageId;
    }

}
