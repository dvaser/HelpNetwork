package com.hack.deprem.repository;

import com.hack.deprem.model.Assistance;
import com.hack.deprem.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssistanceRepository  extends JpaRepository<Assistance, String> {
    boolean existsByFromCity(int fromCity);
    List<Assistance> getAssistancesByFromCity(int fromCity);
}
