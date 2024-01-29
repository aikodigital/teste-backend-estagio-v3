package me.dri.aiko.exception.entities;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ExceptionEntity {


    private Date timestamp;
    private String message;
    private String details;
    private Integer errorStatus;
    private String path;
    public ExceptionEntity() {
    }
}
