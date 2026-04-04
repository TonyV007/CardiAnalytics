import psycopg2

def save_to_db(features, record_name):

    connection = psycopg2.connect(
        host="localhost",
        database="cardianalytics",
        user="postgres",
        password="Admin@1234",
        port="5432"
    )

    cursor = connection.cursor()

    insert_query = """
    INSERT INTO ecg_features
    (sdnn, rmssd, pnn50, mean_hr, spo2_estimate, record_name)
    VALUES (%s, %s, %s, %s, %s, %s)
    """

    cursor.execute(insert_query, (
        features["sdnn"],
        features["rmssd"],
        features["pnn50"],
        features["mean_hr"],
        features["spo2_estimate"],
        record_name
    ))

    connection.commit()

    cursor.close()
    connection.close()

    print(f"Saved {record_name} to PostgreSQL")