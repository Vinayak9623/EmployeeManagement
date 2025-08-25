package com.vsd.service;

import com.vsd.Dto.request.LetterRequest;
import com.vsd.Dto.response.LetterResponse;
import com.vsd.common.ApiResponse;

import java.util.List;

public interface LetterService {

    public ApiResponse<LetterResponse> createOrUpdateLetter(LetterRequest request);
    public ApiResponse<List<LetterResponse>> getLetter();
    public ApiResponse<LetterResponse> deleteLetter(Long id);
    public ApiResponse<List<LetterResponse>> getLetterFromEmployee(Long employeeId);
}
