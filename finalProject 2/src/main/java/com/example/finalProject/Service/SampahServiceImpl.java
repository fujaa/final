package com.example.finalProject.Service;

import com.example.finalProject.Model.Sampah;
import com.example.finalProject.Repository.SampahRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SampahServiceImpl implements SampahService {

    @Autowired
    private SampahRepository sampahRepository;

    @Override
    public Sampah saveSampah(Sampah sampah) throws Exception {
        try {
            return sampahRepository.save(sampah);
        } catch (Exception e) {
            throw new Exception("Error save sampah: "+e.getMessage());
        }
    }
    @Override
    public Sampah updateSampah(long id, Sampah sampah) throws Exception {
        try {
            Sampah sampahExist = sampahRepository.findSampahById(id);
            if (sampahExist != null){
                sampah.setId(sampahExist.getId());
                return sampahRepository.save(sampah);
            } else {
                throw new Exception("Sampah tidak ditemukan");
            }
        } catch (Exception e) {
            throw new Exception("Error update sampah: "+e.getMessage());
        }
    }

    @Override
    public void deleteSampah(long id) {sampahRepository.deleteById(id);
    }

    @Override
    public Sampah getSampahById(long id) {
        return sampahRepository.findSampahById(id);
    }

    @Override
    public List<Sampah> getAllListsampah() {
        return sampahRepository.findAll();
    }
}
