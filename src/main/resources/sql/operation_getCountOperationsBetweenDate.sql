select operation_name, count (operation_name) as "count" from operation_log
where create_date between :begin and :end
group by operation_name