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

    - `\webapp\` 디렉토리에 각 파일 생성하고 다음과 같이 HTML5 boilerplate 작성

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

    1. `header.jsp`

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

    2. `nav.jsp`

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

    3. `footer.jsp`

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

    4. `sub1.jsp`, `sub2.jsp`, `sub3.jsp`, `index.jsp`

       파일 생성 후 아까 만든 HTML5 boilerplate 복붙한다.

       다음과 같이 `<jsp:include />`를 이용해 `header.jsp`, `nav.jsp`, `footer.jsp`를 위치에 맞게 불러온다. `<section>` 영역은 페이지에 따라 다르기 때문에 나중에 구현한다.

       ```html
       <jsp:include page="header.jsp" />
       <jsp:include page="nav.jsp" />
       <section></section>
       <jsp:include page="footer.jsp" />
       ```

       - 아까 언급한대로 `<div>` 사용해도 무관

    5. `index.jsp`

       문제의 사진을 보고 `<section>` 영역을 작성한다.

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

## 2. DB 연동

`\java\exam\` 디렉토리에 `MemberDAO.java` 파일을 생성한다.

- 생성할 디렉토리 우클릭하고, New > Other... > Class 선택 후 Next > Name에 파일명 입력하고 Finish

1. JDBC 객체 선언

   `MemberDAO` class 최상단에 다음과 같은 객체를 선언한다.

   ```java
   public class MemberDAO {
       private Connection conn;
       private PreparedStatement ps;
       private ResultSet rs;

       // 중략
   }
   ```

   각각 DB에 연결하고, SQL문을 수행하고, SQL문 실행 결과를 저장하는 역할을 한다.

2. DB 접속 method 구현

   `getConn()` method를 만들고, DB 연결 정보 변수를 선언한다.

   ```java
   public Connection getConn() {
       String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
       String user = "system";
       String password = "1234";

       // 중략
   }
   ```

   `Class.forName()`을 통해 Oracle JDBC 드라이버를 메모리에 직접 load하고, `DriverManager.getConnection()`의 파라미터로 아까 설정한 DB 연결 정보를 넘겨준다.

   ```java
   try {
       Class.forName("oracle.jdbc.OracleDriver");
       conn = DriverManager.getConnection(url, user, password);
       System.out.println("DB 접속 완료");
   } catch (Exception e) {
       e.printStackTrace();
       System.out.println("getConn() Exception");
   }

   return conn;
   ```

3. DB 연결 종료 method 구현

   `ResultSet`, `PreparedStatement`, `Connection`의 순서로 NullPointerException을 방지하기 위해 if문으로 null 체크를 하고, `close()` method를 호출하여 종료한다.

   ```java
   public void dbClose() {
       try {
           if (rs != null)
               rs.close();
           if (ps != null)
               ps.close();
           if (conn != null)
               conn.close();
           System.out.println("DB 접속 해제");
       } catch (Exception e) {
           // (중략)
       }
   }
   ```

## 3. 회원등록

1.  `sub1.jsp` 퍼블리싱

    1. 문제의 사진을 보고 `<section>` 영역을 작성한다.

       ```html
       <section>
         <div align="center">
           <h3>쇼핑몰 회원등록</h3>
           <form action="sub1Action.jsp" method="post" name="sub1Form">
             <table border="1"></table>
           </form>
         </div>
       </section>
       ```

    2. `<table>`을 구성한다.

       각 `<tr>`를 `MEMBER_TBL_02` DB table의 각 column에 맞게 작성한다.

       1. 자동생성되지 않는 `<input />`

          `<input />`을 작성할 때 `name` 속성은 DB table의 column명과 동일하게 설정하고, `type` 속성은 DB column의 데이터 타입에 맞는 HTML 입력 타입으로 설정한다.

          예를 들어 다음과 같은 column의 경우에는

          ```sql
          CUSTNAME VARCHAR2(20)
          ```

          다음과 같이 작성할 수 있다.

          ```html
          <tr>
            <th>회원성명</th>
            <td>
              <input type="text" name="custname" />
            </td>
          </tr>
          ```

          column명인 `CUSTNAME`을 `<input />`의 `name` 속성에 그대로 넣었고, column이 `VARCHAR2(20)` type을 가지기 때문에 `type` 속성에는 `"text"`를 넣은 모습이다.

       2. 자동생성되는 `<input />`

          요구사항에 따라 일부 `<input />`은 최상단에 JSP 표현식을 작성하여 입력하지 않아도 value가 알맞게 생성되게 처리해야 한다.

          - `readonly` 속성을 `"readonly"`로 설정하여 사용자가 값을 수정할 수 없게 함

          '회원번호'의 경우는 `MemberDAO`의 `getCustno()` method를 호출하여 `custno`를 생성하고

          ```jsp
          <% @page import="exam.MemberDAO" %>

          <%
             MemberDAO dao = new MemberDAO();
             int custno = dao.getCustno();
          %>
          ```

          이를 `value`로 설정하였다.

          ```html
          <tr>
            <th>회원번호(자동발생)</th>
            <td>
              <input type="number" name="custno" value="<%= custno %>" readonly="readonly" />
            </td>
          </tr>
          ```

          '가입일자'의 경우는 java의 `Date` 객체를 이용하여 현재 날짜를 가져왔다. `SimpleDateFormat`을 이용하여 날짜의 형식을 맞춰서 `today`에 할당하고,

          ```jsp
          <% @page import="java.util.Date" %>
          <% @page import="java.text.SimpleDateFormat" %>

          <%
             Date date = new Date();
             SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
             String today = formater.format(date);
          %>
          ```

          이를 `value`로 설정하였다.

          ```html
          <tr>
            <th>가입일자</th>
            <td>
              <input type="text" name="joindate" value="<%= today %>" readonly="readonly" />
            </td>
          </tr>
          ```

    3. 버튼

       최하단 row에 '등록', '조회' 버튼을 만들고, `onClick`을 통해 클릭 이벤트 발생 시 로직을 처리한다.

       ```html
       <tr>
         <td colspan="2" align="center">
           <input type="button" value="등록" onclick="fnSub1FormCheck()" />
           <input type="button" value="조회" onclick="location.href='sub2.jsp'" />
         </td>
       </tr>
       ```

       이때 '등록'은 `check.js`의 `fnSub1FormCheck` 함수를 실행하게 하고, '조회'는 `sub2.jsp` 페이지로 이동하게 한다.

       - html의 `<head>` 영역에 다음과 같이 작성하여 `check.js`를 불러와야 함.

         ```html
         <script type="text/javascript" src="check.js"></script>
         ```

2.  회원번호 생성 method 구현

    `MemberDAO`에 `getCustno()` method를 생성하고, (추가 설명 작성)

    ```java
    public int getCustno() {
    	conn = getConn();

    // (중략)
    }
    ```

    1. SQL문 작성

       `MEMBER_TBL_02` table에서 가장 큰 `CUSTNO`를 가져오는 쿼리이다.

       ```java
       String sql = "SELECT MAX(CUSTNO) FROM MEMBER_TBL_02";
       ```

    2. `custno` 선언

       `custno` 변수를 0으로 초기화한다. 이 값이 나중에 반환된다.

       ```java
       int custno = 0;
       ```

    3. SQL문 실행

       ```java
       try {
           ps = conn.prepareStatement(sql);
           rs = ps.executeQuery();

           // (중략)
       } catch (Exception e) {
           // (중략)
       } finally {
           dbClose();
       }
       ```

    4. `custno` 계산

       SQL 수행결과의 첫번째 column 값에 1을 더해서 `custno`에 저장하는 코드를 `try` 블록 안에 작성한다.

       ```java
        if (rs.next()) {
            custno = rs.getInt(1) + 1;
        }
       ```

       만약 table이 비어있다면 `rs`가 NULL을 반환하고, NULL을 `getInt()`로 가져오면 0이기 때문에 최종 `custno`는 1이 된다.

3.  회원정보 등록 method 구현

    `insertSub1()`

    ```java
    public void insertSub1(MemberDTO dto) {
    	conn = getConn();
    	String sql = "INSERT INTO MEMBER_TBL_02 VALUES(?, ?, ?, ?, ?, ?, ?)";

    	try {
    		ps = conn.prepareStatement(sql);
    		ps.setInt(1, dto.getCustno());
    		ps.setString(2, dto.getCustname());
    		ps.setString(3, dto.getPhone());
    		ps.setString(4, dto.getAddress());
    		ps.setString(5, dto.getJoindate());
    		ps.setString(6, dto.getGrade());
    		ps.setString(7, dto.getCity());
    		ps.executeUpdate();
    	} catch (Exception e) {
    		e.printStackTrace();
    		System.out.println("insertSub1() Exception");
    	} finally {
    		dbClose();
    	}
    }
    ```

4.  회원정보 유효성 검사 구현

    1.  `check.js` 파일 생성

        `\webapp\`에 `check.js` 파일을 생성하고 `fnSub1FormCheck` 함수를 작성한다.

        함수 안에서 유효성 검사를 할 `sub1Form`의 value들을 불러온다.

        ```javascript
        const fnSub1FormCheck = () => {
          const custname = document.sub1Form.custname.value;
          const phone = document.sub1Form.phone.value;

          // (중략)
        };
        ```

    2.  검증 로직

        if문을 돌려서 각 값들의 value가 공백이거나, 공백을 제외하고 value의 길이가 0인지 검사한다. 모든 if문을 통과하고 마지막 else문에 도달하면 `sub1Form`을 submit한다.

        ```javascript
        if (custname === "" || custname.trim().length === 0) {
          alert("회원성명이 입력되지 않았습니다.");
          document.sub1Form.custname.focus();
          return false;
        } else if (phone === "" || phone.trim().length === 0) {
          alert("회원전화가 입력되지 않았습니다.");
          document.sub1Form.phone.focus();
          return false;
        } // (중략)
        else {
          alert("회원등록이 완료 되었습니다.");
          document.sub1Form.submit();
        }
        ```

## 4. 회원목록조회/수정

## 5. 회원매출조회
