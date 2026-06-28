import plotly.express as px
import pandas as pd


def validation_bar(report):

    fig = px.bar(
        report,
        x="Validation",
        y="Count",
        text="Count",
        color="Validation",
        title="Validation Summary"
    )

    fig.update_layout(
        xaxis_title="Validation Type",
        yaxis_title="Count",
        height=450
    )

    return fig


def validation_pie(report):

    fig = px.pie(
        report,
        names="Validation",
        values="Count",
        title="Validation Distribution"
    )

    return fig


def equipment_chart(df):

    if "Equipment" not in df.columns:
        return None

    equipment = (
        df["Equipment"]
        .value_counts()
        .reset_index()
    )

    equipment.columns = ["Equipment", "Count"]

    fig = px.bar(
        equipment,
        x="Equipment",
        y="Count",
        text="Count",
        title="Equipment Distribution"
    )

    return fig


def uom_chart(df):

    if "UOM" not in df.columns:
        return None

    uom = (
        df["UOM"]
        .value_counts()
        .reset_index()
    )

    uom.columns = ["UOM", "Count"]

    fig = px.pie(
        uom,
        names="UOM",
        values="Count",
        title="UOM Distribution"
    )

    return fig