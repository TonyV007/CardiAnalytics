package cardi.analytics.api.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "ecg_features")
public class EcgFeature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "record_name")
    private String recordName;
    
    @Column(name = "mean_hr")
    private Double meanHr;
    
    @Column(name = "sdnn")
    private Double sdnn;
    
    @Column(name = "rmssd")
    private Double rmssd;
    
    @Column(name = "pnn50")
    private Double pnn50;
    
    @Column(name = "spo2_estimate")
    private Double spo2Estimate;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public EcgFeature() {}

    public Long getId() { return id; }
    public String getRecordName() { return recordName; }
    public Double getMeanHr() { return meanHr; }
    public Double getSdnn() { return sdnn; }
    public Double getRmssd() { return rmssd; }
    public Double getPnn50() { return pnn50; }
    public Double getSpo2Estimate() { return spo2Estimate; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setId(Long id) { this.id = id; }
    public void setRecordName(String recordName) { this.recordName = recordName; }
    public void setMeanHr(Double meanHr) { this.meanHr = meanHr; }
    public void setSdnn(Double sdnn) { this.sdnn = sdnn; }
    public void setRmssd(Double rmssd) { this.rmssd = rmssd; }
    public void setPnn50(Double pnn50) { this.pnn50 = pnn50; }
    public void setSpo2Estimate(Double spo2Estimate) { this.spo2Estimate = spo2Estimate; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}