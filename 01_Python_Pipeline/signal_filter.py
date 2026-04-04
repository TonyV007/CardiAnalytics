from scipy.signal import butter, filtfilt

class ECGFilter:

    def __init__(self, lowcut=0.5, highcut=40, fs=360, order=4):
        self.lowcut = lowcut
        self.highcut = highcut
        self.fs = fs
        self.order = order

    def bandpass_filter(self, signal):

        nyquist = 0.5 * self.fs

        low = self.lowcut / nyquist
        high = self.highcut / nyquist

        b, a = butter(self.order, [low, high], btype="band")

        filtered_signal = filtfilt(b, a, signal)

        return filtered_signal