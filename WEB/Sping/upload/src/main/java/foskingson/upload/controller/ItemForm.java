package foskingson.upload.controller;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ItemForm {
    private Long id;
    private String itemName;
    private List<MultipartFile> imageFiles;
    private MultipartFile attachFile;
}
