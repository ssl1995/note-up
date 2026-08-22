select t1.user_id,
       (case
            when t2.action is null then 0
            else round(sum(case when t2.action = 'timeout' then 0 else 1 end) / count(t2.action), 3)
           end) as 'confirmation_rate'
from Signups t1
         left join Confirmations t2 on t1.user_id = t2.user_id
group by t1.user_id
