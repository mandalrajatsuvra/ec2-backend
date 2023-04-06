package com.maliantala.club.users.maliantalaclubbackend.model;

import com.amazonaws.services.s3.model.S3Object;

public class PicS3Object {
    S3Object s3Object;
    String url;
    public PicS3Object(){

    }
    public void setS3Object(S3Object s3Object) {
        this.s3Object = s3Object;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public S3Object getS3Object() {
        return s3Object;
    }
    public String getUrl() {
        return url;
    }
}
