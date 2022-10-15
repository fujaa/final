package com.example.finalProject.Repository;

import com.example.finalProject.Model.Transaksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransaksiRepository extends JpaRepository<Transaksi, Long>, JpaSpecificationExecutor<Transaksi> {

    public Transaksi findTransaksiById(long id);

    @Query(nativeQuery = true, value = "SELECT * FROM transaksi WHERE MONTH(created_date) = :month AND YEAR(created_date) = :year")
    public List<Transaksi> findTransaksiByMonthAndYear(@Param("month") String month, @Param("year") String year);

    @Query(nativeQuery = true, value = "SELECT * FROM transaksi WHERE username = :username AND MONTH(created_date) = :month AND YEAR(created_date) = :year")
    public List<Transaksi> findTransaksiByUsernameAndMonthAndYear(@Param("username") String username, @Param("month") String month, @Param("year") String year);

    @Query(nativeQuery = true, value = "SELECT * FROM transaksi t left join user u on t.username = u.username WHERE u.name like %:name%")
    public List<Transaksi> findTransaksiByName(@Param("name") String name);
}
