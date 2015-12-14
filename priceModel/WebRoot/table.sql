prompt PL/SQL Developer import file
prompt Created on 2015年2月3日 by liuwu
set feedback off
set define off
prompt Creating PR_ALLLEGCARINFO...
create table PR_ALLLEGCARINFO
(
  id          NUMBER,
  carname     VARCHAR2(50),
  incomeprice NUMBER,
  outprocost  NUMBER,
  startcity   VARCHAR2(200),
  endcity     VARCHAR2(200),
  carid       NUMBER,
  updatetime  DATE default sysdate,
  ratio       NUMBER,
  weight      NUMBER,
  length      NUMBER,
  legid       NUMBER,
  typeid      NUMBER,
  username    VARCHAR2(50)
)
;
comment on table PR_ALLLEGCARINFO
  is '价格模型中通过存储过程计算出的所有商品车信息保存在此表';
comment on column PR_ALLLEGCARINFO.carname
  is '商品车车名';
comment on column PR_ALLLEGCARINFO.incomeprice
  is '应收单价';
comment on column PR_ALLLEGCARINFO.outprocost
  is '应付单价';
comment on column PR_ALLLEGCARINFO.startcity
  is '始发地';
comment on column PR_ALLLEGCARINFO.endcity
  is '目的地';
comment on column PR_ALLLEGCARINFO.carid
  is '商品车ID';
comment on column PR_ALLLEGCARINFO.updatetime
  is '更新时间';
comment on column PR_ALLLEGCARINFO.ratio
  is '发车比例';
comment on column PR_ALLLEGCARINFO.weight
  is '重量';
comment on column PR_ALLLEGCARINFO.length
  is '长度';
comment on column PR_ALLLEGCARINFO.legid
  is '线路ID';
comment on column PR_ALLLEGCARINFO.typeid
  is '0：文件导入；1：用户自己输入';
comment on column PR_ALLLEGCARINFO.username
  is '用户名';
alter table PR_ALLLEGCARINFO
  add constraint PK_PRALLLEGCAR primary key (ID)
  disable;

prompt Creating PR_CAR_INFO...
create table PR_CAR_INFO
(
  id           NUMBER(8) not null,
  carname      VARCHAR2(50),
  weight       NUMBER,
  length       NUMBER,
  manufacturer VARCHAR2(50)
)
;
comment on table PR_CAR_INFO
  is '商品车表';
comment on column PR_CAR_INFO.id
  is 'ID';
comment on column PR_CAR_INFO.carname
  is '车名';
comment on column PR_CAR_INFO.weight
  is '重量';
comment on column PR_CAR_INFO.length
  is '长度';
comment on column PR_CAR_INFO.manufacturer
  is '厂商名';
alter table PR_CAR_INFO
  add constraint PK_CARINFO primary key (ID);

prompt Creating PR_COMBINATION...
create table PR_COMBINATION
(
  id            NUMBER(8) not null,
  leg_id        NUMBER(8),
  truck_id      NUMBER(8),
  totalcars     NUMBER(8),
  car_id1       NUMBER(8),
  car_id2       NUMBER(8),
  car_id3       NUMBER(8),
  price         NUMBER(8,5),
  prob1         NUMBER,
  prob2         NUMBER,
  car1num       NUMBER(8),
  car2num       NUMBER(8),
  car3num       NUMBER(8),
  total_length1 NUMBER(8,5),
  total_length2 NUMBER(8,5),
  total_length3 NUMBER(8,5)
)
;
comment on table PR_COMBINATION
  is '存放组合概率的表';
comment on column PR_COMBINATION.leg_id
  is '线路ID';
comment on column PR_COMBINATION.truck_id
  is '拖车ID';
comment on column PR_COMBINATION.totalcars
  is '车辆总数';
comment on column PR_COMBINATION.car_id1
  is '商品车1ID';
comment on column PR_COMBINATION.car_id2
  is '商品车2ID';
comment on column PR_COMBINATION.car_id3
  is '商品车3ID';
comment on column PR_COMBINATION.price
  is '拖车每公里收入';
comment on column PR_COMBINATION.prob1
  is '当前组合出现概率';
comment on column PR_COMBINATION.prob2
  is '总概率';
comment on column PR_COMBINATION.car1num
  is '商品车1数量';
comment on column PR_COMBINATION.car2num
  is '商品车2数量';
comment on column PR_COMBINATION.car3num
  is '商品车3数量';
comment on column PR_COMBINATION.total_length1
  is '第一排总长度';
comment on column PR_COMBINATION.total_length2
  is '第二排总长度';
comment on column PR_COMBINATION.total_length3
  is '第三排总长度';
alter table PR_COMBINATION
  add constraint PK_COMBINATION primary key (ID);

prompt Creating PR_EMPTY_KILOMETER...
create table PR_EMPTY_KILOMETER
(
  id        NUMBER,
  pointname VARCHAR2(50),
  kilometer NUMBER,
  cityname  VARCHAR2(50)
)
;
comment on table PR_EMPTY_KILOMETER
  is '价格模型空载距离表';
comment on column PR_EMPTY_KILOMETER.pointname
  is '提车库名称';
comment on column PR_EMPTY_KILOMETER.kilometer
  is '公里数';
comment on column PR_EMPTY_KILOMETER.cityname
  is '城市';

prompt Creating PR_LEGFILEIMPORT...
create table PR_LEGFILEIMPORT
(
  id             NUMBER not null,
  startcity      VARCHAR2(30),
  startpoint     VARCHAR2(30),
  endcity        VARCHAR2(30),
  loopflag       NUMBER,
  emptlyflag     NUMBER default 0,
  emptlydistance NUMBER default 0,
  incomedistance NUMBER default 0,
  message        VARCHAR2(100),
  username       VARCHAR2(50),
  customername   VARCHAR2(50)
)
;
comment on table PR_LEGFILEIMPORT
  is '价格模型中线路文件导入表';
comment on column PR_LEGFILEIMPORT.id
  is '主键';
comment on column PR_LEGFILEIMPORT.startcity
  is '始发地城市';
comment on column PR_LEGFILEIMPORT.startpoint
  is '始发地提车点';
comment on column PR_LEGFILEIMPORT.endcity
  is '目的地城市';
comment on column PR_LEGFILEIMPORT.loopflag
  is '环线标识';
comment on column PR_LEGFILEIMPORT.emptlyflag
  is '状态:0:有效；1：空载；2：无效;3待定';
comment on column PR_LEGFILEIMPORT.emptlydistance
  is '空载距离';
comment on column PR_LEGFILEIMPORT.incomedistance
  is '收入公里';
comment on column PR_LEGFILEIMPORT.message
  is '提示信息';
comment on column PR_LEGFILEIMPORT.username
  is '用户名';
comment on column PR_LEGFILEIMPORT.customername
  is '客户名字';
alter table PR_LEGFILEIMPORT
  add constraint 线路导入表主键 primary key (ID);

prompt Creating PR_LEGINCOMEDISTANCE...
create table PR_LEGINCOMEDISTANCE
(
  id             NUMBER,
  startcity      VARCHAR2(50),
  endcity        VARCHAR2(50),
  incomedistance NUMBER,
  dtupdate       DATE default sysdate
)
;
comment on table PR_LEGINCOMEDISTANCE
  is '价格模型应收公里统计表';
comment on column PR_LEGINCOMEDISTANCE.id
  is '主键';
comment on column PR_LEGINCOMEDISTANCE.startcity
  is '始发地';
comment on column PR_LEGINCOMEDISTANCE.endcity
  is '目的地';
comment on column PR_LEGINCOMEDISTANCE.incomedistance
  is '收入公里数';
comment on column PR_LEGINCOMEDISTANCE.dtupdate
  is '更新时间';

prompt Creating PR_LEGINFO...
create table PR_LEGINFO
(
  id              NUMBER(8) not null,
  origin          VARCHAR2(50),
  destination     VARCHAR2(50),
  income_distance NUMBER(19,5),
  actual_distance NUMBER(19,5),
  empty_distance  NUMBER(19,5),
  days            NUMBER(19,5)
)
;
comment on table PR_LEGINFO
  is '线路表';
comment on column PR_LEGINFO.id
  is 'ID';
comment on column PR_LEGINFO.origin
  is '出发地';
comment on column PR_LEGINFO.destination
  is '目的地';
comment on column PR_LEGINFO.income_distance
  is '收入距离';
comment on column PR_LEGINFO.actual_distance
  is '实际距离';
comment on column PR_LEGINFO.empty_distance
  is '空载距离';
comment on column PR_LEGINFO.days
  is '天数';
alter table PR_LEGINFO
  add constraint PK_LEG_INFO primary key (ID);

prompt Creating PR_LEG_CAR_INFO...
create table PR_LEG_CAR_INFO
(
  id          NUMBER(8) not null,
  incomeprice NUMBER(8,5),
  ratio       NUMBER(8,5),
  car_id      NUMBER(8) not null,
  leg_id      NUMBER(8) not null,
  vendorcost  NUMBER(8,5)
)
;
comment on table PR_LEG_CAR_INFO
  is '线路与商品车关联表';
comment on column PR_LEG_CAR_INFO.incomeprice
  is '收入单价';
comment on column PR_LEG_CAR_INFO.ratio
  is '概率';
comment on column PR_LEG_CAR_INFO.car_id
  is '商品车ID';
comment on column PR_LEG_CAR_INFO.leg_id
  is '线路ID';
comment on column PR_LEG_CAR_INFO.vendorcost
  is '支付单价';
alter table PR_LEG_CAR_INFO
  add constraint PK_CAR_INFO primary key (ID);

prompt Creating PR_LEG_TRUCKINFO...
create table PR_LEG_TRUCKINFO
(
  id       NUMBER(8) not null,
  legid    NUMBER(8),
  truckid  NUMBER(8),
  fullcost NUMBER(8,5),
  emptcost NUMBER(8,5)
)
;
comment on table PR_LEG_TRUCKINFO
  is '线路拖车关联表';
comment on column PR_LEG_TRUCKINFO.legid
  is '线路ID';
comment on column PR_LEG_TRUCKINFO.truckid
  is '拖车ID';
comment on column PR_LEG_TRUCKINFO.fullcost
  is '每公里满载成本';
comment on column PR_LEG_TRUCKINFO.emptcost
  is '每公里空载成本';
alter table PR_LEG_TRUCKINFO
  add constraint PK_LEG_TRUCK_INFO primary key (ID);

prompt Creating PR_SELFINPUTLEG...
create table PR_SELFINPUTLEG
(
  id             NUMBER not null,
  startcity      VARCHAR2(50),
  startpoint     VARCHAR2(50),
  endcity        VARCHAR2(50),
  emptlyflag     NUMBER default 0,
  loopflag       NUMBER default 0,
  emptlydistance NUMBER,
  incomedistance NUMBER,
  message        VARCHAR2(100),
  username       VARCHAR2(50)
)
;
comment on table PR_SELFINPUTLEG
  is '用户自定义输入线路时存储表';
comment on column PR_SELFINPUTLEG.startcity
  is '出发地';
comment on column PR_SELFINPUTLEG.startpoint
  is '出发地提车点';
comment on column PR_SELFINPUTLEG.endcity
  is '目的地';
comment on column PR_SELFINPUTLEG.emptlyflag
  is '标识状态:0:有效；1：空载；2：无效;3待定';
comment on column PR_SELFINPUTLEG.loopflag
  is '环线标识';
comment on column PR_SELFINPUTLEG.emptlydistance
  is '空载距离';
comment on column PR_SELFINPUTLEG.incomedistance
  is '收入公里';
comment on column PR_SELFINPUTLEG.message
  is '提示信息';
comment on column PR_SELFINPUTLEG.username
  is '用户名';
alter table PR_SELFINPUTLEG
  add constraint 唯一主键 primary key (ID);

prompt Creating PR_TRUCKCOST_NORELATED...
create table PR_TRUCKCOST_NORELATED
(
  reid      NUMBER(8) not null,
  dcai      NUMBER,
  dcmvalci  NUMBER,
  dcac      NUMBER,
  dcml      NUMBER,
  dcmc      NUMBER,
  dcdi      NUMBER,
  valuerate NUMBER,
  years     NUMBER,
  inrate    NUMBER,
  oilprice  NUMBER,
  km        NUMBER
)
;
comment on column PR_TRUCKCOST_NORELATED.reid
  is '主键';
comment on column PR_TRUCKCOST_NORELATED.dcai
  is '年检';
comment on column PR_TRUCKCOST_NORELATED.dcmvalci
  is '交强险';
comment on column PR_TRUCKCOST_NORELATED.dcac
  is '事故';
comment on column PR_TRUCKCOST_NORELATED.dcml
  is '质损';
comment on column PR_TRUCKCOST_NORELATED.dcmc
  is '维修';
comment on column PR_TRUCKCOST_NORELATED.dcdi
  is '司机保险';
comment on column PR_TRUCKCOST_NORELATED.valuerate
  is '残余价值率';
comment on column PR_TRUCKCOST_NORELATED.years
  is '使用年限';
comment on column PR_TRUCKCOST_NORELATED.inrate
  is '保险率';
comment on column PR_TRUCKCOST_NORELATED.oilprice
  is '柴油价格';
comment on column PR_TRUCKCOST_NORELATED.km
  is '行驶公里数';
alter table PR_TRUCKCOST_NORELATED
  add constraint PK_TRUCKCOST_NO primary key (REID);

prompt Creating PR_TRUCKCOST_RELATED...
create table PR_TRUCKCOST_RELATED
(
  typeid          NUMBER(8) not null,
  vcvechcle       VARCHAR2(20),
  truck_cost_year NUMBER,
  oilcost_per_k   NUMBER,
  dcds            NUMBER,
  goods_cost      NUMBER
)
;
comment on column PR_TRUCKCOST_RELATED.typeid
  is '板车类型ID';
comment on column PR_TRUCKCOST_RELATED.vcvechcle
  is '板车类型';
comment on column PR_TRUCKCOST_RELATED.truck_cost_year
  is '采购成本/每年';
comment on column PR_TRUCKCOST_RELATED.oilcost_per_k
  is '单公里油耗';
comment on column PR_TRUCKCOST_RELATED.dcds
  is '司机工资';
comment on column PR_TRUCKCOST_RELATED.goods_cost
  is '货物价值';
alter table PR_TRUCKCOST_RELATED
  add constraint PK_TRUCKCOST_RE primary key (TYPEID);

prompt Creating PR_TRUCK_INFO...
create table PR_TRUCK_INFO
(
  trucktype    VARCHAR2(50),
  vcvechcle    VARCHAR2(50),
  dcaxle       NUMBER,
  dctoplayer   NUMBER,
  dclowlayer   NUMBER,
  dcwidth      NUMBER,
  dcweightself NUMBER,
  id           NUMBER not null,
  dchighlength NUMBER,
  dclowlength  NUMBER,
  reid         NUMBER(8),
  noreid       NUMBER(8),
  dclowhigh    NUMBER,
  typeid       NUMBER(8),
  weightload   NUMBER
)
;
comment on table PR_TRUCK_INFO
  is '拖车信息表';
comment on column PR_TRUCK_INFO.trucktype
  is '拖车类型（名字）';
comment on column PR_TRUCK_INFO.vcvechcle
  is '车型';
comment on column PR_TRUCK_INFO.dcaxle
  is '轴数';
comment on column PR_TRUCK_INFO.dctoplayer
  is '上层排数
';
comment on column PR_TRUCK_INFO.dclowlayer
  is '下层排数';
comment on column PR_TRUCK_INFO.dcwidth
  is '每排宽度';
comment on column PR_TRUCK_INFO.dcweightself
  is '车辆自重';
comment on column PR_TRUCK_INFO.id
  is '主键';
comment on column PR_TRUCK_INFO.dchighlength
  is '上层长度';
comment on column PR_TRUCK_INFO.dclowlength
  is '下层长度';
comment on column PR_TRUCK_INFO.reid
  is '与车型相关的成本表';
comment on column PR_TRUCK_INFO.noreid
  is '与车型不相关的成本表';
comment on column PR_TRUCK_INFO.dclowhigh
  is '下层高度';
comment on column PR_TRUCK_INFO.typeid
  is '类型ID';
comment on column PR_TRUCK_INFO.weightload
  is '额定载重';
alter table PR_TRUCK_INFO
  add constraint PK_TRUCK_INFO primary key (ID);
alter table PR_TRUCK_INFO
  add constraint FK_TRUCK_NO foreign key (NOREID)
  references PR_TRUCKCOST_NORELATED (REID);
alter table PR_TRUCK_INFO
  add constraint FK_TRUCK_RE foreign key (REID)
  references PR_TRUCKCOST_RELATED (TYPEID);

prompt 734 records loaded
prompt Loading PR_CAR_INFO...
prompt Table is empty
prompt Loading PR_COMBINATION...
prompt Table is empty
prompt Loading PR_EMPTY_KILOMETER...
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (693, '西安比亚迪西太库', 1694, '格尔木');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (694, '西安比亚迪西太库', 1694, '格尔木');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (695, '西安比亚迪西太库', 1722, '敦煌');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (696, '西安比亚迪西太库', 1963, '哈密');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (697, '西安比亚迪西太库', 2347, '吐鲁番');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (698, '西安比亚迪西太库', 2373, '奇台');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (699, '西安比亚迪西太库', 2522, '那曲');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (700, '西安比亚迪西太库', 2534, '乌鲁木齐');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (701, '西安比亚迪西太库', 2570, '昌吉');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (702, '西安比亚迪西太库', 2670, '石河子');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (703, '西安比亚迪西太库', 2707, '沙湾');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (704, '西安比亚迪西太库', 2750, '库尔勒');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (705, '西安比亚迪西太库', 2769, '奎屯');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (706, '西安比亚迪西太库', 2848, '拉萨');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (707, '西安比亚迪西太库', 2916, '克拉玛依');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (708, '西安比亚迪西太库', 2920, '北屯');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (709, '西安比亚迪西太库', 2987, '阿勒泰');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (710, '西安比亚迪西太库', 3031, '托里');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (711, '西安比亚迪西太库', 3033, '库车');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (712, '西安比亚迪西太库', 3036, '博乐');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (713, '西安比亚迪西太库', 3143, '塔城');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (714, '西安比亚迪西太库', 3158, '巴克图口岸');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (715, '西安比亚迪西太库', 3212, '伊犁');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (716, '西安比亚迪西太库', 3212, '伊宁');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (717, '西安比亚迪西太库', 3236, '吉木乃');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (718, '西安比亚迪西太库', 3259, '吉木乃口岸');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (719, '西安比亚迪西太库', 3282, '阿克苏');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (720, '西安比亚迪西太库', 3519, '巴楚');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (721, '西安比亚迪西太库', 3672, '麦盖提');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (722, '西安比亚迪西太库', 3695, '岳普湖');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (723, '西安比亚迪西太库', 3702, '阿图什');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (724, '西安比亚迪西太库', 3744, '喀什');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (725, '西安比亚迪西太库', 3755, '泽普');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (726, '西安比亚迪西太库', 3770, '和田');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (727, '西安比亚迪西太库', 3862, '吐尔尕特口岸');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (728, '西安比亚迪西太库', 3929, '伊尔克什坦口岸');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (729, '西安比亚迪西太库', 3973, '卡拉苏口岸');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (730, '西安比亚迪亚迪库', 20.5, '西安');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (731, '西安比亚迪亚迪库', 32, '咸阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (732, '西安比亚迪亚迪库', 60, '兴平');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (733, '西安比亚迪亚迪库', 70, '渭南');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (734, '西安比亚迪亚迪库', 71, '铜川');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (735, '西安比亚迪亚迪库', 130, '商洛');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (736, '西安比亚迪亚迪库', 174, '合阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (737, '西安比亚迪亚迪库', 183, '宝鸡');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (738, '西安比亚迪亚迪库', 194, '洛川');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (739, '西安比亚迪亚迪库', 219, '韩城');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (740, '西安比亚迪亚迪库', 230, '富县');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (741, '西安比亚迪亚迪库', 236, '三门峡');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (742, '西安比亚迪亚迪库', 244, '泾川');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (743, '西安比亚迪亚迪库', 246, '安康');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (744, '西安比亚迪亚迪库', 260, '庆阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (745, '西安比亚迪亚迪库', 279, '甘泉');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (746, '西安比亚迪亚迪库', 294, '汉中');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (747, '西安比亚迪亚迪库', 295, '延安');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (748, '西安比亚迪亚迪库', 313, '平凉');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (749, '西安比亚迪亚迪库', 317, '庆城');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (750, '西安比亚迪亚迪库', 361, '天水');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (751, '西安比亚迪亚迪库', 375, '清水');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (752, '西安比亚迪亚迪库', 381, '庄浪');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (753, '西安比亚迪亚迪库', 383, '隆德');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (754, '西安比亚迪亚迪库', 403, '固原');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (755, '西安比亚迪亚迪库', 405, '甘谷');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (756, '西安比亚迪亚迪库', 422, '静宁');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (757, '西安比亚迪亚迪库', 442, '武山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (758, '西安比亚迪亚迪库', 443, '靖边');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (759, '西安比亚迪亚迪库', 450, '成县');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (760, '西安比亚迪亚迪库', 465, '通渭');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (761, '西安比亚迪亚迪库', 512, '漳县');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (762, '西安比亚迪亚迪库', 532, '渭源');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (763, '西安比亚迪亚迪库', 551, '定西');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (764, '西安比亚迪亚迪库', 563, '榆林');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (765, '西安比亚迪亚迪库', 566, '定边');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (766, '西安比亚迪亚迪库', 586, '临洮');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (767, '西安比亚迪亚迪库', 599, '岷县');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (768, '西安比亚迪亚迪库', 642, '陇南');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (769, '西安比亚迪亚迪库', 655, '兰州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (770, '西安比亚迪亚迪库', 661, '神木');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (771, '西安比亚迪亚迪库', 673, '吴忠');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (772, '西安比亚迪亚迪库', 685, '文峰镇');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (773, '西安比亚迪亚迪库', 686, '靖远');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (774, '西安比亚迪亚迪库', 708, '临夏');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (775, '西安比亚迪亚迪库', 727, '府谷');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (776, '西安比亚迪亚迪库', 727, '白银');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (777, '西安比亚迪亚迪库', 734, '景泰');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (778, '西安比亚迪亚迪库', 737, '银川');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (779, '西安比亚迪亚迪库', 758, '永登');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (780, '西安比亚迪亚迪库', 786, '天祝');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (781, '西安比亚迪亚迪库', 790, '民和');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (782, '西安比亚迪亚迪库', 798, '石嘴山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (783, '西安比亚迪亚迪库', 857, '平安');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (784, '西安比亚迪亚迪库', 893, '西宁');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (785, '西安比亚迪亚迪库', 901, '武威');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (786, '西安比亚迪亚迪库', 968, '永昌');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (787, '西安比亚迪亚迪库', 1008, '民勤');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (788, '西安比亚迪亚迪库', 1028, '共和');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (789, '西安比亚迪亚迪库', 1132, '张掖');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (790, '西安比亚迪亚迪库', 1337, '酒泉');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (791, '西安比亚迪亚迪库', 1354, '嘉峪关');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (792, '西安比亚迪亚迪库', 1693, '玉树');
commit;
prompt 100 records committed...
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (793, '西安比亚迪亚迪库', 1694, '格尔木');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (794, '西安比亚迪亚迪库', 1694, '格尔木');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (795, '西安比亚迪亚迪库', 1722, '敦煌');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (796, '西安比亚迪亚迪库', 1963, '哈密');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (797, '西安比亚迪亚迪库', 2347, '吐鲁番');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (798, '西安比亚迪亚迪库', 2373, '奇台');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (799, '西安比亚迪亚迪库', 2503, '那曲');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (800, '西安比亚迪亚迪库', 2534, '乌鲁木齐');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (801, '西安比亚迪亚迪库', 2570, '昌吉');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (802, '西安比亚迪亚迪库', 2670, '石河子');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (803, '西安比亚迪亚迪库', 2707, '沙湾');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (804, '西安比亚迪亚迪库', 2750, '库尔勒');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (805, '西安比亚迪亚迪库', 2769, '奎屯');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (806, '西安比亚迪亚迪库', 2828, '拉萨');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (807, '西安比亚迪亚迪库', 2916, '克拉玛依');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (808, '西安比亚迪亚迪库', 2920, '北屯');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (809, '西安比亚迪亚迪库', 2987, '阿勒泰');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (810, '西安比亚迪亚迪库', 3031, '托里');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (811, '西安比亚迪亚迪库', 3033, '库车');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (812, '西安比亚迪亚迪库', 3036, '博乐');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (813, '西安比亚迪亚迪库', 3143, '塔城');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (814, '西安比亚迪亚迪库', 3158, '巴克图口岸');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (815, '西安比亚迪亚迪库', 3212, '伊犁');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (816, '西安比亚迪亚迪库', 3212, '伊宁');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (817, '西安比亚迪亚迪库', 3236, '吉木乃');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (818, '西安比亚迪亚迪库', 3259, '吉木乃口岸');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (819, '西安比亚迪亚迪库', 3282, '阿克苏');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (820, '西安比亚迪亚迪库', 3519, '巴楚');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (821, '西安比亚迪亚迪库', 3672, '麦盖提');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (822, '西安比亚迪亚迪库', 3695, '岳普湖');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (823, '西安比亚迪亚迪库', 3702, '阿图什');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (824, '西安比亚迪亚迪库', 3744, '喀什');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (825, '西安比亚迪亚迪库', 3755, '泽普');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (826, '西安比亚迪亚迪库', 3770, '和田');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (827, '西安比亚迪亚迪库', 3862, '吐尔尕特口岸');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (828, '西安比亚迪亚迪库', 3929, '伊尔克什坦口岸');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (829, '西安比亚迪亚迪库', 3973, '卡拉苏口岸');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (830, '湘潭吉利大众西库', 13.9, '湘潭');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (831, '湘潭吉利大众西库', 119, '娄底');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (832, '湘潭吉利大众西库', 136, '双峰');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (833, '湘潭吉利大众西库', 157, '涟源');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (834, '湘潭吉利大众西库', 185, '耒阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (835, '湘潭吉利大众西库', 195, '冷水江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (836, '湘潭吉利大众西库', 205, '新化');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (837, '襄阳东风车城天籁库', 0, '襄樊');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (838, '襄阳东风车城天籁库', 23.4, '襄阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (839, '襄阳东风车城天籁库', 132, '荆门');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (840, '襄阳东风车城天籁库', 139, '南阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (841, '襄阳东风车城天籁库', 172, '十堰');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (842, '襄阳东风车城天籁库', 181, '随州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (843, '襄阳东风车城天籁库', 207, '荆州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (844, '盐城', 0, '盐城');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (845, '盐城', 52, '大丰');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (846, '盐城', 74, '兴化');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (847, '盐城', 85, '东台');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (848, '盐城', 105, '姜堰');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (849, '盐城', 125, '宝应');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (850, '盐城', 140, '如皋');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (851, '盐城', 151, '泰兴');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (852, '盐城', 165, '靖江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (853, '盐城', 167, '泗阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (854, '盐城', 185, '沭阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (855, '盐城', 241, '睢宁');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (856, '盐城', 272, '邳州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (857, '盐城', 403, '丰县');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (858, '永州长丰张家铺库', 4.6, '永州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (859, '永州长丰张家铺库', 123, '邵阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (860, '永州长丰张家铺库', 308, '怀化');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (861, '长沙广汽日邮漓湘库', 18.1, '长沙');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (862, '长沙广汽日邮漓湘库', 45, '宁乡');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (863, '长沙广汽日邮漓湘库', 78, '益阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (864, '长沙广汽日邮漓湘库', 116, '浏阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (865, '长沙广汽日邮漓湘库', 117, '平江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (866, '长沙广汽日邮漓湘库', 157, '岳阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (867, '长沙广汽日邮漓湘库', 161, '铜鼓');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (868, '长沙广汽日邮漓湘库', 165, '常德');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (869, '长沙广汽日邮漓湘库', 322, '张家界');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (870, '长沙广汽日邮漓湘库', 430, '吉首');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (871, '郑州日邮日产中心库', 0, '巩义');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (872, '郑州日邮日产中心库', 18.7, '郑州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (873, '郑州日邮日产中心库', 76, '长葛');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (874, '郑州日邮日产中心库', 85, '焦作');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (875, '郑州日邮日产中心库', 98, '新乡');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (876, '郑州日邮日产中心库', 139, '晋城');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (877, '郑州日邮日产中心库', 142, '洛阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (878, '郑州日邮日产中心库', 145, '平顶山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (879, '郑州日邮日产中心库', 159, '鹤壁');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (880, '郑州日邮日产中心库', 164, '漯河');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (881, '郑州日邮日产中心库', 181, '汝州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (882, '郑州日邮日产中心库', 203, '安阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (883, '郑州日邮日产中心库', 227, '商丘');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (884, '郑州日邮日产中心库', 228, '濮阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (885, '郑州日邮日产中心库', 231, '驻马店');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (886, '郑州日邮日产中心库', 323, '淮北');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (887, '中牟', 0, '中牟');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (888, '中牟', 46, '开封');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (889, '中牟', 67, '新郑');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (890, '中牟', 89, '禹州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (891, '中牟', 110, '许昌');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (892, '中牟', 177, '周口');
commit;
prompt 200 records committed...
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (893, '中牟', 182, '菏泽');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (894, '中牟', 200, '项城');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (895, '重庆', 0, '渝北');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (896, '重庆', 0, '重庆');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (897, '重庆', 64, '璧山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (898, '重庆', 72, '合川');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (899, '重庆', 73, '綦江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (900, '重庆', 76, '江津');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (901, '重庆', 76, '永川');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (902, '重庆', 77, '长寿县');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (903, '重庆', 90, '南川');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (904, '重庆', 108, '涪陵');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (905, '重庆', 112, '大足');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (906, '重庆', 162, '大竹县');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (907, '重庆', 170, '武隆');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (665, '西安比亚迪西太库', 566, '定边');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (666, '西安比亚迪西太库', 586, '临洮');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (667, '西安比亚迪西太库', 599, '岷县');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (668, '西安比亚迪西太库', 642, '陇南');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (669, '西安比亚迪西太库', 655, '兰州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (670, '西安比亚迪西太库', 661, '神木');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (671, '西安比亚迪西太库', 673, '吴忠');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (672, '西安比亚迪西太库', 685, '文峰镇');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (673, '西安比亚迪西太库', 686, '靖远');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (674, '西安比亚迪西太库', 708, '临夏');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (675, '西安比亚迪西太库', 727, '府谷');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (676, '西安比亚迪西太库', 727, '白银');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (677, '西安比亚迪西太库', 734, '景泰');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (678, '西安比亚迪西太库', 737, '银川');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (679, '西安比亚迪西太库', 758, '永登');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (680, '西安比亚迪西太库', 786, '天祝');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (681, '西安比亚迪西太库', 790, '民和');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (682, '西安比亚迪西太库', 798, '石嘴山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (683, '西安比亚迪西太库', 857, '平安');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (684, '西安比亚迪西太库', 893, '西宁');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (685, '西安比亚迪西太库', 901, '武威');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (686, '西安比亚迪西太库', 968, '永昌');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (687, '西安比亚迪西太库', 1008, '民勤');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (688, '西安比亚迪西太库', 1028, '共和');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (689, '西安比亚迪西太库', 1132, '张掖');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (690, '西安比亚迪西太库', 1337, '酒泉');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (691, '西安比亚迪西太库', 1354, '嘉峪关');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (692, '西安比亚迪西太库', 1693, '玉树');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (665, '福州', 0, '福州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (954, '福州', 0, '福州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (908, '重庆', 181, '内江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (909, '重庆', 196, '梁平');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (910, '重庆', 221, '彭水');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (911, '重庆', 243, '遵义');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (912, '重庆', 274, '万州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (913, '重庆', 280, '黔江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (914, '重庆', 297, '利川');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (915, '重庆', 307, '开江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (916, '重庆', 316, '开县');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (917, '重庆', 320, '云阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (918, '重庆', 376, '贵阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (919, '重庆', 389, '奉节');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (920, '重庆', 418, '秀山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (921, '重庆', 438, '毕节');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (922, '重庆', 439, '巫山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (923, '重庆', 484, '都匀');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (924, '重庆', 503, '安顺');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (925, '重庆', 518, '昭通');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (926, '重庆', 537, '铜仁');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (927, '重庆', 544, '凯里');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (928, '重庆', 643, '六盘水');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (929, '重庆', 718, '兴义');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (930, '重庆', 748, '曲靖');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (931, '重庆', 883, '昆明');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (932, '重庆', 970, '玉溪');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (933, '重庆', 994, '开远');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (934, '重庆', 1037, '楚雄');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (935, '重庆', 1122, '文山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (936, '重庆', 1204, '大理');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (937, '重庆', 1292, '思茅');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (938, '重庆', 1374, '保山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (939, '重庆', 1388, '丽江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (940, '重庆', 1404, '临沧');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (941, '重庆', 1412, '景洪');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (942, '重庆', 1517, '德宏');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (943, '重庆', 1523, '腾冲');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (944, '株洲栗雨工业园库', 7, '株洲');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (945, '株洲栗雨工业园库', 58, '醴陵');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (946, '株洲栗雨工业园库', 99, '萍乡');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (947, '株洲栗雨工业园库', 102, '上栗');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (948, '株洲栗雨工业园库', 141, '攸县');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (949, '株洲栗雨工业园库', 142, '衡阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (950, '株洲栗雨工业园库', 151, '宜春');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (951, '株洲栗雨工业园库', 180, '万载');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (952, '株洲栗雨工业园库', 191, '茶陵');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (953, '株洲栗雨工业园库', 264, '郴州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (101, '花都安吉日邮红棉库', 91, '从化');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (102, '花都安吉日邮红棉库', 122, '增城');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (103, '花都安吉日邮红棉库', 167, '英德');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (104, '花都安吉日邮红棉库', 218, '韶关');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (105, '花都安吉日邮红棉库', 392, '全南');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (106, '黄岛', 0, '黄岛');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (107, '黄岛', 0, '胶南');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (108, '黄岛', 0, '青岛');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (109, '黄岛', 62, '胶州');
commit;
prompt 300 records committed...
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (110, '黄岛', 86, '高密');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (111, '黄岛', 87, '日照');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (112, '黄岛', 89, '诸城');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (113, '黄岛', 113, '即墨');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (114, '黄岛', 129, '平度');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (115, '黄岛', 151, '莱西');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (116, '黄岛', 164, '潍坊');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (117, '黄岛', 168, '赣榆');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (118, '黄岛', 177, '沂水');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (119, '黄岛', 220, '东海县');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (120, '黄岛', 221, '临沂');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (121, '黄岛', 228, '灌云');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (122, '黄岛', 278, '新沂');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (123, '黄岛', 298, '郯城');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (124, '黄岛', 460, '沛县');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (125, '济南', 0, '济南');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (126, '济南', 50, '齐河');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (127, '济南', 77, '邹平');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (128, '济南', 85, '莱芜');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (129, '济南', 99, '泰安');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (130, '济南', 109, '高唐');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (131, '济南', 111, '淄博');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (132, '济南', 127, '德州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (133, '济南', 127, '聊城');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (134, '济南', 144, '博兴');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (135, '济南', 150, '广饶');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (136, '济南', 155, '临清');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (137, '济南', 158, '滨州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (138, '济南', 158, '青州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (139, '济南', 166, '曲阜');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (140, '济南', 178, '寿光');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (141, '济南', 188, '临朐');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (142, '济南', 205, '济宁');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (143, '济南', 219, '滕州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (144, '济南', 224, '东营');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (145, '济南', 255, '枣庄');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (146, '景德镇昌河新厂库', 11.1, '景德镇');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (147, '景德镇昌河新厂库', 41, '乐平');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (148, '景德镇昌河新厂库', 96, '鄱阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (149, '景德镇昌河新厂库', 96, '鄱阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (150, '景德镇昌河新厂库', 165, '黄山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (151, '柳州', 0, '柳州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (152, '柳州', 120, '来宾');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (153, '柳州', 164, '河池');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (154, '柳州', 167, '桂林');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (155, '柳州', 241, '南宁');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (156, '柳州', 251, '南丹');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (157, '柳州', 273, '贵港');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (158, '柳州', 281, '灵山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (159, '柳州', 330, '玉林');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (160, '柳州', 351, '贺州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (161, '柳州', 357, '钦州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (162, '柳州', 409, '北海');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (163, '柳州', 410, '防城港');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (164, '柳州', 416, '崇左');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (165, '柳州', 433, '梧州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (166, '柳州', 520, '百色');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (167, '柳州', 645, '乐业');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (168, '柳州申菱九江港城库', 14.4, '九江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (169, '柳州申菱九江港城库', 28, '湖口');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (170, '柳州申菱九江港城库', 57, '瑞昌');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (171, '柳州申菱九江港城库', 212, '安庆');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (172, '南昌江铃昌南库', 13.7, '南昌');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (173, '南昌江铃昌南库', 63, '高安');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (174, '南昌江铃昌南库', 66, '奉新');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (175, '南昌江铃昌南库', 78, '丰城');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (176, '南昌江铃昌南库', 87, '进贤');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (177, '南昌江铃昌南库', 88, '樟树');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (178, '南昌江铃昌南库', 127, '抚州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (179, '南昌江铃昌南库', 145, '武宁');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (180, '南昌江铃昌南库', 149, '新干');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (181, '南昌江铃昌南库', 150, '宜丰');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (182, '南昌江铃昌南库', 154, '新余');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (183, '南昌江铃昌南库', 177, '鹰潭');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (184, '南昌江铃昌南库', 183, '上高');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (185, '南昌江铃昌南库', 201, '修水');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (186, '南昌江铃昌南库', 215, '吉安');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (187, '南昌江铃昌南库', 223, '都昌');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (188, '南昌江铃昌南库', 225, '乐安');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (189, '南昌江铃昌南库', 236, '安福');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (190, '南昌江铃昌南库', 245, '永丰');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (191, '南昌江铃昌南库', 261, '上饶');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (192, '南昌江铃昌南库', 266, '泰和');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (193, '南昌江铃昌南库', 310, '邵武');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (194, '南昌江铃昌南库', 310, '邵武');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (195, '南昌江铃昌南库', 313, '永新');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (196, '南昌江铃昌南库', 314, '万安');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (197, '南昌江铃昌南库', 325, '武夷山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (198, '南昌江铃昌南库', 326, '遂川');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (199, '南昌江铃昌南库', 330, '宁都');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (200, '南昌江铃昌南库', 330, '兴国');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (201, '南昌江铃昌南库', 389, '瑞金');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (202, '南昌江铃昌南库', 391, '沙县');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (203, '南昌江铃昌南库', 392, '赣州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (204, '南昌江铃昌南库', 394, '南康');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (205, '南昌江铃昌南库', 403, '赣县');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (206, '南昌江铃昌南库', 412, '三明');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (207, '南昌江铃昌南库', 429, '崇义');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (208, '南昌江铃昌南库', 436, '南平');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (209, '南昌江铃昌南库', 437, '建瓯');
commit;
prompt 400 records committed...
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (210, '南昌江铃昌南库', 440, '会昌');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (211, '南昌江铃昌南库', 441, '信丰');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (212, '南昌江铃昌南库', 447, '于都');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (213, '南昌江铃昌南库', 465, '永安');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (214, '南昌江铃昌南库', 503, '龙南');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (215, '南昌江铃昌南库', 509, '定南');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (216, '南昌江铃昌南库', 520, '寻乌');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (217, '南昌江铃昌南库', 531, '安远');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (218, '南昌江铃昌南库', 536, '上杭');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (219, '南昌江铃昌南库', 543, '武平');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (220, '南昌江铃昌南库', 551, '龙岩');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (221, '南昌江铃昌南库', 563, '闽侯');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (222, '南昌江铃昌南库', 567, '闽侯县');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (223, '南昌江铃昌南库', 577, '漳平');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (224, '南昌江铃昌南库', 585, '福州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (225, '南昌江铃昌南库', 590, '长乐');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (226, '南昌江铃昌南库', 601, '永定');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (227, '南昌江铃昌南库', 628, '宁德');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (228, '南昌江铃昌南库', 629, '福清');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (229, '南昌江铃昌南库', 646, '漳州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (230, '南昌江铃昌南库', 661, '涵江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (231, '南昌江铃昌南库', 665, '龙海');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (232, '南昌江铃昌南库', 677, '莆田');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (233, '南昌江铃昌南库', 695, '厦门');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (234, '南昌江铃昌南库', 708, '晋江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (235, '南昌江铃昌南库', 708, '泉州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (236, '南昌江铃小蓝库 ', 26, '南昌');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (237, '南昌江铃小蓝库 ', 63, '高安');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (238, '南昌江铃小蓝库 ', 66, '奉新');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (239, '南昌江铃小蓝库 ', 78, '丰城');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (240, '南昌江铃小蓝库 ', 87, '进贤');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (241, '南昌江铃小蓝库 ', 88, '樟树');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (242, '南昌江铃小蓝库 ', 127, '抚州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (243, '南昌江铃小蓝库 ', 145, '武宁');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (244, '南昌江铃小蓝库 ', 149, '新干');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (245, '南昌江铃小蓝库 ', 150, '宜丰');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (246, '南昌江铃小蓝库 ', 154, '新余');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (247, '南昌江铃小蓝库 ', 177, '鹰潭');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (248, '南昌江铃小蓝库 ', 183, '上高');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (249, '南昌江铃小蓝库 ', 201, '修水');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (250, '南昌江铃小蓝库 ', 215, '吉安');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (1, '北京现代龙太库', 43.3, '北京');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (2, '北京现代龙太库', 55, '廊坊');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (3, '北京现代龙太库', 61, '固安');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (4, '北京现代龙太库', 71, '涿州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (5, '北京现代龙太库', 178, '唐山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (6, '北京现代龙太库', 197, '张家口');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (7, '北京现代龙太库', 229, '承德');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (8, '北京现代龙太库', 351, '大同');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (9, '北仑', 0, '北仑');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (10, '北仑', 60, '奉化');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (11, '北仑', 83, '余姚');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (12, '北仑', 88, '象山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (13, '北仑', 95, '舟山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (14, '北仑', 97, '宁海');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (15, '昌河九江长江库', 4.5, '九江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (16, '昌河九江长江库', 28, '湖口');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (17, '昌河九江长江库', 57, '瑞昌');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (18, '昌河九江长江库', 212, '安庆');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (19, '成都吉利龙安库', 30.4, '成都');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (20, '成都吉利龙安库', 74, '眉山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (21, '成都吉利龙安库', 88, '德阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (22, '成都吉利龙安库', 98, '资阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (23, '成都吉利龙安库', 131, '雅安');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (24, '成都吉利龙安库', 135, '绵阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (25, '成都吉利龙安库', 137, '乐山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (26, '成都吉利龙安库', 170, '遂宁');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (27, '成都吉利龙安库', 183, '自贡');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (28, '成都吉利龙安库', 252, '宜宾');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (29, '成都吉利龙安库', 260, '泸州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (30, '成都吉利龙安库', 264, '南充');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (31, '成都吉利龙安库', 300, '广安');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (32, '成都吉利龙安库', 301, '广元');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (33, '成都吉利龙安库', 328, '康定');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (34, '成都吉利龙安库', 343, '巴中');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (35, '成都吉利龙安库', 437, '西昌');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (36, '成都吉利龙安库', 448, '康县');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (37, '成都吉利龙安库', 475, '达州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (38, '成都吉利龙安库', 633, '攀枝花');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (39, '慈溪吉利平安库', 0, '宁波五里牌');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (40, '慈溪吉利平安库', 23.6, '慈溪');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (41, '定州鑫和晟库', 4.7, '定州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (42, '定州鑫和晟库', 63, '保定');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (43, '定州鑫和晟库', 82, '石家庄');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (44, '定州鑫和晟库', 130, '任丘');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (45, '定州鑫和晟库', 191, '邢台');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (46, '定州鑫和晟库', 196, '衡水');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (47, '定州鑫和晟库', 244, '邯郸');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (48, '定州鑫和晟库', 297, '晋中');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (49, '定州鑫和晟库', 329, '太原');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (50, '定州鑫和晟库', 415, '朔州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (51, '佛山', 0, '佛山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (52, '佛山', 40, '南海');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (53, '佛山', 40, '顺德');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (54, '佛山', 70, '江门');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (55, '佛山', 74, '番禺');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (56, '佛山', 80, '中山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (57, '佛山', 83, '三水');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (58, '佛山', 85, '肇庆');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (59, '佛山', 88, '鹤山');
commit;
prompt 500 records committed...
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (60, '佛山', 120, '珠海');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (61, '佛山', 139, '开平');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (62, '佛山', 152, '台山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (63, '佛山', 171, '云浮');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (64, '佛山', 204, '阳江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (65, '佛山', 249, '阳春');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (66, '佛山', 319, '茂名');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (67, '佛山', 395, '湛江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (68, '杭州', 0, '杭州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (69, '杭州', 0, '萧山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (70, '杭州', 45, '富阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (71, '杭州', 61, '绍兴');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (72, '杭州', 75, '海宁');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (73, '杭州', 83, '安吉');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (74, '杭州', 86, '上虞');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (75, '杭州', 88, '湖州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (76, '杭州', 91, '诸暨');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (77, '杭州', 101, '长兴');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (78, '杭州', 138, '义乌');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (79, '杭州', 152, '东阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (80, '杭州', 182, '金华');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (81, '杭州', 197, '龙游');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (82, '杭州', 199, '兰溪');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (83, '杭州', 223, '永康');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (84, '杭州', 226, '衢州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (85, '杭州', 269, '江山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (86, '合肥', 0, '合肥');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (87, '合肥', 107, '六安');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (88, '合肥', 140, '滁州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (89, '合肥', 152, '淮南');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (90, '合肥', 198, '蚌埠');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (91, '合肥', 198, '池州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (92, '合肥', 198, '马鞍山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (93, '合肥', 206, '铜陵');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (94, '合肥', 216, '宣城');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (95, '合肥', 225, '阜阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (96, '合肥', 241, '蒙城');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (97, '合肥', 246, '宿州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (98, '花都安吉日邮红棉库', 0, '广州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (99, '花都安吉日邮红棉库', 10.1, '花都');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (100, '花都安吉日邮红棉库', 46, '清远');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (251, '南昌江铃小蓝库 ', 223, '都昌');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (252, '南昌江铃小蓝库 ', 225, '乐安');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (253, '南昌江铃小蓝库 ', 236, '安福');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (254, '南昌江铃小蓝库 ', 245, '永丰');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (255, '南昌江铃小蓝库 ', 261, '上饶');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (256, '南昌江铃小蓝库 ', 266, '泰和');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (257, '南昌江铃小蓝库 ', 310, '邵武');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (258, '南昌江铃小蓝库 ', 310, '邵武');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (259, '南昌江铃小蓝库 ', 313, '永新');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (260, '南昌江铃小蓝库 ', 314, '万安');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (261, '南昌江铃小蓝库 ', 325, '武夷山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (262, '南昌江铃小蓝库 ', 326, '遂川');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (263, '南昌江铃小蓝库 ', 330, '宁都');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (264, '南昌江铃小蓝库 ', 330, '兴国');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (265, '南昌江铃小蓝库 ', 389, '瑞金');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (266, '南昌江铃小蓝库 ', 391, '沙县');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (267, '南昌江铃小蓝库 ', 392, '赣州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (268, '南昌江铃小蓝库 ', 394, '南康');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (269, '南昌江铃小蓝库 ', 403, '赣县');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (270, '南昌江铃小蓝库 ', 412, '三明');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (271, '南昌江铃小蓝库 ', 429, '崇义');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (272, '南昌江铃小蓝库 ', 436, '南平');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (273, '南昌江铃小蓝库 ', 437, '建瓯');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (274, '南昌江铃小蓝库 ', 440, '会昌');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (275, '南昌江铃小蓝库 ', 441, '信丰');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (276, '南昌江铃小蓝库 ', 447, '于都');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (277, '南昌江铃小蓝库 ', 465, '永安');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (278, '南昌江铃小蓝库 ', 503, '龙南');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (279, '南昌江铃小蓝库 ', 509, '定南');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (280, '南昌江铃小蓝库 ', 520, '寻乌');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (281, '南昌江铃小蓝库 ', 531, '安远');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (282, '南昌江铃小蓝库 ', 536, '上杭');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (283, '南昌江铃小蓝库 ', 543, '武平');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (284, '南昌江铃小蓝库 ', 551, '龙岩');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (285, '南昌江铃小蓝库 ', 563, '闽侯');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (286, '南昌江铃小蓝库 ', 567, '闽侯县');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (287, '南昌江铃小蓝库 ', 577, '漳平');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (288, '南昌江铃小蓝库 ', 585, '福州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (289, '南昌江铃小蓝库 ', 590, '长乐');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (290, '南昌江铃小蓝库 ', 601, '永定');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (291, '南昌江铃小蓝库 ', 628, '宁德');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (292, '南昌江铃小蓝库 ', 629, '福清');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (293, '南昌江铃小蓝库 ', 646, '漳州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (294, '南昌江铃小蓝库 ', 661, '涵江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (295, '南昌江铃小蓝库 ', 665, '龙海');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (296, '南昌江铃小蓝库 ', 677, '莆田');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (297, '南昌江铃小蓝库 ', 695, '厦门');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (298, '南昌江铃小蓝库 ', 708, '晋江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (299, '南昌江铃小蓝库 ', 708, '泉州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (300, '南昌江铃迎宾北库', 12.5, '南昌');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (301, '南昌江铃迎宾北库', 63, '高安');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (302, '南昌江铃迎宾北库', 66, '奉新');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (303, '南昌江铃迎宾北库', 78, '丰城');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (304, '南昌江铃迎宾北库', 87, '进贤');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (305, '南昌江铃迎宾北库', 88, '樟树');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (306, '南昌江铃迎宾北库', 127, '抚州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (307, '南昌江铃迎宾北库', 145, '武宁');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (308, '南昌江铃迎宾北库', 149, '新干');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (309, '南昌江铃迎宾北库', 150, '宜丰');
commit;
prompt 600 records committed...
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (310, '南昌江铃迎宾北库', 154, '新余');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (311, '南昌江铃迎宾北库', 177, '鹰潭');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (312, '南昌江铃迎宾北库', 183, '上高');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (313, '南昌江铃迎宾北库', 201, '修水');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (314, '南昌江铃迎宾北库', 215, '吉安');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (315, '南昌江铃迎宾北库', 223, '都昌');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (316, '南昌江铃迎宾北库', 225, '乐安');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (317, '南昌江铃迎宾北库', 236, '安福');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (318, '南昌江铃迎宾北库', 245, '永丰');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (319, '南昌江铃迎宾北库', 261, '上饶');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (320, '南昌江铃迎宾北库', 266, '泰和');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (321, '南昌江铃迎宾北库', 310, '邵武');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (322, '南昌江铃迎宾北库', 310, '邵武');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (323, '南昌江铃迎宾北库', 313, '永新');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (324, '南昌江铃迎宾北库', 314, '万安');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (325, '南昌江铃迎宾北库', 325, '武夷山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (326, '南昌江铃迎宾北库', 326, '遂川');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (327, '南昌江铃迎宾北库', 330, '宁都');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (328, '南昌江铃迎宾北库', 330, '兴国');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (329, '南昌江铃迎宾北库', 389, '瑞金');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (330, '南昌江铃迎宾北库', 391, '沙县');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (331, '南昌江铃迎宾北库', 392, '赣州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (332, '南昌江铃迎宾北库', 394, '南康');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (333, '南昌江铃迎宾北库', 403, '赣县');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (334, '南昌江铃迎宾北库', 412, '三明');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (335, '南昌江铃迎宾北库', 429, '崇义');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (336, '南昌江铃迎宾北库', 436, '南平');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (337, '南昌江铃迎宾北库', 437, '建瓯');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (338, '南昌江铃迎宾北库', 440, '会昌');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (339, '南昌江铃迎宾北库', 441, '信丰');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (340, '南昌江铃迎宾北库', 447, '于都');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (341, '南昌江铃迎宾北库', 465, '永安');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (342, '南昌江铃迎宾北库', 503, '龙南');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (343, '南昌江铃迎宾北库', 509, '定南');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (344, '南昌江铃迎宾北库', 520, '寻乌');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (345, '南昌江铃迎宾北库', 531, '安远');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (346, '南昌江铃迎宾北库', 536, '上杭');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (347, '南昌江铃迎宾北库', 543, '武平');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (348, '南昌江铃迎宾北库', 551, '龙岩');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (349, '南昌江铃迎宾北库', 563, '闽侯');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (350, '南昌江铃迎宾北库', 567, '闽侯县');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (351, '南昌江铃迎宾北库', 577, '漳平');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (352, '南昌江铃迎宾北库', 585, '福州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (353, '南昌江铃迎宾北库', 590, '长乐');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (354, '南昌江铃迎宾北库', 601, '永定');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (355, '南昌江铃迎宾北库', 628, '宁德');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (356, '南昌江铃迎宾北库', 629, '福清');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (357, '南昌江铃迎宾北库', 646, '漳州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (358, '南昌江铃迎宾北库', 661, '涵江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (359, '南昌江铃迎宾北库', 665, '龙海');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (360, '南昌江铃迎宾北库', 677, '莆田');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (361, '南昌江铃迎宾北库', 695, '厦门');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (362, '南昌江铃迎宾北库', 708, '晋江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (363, '南昌江铃迎宾北库', 708, '泉州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (364, '南京', 0, '南京');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (365, '南京', 0, '浦口');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (366, '南京', 71, '仪征');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (367, '南京', 81, '镇江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (368, '南京', 85, '镇江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (369, '南京', 86, '丹阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (370, '南京', 99, '高淳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (371, '南京', 101, '扬州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (372, '南京', 110, '金坛');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (373, '南京', 111, '溧阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (374, '南京', 119, '江都');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (375, '南京', 148, '宜兴');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (376, '南京', 156, '泰州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (377, '南京', 195, '淮安');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (378, '南京', 292, '宿迁');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (379, '南京', 327, '连云港');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (380, '南京', 373, '徐州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (381, '宁波', 0, '宁波');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (382, '上海安吉F1斯柯达库', 38.7, '上海');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (383, '上海安吉F1斯柯达库', 63, '太仓');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (384, '上海安吉F1斯柯达库', 69, '昆山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (385, '上海安吉F1斯柯达库', 98, '吴江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (386, '上海安吉F1斯柯达库', 98, '嘉兴');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (387, '上海安吉F1斯柯达库', 107, '启东');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (388, '上海安吉F1斯柯达库', 112, '苏州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (389, '上海安吉F1斯柯达库', 113, '常熟');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (390, '上海安吉F1斯柯达库', 133, '南通');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (391, '上海安吉F1斯柯达库', 136, '海门');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (392, '上海安吉F1斯柯达库', 140, '无锡');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (393, '上海安吉F1斯柯达库', 144, '张家港');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (394, '上海安吉F1斯柯达库', 174, '江阴');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (395, '上海安吉F1斯柯达库', 186, '常州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (396, '上海安吉F1通用库', 38.2, '上海');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (397, '上海安吉F1通用库', 63, '太仓');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (398, '上海安吉F1通用库', 69, '昆山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (399, '上海安吉F1通用库', 98, '吴江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (400, '上海安吉F1通用库', 98, '嘉兴');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (401, '上海安吉F1通用库', 107, '启东');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (402, '上海安吉F1通用库', 112, '苏州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (403, '上海安吉F1通用库', 113, '常熟');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (404, '上海安吉F1通用库', 133, '南通');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (405, '上海安吉F1通用库', 136, '海门');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (406, '上海安吉F1通用库', 140, '无锡');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (407, '上海安吉F1通用库', 144, '张家港');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (408, '上海安吉F1通用库', 174, '江阴');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (409, '上海安吉F1通用库', 186, '常州');
commit;
prompt 700 records committed...
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (410, '上海安吉安亭总库', 37.8, '上海');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (411, '上海安吉安亭总库', 63, '太仓');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (412, '上海安吉安亭总库', 69, '昆山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (413, '上海安吉安亭总库', 98, '吴江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (414, '上海安吉安亭总库', 98, '嘉兴');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (415, '上海安吉安亭总库', 107, '启东');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (416, '上海安吉安亭总库', 112, '苏州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (417, '上海安吉安亭总库', 113, '常熟');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (418, '上海安吉安亭总库', 133, '南通');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (419, '上海安吉安亭总库', 136, '海门');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (420, '上海安吉安亭总库', 140, '无锡');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (421, '上海安吉安亭总库', 144, '张家港');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (422, '上海安吉安亭总库', 174, '江阴');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (423, '上海安吉安亭总库', 186, '常州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (424, '上海安吉白鹤大众库', 45.3, '上海');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (425, '上海安吉白鹤大众库', 63, '太仓');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (426, '上海安吉白鹤大众库', 69, '昆山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (427, '上海安吉白鹤大众库', 98, '吴江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (428, '上海安吉白鹤大众库', 98, '嘉兴');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (429, '上海安吉白鹤大众库', 107, '启东');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (430, '上海安吉白鹤大众库', 112, '苏州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (431, '上海安吉白鹤大众库', 113, '常熟');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (432, '上海安吉白鹤大众库', 133, '南通');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (433, '上海安吉白鹤大众库', 136, '海门');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (434, '上海安吉白鹤大众库', 140, '无锡');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (435, '上海安吉白鹤大众库', 144, '张家港');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (436, '上海安吉白鹤大众库', 174, '江阴');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (437, '上海安吉白鹤大众库', 186, '常州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (438, '上海安吉白鹤通用库', 47.5, '上海');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (439, '上海安吉白鹤通用库', 63, '太仓');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (440, '上海安吉白鹤通用库', 69, '昆山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (441, '上海安吉白鹤通用库', 98, '吴江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (442, '上海安吉白鹤通用库', 98, '嘉兴');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (443, '上海安吉白鹤通用库', 107, '启东');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (444, '上海安吉白鹤通用库', 112, '苏州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (445, '上海安吉白鹤通用库', 113, '常熟');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (446, '上海安吉白鹤通用库', 133, '南通');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (447, '上海安吉白鹤通用库', 136, '海门');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (448, '上海安吉白鹤通用库', 140, '无锡');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (449, '上海安吉白鹤通用库', 144, '张家港');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (450, '上海安吉白鹤通用库', 174, '江阴');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (451, '上海安吉白鹤通用库', 186, '常州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (452, '上海安吉沧海库', 63, '太仓');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (453, '上海安吉沧海库', 69, '昆山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (454, '上海安吉沧海库', 76.6, '上海');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (455, '上海安吉沧海库', 98, '吴江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (456, '上海安吉沧海库', 98, '嘉兴');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (457, '上海安吉沧海库', 107, '启东');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (458, '上海安吉沧海库', 112, '苏州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (459, '上海安吉沧海库', 113, '常熟');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (460, '上海安吉沧海库', 133, '南通');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (461, '上海安吉沧海库', 136, '海门');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (462, '上海安吉沧海库', 140, '无锡');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (463, '上海安吉沧海库', 144, '张家港');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (464, '上海安吉沧海库', 174, '江阴');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (465, '上海安吉沧海库', 186, '常州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (466, '上海安吉金桥库', 25.5, '上海');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (467, '上海安吉金桥库', 63, '太仓');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (468, '上海安吉金桥库', 69, '昆山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (469, '上海安吉金桥库', 98, '吴江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (470, '上海安吉金桥库', 98, '嘉兴');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (471, '上海安吉金桥库', 107, '启东');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (472, '上海安吉金桥库', 112, '苏州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (473, '上海安吉金桥库', 113, '常熟');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (474, '上海安吉金桥库', 133, '南通');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (475, '上海安吉金桥库', 136, '海门');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (476, '上海安吉金桥库', 140, '无锡');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (477, '上海安吉金桥库', 144, '张家港');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (478, '上海安吉金桥库', 174, '江阴');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (479, '上海安吉金桥库', 186, '常州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (480, '上海安吉良友库', 30.7, '上海');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (481, '上海安吉良友库', 63, '太仓');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (482, '上海安吉良友库', 69, '昆山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (483, '上海安吉良友库', 98, '吴江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (484, '上海安吉良友库', 98, '嘉兴');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (485, '上海安吉良友库', 107, '启东');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (486, '上海安吉良友库', 112, '苏州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (487, '上海安吉良友库', 113, '常熟');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (488, '上海安吉良友库', 133, '南通');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (489, '上海安吉良友库', 136, '海门');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (490, '上海安吉良友库', 140, '无锡');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (491, '上海安吉良友库', 144, '张家港');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (492, '上海安吉良友库', 174, '江阴');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (493, '上海安吉良友库', 186, '常州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (494, '上海安吉临港库', 63, '太仓');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (495, '上海安吉临港库', 67.5, '上海');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (496, '上海安吉临港库', 69, '昆山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (497, '上海安吉临港库', 98, '吴江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (498, '上海安吉临港库', 98, '嘉兴');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (499, '上海安吉临港库', 107, '启东');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (500, '上海安吉临港库', 112, '苏州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (501, '上海安吉临港库', 113, '常熟');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (502, '上海安吉临港库', 133, '南通');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (503, '上海安吉临港库', 136, '海门');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (504, '上海安吉临港库', 140, '无锡');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (505, '上海安吉临港库', 144, '张家港');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (506, '上海安吉临港库', 174, '江阴');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (507, '上海安吉临港库', 186, '常州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (508, '上海安吉马陆库', 33.6, '上海');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (509, '上海安吉马陆库', 63, '太仓');
commit;
prompt 800 records committed...
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (510, '上海安吉马陆库', 69, '昆山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (511, '上海安吉马陆库', 98, '吴江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (512, '上海安吉马陆库', 98, '嘉兴');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (513, '上海安吉马陆库', 107, '启东');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (514, '上海安吉马陆库', 112, '苏州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (515, '上海安吉马陆库', 113, '常熟');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (516, '上海安吉马陆库', 133, '南通');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (517, '上海安吉马陆库', 136, '海门');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (518, '上海安吉马陆库', 140, '无锡');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (519, '上海安吉马陆库', 144, '张家港');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (520, '上海安吉马陆库', 174, '江阴');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (521, '上海安吉马陆库', 186, '常州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (522, '上海安吉三甲港库', 33.4, '上海');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (523, '上海安吉三甲港库', 63, '太仓');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (524, '上海安吉三甲港库', 69, '昆山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (525, '上海安吉三甲港库', 98, '吴江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (526, '上海安吉三甲港库', 98, '嘉兴');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (527, '上海安吉三甲港库', 107, '启东');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (528, '上海安吉三甲港库', 112, '苏州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (529, '上海安吉三甲港库', 113, '常熟');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (530, '上海安吉三甲港库', 133, '南通');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (531, '上海安吉三甲港库', 136, '海门');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (532, '上海安吉三甲港库', 140, '无锡');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (533, '上海安吉三甲港库', 144, '张家港');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (534, '上海安吉三甲港库', 174, '江阴');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (535, '上海安吉三甲港库', 186, '常州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (536, '上海安吉申江库', 23.7, '上海');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (537, '上海安吉申江库', 63, '太仓');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (538, '上海安吉申江库', 69, '昆山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (539, '上海安吉申江库', 98, '吴江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (540, '上海安吉申江库', 98, '嘉兴');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (541, '上海安吉申江库', 107, '启东');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (542, '上海安吉申江库', 112, '苏州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (543, '上海安吉申江库', 113, '常熟');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (544, '上海安吉申江库', 133, '南通');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (545, '上海安吉申江库', 136, '海门');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (546, '上海安吉申江库', 140, '无锡');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (547, '上海安吉申江库', 144, '张家港');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (548, '上海安吉申江库', 174, '江阴');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (549, '上海安吉申江库', 186, '常州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (550, '上海安吉室内库', 40.9, '上海');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (551, '上海安吉室内库', 63, '太仓');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (552, '上海安吉室内库', 69, '昆山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (553, '上海安吉室内库', 98, '吴江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (554, '上海安吉室内库', 98, '嘉兴');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (555, '上海安吉室内库', 107, '启东');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (556, '上海安吉室内库', 112, '苏州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (557, '上海安吉室内库', 113, '常熟');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (558, '上海安吉室内库', 133, '南通');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (559, '上海安吉室内库', 136, '海门');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (560, '上海安吉室内库', 140, '无锡');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (561, '上海安吉室内库', 144, '张家港');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (562, '上海安吉室内库', 174, '江阴');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (563, '上海安吉室内库', 186, '常州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (564, '上海安吉烟台祁连山库', 0, '牟平');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (565, '上海安吉烟台祁连山库', 38.5, '烟台');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (566, '上海安吉烟台祁连山库', 66, '威海');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (567, '上海安吉烟台祁连山库', 76, '栖霞');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (568, '上海安吉烟台祁连山库', 78, '文登');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (569, '上海安吉烟台祁连山库', 95, '蓬莱');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (570, '上海安吉烟台祁连山库', 104, '海阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (571, '上海安吉烟台祁连山库', 108, '龙口');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (572, '上海安吉烟台祁连山库', 117, '荣成');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (573, '上海安吉烟台祁连山库', 133, '莱阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (574, '上海安吉烟台祁连山库', 144, '招远');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (575, '上海安吉烟台祁连山库', 184, '莱州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (576, '深圳比亚迪库', 44.1, '深圳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (577, '深圳比亚迪库', 74, '东莞');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (578, '深圳比亚迪库', 74, '海口');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (579, '深圳比亚迪库', 74, '澄迈');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (580, '深圳比亚迪库', 74, '三亚');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (581, '深圳比亚迪库', 94, '惠州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (582, '深圳比亚迪库', 142, '惠东');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (583, '深圳比亚迪库', 169, '汕尾');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (584, '深圳比亚迪库', 180, '河源');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (585, '深圳比亚迪库', 334, '汕头');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (586, '深圳比亚迪库', 355, '潮州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (587, '深圳比亚迪库', 363, '梅州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (588, '深圳比亚迪库', 365, '揭阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (589, '台州', 0, '台州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (590, '台州', 36, '温岭');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (591, '台州', 50, '临海');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (592, '台州', 98, '乐清');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (593, '台州', 134, '温州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (594, '台州', 141, '永嘉');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (595, '台州', 152, '瑞安');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (596, '台州', 168, '平阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (597, '台州', 171, '缙云');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (598, '台州', 188, '苍南');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (599, '台州', 195, '武义');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (600, '台州', 220, '丽水');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (601, '天津', 0, '天津');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (602, '天津', 0, '塘沽');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (603, '天津', 37, '静海');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (604, '天津', 40, '大港');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (605, '天津', 47, '武清');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (606, '天津', 78, '宁河');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (607, '天津', 84, '汉沽');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (608, '天津', 89, '宝坻');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (609, '天津', 96, '霸州');
commit;
prompt 900 records committed...
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (610, '天津', 126, '蓟县');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (611, '天津', 127, '沧州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (612, '天津', 198, '迁西');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (613, '天津', 209, '迁安');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (614, '天津', 221, '昌黎');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (615, '芜湖', 0, '芜湖');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (616, '芜湖', 60, '无为');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (617, '芜湖', 77, '南陵');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (618, '武汉', 0, '武汉');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (619, '武汉', 73, '孝感');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (620, '武汉', 79, '鄂州');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (621, '武汉', 90, '黄冈');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (622, '武汉', 93, '咸宁');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (623, '武汉', 99, '黄石');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (624, '武汉', 99, '黄石');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (625, '武汉', 106, '仙桃');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (626, '武汉', 158, '潜江');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (627, '武汉', 218, '信阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (628, '武汉', 324, '宜昌');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (629, '武汉', 528, '恩施');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (630, '西安比亚迪西太库', 32, '咸阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (631, '西安比亚迪西太库', 41, '西安');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (632, '西安比亚迪西太库', 60, '兴平');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (633, '西安比亚迪西太库', 70, '渭南');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (634, '西安比亚迪西太库', 71, '铜川');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (635, '西安比亚迪西太库', 130, '商洛');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (636, '西安比亚迪西太库', 174, '合阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (637, '西安比亚迪西太库', 183, '宝鸡');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (638, '西安比亚迪西太库', 194, '洛川');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (639, '西安比亚迪西太库', 219, '韩城');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (640, '西安比亚迪西太库', 230, '富县');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (641, '西安比亚迪西太库', 236, '三门峡');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (642, '西安比亚迪西太库', 244, '泾川');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (643, '西安比亚迪西太库', 246, '安康');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (644, '西安比亚迪西太库', 260, '庆阳');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (645, '西安比亚迪西太库', 279, '甘泉');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (646, '西安比亚迪西太库', 294, '汉中');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (647, '西安比亚迪西太库', 295, '延安');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (648, '西安比亚迪西太库', 313, '平凉');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (649, '西安比亚迪西太库', 317, '庆城');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (650, '西安比亚迪西太库', 361, '天水');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (651, '西安比亚迪西太库', 375, '清水');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (652, '西安比亚迪西太库', 381, '庄浪');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (653, '西安比亚迪西太库', 383, '隆德');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (654, '西安比亚迪西太库', 403, '固原');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (655, '西安比亚迪西太库', 405, '甘谷');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (656, '西安比亚迪西太库', 422, '静宁');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (657, '西安比亚迪西太库', 442, '武山');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (658, '西安比亚迪西太库', 443, '靖边');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (659, '西安比亚迪西太库', 450, '成县');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (660, '西安比亚迪西太库', 465, '通渭');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (661, '西安比亚迪西太库', 512, '漳县');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (662, '西安比亚迪西太库', 532, '渭源');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (663, '西安比亚迪西太库', 551, '定西');
insert into PR_EMPTY_KILOMETER (id, pointname, kilometer, cityname)
values (664, '西安比亚迪西太库', 563, '榆林');
commit;
insert into PR_TRUCKCOST_NORELATED (reid, dcai, dcmvalci, dcac, dcml, dcmc, dcdi, valuerate, years, inrate, oilprice, km)
values (1, 2000, 4500, .19, .1, .5, 2000, .1, 6, .018, 5.88, 8000);
commit;
prompt 1 records loaded
prompt Loading PR_TRUCKCOST_RELATED...
insert into PR_TRUCKCOST_RELATED (typeid, vcvechcle, truck_cost_year, oilcost_per_k, dcds, goods_cost)
values (1, '单车航母', 500000, .42, 1.3, 1000000);
insert into PR_TRUCKCOST_RELATED (typeid, vcvechcle, truck_cost_year, oilcost_per_k, dcds, goods_cost)
values (2, '单车', 450000, .37, 1, 500000);
commit;
prompt 2 records loaded
prompt Loading PR_TRUCK_INFO...
insert into PR_TRUCK_INFO (trucktype, vcvechcle, dcaxle, dctoplayer, dclowlayer, dcwidth, dcweightself, id, dchighlength, dclowlength, reid, noreid, dclowhigh, typeid, weightload)
values ('4轴单车', '4轴单车', 4, 1, 1, 2.1, 23, 1, 27, 25, 2, 1, 2.1, 3, 35);
insert into PR_TRUCK_INFO (trucktype, vcvechcle, dcaxle, dctoplayer, dclowlayer, dcwidth, dcweightself, id, dchighlength, dclowlength, reid, noreid, dclowhigh, typeid, weightload)
values ('5轴单车', '5轴单车', 5, 1, 1, 2.7, 23, 2, 27, 25, 2, 1, 2.3, 2, 43);
insert into PR_TRUCK_INFO (trucktype, vcvechcle, dcaxle, dctoplayer, dclowlayer, dcwidth, dcweightself, id, dchighlength, dclowlength, reid, noreid, dclowhigh, typeid, weightload)
values ('5轴航母', '5轴航母', 5, 2, 1, 3.7, 24, 54, 30, 28, 1, 1, 2, 3, 43);
insert into PR_TRUCK_INFO (trucktype, vcvechcle, dcaxle, dctoplayer, dclowlayer, dcwidth, dcweightself, id, dchighlength, dclowlength, reid, noreid, dclowhigh, typeid, weightload)
values ('6轴航母', '6轴航母', 6, 2, 1, 3.8, 26, 56, 33, 31, 1, 1, 2.1, 3, 49);
commit;

create or replace view v_expense_truck_inf as
select
T.ID AS IVECHCLEID--编号 IVECHCLEID
,RE.VCVECHCLE AS VCVECHCLE --型号

,T.DCAXLE AS DCAXLE --轴数

,T.DCTOPLAYER AS DCTOPLAYER --上层排数
,T.DCLOWLAYER AS DCLOWLAYER --下层排数
,T.DCHIGHLENGTH AS DCHIGHLENGTH --上层长度
,T.DCLOWLENGTH AS DCLOWLENGTH  --下层长度
,T.DCWIDTH AS DCWIDTH --每排宽度
,T.DCLOWHIGH AS DCLOWHIGH --下层高度
,T.DCWEIGHTSELF AS DCWEIGHTSELF --自重
,RE.TRUCK_COST_YEAR * (1 - NO.VALUERATE) / NO.YEARS / NO.KM / 12 AS DCZJ --单公里折旧费
, ROUND(NO.DCAI / NO.KM / 12,3) AS DCAI--单公里年检费
, ROUND((NO.DCMVALCI) / NO.KM / 12,3) AS DCMVALCI --单公里交强险(不包含了司机保险)
, RE.GOODS_COST * NO.INRATE / NO.KM / 12  AS DCCI --单公里货物险
,RE.DCDS AS DCDS --单公里司机工资
,NO.OILPRICE * RE.OILCOST_PER_K AS DCOILPER --单公里油费
, NO.DCAC AS DCAC --事故
, NO.DCML AS DCML --质损
, NO.DCMC AS DCMC --维修
,ROUND((( NO.DCAI / NO.KM / 12)+( RE.TRUCK_COST_YEAR * (1 - NO.VALUERATE) / NO.YEARS / NO.KM / 12)+ROUND((NO.DCMVALCI+ + NO.DCDI) / NO.KM / 12,3)+( RE.GOODS_COST * NO.INRATE / NO.KM / 12)  +( NO.OILPRICE * RE.OILCOST_PER_K)
+( NO.DCAC)  +( NO.DCML) +( NO.DCMC) )*0.05,3) AS DCOC--重载其它(上面各项成本和)
,0.6 AS DCDS_EMPTY --空载时司机工资（空载时单车航母司机工资一样）
,NO.OILPRICE * 0.32 AS DCOILPER_EMPTY --空载时单公里油费 = 柴油价格*单公里油耗

,ROUND((( NO.DCAI / NO.KM / 12)+( RE.TRUCK_COST_YEAR * (1 - NO.VALUERATE) / NO.YEARS / NO.KM / 12)+(ROUND((NO.DCMVALCI+ + NO.DCDI) / NO.KM / 12,3) )+(0 * NO.INRATE / NO.KM / 12) +( NO.DCDI / NO.KM / 12) +( NO.OILPRICE * 0.32)
+0  +0 +( NO.DCMC) )*0.05,3) AS DCOC_EMPTY--空载其它(上面各项成本和)

  FROM PR_TRUCK_INFO T
  LEFT JOIN PR_TRUCKCOST_NORELATED NO
    ON T.NOREID = NO.REID
  LEFT JOIN PR_TRUCKCOST_RELATED RE
    ON T.REID = RE.TYPEID
ORDER BY T.ID
;



--存储过程
create or replace procedure PRICE_GETLEGCARINFO(startcity IN VARCHAR2,endcity IN VARCHAR2,carid in number,ratio OUT number,incomeprice out number,outprocost out number)
AS
   totalnum NUMBER;
   carnum number;
   inprice number:=0;
   countIndex number;
   countIndex2 number;
   procost number:=0;

BEGIN
  --发车比率
   SELECT sum(so.dcShipedQty) ratio
     INTO totalnum
     FROM soOrder so
     left join smStyle sms on so.istyleid = sms.ilineid
     where sms.irate !=0
     and so.vcstartcity = startcity
     and so.vccityname = endcity
     and so.itype = 1
    and so.dtdate>(select add_months(sysdate,-6) from dual);

    SELECT nvl(sum(so.dcshipedqty),0) ratio
     INTO carnum
     FROM soOrder so
    WHERE so.vcstartcity = startcity
    and so.vccityname = endcity
    and so.istyleid = carid and so.vccityname = endcity
    and so.itype = 1
    and so.dtdate>(select add_months(sysdate,-6) from dual);

    ratio:=carnum/totalnum;

    --应收单价
    select count(1)
    into countIndex
    from smARPrice
    where vcstartcity = startcity
         and vcdestcity = endcity
         and istyleid = carid
         and dtedate>(select add_months(sysdate,-2) from dual);
    if (countIndex >0)
      then
        select sma.dcprice into inprice
         from smARPrice sma
         where sma.vcstartcity = startcity
         and sma.vcdestcity = endcity
         and sma.istyleid = carid
         and sma.dtedate>(select add_months(sysdate,-2) from dual)
         and rownum = 1;
    end if;
       incomeprice := inprice;
    --应付单价(得到未过期的非临时个人，非人送车队的应付单价平均值)
    select count(1) into countIndex2
    from smAPPrice smap,smfleet smf
     where smap.ifleetid=smf.ilineid
     and smap.vcStartCity = startcity
     and smap.vcDestCity = endcity
     and smap.istyleid=carid
     and smap.dtedate>(select add_months(sysdate,-2) from dual)
     and smf.vcfleetname not like '%临时个人%'
     and smf.vcfleetname not like '%人送%';
     --如果存在多个车队，删除掉安联的
     if(countIndex2>1)
        then
     select nvl(avg(smap.dcprice),0) into procost from smAPPrice smap,smfleet smf
     where smap.ifleetid=smf.ilineid
     and smap.vcStartCity =  startcity
     and smap.vcDestCity = endcity
     and smap.istyleid=carid
     and smap.dtedate>(select add_months(sysdate,-2) from dual)
     and smf.vcfleetname not like '%临时个人%'
     and smf.vcfleetname not like '%人送%'
     and smf.vcfleetname not like '%安联%';
     --如果平均值大于10说明是单台的，那么重新查询因为可能平均值里面算了单公里的
     if(procost>10)
        then
          select nvl(avg(smap.dcprice),0) into procost from smAPPrice smap,smfleet smf
     where smap.ifleetid=smf.ilineid
     and smap.vcStartCity =  startcity
     and smap.vcDestCity = endcity
     and smap.istyleid=carid
     and smap.dtedate>(select add_months(sysdate,-2) from dual)
     and smap.dcprice >10
     and smf.vcfleetname not like '%临时个人%'
     and smf.vcfleetname not like '%人送%'
     and smf.vcfleetname not like '%安联%';
         end if;

     else
     select nvl(avg(smap.dcprice),0) into procost from smAPPrice smap,smfleet smf
     where smap.ifleetid=smf.ilineid
     and smap.vcStartCity =  startcity
     and smap.vcDestCity = endcity
     and smap.istyleid=carid
     and smap.dtedate>(select add_months(sysdate,-2) from dual)
     and smf.vcfleetname not like '%临时个人%'
     and smf.vcfleetname not like '%人送%';
     --如果平均值大于10说明是单台的，那么重新查询因为可能平均值里面算了单公里的
     if(procost>10)
        then
          select nvl(avg(smap.dcprice),0) into procost from smAPPrice smap,smfleet smf
     where smap.ifleetid=smf.ilineid
     and smap.vcStartCity =  startcity
     and smap.vcDestCity = endcity
     and smap.istyleid=carid
     and smap.dtedate>(select add_months(sysdate,-2) from dual)
     and smap.dcprice >10
     and smf.vcfleetname not like '%临时个人%'
     and smf.vcfleetname not like '%人送%';

         end if;
     end if;




      outprocost := procost;


end PRICE_GETLEGCARINFO;



CREATE OR REPLACE PROCEDURE price_legcostdistance(startcity in varchar , endcity in varchar,costdistance out number)--计算线路应收公里（加权平均算法）
AS
    countIndex number;
    costdistance2 number:=0;

BEGIN
    SELECT COUNT(1) INTO countIndex
      from smAPKilometer a, Smstartcity b, Smdestcity c, SmFleet d
      where 1 = 1
           and a.dtedate >= sysdate
           and a.dtbdate <= a.dtedate
           and a.istartcityid = b.ilineid(+)
           and a.idestcityid = c.ilineid(+)
           and a.iFleetID = d.ilineid(+)
           and d.iflag = 1
           and b.vccityname = startcity
           and c.vccityname = endcity
          -- and a.iflag=0
           and d.vcfleetname not like '%临时个人%'
            and d.vcfleetname not like '%人送%';
 --如果存在多个车队，删除掉安联的
      if(countIndex>1)
      then
           SELECT nvl(avg(a.dckilometer),0) INTO costdistance2
           from smAPKilometer a, Smstartcity b, Smdestcity c, SmFleet d
           where 1 = 1
           and a.dtedate >= sysdate
           and a.dtbdate <= a.dtedate
           and a.istartcityid = b.ilineid(+)
           and a.idestcityid = c.ilineid(+)
           and a.iFleetID = d.ilineid(+)
           and d.iflag = 1
           and b.vccityname = startcity
           and c.vccityname = endcity
           --and a.iflag=0
           and d.vcfleetname not like '%临时个人%'
           and d.vcfleetname not like '%安联%'
           and d.vcfleetname not like '%人送%';
       --如果平均值大于10说明是单台的，那么重新查询因为可能平均值里面算了单公里的
       if(costdistance2>10)
       then
            SELECT nvl(avg(a.dckilometer),0) INTO costdistance2
           from smAPKilometer a, Smstartcity b, Smdestcity c, SmFleet d
           where 1 = 1
           and a.dtedate >= sysdate
           and a.dtbdate <= a.dtedate
           and a.istartcityid = b.ilineid(+)
           and a.idestcityid = c.ilineid(+)
           and a.iFleetID = d.ilineid(+)
           and d.iflag = 1
           and b.vccityname = startcity
           and c.vccityname = endcity
           --and a.iflag=0
           and a.dckilometer>10
           and d.vcfleetname not like '%临时个人%'
           and d.vcfleetname not like '%安联%'
           and d.vcfleetname not like '%人送%';
       end if;
    else
        SELECT nvl(avg(a.dckilometer),0) INTO costdistance2
           from smAPKilometer a, Smstartcity b, Smdestcity c, SmFleet d
           where 1 = 1
           and a.dtedate >= sysdate
           and a.dtbdate <= a.dtedate
           and a.istartcityid = b.ilineid(+)
           and a.idestcityid = c.ilineid(+)
           and a.iFleetID = d.ilineid(+)
           and d.iflag = 1
           and b.vccityname = startcity
           and c.vccityname = endcity
           --and a.iflag=0
           and d.vcfleetname not like '%临时个人%'
           and d.vcfleetname not like '%安联%';
          --如果平均值大于10说明是单台的，那么重新查询因为可能平均值里面算了单公里的
       if(costdistance2>10)
       then
            SELECT nvl(avg(a.dckilometer),0) INTO costdistance2
           from smAPKilometer a, Smstartcity b, Smdestcity c, SmFleet d
           where 1 = 1
           and a.dtedate >= sysdate
           and a.dtbdate <= a.dtedate
           and a.istartcityid = b.ilineid(+)
           and a.idestcityid = c.ilineid(+)
           and a.iFleetID = d.ilineid(+)
           and d.iflag = 1
           and b.vccityname = startcity
           and c.vccityname = endcity
          -- and a.iflag=0
           and a.dckilometer>10
           and d.vcfleetname not like '%临时个人%'
           and d.vcfleetname not like '%人送%';
       end if;
       end if;
    costdistance := costdistance2;
END price_legcostdistance;



CREATE OR REPLACE PROCEDURE price_legincomedistance(startcity in varchar , endcity in varchar,legincomedistance out number)--计算线路应收公里（加权平均算法）
AS
   cursor legcursor is
   select  nvl(sum(so.dcshipedqty),0) num,so.icustomerid from soorder so
   where so.vcstartcity=startcity and so.vccityname=endcity
   and so.itype=1
   and  so.dtDate>(select add_months(sysdate,-6) from dual)
   group by so.icustomerid;
   leginfo legcursor%rowtype;

   totalsum number:=0;--统计数量
   totalkilo number:=0;--总公里数
   
   countindex number;--判断标识
   countindex2 number;
   
   kilonumber2 number;--每次循环中的公里数
BEGIN
   --  select count(*) into countindex from soorder so
    --where so.vcstartcity=startcity and so.vccityname=endcity
    --  and so.itype=1
   --and  so.dtDate>(select add_months(sysdate,-6) from dual);
   select count(*) into countindex from smARKilometer sma
    where sma.istartcityid=(select ilineid from smStartCity where vccityname=startcity)
    and sma.idestcityid=(select ilineid from smDestCity where vccityname=endcity);
  
   if(countindex=0)
   then
     dbms_output.put_line('if ');
     legincomedistance:=countindex;
   else 
  open legcursor;
       loop                      
              kilonumber2 :=0;
       fetch legcursor into leginfo;
       exit when  legcursor%notfound;
       totalsum := totalsum +  leginfo.num;
        dbms_output.put_line('totalsum ' || totalsum);
         dbms_output.put_line('cusomerid ' || leginfo.icustomerid);
         
       select count(1) into countindex2
       from smARKilometer sma
       where sma.istartcityid=(select ilineid from smStartCity where vccityname=startcity)
       and sma.idestcityid=(select ilineid from smDestCity where vccityname=endcity)
       and sma.icustomerid=leginfo.icustomerid;
  if(countindex2=0)
  then
    dbms_output.put_line('else ');
      kilonumber2 :=0;
   else
       select sma.dcKilometer into kilonumber2
       from smARKilometer sma
       where sma.istartcityid=(select ilineid from smStartCity where vccityname=startcity)
       and sma.idestcityid=(select ilineid from smDestCity where vccityname=endcity)
       and sma.icustomerid=leginfo.icustomerid
       and sma.dtEDate>sysdate and rownum=1;
       totalkilo := totalkilo +(leginfo.num*kilonumber2);
       
       
    end if;
       end loop;
       -- outincomedistance:=totalkilo/totalsum;
       if(totalsum=0)
          then
          legincomedistance :=1;
        else  
         dbms_output.put_line('outincomedistance ' || totalkilo/totalsum);
         legincomedistance :=totalkilo/totalsum;
        end if;
       end if;
END price_legincomedistance;


--结束




