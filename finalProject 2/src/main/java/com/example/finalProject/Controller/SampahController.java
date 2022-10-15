package com.example.finalProject.Controller;

import com.example.finalProject.Model.Sampah;
import com.example.finalProject.Service.SampahServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SampahController {

    @Autowired
    private SampahServiceImpl sampahService;

    @PostMapping("/sampah")
    public ResponseEntity<?> saveSampah(@RequestBody Sampah sampah) throws Exception {
        sampahService.saveSampah(sampah);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/sampah")
    public ResponseEntity<?> updateSampah (@RequestParam long id, @RequestBody Sampah sampah) throws Exception {
        sampahService.updateSampah(id, sampah);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/sampah")
    public ResponseEntity<?> deleteSampah (@RequestParam long id){
        sampahService.deleteSampah(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/sampahId")
    public Sampah getSampahById (@RequestParam long id){
        return sampahService.getSampahById(id);
    }

    @GetMapping("/sampah/list")
    public List<Sampah> getAllListOfSampah() {
        return sampahService.getAllListsampah();
    }
}