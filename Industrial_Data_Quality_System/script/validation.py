import pandas as pd
import json

def check_missing_values(df):
    missing = df[df.isnull().any(axis=1)]
    return missing
def check_duplicate_tags(df):
    duplicate = df[df.duplicated(subset="Tag", keep=False)]
    return duplicate
def standardize_tag(tag):
    if pd.isna(tag):
        return tag
    tag = str(tag).upper()
    tag = tag.replace(" ", "_")
    return tag
with open("config/validation_rules.json","r") as file:
    config=json.load(file)

allowed_uom=config["allowed_uom"]
ranges=config["ranges"]
def check_uom(df):
    invalid = df[~df["UOM"].isin(allowed_uom)]
    return invalid
def check_range(df):

    invalid=[]

    for index,row in df.iterrows():

        tag=row["Tag"]

        value=row["Value"]

        for keyword in ranges:

            if keyword in tag:

                minimum=ranges[keyword]["min"]

                maximum=ranges[keyword]["max"]

                if value<minimum or value>maximum:

                    invalid.append(index)

    return df.loc[invalid]
def check_timestamp(df):

    df["Timestamp"] = pd.to_datetime(
        df["Timestamp"],
        errors="coerce"
    )

    invalid = df[df["Timestamp"].isna()]

    return invalid
