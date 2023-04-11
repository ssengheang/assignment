package com.example.groupassessment.service;

import com.example.groupassessment.enitity.BorrowerImage;
import com.example.groupassessment.request_param.borrower_image.CreateReqParam;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BorrowerImageService {
    void save(MultipartFile file, CreateReqParam params);
    Resource load(String filename);
    List<BorrowerImage> index();
    BorrowerImage show(Long id);
    BorrowerImage update(Long id, MultipartFile file);
    String delete(Long id);
}
