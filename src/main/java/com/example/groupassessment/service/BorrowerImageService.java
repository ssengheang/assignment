package com.example.groupassessment.service;

import com.example.groupassessment.enitity.BorrowerImage;
import com.example.groupassessment.enitity.projection.BorrowerImageProjection;
import com.example.groupassessment.enitity.response.Pagination;
import com.example.groupassessment.request_param.borrower_image.CreateReqParam;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BorrowerImageService {
    void save(MultipartFile file, CreateReqParam params);
    Resource load(String filename);
    List<BorrowerImageProjection> index(Pagination pagination);
    BorrowerImageProjection show(Long id);
    BorrowerImage update(Long id, MultipartFile file);
    Boolean delete(Long id);
}
