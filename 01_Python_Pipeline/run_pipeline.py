import os
import pandas as pd

from db_writer import save_to_db
from signal_loader import SignalLoader
from signal_filter import ECGFilter
from rpeak_detector import PanTompkinsDetector
from feature_extractor import HRVFeatureExtractor


DATASET_PATH = "C:/CardiAnalytics/05_Dataset/mitdb"
REPORT_PATH = "C:/CardiAnalytics/06_Reports/pipeline_results.csv"

loader = SignalLoader(DATASET_PATH)
filter_obj = ECGFilter()
detector = PanTompkinsDetector()
extractor = HRVFeatureExtractor()

records_to_process = [
    "100", "101", "102", "103", "104",
    "105", "106", "107", "108", "109"
]

all_results = []

for record_name in records_to_process:
    try:
        signal, annotation = loader.load_record(record_name)

        ecg_signal = signal[:, 0]

        filtered_signal = filter_obj.bandpass_filter(ecg_signal)

        rpeaks = detector.detect_rpeaks(filtered_signal)

        features = extractor.extract_features(rpeaks)
        features["record_name"] = record_name

        all_results.append(features)

        save_to_db(features, record_name)

        print(f"Processed record {record_name} successfully.")

    except Exception as e:
        print(f"Error processing record {record_name}: {e}")

df = pd.DataFrame(all_results)

os.makedirs("C:/CardiAnalytics/06_Reports", exist_ok=True)
df.to_csv(REPORT_PATH, index=False)

print("\nMulti-record pipeline complete.")
print(f"Results saved to: {REPORT_PATH}")