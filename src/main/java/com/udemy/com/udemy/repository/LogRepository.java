package com.udemy.com.udemy.repository;

import com.udemy.com.udemy.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("logRepository")
public interface LogRepository extends JpaRepository<Log, Serializable> {
}
