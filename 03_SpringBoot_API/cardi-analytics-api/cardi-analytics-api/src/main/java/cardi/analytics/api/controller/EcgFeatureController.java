package cardi.analytics.api.controller;

import cardi.analytics.api.dto.PredictionResponse;
import cardi.analytics.api.entity.EcgFeature;
import cardi.analytics.api.service.EcgFeatureService;
import cardi.analytics.api.service.PredictionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ecg")
@CrossOrigin("*")
public class EcgFeatureController {

    private final EcgFeatureService service;
    private final PredictionService predictionService;

    public EcgFeatureController(EcgFeatureService service, PredictionService predictionService) {
        this.service = service;
        this.predictionService = predictionService;
    }

    @GetMapping("/all")
    public List<EcgFeature> getAll() {
        return service.getAllFeatures();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EcgFeature> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/record/{recordName}")
    public ResponseEntity<EcgFeature> getByRecordName(@PathVariable String recordName) {
        return service.getByRecordName(recordName)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/predict/{recordName}")
    public ResponseEntity<PredictionResponse> predictByRecordName(@PathVariable String recordName) {
        return service.getByRecordName(recordName)
                .map(ecgFeature -> {
                    String prediction = predictionService.predict(ecgFeature);
                    PredictionResponse response = predictionService.buildResponse(ecgFeature, prediction);
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }
}