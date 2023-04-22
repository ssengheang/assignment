package com.example.groupassessment.controller;

import com.example.groupassessment.enitity.BorrowerImage;
import com.example.groupassessment.request_param.borrower_image.CreateReqParam;
import com.example.groupassessment.service.serviceImp.BorrowerImageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/borrower-images")
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
    public List<BorrowerImage> getAll(){
        return borrowerImageServiceImp.index();
    }

    @GetMapping("/{id}")
    public BorrowerImage getOne(@PathVariable(value = "id") Long id){
        return borrowerImageServiceImp.show(id);
    }

    @PutMapping("/{id}")
    public BorrowerImage update(@PathVariable(value = "id") Long id, @RequestParam("file") MultipartFile file){
        return borrowerImageServiceImp.update(id, file);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable(value = "id") Long id){
        return borrowerImageServiceImp.delete(id);
    }
}