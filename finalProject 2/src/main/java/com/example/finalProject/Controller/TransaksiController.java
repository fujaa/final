package com.example.finalProject.Controller;

import com.example.finalProject.Dto.AmountOfGarbageDTO;
import com.example.finalProject.Dto.HistoryDTO;
import com.example.finalProject.Model.Transaksi;
import com.example.finalProject.Service.TransaksiServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransaksiController {

    @Autowired
    private TransaksiServiceImpl transaksiService;

    @PostMapping("/transaksi")
    public ResponseEntity<?> saveTransaksi(@RequestBody Transaksi transaksi) throws Exception {
        transaksiService.saveTransaksi(transaksi);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/transaksi")
    public ResponseEntity<?> updateTransaksi (@RequestParam long id, @RequestBody Transaksi transaksi) throws Exception {
        transaksiService.updateTransaksi(id, transaksi);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/transaksi")
    public ResponseEntity<?> deleteTransaksi (@RequestParam long id){
        transaksiService.deleteTransaksi(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @GetMapping("/transaksiId")
//    public Transaksi getTransaksiById (@RequestParam long id){
//        return transaksiService.getTransaksiById(id);
//    }

    @GetMapping("/transaksi/list")
    public List<Transaksi> getAllListOfTransaksi() {
        return transaksiService.getAllListTransaksi();
    }

    @GetMapping("/transaksi/amountofgarbage")
    public List<AmountOfGarbageDTO> getAmountOfGarbage() throws Exception {
        return transaksiService.getAmountOfGarbage();
    }

//    @GetMapping("/transaksi/user/amountofgarbage")
//    public List<AmountOfGarbageDTO> getAmountOfGarbageByUser(@RequestParam String username) throws Exception {
//        return transaksiService.getAmountOfGarbageByUsername(username);
//    }

    @GetMapping("/transaksi/history")
    public List<HistoryDTO> getHistory(@RequestParam(required = false) String name) throws Exception {
        return transaksiService.getHistory(name);
    }
}
