import os
import wfdb

BASE_PATH = "C:/CardiAnalytics/05_Dataset"
DATASET_PATH = "C:/CardiAnalytics/05_Dataset/mitdb"

os.makedirs(BASE_PATH, exist_ok=True)
os.makedirs(DATASET_PATH, exist_ok=True)

print("Downloading MIT-BIH dataset...")

wfdb.dl_database("mitdb", DATASET_PATH)

print("Download complete.")