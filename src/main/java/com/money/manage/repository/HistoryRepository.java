package com.money.manage.repository;

import com.money.manage.model.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findByUserId(Long userId);
}
