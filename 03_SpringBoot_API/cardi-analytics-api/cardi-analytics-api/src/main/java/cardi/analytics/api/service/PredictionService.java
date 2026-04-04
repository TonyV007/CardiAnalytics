package cardi.analytics.api.service;

import cardi.analytics.api.dto.PredictionResponse;
import cardi.analytics.api.entity.EcgFeature;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

@Service
public class PredictionService {

    public String predict(EcgFeature ecgFeature) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "python",
                    "predict_from_java.py",
                    ecgFeature.getMeanHr().toString(),
                    ecgFeature.getSdnn().toString(),
                    ecgFeature.getRmssd().toString(),
                    ecgFeature.getPnn50().toString(),
                    ecgFeature.getSpo2Estimate().toString()
            );

            processBuilder.directory(new File("C:/CardiAnalytics/07_Models"));
            processBuilder.redirectErrorStream(true);

            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            StringBuilder output = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                output.append(line);
            }

            int exitCode = process.waitFor();

            if (exitCode != 0 || output.length() == 0) {
                throw new RuntimeException("Prediction script failed with exit code: " + exitCode);
            }

            return output.toString().trim();

        } catch (Exception e) {
            throw new RuntimeException("Error while running Python model: " + e.getMessage(), e);
        }
    }

    public PredictionResponse buildResponse(EcgFeature ecgFeature, String prediction) {
        return new PredictionResponse(
                ecgFeature.getRecordName(),
                ecgFeature.getMeanHr(),
                ecgFeature.getSdnn(),
                ecgFeature.getRmssd(),
                ecgFeature.getPnn50(),
                ecgFeature.getSpo2Estimate(),
                prediction
        );
    }
}