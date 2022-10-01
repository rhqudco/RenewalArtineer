package com.artineer.artineer.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Component
@Slf4j
public class WritingShowImage {

    @Value("${file.dir}")
    private String fileDir;
    public File imagePost(MultipartFile[] uploadFile) {
        File result = null;

        String uploadFolder = fileDir;

        /* 추가된 부분 ......... */
//        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd");
//        Date date = new Date();
//        String formatDate = sdt.format(date);
//
//        String datePath = formatDate.replace("-", File.separator);

        File uploadPath = new File(uploadFolder);

//        if (!uploadPath.exists()) {
//            uploadPath.mkdirs();
//        }
        /*..........*/

        for (MultipartFile multipartFile : uploadFile) {

            String uploadFileName = multipartFile.getOriginalFilename();

            /* 변경 위치 ............. */
            String uuid = UUID.randomUUID().toString();
            uploadFileName = uuid + "_" + uploadFileName;

            File saveFile = new File(uploadPath, uploadFileName);
            log.info("saveFile = {}", saveFile);
            /*.................*/
            result = saveFile;


            try {
                multipartFile.transferTo(saveFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public ResponseEntity<byte[]> displayImage(String fileName) {
        log.info("Controller showImageGET");

        log.info("fileName = {}", fileName);

        File file = new File(fileName);

        log.info("file = {}", file);

        ResponseEntity<byte[]> result = null;

        try {

            HttpHeaders header = new HttpHeaders();

        /*
        Files.probeContentType() 해당 파일의 Content 타입을 인식(image, text/plain ...)
        없으면 null 반환

        file.toPath() -> file 객체를 Path객체로 변환

        */
            header.add("Content-type", Files.probeContentType(file.toPath()));

            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}