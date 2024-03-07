package com.coocit.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author: Coocit
 * @date: 2024/3/7
 * @description:
 */
public interface FileService {

    /**
     * 文件上传
     *
     * @param file 文件
     * @return {@link String}
     */
    String uploadUserImg(MultipartFile file);

}
