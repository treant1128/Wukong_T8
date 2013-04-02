----create table net_enterprise
CREATE TABLE netsource(
source_id INT PRIMARY KEY auto_increment,
source_name VARCHAR (32) NOT NULL,
source_url VARCHAR(128) NOT NULL
);

----create table channel   被referenced的字段不是primary key就必须得添加index
CREATE TABLE channel(
channel_id int PRIMARY KEY auto_increment,
channel_name VARCHAR(128) NOT NULL,
channel_opmlUrl VARCHAR(128) NOT NULL,
channel_fk_url VARCHAR(128) NOT NULL
);

ALTER TABLE netsource ADD INDEX source_url_index (source_url);
ALTER TABLE channel ADD CONSTRAINT FK_netsource_channel_url FOREIGN KEY (channel_fk_url) REFERENCES netsource (source_url) ON UPDATE CASCADE;

----create table opml
CREATE TABLE opml4channel(
opml_id INT PRIMARY KEY auto_increment,
opml_head_title VARCHAR(128) NOT NULL,
opml_body_title VARCHAR(128),
opml_body_text VARCHAR(128),
opml_outline_text VARCHAR(128),
opml_outline_title VARCHAR(128) NOT NULL,
opml_outline_type VARCHAR(24),
opml_outline_xmlUrl VARCHAR(128) NOT NULL,
opml_outline_htmlUrl VARCHAR(128),
opml_fk_url VARCHAR(128) NOT NULL
);

ALTER TABLE channel ADD INDEX channel_opmlUrl_index (channel_opmlUrl);
ALTER TABLE opml4channel ADD CONSTRAINT FK_channel_opml4channel_url FOREIGN KEY (opml_fk_url) REFERENCES channel (channel_opmlUrl) ON UPDATE CASCADE;

----create table feed
CREATE TABLE feed(
feed_id INT PRIMARY KEY auto_increment,
feed_title VARCHAR(128) NOT NULL,
feed_image_title VARCHAR(128),
feed_image_link VARCHAR(128),
feed_image_url VARCHAR(128),
feed_description VARCHAR(256),
feed_link VARCHAR(256) NOT NULL,
feed_lauguage VARCHAR(32),
feed_pubDate datetime NOT NULL,
feed_copyright VARCHAR(128),
feed_type VARCHAR(32),
feed_fk_url VARCHAR(128) NOT NULL
);

ALTER TABLE opml4channel ADD INDEX opml_outline_xmlUrl_index (opml_outline_xmlUrl);
ALTER TABLE feed ADD CONSTRAINT FK_opml4channel_feed_url FOREIGN KEY (feed_fk_url) REFERENCES opml4channel (opml_outline_xmlUrl) ON UPDATE CASCADE;

----create table entry
CREATE TABLE entry(
entry_id INT PRIMARY KEY auto_increment,
entry_title VARCHAR (128) NOT NULL,
entry_link VARCHAR (256),
entry_author VARCHAR (64),
entry_guid VARCHAR (256) NOT NULL,
entry_category VARCHAR (64),
entry_pubDate datetime NOT NULL,
entry_comments TINYBLOB,
entry_description TEXT,

entry_abstract TEXT,
entry_image_url VARCHAR (256),
entry_priority INT,
entry_respond_ratio FLOAT (5,3),

entry_fk_url VARCHAR (128) NOT NULL
);

ALTER TABLE entry ADD CONSTRAINT FK_opml4channel_entry_url FOREIGN KEY (entry_fk_url) REFERENCES opml4channel (opml_outline_xmlUrl) ON UPDATE CASCADE;

----Create table repository
CREATE TABLE repository(
repo_id INT PRIMARY KEY auto_increment,
repo_title VARCHAR (128) NOT NULL,
repo_link VARCHAR (256) NOT NULL,
repo_category VARCHAR (64),
repo_operator VARCHAR (32) NOT NULL,
repo_subDate datetime,
repo_status VARCHAR (32) NOT NULL
);

-------------------------------------------
----Create table operator on 220.181.49.164-->database(wukong_t8_operator) 
-------------------------------------------
CREATE TABLE operator(
name VARCHAR(64) NOT NULL,
region VARCHAR(64) NOT NULL,
upower VARCHAR(32),
password VARCHAR(128),
comment VARCHAR(256)
);

-----------Insert Data to table operator  -s->submit  a->admin  d->delete  w->wukong
INSERT INTO operator (name, region, upower, password, comment) VALUES ('admin', 'God', 'a-s', 'admin', '');
INSERT INTO operator (name, region, upower, password, comment) VALUES ('zhoumj', '省公司', 'a-s', 'zhoumj', '');
INSERT INTO operator (name, region, upower, password, comment) VALUES ('jinancc', '济南', 's', 'jinan0531', '');
INSERT INTO operator (name, region, upower, password, comment) VALUES ('qingdaocc', '青岛', 's', 'qingdao0532', '');
INSERT INTO operator (name, region, upower, password, comment) VALUES ('zibocc', '淄博', 's', 'zibo0533', '');
INSERT INTO operator (name, region, upower, password, comment) VALUES ('zaozhcc', '枣庄', 's', 'zaozh0632', '');
INSERT INTO operator (name, region, upower, password, comment) VALUES ('dongyingcc', '东营', 's', 'dongying0546', '');
INSERT INTO operator (name, region, upower, password, comment) VALUES ('yantaicc', '烟台', 's', 'yantai0535', '');
INSERT INTO operator (name, region, upower, password, comment) VALUES ('weifangcc', '潍坊', 's', 'weifang0536', '');
INSERT INTO operator (name, region, upower, password, comment) VALUES ('jiningcc', '济宁', 's', 'jining0537', '');
INSERT INTO operator (name, region, upower, password, comment) VALUES ('taiancc', '泰安', 's', 'taian0538', '');
INSERT INTO operator (name, region, upower, password, comment) VALUES ('weihaicc', '威海', 's', 'weihai0631', '');
INSERT INTO operator (name, region, upower, password, comment) VALUES ('rizhaocc', '日照', 's', 'rizhao0633', '');
INSERT INTO operator (name, region, upower, password, comment) VALUES ('binzhoucc', '滨州', 's', 'binzhou0543', '');
INSERT INTO operator (name, region, upower, password, comment) VALUES ('dezhoucc', '德州', 's', 'dezhou0534', '');
INSERT INTO operator (name, region, upower, password, comment) VALUES ('liaochcc', '聊城', 's', 'liaoch0635', '');
INSERT INTO operator (name, region, upower, password, comment) VALUES ('linyicc', '临沂', 's', 'linyi0539', '');
INSERT INTO operator (name, region, upower, password, comment) VALUES ('hezecc', '菏泽', 's', 'heze0530', '');
INSERT INTO operator (name, region, upower, password, comment) VALUES ('laiwucc', '莱芜', 's', 'laiwu0634', '');
INSERT INTO operator (name, region, upower, password, comment) VALUES ('demo', 'demo', '', 'demo', '');

INSERT INTO operator (name, region, upower, password, comment) VALUES ('w', '索马里', 'w-s', 'w', '');


-------------Drop Table Clause----------------
--delete tables
DROP TABLE IF EXISTS netsource;
DROP TABLE IF EXISTS channel;
DROP TABLE IF EXISTS opml4channel;
DROP TABLE IF EXISTS feed;
DROP TABLE IF EXISTS entry;
DROP TABLE IF EXISTS repository;

-------------Just As Test Clause--------------
CREATE TABLE test(
content TINYBLOB
)