package com.erick.controller;

import com.erick.pojo.Result;
import com.erick.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Slf4j
@RestController
public class UploadController {

    @Autowired
    private AliyunOSSOperator ossOperator;

    // Save file in Aliyun
    /**
     * Upload file to Aliyun
     * @param file image file
     * @return Result object containing image access url
     */
    @RequestMapping("upload")
    public Result uploadAvatar(MultipartFile file) throws Exception {

        log.info("Upload file: {}", file.getOriginalFilename());

        // send to file to OSS storage management
        String url = ossOperator.upload(file.getBytes(), Objects.requireNonNull(file.getOriginalFilename()));

        log.info("File has been uploaded to Aliyun OSS, url is {}", url);

        return Result.success(url);
    }



    /*//  Save file in local disk
     @RequestMapping("/upload")
    public Result uploadAvatar(String name, Integer age, MultipartFile file) throws IOException {
        log.info("Received parameters: {}, {}, {}", name, age, file);

        // get original file name
        String originalFilename = file.getOriginalFilename();

        // set a new file name
        String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String newFileName = UUID.randomUUID().toString() + extension;

        // save file
        file.transferTo(new File("/Users/chenzewen/Library/CloudStorage/" +
                "SynologyDrive-synology/Backend Files/Tlias/images/" + newFileName));

        return Result.success();
    }*/
}
