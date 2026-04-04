package cardi.analytics.api.service;

import cardi.analytics.api.entity.EcgFeature;
import cardi.analytics.api.repository.EcgFeatureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EcgFeatureService {

    private final EcgFeatureRepository repository;

    public EcgFeatureService(EcgFeatureRepository repository) {
        this.repository = repository;
    }

    public List<EcgFeature> getAllFeatures() {
        return repository.findAll();
    }

    public Optional<EcgFeature> getById(Long id) {
        return repository.findById(id);
    }

    public Optional<EcgFeature> getByRecordName(String recordName) {
        return repository.findByRecordName(recordName);
    }
}