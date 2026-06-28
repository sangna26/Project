import pandas as pd

def add_errors(error_df, invalid_df, validation_name, description):
    if invalid_df.empty:
        return error_df

    temp = invalid_df.copy()

    temp["Validation"] = validation_name
    temp["Description"] = description
    temp["Row"] = temp.index + 2      # +2 because Excel starts at row 2

    cols = ["Row", "Tag", "Equipment", "Validation", "Description"]

    return pd.concat([error_df, temp[cols]], ignore_index=True)