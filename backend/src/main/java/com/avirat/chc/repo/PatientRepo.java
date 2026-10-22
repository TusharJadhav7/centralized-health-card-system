package com.avirat.chc.repo;

import com.avirat.chc.entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepo extends JpaRepository<PatientEntity,Integer> {

  public Optional<PatientEntity> findByHealthCardNo(Integer healthCardNo);
}
