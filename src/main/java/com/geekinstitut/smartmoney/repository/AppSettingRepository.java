package com.geekinstitut.smartmoney.repository;

import com.geekinstitut.smartmoney.model.AppSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppSettingRepository extends JpaRepository<AppSetting, UUID> {
    Optional<AppSetting> findByKey(String key);
}
