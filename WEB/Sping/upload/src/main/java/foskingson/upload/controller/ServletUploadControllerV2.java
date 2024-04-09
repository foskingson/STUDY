package foskingson.upload.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Slf4j
@Controller
@RequestMapping("/servlet/v2")
public class ServletUploadControllerV2 {

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/upload")
    public String newFile() {
        return "upload-form";
    }
    
    @PostMapping("/upload")
    public String savFileV1(HttpServletRequest request) throws IOException, ServletException {

        log.info("request= {} ",request);

        String itemName = request.getParameter("itemName");
        log.info("itemName={}", itemName);

        Collection<Part> parts = request.getParts();
        //log.info("parts={}", parts);

        for (Part part : parts) {
            log.info("=====part=====");
            log.info("name={}", part.getName());
            Collection<String> headerNames = part.getHeaderNames();
            for (String headerName : headerNames) {
                log.info("header {}: {}", headerName,part.getHeader(headerName));
            }

            // 편의메서드
            // content-disposition; filename
            log.info("submitfilename={}", part.getSubmittedFileName());
            log.info("size={}", part.getSize()); // 바디 사이즈

            // 데이터 읽기
            InputStream inputStream = part.getInputStream();
            String body = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
            // log.info("body={}", body);

            // 파일에 저장
            if (StringUtils.hasText(part.getSubmittedFileName())) {
                String fullPath = fileDir+part.getSubmittedFileName();
                log.info("파일저장 경로: {}", fullPath);
                part.write(fullPath);
            }
        }

        return "upload-form";
    }
    
}
