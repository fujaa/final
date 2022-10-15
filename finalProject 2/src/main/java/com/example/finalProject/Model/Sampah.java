package com.example.finalProject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Entity
public class Sampah {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "tipe_sampah")//isi => plastik / kertas / kaca / kain / lainnya
    private String tipeSampah;

    @Column(name = "jenis_sampah")
    private String jenisSampah;

    @Column(name = "harga")
    private BigDecimal harga;

    @Column(name = "keterangan")
    private String keterangan;

    public Sampah(){}
}
