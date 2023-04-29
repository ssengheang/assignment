package com.example.groupassessment.service;

import com.example.groupassessment.enitity.Remark;
import com.example.groupassessment.enitity.projection.RemarkProjection;
import com.example.groupassessment.request_param.remark.CreateReqParam;
import com.example.groupassessment.request_param.remark.UpdateReqParam;

import java.util.List;

public interface RemarkService {
    List<RemarkProjection> index();
    RemarkProjection show(Long id);
    Remark update(Long id, UpdateReqParam remark);
    Remark create(CreateReqParam remark);
    Boolean delete(Long id);
}
