package com.example.finalProject.Service;

import com.example.finalProject.Model.Sampah;

import java.util.List;

public interface SampahService {
    Sampah saveSampah(Sampah sampah) throws Exception;
    Sampah updateSampah(long id, Sampah sampah) throws Exception;
    void deleteSampah(long id);
    Sampah getSampahById(long id);
    List<Sampah> getAllListsampah();
}
