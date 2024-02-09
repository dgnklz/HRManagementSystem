package com.dgnklz.hrmanagementsystem.cores.exceptions.messages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TokenErrorMessage {
    private int statusCode;
    private String message;
}