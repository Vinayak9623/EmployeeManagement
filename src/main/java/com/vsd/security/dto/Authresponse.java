package com.vsd.security.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Authresponse {

    private String token;
}
