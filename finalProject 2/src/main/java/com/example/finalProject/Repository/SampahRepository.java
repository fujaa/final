package com.example.finalProject.Repository;

import com.example.finalProject.Model.Sampah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SampahRepository extends JpaRepository<Sampah, Long>, JpaSpecificationExecutor<Sampah> {
    public Sampah findSampahById(long id);
}