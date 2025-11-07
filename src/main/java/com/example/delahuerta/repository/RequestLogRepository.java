package com.example.delahuerta.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.delahuerta.model.RequestLog;
import java.util.List;

public interface RequestLogRepository extends JpaRepository<RequestLog, Long> {
    List<RequestLog> findByMethod(String method);
}