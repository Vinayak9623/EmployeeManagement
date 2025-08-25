package com.vsd.service.impl;

import com.vsd.Dto.request.LetterRequest;
import com.vsd.Dto.response.LetterResponse;
import com.vsd.Repository.EmployeeRepo;
import com.vsd.Repository.LetterRepository;
import com.vsd.Repository.UserRepository;
import com.vsd.common.ApiResponse;
import com.vsd.entity.Employee;
import com.vsd.entity.Letter;
import com.vsd.entity.User;
import com.vsd.exception.customeEx.EmployeeNotFoundException;
import com.vsd.exception.customeEx.LetterNotFoundException;
import com.vsd.security.JwtService;
import com.vsd.service.LetterService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LetterServiceImpl implements LetterService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private LetterRepository letterRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ApiResponse<LetterResponse> createOrUpdateLetter(LetterRequest request) {

        User user = userRepository.findById(request.getHrId()).orElseThrow(() -> new UsernameNotFoundException("Hr not found"));
        Employee employee = employeeRepo.findById(request.getEmployeeId()).orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));

        Letter letter;
        if (request.getId() == null) {

            letter = Letter.builder()
                    .letterType(request.getLetterType())
                    .createdBy(user)
                    .recipient(employee)
                    .message(request.getMessage())
                    .dateTime(request.getDateTime()).build();
        } else {
            if (request.getId() != null) {
                letter = letterRepository.findById(request.getId())
                        .orElseThrow(()
                                -> new LetterNotFoundException
                                ("Letter not found with given Id: " + request.getId()));
            }
            letter = Letter.builder().letterType(request.getLetterType())
                    .createdBy(user)
                    .recipient(employee)
                    .message(request.getMessage())
                    .dateTime(request.getDateTime()).build();
        }

        Letter save = letterRepository.save(letter);
        LetterResponse response = LetterResponse.builder().id(save.getId())
                .letterType(save.getLetterType())
                .createdBy(user.getEmail())
                .recipient(employee.getEmail())
                .message(save.getMessage())
                .dateTime(save.getDateTime()).build();

        String message = request.getId() == null ? "Letter creted successfully" : "Letter updated successfully";
        return new ApiResponse<>(HttpStatus.OK.value(), message, response, null);
    }

    @Override
    public ApiResponse<List<LetterResponse>> getLetter() {
        List<Letter> letters = letterRepository.findAll();
        List<LetterResponse> list = letters.stream()
                .map(x -> modelMapper
                        .map(x, LetterResponse.class)).toList();
        return new ApiResponse<>(HttpStatus.OK.value(), "Data found successfully",list,null);
    }

    @Override
    public ApiResponse<LetterResponse> deleteLetter(Long id) {
        Letter letterId = letterRepository.findById(id).orElseThrow(() -> new LetterNotFoundException("Letter data not found"));
        letterRepository.deleteById(id);
        return new ApiResponse<>(HttpStatus.OK.value(), "Letter Deleted successfully",null,null);
    }

    @Override
    public ApiResponse<List<LetterResponse>> getLetterFromEmployee(Long employeeId) {
        List<Letter> letters = letterRepository.findByRecipientId(employeeId);
        List<LetterResponse> responses = letters.stream()
                .map(letter -> modelMapper.map(letter, LetterResponse.class))
                .toList();
        return new ApiResponse<>(HttpStatus.OK.value(), "Letters fetched", responses, null);

    }
}
