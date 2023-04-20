package com.example.groupassessment.service;

import com.example.groupassessment.enitity.Remark;
import com.example.groupassessment.request_param.remark.CreateReqParam;
import com.example.groupassessment.request_param.remark.UpdateReqParam;

import java.util.List;

public interface RemarkService {
    List<Remark> index();
    Remark show(Long id);
    Remark update(Long id, UpdateReqParam remark);
    Remark create(CreateReqParam remark);
    String delete(Long id);
}
