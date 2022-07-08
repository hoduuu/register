# register
JSP, 톰캣 8.5, Oracle Database 사용

<hr>
<h3>데이터베이스</h3>
<h5>테이블 생성</h5>
CREATE TABLE EMPLOYEES(<br>
  id VARCHAR2(10) NOT NULL,<br>
  pass VARCHAR2(10) NOT NULL,<br>
  NAME VARCHAR2(24),<br>
  lev char(1) DEFAULT 'A',<br>
  enter DATE,<br>
  gender CHAR(1) DEFAULT '1',<br>
  phone  VARCHAR2(30),<br>
  PRIMARY KEY(id)<br>
);<br>
<h5>데이터 삽입</h5>
INSERT INTO EMPLOYEES<br>
VALUES('admin','1111','정운영','A',TO_DATE('2014/04/17','yy/mm/dd'), '1', '010-1111-1111');<br>

<hr>
