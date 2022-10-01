package com.artineer.artineer.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class UploadFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uploadFile_no")
    private Long no;
    private String uploadFileName;
    private String storeFileName;
    // 같은 이름의 파일을 업로드 했을 때 파일이 덮어짐.
    // 그래서 파일 이름을 구분 시켰다.

    @OneToOne(mappedBy = "uploadFile", fetch = FetchType.LAZY)
    @JsonIgnore
    private Notice notice;

    protected UploadFile() {
    }

    public UploadFile(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }

    public static UploadFile createUploadFile(String uploadFileName, String storeFileName) {
        return new UploadFile(uploadFileName, storeFileName);
    }
}