package com.maliantala.club.users.maliantalaclubbackend.model;

public class TypingMessagePojo {
    private String from;
    private String to;
    private String typing;
    public TypingMessagePojo(String from, String to, String typing) {
        this.from = from;
        this.to = to;
        this.typing = typing;
    }
    public TypingMessagePojo() { }
    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }
    public String getTyping() {
        return typing;
    }
    public void setFrom(String from) {
        this.from = from;
    }
    public void setTo(String to) {
        this.to = to;
    }
    public void setTyping(String typing) {
        this.typing = typing;
    }
    @Override
    public String toString() {
        return "TypingMessagePojo{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", typing='" + typing + '\'' +
                '}';
    }
}
