--회원정보 명세서
CREATE TABLE member_tbl_02
(
    custno   number(6) primary key,
    custname varchar2(20),
    phone    varchar2(13),
    address  varchar2(60),
    joindate date,
    grade    char(1),
    city     char(2)
);

--회원정보 샘플 데이터
INSERT INTO member_tbl_02
VALUES (100001, '김행복', '010-1111-2222', '서울 동대문구 휘경1동', '20151202', 'A', '01')/
INSERT INTO member_tbl_02
VALUES (100002, '이축복', '010-1111-3333', '서울 동대문구 휘경2동', '20151206', 'B', '01')/
INSERT INTO member_tbl_02
VALUES (100003, '장믿음', '010-1111-4444', '울릉군 울릉읍 독도1리', '20151001', 'B', '30')/
INSERT INTO member_tbl_02
VALUES (100004, '최사랑', '010-1111-5555', '울릉군 울릉읍 독도2리', '20251113', 'A', '30')/
INSERT INTO member_tbl_02
VALUES (100005, '진평화', '010-1111-6666', '제주도 제주시 외나무골', '20251225', 'B', '60')/
INSERT INTO member_tbl_02
VALUES (100006, '차공단', '010-1111-7777', '제주도 제주시 감나무골', '20251211', 'C', '60');

--회원매출정보 명세서
CREATE TABLE money_tbl_02
(
    custno  number(6) not null,
    salenol number(8) not null,
    pcost   number(8),
    amount  number(4),
    price   number(8),
    pcode   varchar2(4),
    sdate   date,
    constraint money_tbl_02_pk primary key (custno, salenol)
);

-- 회원정보 샘플 데이터
INSERT INTO money_tbl_02 (custno, salenol, pcost, amount, price, pcode, sdate)
VALUES (100001, 20160001, 500, 5, 2500, 'A001', TO_DATE('20160101', 'YYYYMMDD'));
INSERT INTO money_tbl_02 (custno, salenol, pcost, amount, price, pcode, sdate)
VALUES (100001, 20160002, 1000, 4, 4000, 'A002', TO_DATE('20160101', 'YYYYMMDD'));
INSERT INTO money_tbl_02 (custno, salenol, pcost, amount, price, pcode, sdate)
VALUES (100001, 20160003, 500, 3, 1500, 'A008', TO_DATE('20160101', 'YYYYMMDD'));
INSERT INTO money_tbl_02 (custno, salenol, pcost, amount, price, pcode, sdate)
VALUES (100002, 20160004, 2000, 1, 2000, 'A004', TO_DATE('20160102', 'YYYYMMDD'));
INSERT INTO money_tbl_02 (custno, salenol, pcost, amount, price, pcode, sdate)
VALUES (100002, 20160005, 500, 1, 500, 'A001', TO_DATE('20160103', 'YYYYMMDD'));
INSERT INTO money_tbl_02 (custno, salenol, pcost, amount, price, pcode, sdate)
VALUES (100003, 20160006, 1500, 2, 3000, 'A003', TO_DATE('20160103', 'YYYYMMDD'));
INSERT INTO money_tbl_02 (custno, salenol, pcost, amount, price, pcode, sdate)
VALUES (100003, 20160007, 500, 2, 1000, 'A001', TO_DATE('20160104', 'YYYYMMDD'));
INSERT INTO money_tbl_02 (custno, salenol, pcost, amount, price, pcode, sdate)
VALUES (100004, 20160008, 500, 1, 500, 'A005', TO_DATE('20160104', 'YYYYMMDD'));
INSERT INTO money_tbl_02 (custno, salenol, pcost, amount, price, pcode, sdate)
VALUES (100004, 20160009, 600, 1, 600, 'A006', TO_DATE('20160104', 'YYYYMMDD'));
INSERT INTO money_tbl_02 (custno, salenol, pcost, amount, price, pcode, sdate)
VALUES (100004, 20160010, 3000, 1, 3000, 'A007', TO_DATE('20160106', 'YYYYMMDD'));

commit;

select * from money_tbl_02;