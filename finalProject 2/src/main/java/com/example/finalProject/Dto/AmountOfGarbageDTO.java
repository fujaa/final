package com.example.finalProject.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class AmountOfGarbageDTO {
    private String bulan;
    private Integer sampahPlastik;
    private Integer sampahKertas;
    private Integer sampahBotol;
    private Integer sampahKain;
    private Integer sampahLainnya;
    private Integer total;

    public AmountOfGarbageDTO() {

    }
}
