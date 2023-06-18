package com.hack.deprem.repository;

import com.hack.deprem.model.Help;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HelpRepository  extends JpaRepository<Help, String> {
    List<Help> getHelpsByLocation(String location);
}