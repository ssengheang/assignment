package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.BorrowerImage;
import com.example.groupassessment.enitity.projection.BorrowerImageProjection;
import com.example.groupassessment.enitity.projection.ContractProjection;
import com.example.groupassessment.enitity.response.ApiResponse;
import com.example.groupassessment.enitity.response.ApiStatus;
import com.example.groupassessment.enitity.response.Pagination;
import com.example.groupassessment.request_param.borrower_image.CreateReqParam;
import com.example.groupassessment.service.serviceImp.BorrowerImageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/borrower-images")
public class BorrowerImageController {
    private BorrowerImageServiceImp borrowerImageServiceImp;
    @Autowired
    public BorrowerImageController(BorrowerImageServiceImp borrowerImageServiceImp){
        this.borrowerImageServiceImp = borrowerImageServiceImp;
    }
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("borrowerId") CreateReqParam params) {
        String message = "";
        try {
            borrowerImageServiceImp.save(file, params);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return message;
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return message;
        }
    }

    @GetMapping(value = "/load/{filename:.+}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public ResponseEntity<Resource> loadFile(@PathVariable String filename) {
        Resource file = borrowerImageServiceImp.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("")
    public Map<String, Object> getAll(Pagination pagination){
        List<BorrowerImageProjection> borrowerImageProjections = borrowerImageServiceImp.index(pagination);
        Map<String, Object> map = new HashMap<>();
        map.put("data", borrowerImageProjections);
        map.put("pagination", pagination);
        return map;
    }

    @GetMapping("/{id}")
    public ApiResponse getOne(@PathVariable(value = "id") Long id){
        BorrowerImageProjection borrowerImageProjection = borrowerImageServiceImp.show(id);
        return new ApiResponse<>(
                ApiStatus.SUC_RETRIEVED.getCode(),
                ApiStatus.SUC_RETRIEVED.getMessage(),
                borrowerImageProjection
        );
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable(value = "id") Long id, @RequestParam("file") MultipartFile file){
        BorrowerImage borrowerImage = borrowerImageServiceImp.update(id, file);
        BorrowerImageProjection borrowerImageProjection = borrowerImageServiceImp.show(id);
        return new ApiResponse<>(
                ApiStatus.SUC_UPDATED.getCode(),
                ApiStatus.SUC_UPDATED.getMessage(),
                borrowerImageProjection
        );
    }

    @DeleteMapping("/{id}")
    public ApiResponse delete(@PathVariable(value = "id") Long id){
        Boolean isDeleted = borrowerImageServiceImp.delete(id);
        if (!isDeleted){
            return new ApiResponse<>(
                    ApiStatus.FAI_DELETED.getCode(),
                    ApiStatus.FAI_DELETED.getMessage()
            );
        }
        return new ApiResponse<>(
                ApiStatus.SUC_DELETED.getCode(),
                ApiStatus.SUC_DELETED.getMessage()
        );
    }
}
