import sqlite3

def save_to_database(df):
    conn = sqlite3.connect("database/industrial.db")

    df.to_sql(
        "IndustrialData",
        conn,
        if_exists="replace",
        index=False
    )

    conn.close()

    print("Data saved to SQL database.")