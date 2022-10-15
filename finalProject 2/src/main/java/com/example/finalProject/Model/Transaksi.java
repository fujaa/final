package com.example.finalProject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@Entity
public class Transaksi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username")//isi dengan email
    private String username;

    @Column(name = "sampah_id")
    private long sampahId;

    @Column(name = "jumlah")
    private Integer jumlah;

    @Column(name = "total_harga")
    private BigDecimal totalHarga;

    @Column(name = "created_date")
    private Date createdDate;

    public Transaksi(){}
}
