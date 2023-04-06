package com.maliantala.club.users.maliantalaclubbackend.restservices;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.maliantala.club.users.maliantalaclubbackend.model.PicS3Object;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Component
public class AmazonS3CustomClient {

        AmazonS3 amazonS3;

        @Value("${amazonsPropeties.endpointUrl}")
        public String endpointUrl;

        @Value("${amazonsPropeties.bucketName}")
        public String bucketName;

        @Value("${amazonsPropeties.accessKey}")
        public String accessKey;

        @Value("${amazonsPropeties.secretKey}")
        public String secretKey;

        @PostConstruct
        public void createAmazonS3Client(){
                AWSCredentials awsCredentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
                this.amazonS3 = new AmazonS3Client(awsCredentials);
        }
        private File convertMultiPartToFile(MultipartFile file) throws IOException {
                File convFile = new File(file.getOriginalFilename());
                FileOutputStream fos = new FileOutputStream(convFile);
                fos.write(file.getBytes());
                fos.close();
                return convFile;
        }

        public PicS3Object uploadPicMessage(MultipartFile multipartFile  ){
                S3Object s3Object = null;
                PicS3Object picS3Object = null;
                try{
                        File file = convertMultiPartToFile(multipartFile);
                        String fileName = generateFileName(multipartFile);
                        picS3Object = uploadPictureMessageTos3Bucket(fileName, file);
                }catch (Exception e) {
                        e.printStackTrace();
                }
                return  picS3Object;
        }

        public String uploadFile(MultipartFile multipartFile) {
                String uploadedImageURL = "";
                try {
                        File file = convertMultiPartToFile(multipartFile);
                        String fileName = generateFileName1(multipartFile);
                        uploadedImageURL= uploadFileTos3bucket(fileName, file);
                        file.delete();
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return uploadedImageURL;
        }
        private String generateFileName1(MultipartFile multiPart) {
                return  "Send-" +  new  Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
        }
        private String generateFileName(MultipartFile multiPart) {
                return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
        }
        private String uploadFileTos3bucket(String fileName, File file) {
                this.amazonS3.putObject(new PutObjectRequest(this.bucketName, fileName, file)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
                System.out.println("-----------Uplaod File-----"+this.amazonS3.getUrl(this.bucketName, fileName));
                return  this.amazonS3.getUrl(this.bucketName, fileName).toString();
        }
        private PicS3Object uploadPictureMessageTos3Bucket(String fileaName, File file ){
                this.amazonS3.putObject(new PutObjectRequest(this.bucketName, fileaName,file)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
                S3Object s3Object = this.amazonS3.getObject(this.bucketName,fileaName);
                S3ObjectInputStream s3ObjectInputStream= s3Object.getObjectContent();
                PicS3Object picS3Object = new PicS3Object();
                picS3Object.setUrl(amazonS3.getUrl(this.bucketName, fileaName).toString());
                picS3Object.setS3Object(s3Object);
                return  picS3Object;

        }
}
