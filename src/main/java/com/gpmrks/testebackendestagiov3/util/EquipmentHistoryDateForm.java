package com.gpmrks.testebackendestagiov3.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
public class EquipmentHistoryDateForm {

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime startDate;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime endDate;

    @JsonCreator
    public EquipmentHistoryDateForm(@JsonProperty("startDate") LocalDateTime startDate, @JsonProperty("endDate") LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
