package com.geekinstitut.smartmoney.service;

import com.geekinstitut.smartmoney.model.AppSetting;
import com.geekinstitut.smartmoney.repository.AppSettingRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppSettingService {

    private final AppSettingRepository appSettingRepository;

    /**
     * Recupere toutes les AppSetting
     */
    public List<AppSetting> getAllAppSettings() {
        return appSettingRepository.findAll();
    }

    /**
     * Recuperer un appSetting par son ID
     */
    public Optional<AppSetting> getAppSettingById(UUID id) {
        return appSettingRepository.findById(id);
    }

    /**
     * Crée un nouveau appSetting.
     */
    public AppSetting createAppSetting(AppSetting appSetting) {
        return appSettingRepository.save(appSetting);
    }

    /**
     * Met à jour un appSetting existante.
     */
    public AppSetting updateAppSetting(UUID id, AppSetting updatedAppSetting) {
        return appSettingRepository.findById(id)
            .map(existingAppSetting -> {
                existingAppSetting.setName(updatedAppSetting.getName());
                existingAppSetting.setKey(updatedAppSetting.getKey());
                existingAppSetting.setValue(updatedAppSetting.getValue());
                    return appSettingRepository.save(existingAppSetting);
                })
                .orElseThrow(() -> new EntityNotFoundException("AppSetting not found with id: " + id));
    }

    /**
     * Supprime un appSetting par son ID.
     */
    public void deleteAppSetting(UUID id) {
        if(!appSettingRepository.existsById(id)) {
            throw new EntityNotFoundException("AppSetting not found with id: " + id);
        }
        appSettingRepository.deleteById(id);
    }
}
