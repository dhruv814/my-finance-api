package com.finance.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseModel {

    private String message;
    private boolean status;
    private LocalDateTime dateAndTime;

}
