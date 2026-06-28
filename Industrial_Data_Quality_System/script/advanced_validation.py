import pandas as pd
import re

def check_tag_format(df):

    pattern = r'^[A-Z0-9]+_[A-Z0-9]+_[A-Z0-9]+$'

    invalid = df[
        ~df["Tag"].astype(str).str.match(pattern)
    ]

    return invalid
def check_illegal_characters(df):

    pattern = r'^[A-Z0-9_]+$'

    invalid = df[
        ~df["Tag"].astype(str).str.match(pattern)
    ]

    return invalid
def check_tag_length(df,max_length=50):

    invalid=df[
        df["Tag"].str.len()>max_length
    ]

    return invalid
def check_duplicate_equipment(df):

    duplicate=df[

        df.duplicated(
            subset="Equipment",
            keep=False
        )

    ]

    return duplicate
def check_duplicate_tag_equipment(df):

    duplicate=df[

        df.duplicated(

            subset=["Equipment","Tag"],

            keep=False

        )

    ]

    return duplicate
def check_reserved_keywords(df):

    invalid=[]

    for i,row in df.iterrows():

        tag=str(row["Tag"])

        if tag in RESERVED:

            invalid.append(i)

    return df.loc[invalid]
def check_empty_equipment(df):

    invalid=df[

        df["Equipment"].isna()

    ]

    return invalid
def check_empty_uom(df):

    invalid=df[

        df["UOM"].isna()

    ]

    return invalid
def check_numeric_value(df):

    invalid=[]

    for i,row in df.iterrows():

        try:

            float(row["Value"])

        except:

            invalid.append(i)

    return df.loc[invalid]
from datetime import datetime

def check_future_timestamp(df):

    temp=df.copy()

    temp["Timestamp"]=pd.to_datetime(

        temp["Timestamp"],

        errors="coerce"

    )

    invalid=temp[

        temp["Timestamp"]>

        datetime.now()

    ]

    return invalid

