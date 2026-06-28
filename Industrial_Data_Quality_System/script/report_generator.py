import pandas as pd
from datetime import datetime


def generate_report(
        summary,
        errors,
        clean_df,
        file_name="Output/Industrial_Data_Quality_Report.xlsx"
):

    with pd.ExcelWriter(file_name, engine="openpyxl") as writer:

        summary.to_excel(
            writer,
            sheet_name="Summary",
            index=False
        )

        errors.to_excel(
            writer,
            sheet_name="Detailed Errors",
            index=False
        )

        clean_df.to_excel(
            writer,
            sheet_name="Clean Data",
            index=False
        )

    return file_name