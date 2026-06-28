import pandas as pd

from script.validation import (
    check_missing_values,
    check_duplicate_tags,
    check_uom,
    check_range,
    check_timestamp
)

from script.cleaning import clean_data
from script.quality_score import calculate_quality_score


def process_file(file_path):
    """
    Process uploaded Excel file and return
    validation results, cleaned data,
    summary report and quality score.
    """

    # -------------------------------
    # Read Excel File
    # -------------------------------

    df = pd.read_excel(file_path)

    # -------------------------------
    # Validation
    # -------------------------------

    missing = check_missing_values(df)

    duplicate = check_duplicate_tags(df)

    invalid_uom = check_uom(df)

    invalid_range = check_range(df)

    invalid_timestamp = check_timestamp(df)

    # -------------------------------
    # Total Errors
    # -------------------------------

    total_errors = (
        len(missing)
        + len(duplicate)
        + len(invalid_uom)
        + len(invalid_range)
        + len(invalid_timestamp)
    )

    # -------------------------------
    # Data Quality Score
    # -------------------------------

    quality_score = calculate_quality_score(
        len(df),
        total_errors
    )

    # -------------------------------
    # Validation Summary
    # -------------------------------

    report = pd.DataFrame({

        "Validation": [

            "Missing Values",

            "Duplicate Tags",

            "Invalid UOM",

            "Invalid Range",

            "Invalid Timestamp"

        ],

        "Count": [

            len(missing),

            len(duplicate),

            len(invalid_uom),

            len(invalid_range),

            len(invalid_timestamp)

        ]

    })

    # -------------------------------
    # Clean Data
    # -------------------------------

    clean_df = clean_data(df.copy())

    # -------------------------------
    # Return Everything
    # -------------------------------

    return {

        "df": df,

        "clean_df": clean_df,

        "report": report,

        "quality_score": quality_score,

        "total_records": len(df),

        "total_errors": total_errors,

        "missing": missing,

        "duplicate": duplicate,

        "invalid_uom": invalid_uom,

        "invalid_range": invalid_range,

        "invalid_timestamp": invalid_timestamp

    }