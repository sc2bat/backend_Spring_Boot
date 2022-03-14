DROP TABLE bbs purge;

CREATE TABLE bbs(
	id NUMBER(10),
	writer VARCHAR2(30),
	title VARCHAR2(30),
	content VARCHAR2(1000)
);

CREATE SEQUENCE bbs_seq START WITH 1 INCREMENT BY 1 NOCACHE;

INSERT INTO bbs VALUES(bbs_seq.nextVal, 'wOne', 'hello1', 'testetstestets');
INSERT INTO bbs VALUES(bbs_seq.nextVal, 'wTwo', 'hello2', 'testetstestets');
INSERT INTO bbs VALUES(bbs_seq.nextVal, 'wThree', 'hello3', 'testetstestets');
INSERT INTO bbs VALUES(bbs_seq.nextVal, 'wFour', 'hello4', 'testetstestets');


SELECT * FROM bbs;