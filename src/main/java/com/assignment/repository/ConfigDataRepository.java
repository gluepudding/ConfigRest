package com.assignment.repository;

import com.assignment.model.entity.ConfigData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;


public interface ConfigDataRepository extends JpaRepository<ConfigData, Long> {

    @Query(value = "SELECT u FROM ConfigData u WHERE u.appCode = ?1 and u.version = ?2")
    ConfigData findConfigByAppCodeAndVersion(String appCode, String version);

    @Query(value = "SELECT u FROM ConfigData u WHERE u.appCode = ?1 ORDER BY u.creationTime DESC")
    List<ConfigData> findConfigByAppCode(String appCode);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE ConfigData u SET u.jsonConf = ?3, u.creationTime = ?4, u.hashCode = ?5 WHERE u.appCode = ?1 and u.version = ?2")
    int updateConfigByAppCodeAndVersion(String appCode, String version, String jsonConf, Date creationTime, String hashCode);
}
