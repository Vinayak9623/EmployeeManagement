package com.vsd.controller;

import com.vsd.Dto.request.LetterRequest;
import com.vsd.Dto.response.LetterResponse;
import com.vsd.common.ApiResponse;
import com.vsd.service.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/letter")
public class LetterController {

    @Autowired
    private LetterService service;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<LetterResponse>> create(@RequestBody LetterRequest request) {
        ApiResponse<LetterResponse> orUpdateLetter = service.createOrUpdateLetter(request);
        return ResponseEntity.status(orUpdateLetter.getStatus()).body(orUpdateLetter);
    }

    @GetMapping("/getLetters")
    public ResponseEntity<ApiResponse<List<LetterResponse>>> findLetters() {
        ApiResponse<List<LetterResponse>> letter = service.getLetter();
        return ResponseEntity.status(letter.getStatus()).body(letter);
    }

    @GetMapping("/getLettersByEmployee/{employeeId}")
    public ResponseEntity<ApiResponse<List<LetterResponse>>> getLetterFromEmployee(@PathVariable("employeeId") Long employeeId) {
        ApiResponse<List<LetterResponse>> letter = service.getLetterFromEmployee(employeeId);
        return ResponseEntity.status(letter.getStatus()).body(letter);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse<LetterResponse>> deleteLetter(@PathVariable("id") Long id) {
        ApiResponse<LetterResponse> letterResponseApiResponse = service.deleteLetter(id);
        return ResponseEntity.status(letterResponseApiResponse.getStatus()).body(letterResponseApiResponse);
    }
}
