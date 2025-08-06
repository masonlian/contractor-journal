INSERT INTO calendar (calendar_date, is_weekend)

WITH RECURSIVE date_gen (calendar_date) AS (
    SELECT DATE('2025-06-01') AS calendar_date
    UNION ALL
    SELECT DATE_ADD(calendar_date, INTERVAL 1 DAY) AS calendar_date
    FROM date_gen
    WHERE calendar_date < DATE('2026-06-01')
)

SELECT
    calendar_date,
    DAYOFWEEK(calendar_date) IN (1, 7) AS is_weekend
FROM date_gen;
