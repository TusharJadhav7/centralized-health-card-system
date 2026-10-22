package com.avirat.chc.repo;

import com.avirat.chc.entity.MedicineInFoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicienRepo extends JpaRepository<MedicineInFoEntity,String> {


}
