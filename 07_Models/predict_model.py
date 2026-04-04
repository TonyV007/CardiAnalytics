import joblib
import pandas as pd

model = joblib.load("health_model.pkl")

df = pd.read_csv("../06_Reports/pipeline_results.csv")

X = df[["mean_hr","sdnn","rmssd","pnn50","spo2_estimate"]]

df["ML_Prediction"] = model.predict(X)

df["ML_Prediction"] = df["ML_Prediction"].map({
    0: "Normal",
    1: "Attention Needed"
})

print(df[["record_name","ML_Prediction"]])

df.to_csv("../06_Reports/ml_predictions.csv", index=False)