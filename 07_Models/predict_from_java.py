import sys
import joblib
import pandas as pd

model = joblib.load("health_model.pkl")

mean_hr = float(sys.argv[1])
sdnn = float(sys.argv[2])
rmssd = float(sys.argv[3])
pnn50 = float(sys.argv[4])
spo2_estimate = float(sys.argv[5])

input_data = pd.DataFrame([{
    "mean_hr": mean_hr,
    "sdnn": sdnn,
    "rmssd": rmssd,
    "pnn50": pnn50,
    "spo2_estimate": spo2_estimate
}])

prediction = model.predict(input_data)[0]

label = "Attention Needed" if prediction == 1 else "Normal"

print(label)