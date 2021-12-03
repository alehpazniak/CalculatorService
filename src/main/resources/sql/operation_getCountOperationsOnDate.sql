select operation_name, count (operation_name) as "count" from operation_log
where create_date = :date
group by operation_name