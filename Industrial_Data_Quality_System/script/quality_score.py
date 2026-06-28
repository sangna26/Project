def calculate_quality_score(total_records, total_errors):

    if total_records == 0:
        return 0

    score = (
        (total_records-total_errors)
        / total_records
    )*100

    return round(score,2)