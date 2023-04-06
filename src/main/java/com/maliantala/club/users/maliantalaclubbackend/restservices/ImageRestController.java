package com.maliantala.club.users.maliantalaclubbackend.restservices;
import com.amazonaws.services.s3.model.S3Object;
import com.maliantala.club.users.maliantalaclubbackend.model.PicS3Object;
import com.maliantala.club.users.maliantalaclubbackend.model.UploadedImagePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(path = "/storage/")
public class ImageRestController {

    @Autowired
    AmazonS3CustomClient amazonS3Client;


    @CrossOrigin(value = "*")
    @PostMapping(path = "/uploadImage")
    public UploadedImagePath uploadImageToServer(@RequestPart(value = "file") MultipartFile file){
        UploadedImagePath uploadedImagePath = new UploadedImagePath();
        uploadedImagePath.setImageurl(this.amazonS3Client.uploadFile(file));
        return uploadedImagePath;
    }

    @CrossOrigin(value = "*")
    @PostMapping(value = "/uploadPicMessage")
    public PicS3Object uploadPicMessageToS3(@RequestPart(value = "file") MultipartFile multipartFile ){
       return this.amazonS3Client.uploadPicMessage(multipartFile);
    }

}
