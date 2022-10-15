package com.example.finalProject.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class HistoryDTO {
    private String name;
    private String address;
    private Integer total;
    private BigDecimal totalHarga;

    public HistoryDTO() {

    }
}
