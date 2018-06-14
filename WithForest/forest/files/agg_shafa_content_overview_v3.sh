#!/usr/bin/env bash
source /etc/profile
source ~/.bash_profile

#by zhiguang

#hive settings


if [[ 1 -ne $# ]]; then 
   day=`date -d "$1 days ago " "+%Y%m%d"` 
else  
   day=$1 
fi 

#record the time cost
starttime=$(date +%s)

echo -e "  --- \n"
echo -e "$day is running on......\n"

sql_m="
set mapreduce.map.memory.mb=4096;
set mapreduce.reduce.memory.mb=4096;
insert  overwrite  table  stage_shafa_content_main_tmp    
partition (pdt='$day')   
select  mm.dt,mm.logsrc,mm.obj_type,mm.platform,mm.app_ver,mm.qudao,mm.pindao,mm.candidate,mm.face_code,mm.psrc,mm.province_id,mm.is_uv,mm.is_new,
concat (mm.dt,mm.logsrc,mm.obj_type,mm.platform,mm.app_ver,mm.qudao,mm.pindao,mm.candidate,mm.face_code,mm.psrc,mm.province_id,mm.is_uv,mm.is_new) as join_mark,
mm.click, mm.vv, mm.duration, mm.rec   
from (    
  
select dt,  
(case when type is not null and type in ('APP','H5') then type else 'other' end ) as logsrc,     
(case when entity_detail.content[0].content_type is not null  
then   
 (case when entity_detail.content[0].content_type='CONTENTTYPE_UserVideo' then 2 
       when entity_detail.content[0].content_type='CONTENTTYPE_SmallVideo' then 16 
       else 0 end )  
else 0 end ) as obj_type,    
   
(case when phone_meta.os_type = 'ios' then 2 when phone_meta.os_type = 'android' then 1 else 3 end ) as platform,   
(case   
   when type='APP' and app_meta.app_ver is null  then 'nvn' 
   when type='H5' then 'h5'
   else trim(app_meta.app_ver)  
 end) as app_ver, 
(case   
    when type='APP' and app_meta.channel is null then 'nvn' 
    when type='H5' then 'h5' 
    else app_meta.channel 
 end) as qudao,    

(case   
   when ( type='APP' and event !='REC_REQ' and action_from.page_source='PS_FEEDS' and entity_detail.which_feeds is not null)  then entity_detail.which_feeds   
   when ( type='APP' and event ='REC_REQ' and is_server=true and rec_uniq_par.rec_channel is not null)  then rec_uniq_par.rec_channel   
   when  type='H5' then  5555  
   else 1919
 end ) as pindao,    
   
(case   
   when (event !='REC_REQ' and action_from.page_source='PS_FEEDS' and entity_detail.content[0].extra_info is not null and (substr(entity_detail.content[0].extra_info,-1) rlike '^\\\\d+$' ) )
     then 
        (case when substr(entity_detail.content[0].extra_info, (length(entity_detail.content[0].extra_info)-1), 1)='|' 
                 then substr(entity_detail.content[0].extra_info,-1) 
              when substr(entity_detail.content[0].extra_info, (length(entity_detail.content[0].extra_info)-2), 1)='|' 
                 then substr(entity_detail.content[0].extra_info, (length(entity_detail.content[0].extra_info)-1), 2)    
              when substr(entity_detail.content[0].extra_info, (length(entity_detail.content[0].extra_info)-1), 1) !='|' and substr(entity_detail.content[0].extra_info, (length(entity_detail.content[0].extra_info)-3), 1) ='|'   
                 then substr(entity_detail.content[0].extra_info, (length(entity_detail.content[0].extra_info)-2), 3)     
              else  119  end )     
   when (event ='REC_REQ' and is_server=true and entity_detail.content[0].extra_info is not null and (substr(entity_detail.content[0].extra_info,-1) rlike '^\\\\d+$') )
     then 
        (case when substr(entity_detail.content[0].extra_info, (length(entity_detail.content[0].extra_info)-1), 1)='|' 
                 then substr(entity_detail.content[0].extra_info,-1) 
              when substr(entity_detail.content[0].extra_info, (length(entity_detail.content[0].extra_info)-2), 1)='|' 
                 then substr(entity_detail.content[0].extra_info, (length(entity_detail.content[0].extra_info)-1), 2)    
              when substr(entity_detail.content[0].extra_info, (length(entity_detail.content[0].extra_info)-1), 1) !='|' and substr(entity_detail.content[0].extra_info, (length(entity_detail.content[0].extra_info)-3), 1) ='|'   
                 then substr(entity_detail.content[0].extra_info, (length(entity_detail.content[0].extra_info)-2), 3)     
              else  119  end )     
   else  119  
 end  ) as candidate,     

919  as face_code,
 
(case   
   when event in ('PAGE_ELEMET_CLICK','CONTENT_VIEW') then action_from.page_source   
   when event='REC_REQ' and is_server=true  then 'rec'   
   else 'other'  
 end) as psrc,    
   
(case   
  when (substr(geo_info.city, 1, 2)) is not null and (substr(geo_info.city, 1, 2))  rlike  '^\\\\d+$'
  then  (substr(geo_info.city, 1, 2))   else 888  end )      as province_id,   
(case when t2.user_id is not null then 1 else 0 end )        as is_uv,    
(case when t3.is_new is not null then t3.is_new else 0 end ) as is_new,    
     
sum (case when (event='PAGE_ELEMET_CLICK' and click_element='CE_CONTENT' and (size(entity_detail.content))=1 
and entity_detail.content[0].content_type in ('CONTENTTYPE_UserVideo','CONTENTTYPE_SmallVideo') )  
then 1 else 0   
end  ) as click,
 
sum (case when (event='CONTENT_VIEW' and (size(entity_detail.content))=1 
and entity_detail.content[0].content_type in ('CONTENTTYPE_UserVideo','CONTENTTYPE_SmallVideo') )  
then 1 else 0  
end ) as vv,

( cast(round((sum 
(case when (event='CONTENT_VIEW' and (size(entity_detail.content))=1  and action_measure[0].lingering_time>0   
and entity_detail.content[0].content_type in ('CONTENTTYPE_UserVideo','CONTENTTYPE_SmallVideo') )  
then action_measure[0].lingering_time      
else  0  
end )/1000),0) as bigint) )  as duration,

sum (case when ( event='REC_REQ' and is_server=true ) then 1  else 0  end )   as rec   
from dw_shafa      
left join ( select c.uid as user_id,c.is_new from comm_user_active_daily c where c.dt='$day' and c.busi_type='shafa' ) t2 on uid.did = t2.user_id 
left join ( select c.uid as user_id,c.is_new from comm_user_active_daily c where c.dt='$day' and c.busi_type='shafa' and c.is_new=1 ) t3 on uid.did = t3.user_id   
where dt='$day'   
and data_type=0         
and uid.did is not null  
and event in ('PAGE_ELEMET_CLICK','CONTENT_VIEW','REC_REQ')   
group by  dt,   
   
(case when type is not null and type in ('APP','H5') then type else 'other' end ) ,     

(case when entity_detail.content[0].content_type is not null  
then   
 (case when entity_detail.content[0].content_type='CONTENTTYPE_UserVideo' then 2 
       when entity_detail.content[0].content_type='CONTENTTYPE_SmallVideo' then 16 
       else 0 end )  
else 0 end ) ,    
   
(case when phone_meta.os_type = 'ios' then 2 when phone_meta.os_type = 'android' then 1 else 3 end )  ,   
(case   
   when type='APP' and app_meta.app_ver is null  then 'nvn' 
   when type='H5' then 'h5'
   else trim(app_meta.app_ver)  
 end)  , 
(case   
    when type='APP' and app_meta.channel is null then 'nvn' 
    when type='H5' then 'h5' 
    else app_meta.channel 
 end)  ,    
(case   
   when ( type='APP' and event !='REC_REQ' and action_from.page_source='PS_FEEDS' and entity_detail.which_feeds is not null)  then entity_detail.which_feeds   
   when ( type='APP' and event ='REC_REQ'  and is_server=true  and rec_uniq_par.rec_channel is not null)  then rec_uniq_par.rec_channel   
   when  type='H5' then  5555  
   else 1919
 end )   ,    
   
(case   
   when (event !='REC_REQ' and action_from.page_source='PS_FEEDS' and entity_detail.content[0].extra_info is not null and (substr(entity_detail.content[0].extra_info,-1) rlike '^\\\\d+$' ) )
     then 
        (case when substr(entity_detail.content[0].extra_info, (length(entity_detail.content[0].extra_info)-1), 1)='|' 
                 then substr(entity_detail.content[0].extra_info,-1) 
              when substr(entity_detail.content[0].extra_info, (length(entity_detail.content[0].extra_info)-2), 1)='|' 
                 then substr(entity_detail.content[0].extra_info, (length(entity_detail.content[0].extra_info)-1), 2)    
              when substr(entity_detail.content[0].extra_info, (length(entity_detail.content[0].extra_info)-1), 1) !='|' and substr(entity_detail.content[0].extra_info, (length(entity_detail.content[0].extra_info)-3), 1) ='|'   
                 then substr(entity_detail.content[0].extra_info, (length(entity_detail.content[0].extra_info)-2), 3)     
              else  119  end )     
   when (event ='REC_REQ' and is_server=true and entity_detail.content[0].extra_info is not null and (substr(entity_detail.content[0].extra_info,-1) rlike '^\\\\d+$') )
     then 
        (case when substr(entity_detail.content[0].extra_info, (length(entity_detail.content[0].extra_info)-1), 1)='|' 
                 then substr(entity_detail.content[0].extra_info,-1) 
              when substr(entity_detail.content[0].extra_info, (length(entity_detail.content[0].extra_info)-2), 1)='|' 
                 then substr(entity_detail.content[0].extra_info, (length(entity_detail.content[0].extra_info)-1), 2)    
              when substr(entity_detail.content[0].extra_info, (length(entity_detail.content[0].extra_info)-1), 1) !='|' and substr(entity_detail.content[0].extra_info, (length(entity_detail.content[0].extra_info)-3), 1) ='|'   
                 then substr(entity_detail.content[0].extra_info, (length(entity_detail.content[0].extra_info)-2), 3)     
              else  119  end )     
   else  119  
 end  ) ,
      
(case   
   when event in ('PAGE_ELEMET_CLICK','CONTENT_VIEW') then action_from.page_source   
   when event='REC_REQ' and is_server=true then 'rec'   
   else 'other'  
 end)  ,    
   
(case   
  when (substr(geo_info.city, 1, 2)) is not null and (substr(geo_info.city, 1, 2))  rlike  '^\\\\d+$'
  then  (substr(geo_info.city, 1, 2))   else 888  end )       ,   
(case when t2.user_id is not null then 1 else 0 end )         ,    
(case when t3.is_new is not null then t3.is_new else 0 end )    
     
) mm where (mm.click>0 or mm.vv>0 or mm.duration>0 or mm.rec>0) and mm.face_code rlike  '^\\\\d+$'
;"

$HIVE_CONF -e "$sql_m"

sql_e="
set mapreduce.map.memory.mb=4096;
set mapreduce.reduce.memory.mb=4096;
insert  overwrite  table  stage_shafa_content_expose_tmp    
partition (pdt='$day')   
select  ee.dt,ee.logsrc,ee.obj_type,ee.platform,ee.app_ver,ee.qudao,ee.pindao,ee.candidate,ee.face_code,ee.psrc,ee.province_id,ee.is_uv,ee.is_new,
concat (ee.dt,ee.logsrc,ee.obj_type,ee.platform,ee.app_ver,ee.qudao,ee.pindao,ee.candidate,ee.face_code,ee.psrc,ee.province_id,ee.is_uv,ee.is_new) as join_mark,
ee.expose   
from (    
select 
a.dt, a.logsrc, a.obj_type, a.platform, a.app_ver, a.qudao, a.pindao, a.candidate, a.face_code, a.psrc, a.province_id,  
(case when t2.user_id is not null then 1 else 0 end )        as is_uv, 
(case when t3.is_new is not null then t3.is_new else 0 end ) as is_new,  
count(*) as expose     
from (    

select 
dt, logsrc, platform, app_ver, qudao, pindao, province_id, psrc, 
uid, content.content_id as objid , 

(case  
   when content.content_type is not null 
     then ( case  
              when content.content_type='CONTENTTYPE_UserVideo' then 2    
              when content.content_type='CONTENTTYPE_SmallVideo' then 16 else 0 end )   
   else 0 end ) as obj_type, 
    
(case   
   when (psrc='PS_FEEDS' and content.extra_info is not null and (substr(content.extra_info,-1) rlike '^\\\\d+$' ) )
     then 
        (case when substr(content.extra_info, (length(content.extra_info)-1), 1)='|' 
                 then substr(content.extra_info,-1) 
              when substr(content.extra_info, (length(content.extra_info)-2), 1)='|' 
                 then substr(content.extra_info, (length(content.extra_info)-1), 2)    
              when substr(content.extra_info, (length(content.extra_info)-1), 1) !='|' and substr(content.extra_info, (length(content.extra_info)-3), 1) ='|'   
                 then substr(content.extra_info, (length(content.extra_info)-2), 3)     
              else  119  end )     
   else  119  
 end  ) as candidate, 

919  as face_code

from (    

select dt, uid.did as uid, content, 
(case when type is not null and type in ('APP','H5') then type else 'other' end ) as logsrc, 
(case when phone_meta.os_type = 'ios' then 2 when phone_meta.os_type = 'android' then 1 else 3 end ) as platform , 
(case   
   when type='APP' and app_meta.app_ver is null  then 'nvn' 
   when type='H5' then 'h5'
   else trim(app_meta.app_ver)  
 end ) as app_ver, 
(case   
    when type='APP' and app_meta.channel is null then 'nvn' 
    when type='H5' then 'h5' 
    else app_meta.channel 
 end ) as qudao, 
 (case   
   when ( type='APP' and action_from.page_source='PS_FEEDS' and entity_detail.which_feeds is not null)  then entity_detail.which_feeds   
   when  type='H5' then  5555  
   else 1919
 end ) as pindao,   
(case   
   when geo_info.city is not null and (substr(geo_info.city, 1, 2))  rlike '^\\\\d+$' then (substr(geo_info.city, 1, 2))
   else 888 
 end ) as province_id,   
action_from.page_source as psrc     
from dw_shafa  lateral view explode(entity_detail.content) cont as content 
where dt='$day' 
and data_type=0        
and event='CONTENT_EXPO'   
and uid.did is not null   
and size(entity_detail.content)>0    
and entity_detail.content is not null    
   
) f    
  
) a   
left join ( select c.uid as user_id,c.is_new from comm_user_active_daily c where c.dt='$day' and c.busi_type='shafa' ) t2 on a.uid = t2.user_id 
left join ( select c.uid as user_id,c.is_new from comm_user_active_daily c where c.dt='$day' and c.busi_type='shafa' and c.is_new=1 ) t3 on a.uid = t3.user_id         
group by   
a.dt, a.logsrc, a.obj_type, a.platform, a.app_ver, a.qudao, a.pindao, a.candidate, a.face_code, a.psrc, a.province_id,  
(case when t2.user_id is not null then 1 else 0 end )        ,  
(case when t3.is_new is not null then t3.is_new else 0 end )    
  
) ee  where ee.expose>0 and ee.face_code rlike  '^\\\\d+$'
;"

$HIVE_CONF -e "$sql_e"



sql_r="
set mapreduce.map.memory.mb=4096;
set mapreduce.reduce.memory.mb=4096;
insert  overwrite  table  stage_shafa_content_rec_tmp    
partition (pdt='$day')   
select  rr.dt,rr.logsrc,rr.obj_type,rr.platform,rr.app_ver,rr.qudao,rr.pindao,rr.candidate,rr.face_code,rr.psrc,rr.province_id,rr.is_uv,rr.is_new, 
concat (rr.dt,rr.logsrc,rr.obj_type,rr.platform,rr.app_ver,rr.qudao,rr.pindao,rr.candidate,rr.face_code,rr.psrc,rr.province_id,rr.is_uv,rr.is_new) as join_mark, 
rr.video_rec,rr.video_unrec     
from (    

select 
a.dt, a.logsrc, a.obj_type, a.platform, a.app_ver, a.qudao, a.pindao, a.candidate, a.face_code, a.psrc, a.province_id,  
(case when t2.user_id is not null then 1 else 0 end )         as is_uv, 
(case when t3.is_new is not null then t3.is_new else 0 end )  as is_new,    
sum(case when obj_type in (2,16) then 1 else 0 end ) as video_rec,   
count( distinct  ( case when obj_type in (2,16) then objid else null end ) ) as video_unrec   

from (   

select 
dt, logsrc, platform, app_ver, qudao, pindao, province_id, psrc, 
uid, content.content_id as objid , 

(case  
   when content.content_type is not null 
     then ( case  
              when content.content_type='CONTENTTYPE_UserVideo' then 2    
              when content.content_type='CONTENTTYPE_SmallVideo' then 16 else 0 end )   
   else 0 end ) as obj_type, 
    
(case   
   when ( content.extra_info is not null and (substr(content.extra_info,-1) rlike '^\\\\d+$' ) )
     then 
        (case when substr(content.extra_info, (length(content.extra_info)-1), 1)='|' 
                 then substr(content.extra_info,-1) 
              when substr(content.extra_info, (length(content.extra_info)-2), 1)='|' 
                 then substr(content.extra_info, (length(content.extra_info)-1), 2)    
              when substr(content.extra_info, (length(content.extra_info)-1), 1) !='|' and substr(content.extra_info, (length(content.extra_info)-3), 1) ='|'   
                 then substr(content.extra_info, (length(content.extra_info)-2), 3)     
              else  119  end )     
   else  119  
 end  ) as candidate,

919  as face_code

from (   

select dt, uid.did as uid, content, 
(case when type is not null and type in ('APP','H5') then type else 'other' end ) as logsrc, 
(case when phone_meta.os_type = 'ios' then 2 when phone_meta.os_type = 'android' then 1 else 3 end ) as platform , 
(case   
   when type='APP' and app_meta.app_ver is null  then 'nvn' 
   when type='H5' then 'h5'
   else trim(app_meta.app_ver)  
 end ) as app_ver, 
(case   
    when type='APP' and app_meta.channel is null then 'nvn' 
    when type='H5' then 'h5' 
    else app_meta.channel 
 end ) as qudao,   
 (case   
   when ( type='APP' and is_server=true and rec_uniq_par.rec_channel is not null)  then rec_uniq_par.rec_channel    
   when  type='H5' then  5555  
   else 1919
 end ) as pindao,   
(case   
   when geo_info.city is not null and (substr(geo_info.city, 1, 2))  rlike '^\\\\d+$' then (substr(geo_info.city, 1, 2))
   else 888   
 end ) as province_id,   
'rec' as psrc     
from dw_shafa  lateral view explode(entity_detail.content) cont as content 
where dt='$day'   
and data_type=0      
and uid.did is not null   
and event='REC_REQ'  
and is_server=true    
and size(entity_detail.content)>0       
and entity_detail.content is not null    

)  f   
    
) a   
left join ( select c.uid as user_id,c.is_new from comm_user_active_daily c where c.dt='$day' and c.busi_type='shafa' ) t2 on a.uid = t2.user_id 
left join ( select c.uid as user_id,c.is_new from comm_user_active_daily c where c.dt='$day' and c.busi_type='shafa' and c.is_new=1 ) t3 on a.uid = t3.user_id      
group by   
a.dt, a.logsrc, a.obj_type, a.platform, a.app_ver, a.qudao, a.pindao, a.candidate, a.face_code, a.psrc, a.province_id,   
(case when t2.user_id is not null then 1 else 0 end )         , 
(case when t3.is_new is not null then t3.is_new else 0 end )     
) rr  where ( rr.video_rec>0 or video_unrec>0 ) and rr.face_code rlike  '^\\\\d+$'

;"

$HIVE_CONF -e "$sql_r"

#compass_sofa_content
sql_t="
set mapreduce.map.memory.mb=4096;
set mapreduce.reduce.memory.mb=4096;
insert  overwrite  table  agg_shafa_content      
partition (pdt='$day')   
select    
concat_ws('-',substr(tt.dt, 1, 4),substr(tt.dt, 5, 2),substr(tt.dt, 7, 2))  as  dt,  
unix_timestamp( concat(tt.dt, '120000'), 'yyyyMMddHHmmss')                  as  time_stamp,  
tt.logsrc      as app_type,
tt.platform    as platform,  
tt.app_ver     as version,  
tt.qudao       as qudao,  
(case when d.province_name is null then 'other' else d.province_name end )    as location,  
(case  when tt.user_mark='comm' then 1 when tt.user_mark='new' then 2 else 9 end )  as uv_type,
(case when tt.pindao is null then 1919 else tt.pindao end )     as channel,
tt.obj_type    as content_type,   
tt.candidate   as candidate,
(case when tt.psrc is null then 'nvn' else tt.psrc end )    as page_source,    
tt.face_code    as interface_type, 
tt.click        as click,   
tt.expose       as exposure, 
tt.vv           as vv,  
tt.duration     as video_play,   
tt.video_rec    as video_recom ,   
tt.video_unrec  as video_recom_uniq, 
tt.rec          as req_recom,  
'' as a_col, '' as b_col, '' as c_col,  '' as d_col, '' as e_col, 0  as f_col, 0  as g_col, 0  as h_col, 0  as k_col, 0  as m_col    

from  (   
    
select  
(case   
  when mdt is null and edt is not null then edt   when mdt is null and edt is null then rdt 
else mdt end)        as dt,
'APP'  as logsrc,
(case   
  when mface_code is null and eface_code is not null then eface_code   when mface_code is null and eface_code is null then rface_code 
else mface_code end) as face_code,
(case   
  when mpsrc is null and epsrc is not null then epsrc   when mpsrc is null and epsrc is null then rpsrc 
else mpsrc end)      as psrc,
(case   
  when mplatform is null and eplatform is not null then eplatform   when mplatform is null and eplatform is null then rplatform 
else mplatform end)  as platform,
(case   
  when mapp_ver is null and eapp_ver is not null then eapp_ver   when mapp_ver is null and eapp_ver is null then rapp_ver 
else mapp_ver end)     as app_ver,
(case   
  when mpindao is null and epindao is not null then epindao   when mpindao is null and epindao is null then rpindao 
else mpindao end)      as pindao,
(case   
  when mqudao is null and equdao is not null then equdao  when mqudao is null and equdao is null then rqudao 
else mqudao end)       as qudao,
(case   
  when mcandidate is null and ecandidate is not null then ecandidate  when mcandidate is null and ecandidate is null then rcandidate 
else mcandidate end)   as candidate,
(case   
  when mobj_type is null and eobj_type is not null then eobj_type  when mobj_type is null and eobj_type is null then robj_type 
else mobj_type end)    as obj_type,
(case   
  when mprovince_id is null and eprovince_id is not null then eprovince_id  when mprovince_id is null and eprovince_id is null then rprovince_id 
else mprovince_id end) as province_id,

user_mark,

(case when click is null then 0 else click end)             as click,   
(case when vv is null then 0 else vv end)                   as vv,   
(case when duration is null then 0 else duration end)       as duration,   
(case when rec is null then 0 else rec end)                 as rec,   
(case when expose is null then 0 else expose end)           as expose,
(case when video_rec is null then 0 else video_rec end)     as video_rec,
(case when video_unrec is null then 0 else video_unrec end) as video_unrec   

from 

( select     
m.dt as mdt,m.logsrc as mlogsrc,m.face_code as mface_code,m.psrc as mpsrc,m.platform as mplatform,m.app_ver as mapp_ver,m.qudao as mqudao,m.province_id as mprovince_id,m.obj_type as mobj_type,m.pindao as mpindao,m.candidate as mcandidate,
e.dt as edt,e.logsrc as elogsrc,e.face_code as eface_code,e.psrc as epsrc,e.platform as eplatform,e.app_ver as eapp_ver,e.qudao as equdao,e.province_id as eprovince_id,e.obj_type as eobj_type,e.pindao as epindao,e.candidate as ecandidate,
r.dt as rdt,r.logsrc as rlogsrc,r.face_code as rface_code,r.psrc as rpsrc,r.platform as rplatform,r.app_ver as rapp_ver,r.qudao as rqudao,r.province_id as rprovince_id,r.obj_type as robj_type,r.pindao as rpindao,r.candidate as rcandidate,
'comm' as user_mark,  
m.click, m.vv, m.duration, m.rec, e.expose, r.video_rec, r.video_unrec   
from  
( select t.dt,t.logsrc,t.face_code,t.psrc,t.platform,t.app_ver,t.qudao,t.province_id,t.obj_type,t.pindao,t.candidate,t.is_uv,t.is_new,t.join_mark,
t.click, t.vv, t.duration, t.rec   
from  stage_shafa_content_main_tmp t  
where t.pdt='$day' and t.logsrc='APP' and t.is_uv=1 )   m  
  
full join    
( select t.dt,t.logsrc,t.face_code,t.psrc,t.platform,t.app_ver,t.qudao,t.province_id,t.obj_type,t.pindao,t.candidate,t.is_uv,t.is_new,t.join_mark,
t.expose  
from stage_shafa_content_expose_tmp t   
where t.pdt='$day' and t.logsrc='APP' and t.is_uv=1 )   e   
on  m.join_mark=e.join_mark    

full join    
( select t.dt,t.logsrc,t.face_code,t.psrc,t.platform,t.app_ver,t.qudao,t.province_id,t.obj_type,t.pindao,t.candidate,t.is_uv,t.is_new,t.join_mark,
t.video_rec, t.video_unrec   
from stage_shafa_content_rec_tmp t   
where t.pdt='$day' and t.logsrc='APP' and t.is_uv=1 )   r   
on  m.join_mark=r.join_mark  
   
) k  

union all  

select  
(case   
  when mdt is null and edt is not null then edt   when mdt is null and edt is null then rdt 
else mdt end)        as dt,
'APP'  as logsrc,
(case   
  when mface_code is null and eface_code is not null then eface_code   when mface_code is null and eface_code is null then rface_code 
else mface_code end) as face_code,
(case   
  when mpsrc is null and epsrc is not null then epsrc   when mpsrc is null and epsrc is null then rpsrc 
else mpsrc end)      as psrc,
(case   
  when mplatform is null and eplatform is not null then eplatform   when mplatform is null and eplatform is null then rplatform 
else mplatform end)  as platform,
(case   
  when mapp_ver is null and eapp_ver is not null then eapp_ver   when mapp_ver is null and eapp_ver is null then rapp_ver 
else mapp_ver end)   as app_ver,
(case   
  when mpindao is null and epindao is not null then epindao   when mpindao is null and epindao is null then rpindao 
else mpindao end)    as pindao,
(case   
  when mqudao is null and equdao is not null then equdao  when mqudao is null and equdao is null then rqudao 
else mqudao end)     as qudao,
(case   
  when mcandidate is null and ecandidate is not null then ecandidate  when mcandidate is null and ecandidate is null then rcandidate 
else mcandidate end) as candidate,
(case   
  when mobj_type is null and eobj_type is not null then eobj_type  when mobj_type is null and eobj_type is null then robj_type 
else mobj_type end)  as obj_type,
(case   
  when mprovince_id is null and eprovince_id is not null then eprovince_id  when mprovince_id is null and eprovince_id is null then rprovince_id 
else mprovince_id end)   as province_id,

user_mark,
(case when click is null then 0 else click end)             as click,   
(case when vv is null then 0 else vv end)                   as vv,   
(case when duration is null then 0 else duration end)       as duration,   
(case when rec is null then 0 else rec end)                 as rec,   
(case when expose is null then 0 else expose end)           as expose,
(case when video_rec is null then 0 else video_rec end)     as video_rec,
(case when video_unrec is null then 0 else video_unrec end) as video_unrec   

from 

( select     
m.dt as mdt,m.logsrc as mlogsrc,m.face_code as mface_code,m.psrc as mpsrc,m.platform as mplatform,m.app_ver as mapp_ver,m.qudao as mqudao,m.province_id as mprovince_id,m.obj_type as mobj_type,m.pindao as mpindao,m.candidate as mcandidate,
e.dt as edt,e.logsrc as elogsrc,e.face_code as eface_code,e.psrc as epsrc,e.platform as eplatform,e.app_ver as eapp_ver,e.qudao as equdao,e.province_id as eprovince_id,e.obj_type as eobj_type,e.pindao as epindao,e.candidate as ecandidate,
r.dt as rdt,r.logsrc as rlogsrc,r.face_code as rface_code,r.psrc as rpsrc,r.platform as rplatform,r.app_ver as rapp_ver,r.qudao as rqudao,r.province_id as rprovince_id,r.obj_type as robj_type,r.pindao as rpindao,r.candidate as rcandidate,
'new' as user_mark,  
m.click, m.vv, m.duration, m.rec, e.expose, r.video_rec, r.video_unrec   
from  
( select t.dt,t.logsrc,t.face_code,t.psrc,t.platform,t.app_ver,t.qudao,t.province_id,t.obj_type,t.pindao,t.candidate,t.is_uv,t.is_new,t.join_mark,
t.click, t.vv, t.duration, t.rec   
from  stage_shafa_content_main_tmp t  
where t.pdt='$day' and t.logsrc='APP' and t.is_uv=1 and t.is_new=1 )   m  
  
full join    
( select t.dt,t.logsrc,t.face_code,t.psrc,t.platform,t.app_ver,t.qudao,t.province_id,t.obj_type,t.pindao,t.candidate,t.is_uv,t.is_new,t.join_mark,
t.expose  
from stage_shafa_content_expose_tmp t   
where t.pdt='$day' and t.logsrc='APP' and t.is_uv=1 and t.is_new=1 )   e   
on  m.join_mark=e.join_mark    

full join    
( select t.dt,t.logsrc,t.face_code,t.psrc,t.platform,t.app_ver,t.qudao,t.province_id,t.obj_type,t.pindao,t.candidate,t.is_uv,t.is_new,t.join_mark,
t.video_rec, t.video_unrec   
from stage_shafa_content_rec_tmp t   
where t.pdt='$day' and t.logsrc='APP' and t.is_uv=1 and t.is_new=1 )   r   
on  m.join_mark=r.join_mark  
   
) n  
   
   
union all    
   
   
select  
(case   
  when mdt is null and edt is not null then edt   when mdt is null and edt is null then rdt 
else mdt end)        as dt,
'H5'  as logsrc,
(case   
  when mface_code is null and eface_code is not null then eface_code   when mface_code is null and eface_code is null then rface_code 
else mface_code end) as face_code,
(case   
  when mpsrc is null and epsrc is not null then epsrc   when mpsrc is null and epsrc is null then rpsrc 
else mpsrc end)      as psrc,
(case   
  when mplatform is null and eplatform is not null then eplatform   when mplatform is null and eplatform is null then rplatform 
else mplatform end)  as platform,
(case   
  when mapp_ver is null and eapp_ver is not null then eapp_ver   when mapp_ver is null and eapp_ver is null then rapp_ver 
else mapp_ver end)     as app_ver,
(case   
  when mpindao is null and epindao is not null then epindao   when mpindao is null and epindao is null then rpindao 
else mpindao end)      as pindao,
(case   
  when mqudao is null and equdao is not null then equdao  when mqudao is null and equdao is null then rqudao 
else mqudao end)       as qudao,
(case   
  when mcandidate is null and ecandidate is not null then ecandidate  when mcandidate is null and ecandidate is null then rcandidate 
else mcandidate end)   as candidate,
(case   
  when mobj_type is null and eobj_type is not null then eobj_type  when mobj_type is null and eobj_type is null then robj_type 
else mobj_type end)    as obj_type,
(case   
  when mprovince_id is null and eprovince_id is not null then eprovince_id  when mprovince_id is null and eprovince_id is null then rprovince_id 
else mprovince_id end) as province_id,

user_mark,
(case when click is null then 0 else click end)             as click,   
(case when vv is null then 0 else vv end)                   as vv,   
(case when duration is null then 0 else duration end)       as duration,   
(case when rec is null then 0 else rec end)                 as rec,   
(case when expose is null then 0 else expose end)           as expose,
(case when video_rec is null then 0 else video_rec end)     as video_rec,
(case when video_unrec is null then 0 else video_unrec end) as video_unrec   

from 

( select     
m.dt as mdt,m.logsrc as mlogsrc,m.face_code as mface_code,m.psrc as mpsrc,m.platform as mplatform,m.app_ver as mapp_ver,m.qudao as mqudao,m.province_id as mprovince_id,m.obj_type as mobj_type,m.pindao as mpindao,m.candidate as mcandidate,
e.dt as edt,e.logsrc as elogsrc,e.face_code as eface_code,e.psrc as epsrc,e.platform as eplatform,e.app_ver as eapp_ver,e.qudao as equdao,e.province_id as eprovince_id,e.obj_type as eobj_type,e.pindao as epindao,e.candidate as ecandidate,
r.dt as rdt,r.logsrc as rlogsrc,r.face_code as rface_code,r.psrc as rpsrc,r.platform as rplatform,r.app_ver as rapp_ver,r.qudao as rqudao,r.province_id as rprovince_id,r.obj_type as robj_type,r.pindao as rpindao,r.candidate as rcandidate,
'comm' as user_mark,  
m.click, m.vv, m.duration, m.rec, e.expose, r.video_rec, r.video_unrec   
from  
( select t.dt,t.logsrc,t.face_code,t.psrc,t.platform,t.app_ver,t.qudao,t.province_id,t.obj_type,t.pindao,t.candidate,t.is_uv,t.is_new,t.join_mark,
t.click, t.vv, t.duration, t.rec   
from  stage_shafa_content_main_tmp t  
where t.pdt='$day' and t.logsrc='H5' and t.is_uv=1 )   m  
  
full join    
( select t.dt,t.logsrc,t.face_code,t.psrc,t.platform,t.app_ver,t.qudao,t.province_id,t.obj_type,t.pindao,t.candidate,t.is_uv,t.is_new,t.join_mark,
t.expose  
from stage_shafa_content_expose_tmp t   
where t.pdt='$day' and t.logsrc='H5' and t.is_uv=1 )   e   
on  m.join_mark=e.join_mark    

full join    
( select t.dt,t.logsrc,t.face_code,t.psrc,t.platform,t.app_ver,t.qudao,t.province_id,t.obj_type,t.pindao,t.candidate,t.is_uv,t.is_new,t.join_mark,
t.video_rec, t.video_unrec   
from stage_shafa_content_rec_tmp t   
where t.pdt='$day' and t.logsrc='H5' and t.is_uv=1 )   r   
on  m.join_mark=r.join_mark  
   
) hk  

union all  

select  
(case   
  when mdt is null and edt is not null then edt   when mdt is null and edt is null then rdt 
else mdt end)        as dt,
'H5'  as  logsrc,
(case   
  when mface_code is null and eface_code is not null then eface_code   when mface_code is null and eface_code is null then rface_code 
else mface_code end) as face_code,
(case   
  when mpsrc is null and epsrc is not null then epsrc   when mpsrc is null and epsrc is null then rpsrc 
else mpsrc end)      as psrc,
(case   
  when mplatform is null and eplatform is not null then eplatform   when mplatform is null and eplatform is null then rplatform 
else mplatform end)  as platform,
(case   
  when mapp_ver is null and eapp_ver is not null then eapp_ver   when mapp_ver is null and eapp_ver is null then rapp_ver 
else mapp_ver end)   as app_ver,
(case   
  when mpindao is null and epindao is not null then epindao   when mpindao is null and epindao is null then rpindao 
else mpindao end)    as pindao,
(case   
  when mqudao is null and equdao is not null then equdao  when mqudao is null and equdao is null then rqudao 
else mqudao end)     as qudao,
(case   
  when mcandidate is null and ecandidate is not null then ecandidate  when mcandidate is null and ecandidate is null then rcandidate 
else mcandidate end) as candidate,
(case   
  when mobj_type is null and eobj_type is not null then eobj_type  when mobj_type is null and eobj_type is null then robj_type 
else mobj_type end)  as obj_type,
(case   
  when mprovince_id is null and eprovince_id is not null then eprovince_id  when mprovince_id is null and eprovince_id is null then rprovince_id 
else mprovince_id end)   as province_id,

user_mark,
(case when click is null then 0 else click end)             as click,   
(case when vv is null then 0 else vv end)                   as vv,   
(case when duration is null then 0 else duration end)       as duration,   
(case when rec is null then 0 else rec end)                 as rec,   
(case when expose is null then 0 else expose end)           as expose,
(case when video_rec is null then 0 else video_rec end)     as video_rec,
(case when video_unrec is null then 0 else video_unrec end) as video_unrec   

from 

( select     
m.dt as mdt,m.logsrc as mlogsrc,m.face_code as mface_code,m.psrc as mpsrc,m.platform as mplatform,m.app_ver as mapp_ver,m.qudao as mqudao,m.province_id as mprovince_id,m.obj_type as mobj_type,m.pindao as mpindao,m.candidate as mcandidate,
e.dt as edt,e.logsrc as elogsrc,e.face_code as eface_code,e.psrc as epsrc,e.platform as eplatform,e.app_ver as eapp_ver,e.qudao as equdao,e.province_id as eprovince_id,e.obj_type as eobj_type,e.pindao as epindao,e.candidate as ecandidate,
r.dt as rdt,r.logsrc as rlogsrc,r.face_code as rface_code,r.psrc as rpsrc,r.platform as rplatform,r.app_ver as rapp_ver,r.qudao as rqudao,r.province_id as rprovince_id,r.obj_type as robj_type,r.pindao as rpindao,r.candidate as rcandidate,
'new' as user_mark,  
m.click, m.vv, m.duration, m.rec, e.expose, r.video_rec, r.video_unrec   
from  
( select t.dt,t.logsrc,t.face_code,t.psrc,t.platform,t.app_ver,t.qudao,t.province_id,t.obj_type,t.pindao,t.candidate,t.is_uv,t.is_new,t.join_mark,
t.click, t.vv, t.duration, t.rec   
from  stage_shafa_content_main_tmp t  
where t.pdt='$day' and t.logsrc='H5' and t.is_uv=1 and t.is_new=1 )   m  
  
full join    
( select t.dt,t.logsrc,t.face_code,t.psrc,t.platform,t.app_ver,t.qudao,t.province_id,t.obj_type,t.pindao,t.candidate,t.is_uv,t.is_new,t.join_mark,
t.expose  
from stage_shafa_content_expose_tmp t   
where t.pdt='$day' and t.logsrc='H5' and t.is_uv=1 and t.is_new=1 )   e   
on  m.join_mark=e.join_mark    

full join    
( select t.dt,t.logsrc,t.face_code,t.psrc,t.platform,t.app_ver,t.qudao,t.province_id,t.obj_type,t.pindao,t.candidate,t.is_uv,t.is_new,t.join_mark,
t.video_rec, t.video_unrec   
from stage_shafa_content_rec_tmp t   
where t.pdt='$day' and t.logsrc='H5' and t.is_uv=1 and t.is_new=1 )   r   
on  m.join_mark=r.join_mark  
   
) hn  
  
   
) tt left join dim_province d on tt.province_id = d.province_id  
;"

$HIVE_CONF -e "$sql_t"

echo -e "$day  is  ok \n"
sleep 1s

endtime=$(date +%s)

echo -e "\n   ......sir, hive mission is accomplished.....  \n"
echo -e "   ......total time cost: $(( $endtime - $starttime )) seconds  \n"

