import pandas as pd

def clean_data(df):
    # Remove duplicate rows
    df = df.drop_duplicates()

    # Remove rows with missing values
    df = df.dropna()

    # Standardize Tag names
    df["Tag"] = (
        df["Tag"]
        .str.upper()
        .str.strip()
        .str.replace(" ", "_", regex=False)
    )

    return df