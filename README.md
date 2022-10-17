# Artineer 리뉴얼 프로젝트

- 개발 일지 : https://5bong2-develop.tistory.com/178?category=1016445
- IDEA : IntelliJ Ultimate
- FrontEnd : HTML, CSS, JavaScript, jQuery, Thymeleaf
- Template Engine : Thymeleaf
- BackEnd : Spring Boot 2.7.2
- Language : Java 11
- Build : Gradle
- Packaging : Jar
- DB : MySQL
- ORM : JPA
- DevOps : Jenkins, AWS, Docker


### 2022 10 17
- 완료 기능
  - 회원가입
    - input 태그 빈값 검증
  - 로그인
    - 세션에 member정보 저장
  - 글 작성
    - 사진, 파일 첨부 가능
    - 첨부 파일 다운로드 가능
  - 글 보기
    - 서버 기능 구현만 되어있음.
  - 댓글, 대댓글 작성
    - 서버만
- 만들어야 할 기능
  - 회원가입 아이디 중복 체크
  - 비밀번호 확인(회원가입 할 때)
  - 로그인 할 때 ID/PW 틀리면 검증하는 로직 수정
    - 현재 작동하기는 함.
  - 댓글, 대댓글 작성 테스트
    - ajax구현 아직.