package com.coocit.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectResult;
import com.coocit.config.OSSConfig;
import com.coocit.service.FileService;
import com.coocit.utils.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author: Coocit
 * @date: 2024/3/7
 * @description:
 */
@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Autowired
    private OSSConfig ossConfig;

    @Override
    public String uploadUserImg(MultipartFile file) {
        String bucketName = ossConfig.getBucketname();
        String endpoint = ossConfig.getEndpoint();
        String accessKeyId = ossConfig.getAccessKeyId();
        String accessKeySecret = ossConfig.getAccessKeySecret();

        //oss客户端构建
        OSS ossClient = new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);

        //获取文件原始名称 xxx.jpg
        String originalFilename = file.getOriginalFilename();

        //jdk8语法日期格式
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        //user/2022/12/12/sdsdwe/
        String folder = pattern.format(ldt);
        String fileName = CommonUtil.generateUUID();
        String extendsion = originalFilename.substring(originalFilename.lastIndexOf("."));

        //在oss上的bucket创建文件夹
        String newFilename = "user/"+folder+"/"+fileName+extendsion;

        try {
            PutObjectResult putObjectResult = ossClient.putObject(bucketName, newFilename, file.getInputStream());
            //拼装返回路径
            if(putObjectResult!=null){
                String imgUrl = "https://"+bucketName+"."+endpoint+"/"+newFilename;
                return imgUrl;
            }

        } catch (IOException e) {
            log.error("文件上传失败:{}",e.getMessage());
        }finally {
            ossClient.shutdown();
        }

        return null;
    }

}
