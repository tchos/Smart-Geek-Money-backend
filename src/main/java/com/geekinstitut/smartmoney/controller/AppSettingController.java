package com.geekinstitut.smartmoney.controller;

import com.geekinstitut.smartmoney.model.AppSetting;
import com.geekinstitut.smartmoney.model.Category;
import com.geekinstitut.smartmoney.service.AppSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/appsettings")
public class AppSettingController {

    private final AppSettingService appSettingService;

    /**
     * Récupérer toutes les appSettings.
     */
    @GetMapping
    public List<AppSetting> getAppSettings() {
        return appSettingService.getAllAppSettings();
    }

    /**
     * Récupérer une appSettings par ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AppSetting> getAppSettingById(@PathVariable UUID id) {
        return appSettingService.getAppSettingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Créer une nouvelle appSetting.
     */
    @PostMapping
    public ResponseEntity<AppSetting> createAppSetting(@RequestBody AppSetting appSetting) {
        return ResponseEntity.ok(appSettingService.createAppSetting(appSetting));
    }

    /**
     * Mettre à jour une appSetting existante.
     */
    public ResponseEntity<AppSetting> updateAppSetting(@PathVariable UUID id,
                                                       @RequestBody AppSetting updatedAppSetting) {
        return ResponseEntity.ok(appSettingService.updateAppSetting(id, updatedAppSetting));
    }

    /**
     * Supprimer une appSetting par ID.
     */
    public ResponseEntity<Void> deleteAppSetting(@PathVariable UUID id) {
        appSettingService.deleteAppSetting(id);
        return ResponseEntity.noContent().build();
    }
}
