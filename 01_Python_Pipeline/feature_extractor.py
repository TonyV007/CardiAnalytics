import numpy as np

class HRVFeatureExtractor:

    def extract_features(self, rpeaks, fs=360):

        rr_intervals = np.diff(rpeaks) / fs

        sdnn = np.std(rr_intervals)

        rmssd = np.sqrt(
            np.mean(
                np.diff(rr_intervals) ** 2
            )
        )

        pnn50 = np.mean(
            np.abs(
                np.diff(rr_intervals)
            ) > 0.05
        )

        mean_hr = 60 / np.mean(rr_intervals)

        spo2_estimate = 98 - (sdnn * 2)

        return {
    "sdnn": float(sdnn),
    "rmssd": float(rmssd),
    "pnn50": float(pnn50),
    "mean_hr": float(mean_hr),
    "spo2_estimate": float(spo2_estimate)
}