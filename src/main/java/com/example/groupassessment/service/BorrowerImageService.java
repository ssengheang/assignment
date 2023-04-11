package com.example.groupassessment.service;

import com.example.groupassessment.enitity.Borrower;
import com.example.groupassessment.enitity.BorrowerImage;
import com.example.groupassessment.repository.BorrowerImageRepo;
import com.example.groupassessment.repository.BorrowerRepo;
import com.example.groupassessment.request_param.borrower_image.CreateReqParam;
import com.example.groupassessment.utils.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.multipart.MultipartFile;

import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.List;

@Service
public class BorrowerImageService {
    private BorrowerImageRepo borrowerImageRepo;
    private BorrowerRepo borrowerRepo;
    @Autowired
    public BorrowerImageService(BorrowerImageRepo borrowerImageRepo, BorrowerRepo borrowerRepo){
        this.borrowerImageRepo = borrowerImageRepo;
        this.borrowerRepo = borrowerRepo;
    }

    public void save(MultipartFile file, CreateReqParam params) {
        try {
            //Upload file
            UploadFile uploadFile = new UploadFile(file);
            String path = uploadFile.uploadFile();

            // Add to DB
            Long borrower_id = Long.parseLong(params.getBorrowerId());
            Borrower borrower = borrowerRepo.findById(borrower_id)
                    .orElseThrow(() -> new ResourceAccessException("No resource constrain found!"));
            BorrowerImage borrowerImage1 = new BorrowerImage();
            borrowerImage1.setPath(path);
            borrowerImage1.setBorrower(borrower);
            borrowerImageRepo.save(borrowerImage1);
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException) {
                throw new RuntimeException("A file of that name already exists.");
            }

            throw new RuntimeException(e.getMessage());
        }
    }

    public Resource load(String filename) {
        try {
            UploadFile uploadFile = new UploadFile();
            Path file = uploadFile.getRoot().resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public List<BorrowerImage> index(){
        return borrowerImageRepo.findAll();
    }

    public BorrowerImage show(Long id){
        return borrowerImageRepo.findById(id).orElseThrow(() -> new ResourceAccessException("No resource found!"));
    }

    public BorrowerImage update(Long id, MultipartFile file){
        BorrowerImage borrowerImage;
        try {
            BorrowerImage update_borrower_image = borrowerImageRepo.findById(id).orElseThrow(() -> new ResourceAccessException("No resource found!"));
            //Upload file
            UploadFile uploadFile = new UploadFile(file);
            String path = uploadFile.uploadFile();

            // Update record
            update_borrower_image.setPath(path);
            borrowerImage = borrowerImageRepo.save(update_borrower_image);
        }catch (Exception e){throw new RuntimeException(e.getMessage());}

        return borrowerImage;
    }

    public String delete(Long id){
        BorrowerImage delete_borrower_image = borrowerImageRepo.findById(id).orElseThrow(() -> new ResourceAccessException("No resource found!"));
        borrowerImageRepo.delete(delete_borrower_image);
        return "Deleted";
    }


}
