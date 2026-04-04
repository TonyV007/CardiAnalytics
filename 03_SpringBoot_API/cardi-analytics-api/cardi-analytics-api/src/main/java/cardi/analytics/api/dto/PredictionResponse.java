package cardi.analytics.api.dto;

public class PredictionResponse {

    private String recordName;
    private Double meanHr;
    private Double sdnn;
    private Double rmssd;
    private Double pnn50;
    private Double spo2Estimate;
    private String prediction;

    public PredictionResponse(
            String recordName,
            Double meanHr,
            Double sdnn,
            Double rmssd,
            Double pnn50,
            Double spo2Estimate,
            String prediction) {

        this.recordName = recordName;
        this.meanHr = meanHr;
        this.sdnn = sdnn;
        this.rmssd = rmssd;
        this.pnn50 = pnn50;
        this.spo2Estimate = spo2Estimate;
        this.prediction = prediction;
    }

    public String getRecordName() { return recordName; }
    public Double getMeanHr() { return meanHr; }
    public Double getSdnn() { return sdnn; }
    public Double getRmssd() { return rmssd; }
    public Double getPnn50() { return pnn50; }
    public Double getSpo2Estimate() { return spo2Estimate; }
    public String getPrediction() { return prediction; }
}