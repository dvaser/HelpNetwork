package com.hack.deprem.repository;

import com.hack.deprem.model.AssistancePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssistancePointRepository  extends JpaRepository<AssistancePoint, String> {
    AssistancePoint getAssistancePointByLocation(String location);
    Boolean existsByLocation(String location);
}
