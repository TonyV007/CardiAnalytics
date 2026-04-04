package cardi.analytics.api.repository;

import cardi.analytics.api.entity.EcgFeature;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EcgFeatureRepository extends JpaRepository<EcgFeature, Long> {
    Optional<EcgFeature> findByRecordName(String recordName);
}