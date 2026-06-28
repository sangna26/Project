import pandas as pd
from script.advanced_validation import *
from validation import (
    check_missing_values,
    check_duplicate_tags,
    check_uom,
    check_range,
    check_timestamp
)
from cleaning import clean_data
from database import save_to_database
from logger import logger
from Script.error_report import add_errors

# Read input file
df = pd.read_excel("input/industrial_data.xlsx")
logger.info("Input file loaded successfully.")

# Run validations
missing = check_missing_values(df)
duplicate = check_duplicate_tags(df)
invalid_uom = check_uom(df)
invalid_range = check_range(df)
invalid_time = check_timestamp(df)
invalid_format = check_tag_format(df)

illegal = check_illegal_characters(df)

long_tag = check_tag_length(df)

duplicate_equipment = check_duplicate_equipment(df)

duplicate_combination = check_duplicate_tag_equipment(df)

reserved = check_reserved_keywords(df)

empty_equipment = check_empty_equipment(df)

empty_uom = check_empty_uom(df)

numeric = check_numeric_value(df)

future = check_future_timestamp(df)

# Create validation summary
summary={
    "Validation":[
        "Missing Values",

        "Duplicate Tags",

        "Invalid UOM",

        "Invalid Range",

        "Invalid Timestamp",

        "Invalid Tag Format",

        "Illegal Characters",

        "Long Tag",

        "Duplicate Equipment",

        "Duplicate Equipment+Tag",

        "Reserved Keyword",

        "Empty Equipment",

        "Empty UOM",

        "Invalid Numeric Value",

        "Future Timestamp"

        ],

    "Count":[

        len(missing),

        len(duplicate),

        len(invalid_uom),

        len(invalid_range),

        len(invalid_time),

        len(invalid_format),

        len(illegal),

        len(long_tag),

        len(duplicate_equipment),

        len(duplicate_combination),

        len(reserved),

        len(empty_equipment),

        len(empty_uom),

        len(numeric),

        len(future)

        ]

}


errors = pd.DataFrame()

errors = add_errors(
    errors,
    missing,
    "Missing Value",
    "One or more required fields are empty"
)

errors = add_errors(
    errors,
    duplicate,
    "Duplicate Tag",
    "Duplicate tag found"
)

errors = add_errors(
    errors,
    invalid_uom,
    "Invalid UOM",
    "Unit of Measure is not allowed"
)

errors = add_errors(
    errors,
    invalid_range,
    "Range Error",
    "Value is outside the allowed range"
)

errors = add_errors(
    errors,
    invalid_time,
    "Invalid Timestamp",
    "Timestamp format is invalid"
)

errors = add_errors(
    errors,
    illegal,
    "Illegal Character",
    "Tag contains invalid characters"
)

errors = add_errors(
    errors,
    invalid_format,
    "Invalid Tag Format",
    "Tag does not follow the naming convention"
)

errors.to_excel(
    "Output/error_report.xlsx",
    index=False
)

# Save validation report
report = pd.DataFrame(summary)
report.to_excel("output/validation_report.xlsx", index=False)
logger.info("Validation report generated.")

# Clean data
clean_df = clean_data(df)

# Save cleaned data
clean_df.to_excel("output/clean_data.xlsx", index=False)

logger.info("Clean data exported.")

# Save to SQLite database
save_to_database(clean_df)
logger.info("Database saved successfully.")