package com.maliantala.club.users.maliantalaclubbackend.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String text;
    private String to0;
    private String form;
    private Long timestamp;
    private String type;
    private String url;
    private String hashKey;
    private String caption;
    private String hasSeenByPeer;
    private String sendOrReceive;
    public Message(){ }

    public Message(Integer id, String text, String to, String form, Long timestamp, String type, String url, String hashKey, String caption, String hasSeenByPeer,String sendOrReceive) {
        this.id = id;
        this.text = text;
        this.to0 = to;
        this.form = form;
        this.timestamp = timestamp;
        this.type = type;
        this.url = url;
        this.hashKey = hashKey;
        this.caption = caption;
        this.hasSeenByPeer = hasSeenByPeer;
        this.sendOrReceive = sendOrReceive;
    }


    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
    public Long getTimestamp() {
        return timestamp;
    }
    public void setSendOrReceive(String sendOrReceive) {
        this.sendOrReceive = sendOrReceive;
    }
    public String getSendOrReceive() {
        return sendOrReceive;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public void setHasSeenByPeer(String hasSeenByPeer) {
        this.hasSeenByPeer = hasSeenByPeer;
    }

    public Integer getId() {
        return id;
    }
    public String getHasSeenByPeer() {
        return hasSeenByPeer;
    }
    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String getHashKey() {
        return hashKey;
    }

    public String getCaption() {
        return caption;
    }
    public void setType(String type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getText() {
        return text;
    }

    public String getTo0() {
        return to0;
    }

    public String getForm() {
        return form;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTo0(String to) {
        this.to0 = to;
    }

    public void setForm(String form) {
        this.form = form;
    }
    /*@Override
    public String toString() {
        return "Message{" +
                "text='" + text + '\'' +
                ", to='" + to + '\'' +
                ", form='" + form + '\'' +
                ", timestamp=" + timestamp +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", hashKey='" + hashKey + '\'' +
                ", caption='" + caption + '\'' +
                '}';
    }*/
}
