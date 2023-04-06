package com.maliantala.club.users.maliantalaclubbackend.jms;

public class QueueName {
    private String readQueue;
    private String writeQueue;
    private String readGroupName;
    public QueueName(){ }
    public void setReadGroupName(String readGroupName) {
        this.readGroupName = readGroupName;
    }
    public String getReadGroupName() {
        return readGroupName;
    }
    public QueueName(String readQueue, String writeQueue, String readGroupName) {
        this.readQueue = readQueue;
        this.writeQueue = writeQueue;
        this.readGroupName = readGroupName;
    }
    public String getReadQueue() {
        return readQueue;
    }
    public void setReadQueue(String readQueue) {
        this.readQueue = readQueue;
    }
    public void setWriteQueue(String writeQueue) {
        this.writeQueue = writeQueue;
    }
    public String getWriteQueue() {
        return writeQueue;
    }
}
