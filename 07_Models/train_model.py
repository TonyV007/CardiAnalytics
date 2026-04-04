import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import classification_report
import joblib

# load dataset
df = pd.read_csv("../06_Reports/pipeline_results.csv")

# create label (same logic as Power BI)
df["Health_Status"] = (
    (df["mean_hr"] > 220) |
    (df["sdnn"] < 0.05) |
    (df["spo2_estimate"] < 95)
).astype(int)

# features
X = df[["mean_hr","sdnn","rmssd","pnn50","spo2_estimate"]]

# label
y = df["Health_Status"]

# split data
X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.2, random_state=42
)

# model
model = RandomForestClassifier()

# train
model.fit(X_train, y_train)

# evaluate
predictions = model.predict(X_test)

print(classification_report(y_test, predictions))

# save model
joblib.dump(model, "health_model.pkl")

print("Model saved successfully")