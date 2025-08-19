package com.subString.irctc.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "imageUpload")
public class TrainImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String fileType;
    private long size;
    private LocalDateTime uploadTime = LocalDateTime.now();

    @OneToOne(mappedBy = "trainImage")
    private Train train1;

    public TrainImage(Long id, String fileName, String fileType, long size, LocalDateTime uploadTime, Train train1) {
        this.id = id;
        this.fileName = fileName;
        this.fileType = fileType;
        this.size = size;
        this.uploadTime = uploadTime;
        this.train1 = train1;
    }

    public TrainImage() {

    }



    public Train getTrain1() {
        return train1;
    }

    public void setTrain1(Train train1) {
        this.train1 = train1;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public LocalDateTime getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(LocalDateTime uploadTime) {
        this.uploadTime = uploadTime;
    }
}
