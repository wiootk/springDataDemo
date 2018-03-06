---如果从MySQL导入数据到H2
insert into demo ( select * from csvread('src/test/resources/data.csv') ) ; 