package com.example.finalProject.Service;

import com.example.finalProject.Dto.AmountOfGarbageDTO;
import com.example.finalProject.Dto.HistoryDTO;
import com.example.finalProject.Model.Sampah;
import com.example.finalProject.Model.Transaksi;
import com.example.finalProject.Model.User;
import com.example.finalProject.Repository.TransaksiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransaksiServiceImpl implements TransaksiService {

    @Autowired
    private TransaksiRepository transaksiRepository;

    @Autowired
    private SampahServiceImpl sampahService;

    @Autowired
    private JwtUserDetailService jwtUserDetailService;

    @Override
    public Transaksi saveTransaksi(Transaksi transaksi) throws Exception {
        try {
            //Sampah sampah = sampahService.getSampahById(transaksi.getSampahId());
//            double totalHarga = 0.00;
//            if (sampah != null) {
//                if(sampah.getTipeSampah().equalsIgnoreCase("plastik"))
//                     totalHarga = sampah.getHarga() * (transaksi.getJumlah());
//                else if(sampah.getTipeSampah().equalsIgnoreCase("botol"))
//                    totalHarga = sampah.getHarga() * (transaksi.getJumlah());
//                else if(sampah.getTipeSampah().equalsIgnoreCase("kertas"))
//                    totalHarga = sampah.getHarga() * (transaksi.getJumlah());
//                else if(sampah.getTipeSampah().equalsIgnoreCase("kain"))
//                    totalHarga = sampah.getHarga() * (transaksi.getJumlah());
//                else if(sampah.getTipeSampah().equalsIgnoreCase("lainnya"))
//                    totalHarga = sampah.getHarga() * (transaksi.getJumlah());
//            }
            //BigDecimal totalHarga = sampah.getHarga().multiply(BigDecimal.valueOf(transaksi.getJumlah()));
            Sampah sampah = sampahService.getSampahById(transaksi.getSampahId());
            BigDecimal totalHarga = sampah.getHarga().multiply(BigDecimal.valueOf(transaksi.getJumlah()));
            transaksi.setTotalHarga(totalHarga);
            transaksi.setCreatedDate(new Date());
            //transaksiRepository.save(transaksi);
//            transaksi.setTotalHarga(totalHarga);
//            transaksi.setCreatedDate(new Date());
            return transaksiRepository.save(transaksi);
        } catch (Exception e) {
            throw new Exception("Error save transaksi: "+e.getMessage());
        }
    }

    @Override
    public Transaksi updateTransaksi(long id, Transaksi transaksi) throws Exception {
        try {
            Transaksi transaksiExist = transaksiRepository.findTransaksiById(id);
            if (transaksiExist != null){
                transaksi.setId(transaksiExist.getId());
//                Sampah sampah = sampahService.getSampahById(transaksi.getSampahId());
//                BigDecimal totalHarga = sampah.getHarga().multiply(BigDecimal.valueOf(transaksi.getJumlah()));
//                transaksi.setTotalHarga(totalHarga);
                return transaksiRepository.save(transaksi);
            } else {
                throw new Exception("Transaksi tidak ditemukan");
            }
        } catch (Exception e) {
            throw new Exception("Error update transaksi: "+e.getMessage());
        }
    }

    @Override
    public void deleteTransaksi(long id) {
        transaksiRepository.deleteById(id);
    }

    @Override
    public Transaksi getTransaksiById(long id) {
        return transaksiRepository.findTransaksiById(id);
    }

    @Override
    public List<Transaksi> getAllListTransaksi() {
        return transaksiRepository.findAll();
    }

    @Override
    public List<AmountOfGarbageDTO> getAmountOfGarbage() throws Exception {
        try {
            String[] months = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dev" };

            List<AmountOfGarbageDTO> amountOfGarbageDTOS = new ArrayList<>();
            for (int i = 1; i < months.length+1; i++) {
                AmountOfGarbageDTO amountOfGarbageDTO = new AmountOfGarbageDTO();
                amountOfGarbageDTO.setBulan(months[i-1]);

                Date date = new Date();
                Integer year = date.getYear();
                Integer currentYear = year+1900;

                List<Transaksi> transaksis = transaksiRepository.findTransaksiByMonthAndYear(String.valueOf(i), String.valueOf(currentYear));
                if (!transaksis.isEmpty()) {
                    Integer jumlahSampahPlastik = 0;
                    Integer jumlahSampahBotol = 0;
                    Integer jumlahSampahKertas = 0;
                    Integer jumlahSampahKain = 0;
                    Integer jumlahSampahLainnya = 0;
                    for (Transaksi transaksi : transaksis) {
                        Sampah sampah = sampahService.getSampahById(transaksi.getSampahId());
                        if (sampah != null) {
                            if(sampah.getTipeSampah().equalsIgnoreCase("plastik"))
                                jumlahSampahPlastik = jumlahSampahPlastik + transaksi.getJumlah();
                            else if(sampah.getTipeSampah().equalsIgnoreCase("kaca"))
                                jumlahSampahBotol = jumlahSampahBotol + transaksi.getJumlah();
                            else if(sampah.getTipeSampah().equalsIgnoreCase("kertas"))
                                jumlahSampahKertas = jumlahSampahKertas + transaksi.getJumlah();
                            else if(sampah.getTipeSampah().equalsIgnoreCase("kain"))
                                jumlahSampahKain = jumlahSampahKain + transaksi.getJumlah();
                            else if(sampah.getTipeSampah().equalsIgnoreCase("lainnya"))
                                jumlahSampahLainnya = jumlahSampahLainnya + transaksi.getJumlah();
                        }
                    }
                    amountOfGarbageDTO.setSampahPlastik(jumlahSampahPlastik);
                    amountOfGarbageDTO.setSampahBotol(jumlahSampahBotol);
                    amountOfGarbageDTO.setSampahKertas(jumlahSampahKertas);
                    amountOfGarbageDTO.setSampahKain(jumlahSampahKain);
                    amountOfGarbageDTO.setSampahLainnya(jumlahSampahLainnya);
                    amountOfGarbageDTO.setTotal(jumlahSampahPlastik
                            +jumlahSampahBotol
                            +jumlahSampahKertas
                            +jumlahSampahKain
                            +jumlahSampahLainnya);
                } else {
                    amountOfGarbageDTO.setSampahPlastik(0);
                    amountOfGarbageDTO.setSampahBotol(0);
                    amountOfGarbageDTO.setSampahKertas(0);
                    amountOfGarbageDTO.setSampahKain(0);
                    amountOfGarbageDTO.setSampahLainnya(0);
                    amountOfGarbageDTO.setTotal(0);
                }

                amountOfGarbageDTOS.add(amountOfGarbageDTO);
            }
            return amountOfGarbageDTOS;
        } catch (Exception e) {
            throw new Exception("Error get amount of garbage: "+e.getMessage());
        }
    }

//    @Override
//    public List<AmountOfGarbageDTO> getAmountOfGarbageByUsername(String username) throws Exception {
//        try {
//            String[] months = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
//
//            List<AmountOfGarbageDTO> amountOfGarbageDTOS = new ArrayList<>();
//            for (int i = 1; i < months.length+1; i++) {
//                AmountOfGarbageDTO amountOfGarbageDTO = new AmountOfGarbageDTO();
//                amountOfGarbageDTO.setBulan(months[i-1]);
//
//                Date date = new Date();
//                Integer year = date.getYear();
//                Integer currentYear = year+1900;
//
//                List<Transaksi> transaksis = transaksiRepository.findTransaksiByUsernameAndMonthAndYear(username, String.valueOf(i), String.valueOf(currentYear));
//                if (!transaksis.isEmpty()) {
//                    Integer jumlahSampahPlastik = 0;
//                    Integer jumlahSampahBotol = 0;
//                    Integer jumlahSampahKertas = 0;
//                    Integer jumlahSampahKain = 0;
//                    Integer jumlahSampahLainnya = 0;
//                    for (Transaksi transaksi : transaksis) {
//                        Sampah sampah = sampahService.getSampahById(transaksi.getSampahId());
//                        if (sampah != null) {
//                            if(sampah.getTipeSampah().equalsIgnoreCase("plastik"))
//                                jumlahSampahPlastik = jumlahSampahPlastik + transaksi.getJumlah();
//                            else if(sampah.getTipeSampah().equalsIgnoreCase("botol"))
//                                jumlahSampahBotol = jumlahSampahBotol + transaksi.getJumlah();
//                            else if(sampah.getTipeSampah().equalsIgnoreCase("kertas"))
//                                jumlahSampahKertas = jumlahSampahKertas + transaksi.getJumlah();
//                            else if(sampah.getTipeSampah().equalsIgnoreCase("kain"))
//                                jumlahSampahKain = jumlahSampahKain + transaksi.getJumlah();
//                            else if(sampah.getTipeSampah().equalsIgnoreCase("lainnya"))
//                                jumlahSampahLainnya = jumlahSampahLainnya + transaksi.getJumlah();
//                        }
//                    }
//                    amountOfGarbageDTO.setSampahPlastik(jumlahSampahPlastik);
//                    amountOfGarbageDTO.setSampahBotol(jumlahSampahBotol);
//                    amountOfGarbageDTO.setSampahKertas(jumlahSampahKertas);
//                    amountOfGarbageDTO.setSampahKain(jumlahSampahKain);
//                    amountOfGarbageDTO.setSampahLainnya(jumlahSampahLainnya);
//                    amountOfGarbageDTO.setTotal(jumlahSampahPlastik
//                            +jumlahSampahBotol
//                            +jumlahSampahKertas
//                            +jumlahSampahKain
//                            +jumlahSampahLainnya);
//                } else {
//                    amountOfGarbageDTO.setSampahPlastik(0);
//                    amountOfGarbageDTO.setSampahBotol(0);
//                    amountOfGarbageDTO.setSampahKertas(0);
//                    amountOfGarbageDTO.setSampahKain(0);
//                    amountOfGarbageDTO.setSampahLainnya(0);
//                    amountOfGarbageDTO.setTotal(0);
//                }
//
//                amountOfGarbageDTOS.add(amountOfGarbageDTO);
//            }
//            return amountOfGarbageDTOS;
//        } catch (Exception e) {
//            throw new Exception("Error get amount of garbage by username: "+e.getMessage());
//        }
//    }

    @Override
    public List<HistoryDTO> getHistory(String name) throws Exception {
        try {
            List<HistoryDTO> historyDTOS = new ArrayList<>();
            List<Transaksi> transaksis = new ArrayList<>();
            if (name != null) {
                if (name.length() != 0) {
                    transaksis = transaksiRepository.findTransaksiByName(name);
                } else transaksis = transaksiRepository.findAll();
            } else transaksis = transaksiRepository.findAll();

            if(transaksis != null) {
                for (Transaksi transaksi : transaksis) {
                    User user = jwtUserDetailService.getUserByUsername(transaksi.getUsername());
                    HistoryDTO historyDTO = new HistoryDTO();
                    if (user != null) {
                        historyDTO.setName(user.getName());
                        historyDTO.setAddress(user.getAlamat());
                    }
                    historyDTO.setTotal(transaksi.getJumlah());
                    historyDTO.setTotalHarga(transaksi.getTotalHarga());
                    historyDTOS.add(historyDTO);
                }
            }

            return historyDTOS;
        } catch (Exception e) {
            throw new Exception("Error get History: "+e.getMessage());
        }
    }
}
