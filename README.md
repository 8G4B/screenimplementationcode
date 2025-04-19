# 프로젝트 구조

```
HRD_01/
├── build/
└── src/
    └── main/
        ├── java/
        │   └── exam/
        └── webapp/
            ├──WEB-INF/
            │   ├── classes/
            │   ├── lib/
            │   └── web.xml
            └── META-INF/
Servers/
└──Tomcat v9.0 Server at localhost-config/
```

이 모든 디렉토리가 포함된 `C:\hrdkorea` 경로에서 .git 생성 합니다.

# 실습

## 0. 개발환경구축

이 부분은 시험장 PC에 모두 설정돼있다. 여기 관련에서 문제 발생 시 감독관에게 바로 이의제기 ㄱㄱ

1.  JDK, DB, WAS 설치 + 환경설정

2.  Eclipse WorkSpace 생성

    경로는 `C:\hrdkorea`이고,

3.  Dynamic Web Project 생성

4.  Class Path 변경

## 1. inital

대부분의 문제에서 동일한 부분이다.

1.  DB table 구성

    제공된 table 명세서 바탕으로 table을 생성하고, 그 안에 data를 넣는 과정이다.

    - Column ID 주의
    - 소문자 쓰는게 더 편함
    - 수업에서는 `\java\exam\db.sql`에 미리 작성하고 복붙했음

    1. cmd를 이용해서 직접 sqlplus 접속하여 아래와 같이 직접 SQL문을 작성하고 실행한다.

       ```sql
       CREATE TABLE MEMBER_TBL_02(
           CUSTNO NUMBER(6) NOT NULL,
           CUSTNAME VARCHAR2(20),
           PHONE VARCHAR2(13),
           ADDRESS VARCHAR2(60),
           JOINDATE DATE,
           GRADE CHAR(1),
           CITY CHAR(2),
           PRIMARY KEY(CUSTNO)
       );
       ```

       ```sql
       CREATE TABLE MONEY_TBL_02 (
           CUSTNO  NUMBER(6) NOT NULL,
           SALENOL NUMBER(8) NOT NULL,
           PCOST   NUMBER(8),
           AMOUNT  NUMBER(4),
           PRICE   NUMBER(8),
           PCODE   VARCHAR2(4),
           SDATE   DATE,
           CONSTRAINT MONEY_TBL_02_PK PRIMARY KEY (CUSTNO, SALENOL)
       );
       ```

    2. 생성한 table에 마찬가지로 cmd를 이용하여 제공된 sample data를 입력한다.

       ```sql
       INSERT INTO MEMBER_TBL_02 VALUES(100001, '김행복', '010-1111-2222', '서울 동대문구 휘경1동', '20151202', 'A', '01');
       INSERT INTO MEMBER_TBL_02 VALUES(100002, '이축복', '010-1111-3333', '서울 동대문구 휘경2동', '20151206', 'B', '01');
       -- (중략)
       ```

       ```sql
       INSERT INTO MONEY_TBL_02 VALUES (100001, 20160001, 500, 5, 2500, 'A001', TO_DATE('20160101', 'YYYYMMDD'));
       INSERT INTO MONEY_TBL_02 VALUES (100001, 20160002, 1000, 4, 4000, 'A002', TO_DATE('20160101', 'YYYYMMDD'));
       -- (중략)
       ```

2.  jsp 코드 구성

    웹페이지의 구조를 잡는 부분이다.

    - 코드에서는 `<header>`, `<nav>`, `<footer>`, `<section>` 이렇게 시멘틱 태그 처리가 되어있는데, 그냥 모두 `<div>` 사용해도 무관

    1. `\webapp\` 디렉토리에 각 파일 생성하고 다음과 같이 HTML5 boilerplate를 작성한다.

       - 이 코드는 앞으로 `.jsp` 파일 만들때마다 복붙해서 이용

       ```html
       <!DOCTYPE html>
       <html>
         <head>
           <meta charset="UTF-8" />
           <title></title>
         </head>
         <body></body>
       </html>
       ```

    2. `header.jsp` 작성

       ```html
       <header>
         <table style="background-color: #0000ff; width: 100%">
           <tr>
             <th style="color: #ffffff">
               <h3>쇼핑몰 회원관리 ver 1.0</h3>
             </th>
           </tr>
         </table>
       </header>
       ```

    3. `nav.jsp` 작성

       각 sub페이지들을 `<a>`를 이용하여 라우팅 처리한다.

       ```html
       <nav>
         <table style="background-color: lime; width: 100%; color: black">
           <tr>
             <th style="color: black">
               <a href="sub1.jsp">회원등록</a>
             </th>
             <th>
               <a href="sub2.jsp">회원목록조회/수정</a>
             </th>
             <th>
               <a href="sub3.jsp">회원매출조회</a>
             </th>
             <th>
               <a href="index.jsp">홈으로</a>
             </th>
           </tr>
         </table>
       </nav>
       ```

    4. `footer.jsp` 작성

       `position: fixed;`를 이용하여 화면 하단에 고정시킨다.

       ```html
       <footer style="bottom: 0; position: fixed; width: 100%">
         <table style="background-color: #0000ff; width: 100%">
           <tr>
             <th style="color: #ffffff">
               <h3>HRDKOREA Copyrightⓒ2016 All rights reserved. Human Resources Development Service of Korea.</h3>
             </th>
           </tr>
         </table>
       </footer>
       ```

    5. `sub1.jsp`, `sub2.jsp`, `sub3.jsp`, `index.jsp`

       파일 생성 후 아까 만든 HTML5 boilerplate 복붙한다.

       아래와 같이 body 하위에 `<secton>`만 해놓는다. 이 안쪽 부분은 나중에 구현할거다.

       ```html
       <body>
         <section></section>
       </body>
       ```

       - 아까 언급한대로 `<div>` 사용해도 무관

    6. `index.jsp`

       다음과 같이 `<jsp:include />`를 이용해 `header.jsp`, `nav.jsp`, `footer.jsp`를 위치에 맞게 불러온다.

       ```html
       <jsp:include page="header.jsp" />
       <jsp:include page="nav.jsp" />
       <section></section>
       <jsp:include page="footer.jsp" />
       ```

       문제의 사진을 보고 `<section>` 하위 부분을 복붙한다.

       ```html
       <section>
         <div align="center">
           <h3>쇼핑몰 회원관리 프로그램</h3>
         </div>
         <br />
         <p>
           국가직무역량표준(NCS: National Competency Standards)으로 설계된 교육 · 훈련 과정을 체계적으로 이수하고 내 ·
           외부 평가를 거쳐 취득하는 국가기술자격입니다.
         </p>
         <p>
           <!--(중략)-->
         </p>
       </section>
       ```

## 2. 회원등록

## 3. 회원목록조회/수정

## 4. 회원매출조회
