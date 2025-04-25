create table tbl_student_202210(
	stuid char(8) not null,
	sname varchar2(20),
	deptname varchar2(20),
	jumin char(13),
	phone char(13),
	email char(30),
	primary key(stuid)
);


insert into tbl_student_202210 values('20220011', '김한국', '사회복지학과', '0301013111111', '010-1111-1111', 'hankuk@naver.com');
insert into tbl_student_202210 values('20220012', '홍길동', '관광과', '0202013222222', '010-2222-2222', 'hongkil@naver.com');
insert into tbl_student_202210 values('20220013', '김미자', '패션디자인과', '0203014333333', '010-3333-3333', 'kimja@naver.com');
insert into tbl_student_202210 values('20220014', '윤한얼', '물리치료과', '0204014444444', '010-4444-4444', 'yoon@naver.com');
insert into tbl_student_202210 values('20220015', '김성진', '모던음악과', '0205013555555', '010-5555-5555', 'kimsj@naver.com');
insert into tbl_student_202210 values('20220016', '조민지', '건축과', '0206014666666', '010-6666-6666', 'jmg@naver.com');

create table tbl_score_202210(
	stuid char(8) not null,
	subcode char(8),
	midscore number(),
	finalscore number(),
	attend number(),
	report number(),
	etc number(),
)
