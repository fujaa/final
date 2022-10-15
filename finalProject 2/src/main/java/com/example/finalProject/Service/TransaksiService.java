package com.example.finalProject.Service;

import com.example.finalProject.Dto.AmountOfGarbageDTO;
import com.example.finalProject.Dto.HistoryDTO;
import com.example.finalProject.Model.Transaksi;

import java.util.List;

public interface TransaksiService {
    Transaksi saveTransaksi(Transaksi transaksi) throws Exception;
    Transaksi updateTransaksi(long id, Transaksi transaksi) throws Exception;
    void deleteTransaksi(long id);
    Transaksi getTransaksiById(long id);
    List<Transaksi> getAllListTransaksi();
    List<AmountOfGarbageDTO> getAmountOfGarbage() throws Exception;
    //List<AmountOfGarbageDTO> getAmountOfGarbageByUsername(String username) throws Exception;
    List<HistoryDTO> getHistory(String name) throws Exception;
}
