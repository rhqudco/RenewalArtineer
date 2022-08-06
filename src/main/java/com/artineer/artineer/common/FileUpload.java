package com.artineer.artineer.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class FileUpload {
    public String fileUpload(List<MultipartFile> files) throws IOException {
        String basePath = "/upload"; // 파일 업로드될 경로
        String originalName = "";
        StringBuilder name = new StringBuilder();
        if (files.size() >= 1) {
            // 파일 업로드(여러개) 처리 부분
            for (MultipartFile file : files) {
                originalName = Objects.requireNonNull(file.getOriginalFilename()).replace(" ", "");
                String filePath = basePath + "/" + originalName;
                if (originalName.equals("")) { // 파일 아에 없을 때도 size가 1이라 orginalName이 공백이면 작동
                    System.out.println("등록된 파일 없음.");
                }
                // 파일 1개 일 때도 size가 1로 들어와서 파일이 1개 있을 때 else문 작동
                else {
                    File dest = new File(filePath);
                    file.transferTo(dest);
                }
                name.append(originalName).append(" ");
            }
        }
        return name.toString();
    }
}