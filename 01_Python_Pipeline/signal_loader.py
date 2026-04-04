import wfdb
import os

class SignalLoader:

    def __init__(self, dataset_path):
        self.dataset_path = dataset_path

    def load_record(self, record_name):
        record_path = os.path.join(self.dataset_path, record_name)

        record = wfdb.rdrecord(record_path)
        annotation = wfdb.rdann(record_path, "atr")

        return record.p_signal, annotation.sample