import streamlit as st
import tempfile
import os

from script.processor import process_file

from script.charts import (
    validation_bar,
    validation_pie,
    equipment_chart,
    uom_chart
)

# -------------------------------------------------
# Page Configuration
# -------------------------------------------------

st.set_page_config(
    page_title="Industrial Data Quality System",
    page_icon="🏭",
    layout="wide"
)

st.title("🏭 Industrial Data Quality & Validation System")

st.markdown(
    """
Validate industrial Excel data, detect quality issues,
clean the data and generate downloadable reports.
"""
)

st.divider()

# -------------------------------------------------
# File Upload
# -------------------------------------------------

uploaded_file = st.file_uploader(
    "Upload Industrial Excel File",
    type=["xlsx"]
)

if uploaded_file:

    temp_file = tempfile.NamedTemporaryFile(
        delete=False,
        suffix=".xlsx"
    )

    temp_file.write(uploaded_file.read())
    temp_file.close()

    # ---------------------------------------------
    # Process File
    # ---------------------------------------------

    result = process_file(temp_file.name)

    df = result["df"]

    clean_df = result["clean_df"]

    report = result["report"]

    quality_score = result["quality_score"]

    total_records = result["total_records"]

    total_errors = result["total_errors"]

    missing = result["missing"]

    duplicate = result["duplicate"]

    invalid_uom = result["invalid_uom"]

    invalid_range = result["invalid_range"]

    invalid_timestamp = result["invalid_timestamp"]

    # ---------------------------------------------
    # Success
    # ---------------------------------------------

    st.success("Excel File Loaded Successfully")

    # ---------------------------------------------
    # Input Data
    # ---------------------------------------------

    st.subheader("Input Data")

    st.dataframe(
        df,
        use_container_width=True,
        height=350
    )

    st.divider()

    # ---------------------------------------------
    # KPI Cards
    # ---------------------------------------------

    st.subheader("Dashboard")

    c1, c2, c3, c4 = st.columns(4)

    c1.metric(
        "Total Records",
        total_records
    )

    c2.metric(
        "Total Errors",
        total_errors
    )

    c3.metric(
        "Quality Score",
        f"{quality_score}%"
    )

    c4.metric(
        "Clean Records",
        len(clean_df)
    )

    st.divider()

    # ---------------------------------------------
    # Validation Summary
    # ---------------------------------------------

    st.subheader("Validation Summary")

    c1, c2, c3, c4, c5 = st.columns(5)

    c1.metric(
        "Missing",
        len(missing)
    )

    c2.metric(
        "Duplicate",
        len(duplicate)
    )

    c3.metric(
        "Invalid UOM",
        len(invalid_uom)
    )

    c4.metric(
        "Range Error",
        len(invalid_range)
    )

    c5.metric(
        "Timestamp",
        len(invalid_timestamp)
    )

    st.divider()

    # ---------------------------------------------
    # Validation Report
    # ---------------------------------------------

    st.subheader("Validation Report")

    st.dataframe(
        report,
        use_container_width=True
    )

    st.divider()

    # ---------------------------------------------
    # Charts
    # ---------------------------------------------

    st.subheader("Analytics")

    left, right = st.columns(2)

    with left:

        st.plotly_chart(
            validation_bar(report),
            use_container_width=True
        )

    with right:

        st.plotly_chart(
            validation_pie(report),
            use_container_width=True
        )

    equipment_fig = equipment_chart(df)

    if equipment_fig is not None:

        st.plotly_chart(
            equipment_fig,
            use_container_width=True
        )

    uom_fig = uom_chart(df)

    if uom_fig is not None:

        st.plotly_chart(
            uom_fig,
            use_container_width=True
        )

    st.divider()
        # -------------------------------------------------
    # Clean Data
    # -------------------------------------------------

    st.subheader("Clean Data")

    st.dataframe(
        clean_df,
        use_container_width=True,
        height=350
    )

    st.divider()

    # -------------------------------------------------
    # Save Output Files
    # -------------------------------------------------

    os.makedirs("output", exist_ok=True)

    report_file = "output/validation_report.xlsx"

    clean_file = "output/clean_data.xlsx"

    report.to_excel(
        report_file,
        index=False
    )

    clean_df.to_excel(
        clean_file,
        index=False
    )

    # -------------------------------------------------
    # Download Buttons
    # -------------------------------------------------

    st.subheader("Download Reports")

    col1, col2 = st.columns(2)

    with col1:

        with open(report_file, "rb") as file:

            st.download_button(
                label="📥 Download Validation Report",
                data=file,
                file_name="validation_report.xlsx",
                mime="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
            )

    with col2:

        with open(clean_file, "rb") as file:

            st.download_button(
                label="📥 Download Clean Data",
                data=file,
                file_name="clean_data.xlsx",
                mime="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
            )

    st.divider()

    # -------------------------------------------------
    # Validation Details
    # -------------------------------------------------

    with st.expander("Missing Value Records"):

        st.dataframe(
            missing,
            use_container_width=True
        )

    with st.expander("Duplicate Tag Records"):

        st.dataframe(
            duplicate,
            use_container_width=True
        )

    with st.expander("Invalid UOM Records"):

        st.dataframe(
            invalid_uom,
            use_container_width=True
        )

    with st.expander("Range Validation Records"):

        st.dataframe(
            invalid_range,
            use_container_width=True
        )

    with st.expander("Invalid Timestamp Records"):

        st.dataframe(
            invalid_timestamp,
            use_container_width=True
        )

    st.divider()

    # -------------------------------------------------
    # Dashboard Footer
    # -------------------------------------------------

    st.success("✅ Validation Completed Successfully")

    st.info(
        f"""
        Total Records : {total_records}

        Total Errors : {total_errors}

        Data Quality Score : {quality_score}%
        """
    )

    # -------------------------------------------------
    # Remove Temporary File
    # -------------------------------------------------

    os.unlink(temp_file.name)