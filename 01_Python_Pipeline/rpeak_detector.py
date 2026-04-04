import numpy as np
from scipy.signal import find_peaks

class PanTompkinsDetector:

    def detect_rpeaks(self, signal, fs=360):

        diff_signal = np.diff(signal)

        squared_signal = diff_signal ** 2

        window_size = int(0.150 * fs)

        integrated_signal = np.convolve(
            squared_signal,
            np.ones(window_size) / window_size,
            mode="same"
        )

        peaks, _ = find_peaks(
            integrated_signal,
            distance=fs * 0.2
        )

        return peaks