# Spring Boot 게시판 웹사이트 프로젝트

## 프로젝트 개요
이 프로젝트는 사용자가 자신의 정보를 드러낼 필요 없이 의견을 교환할 수 있는 간단한 게시판 웹사이트입니다. 사용자는 게시글을 작성, 수정, 삭제할 수 있으며 댓글 기능을 통해 소통할 수 있습니다. 또한, 해시태그와 검색 기능을 통해 게시글을 쉽게 찾을 수 있습니다.

## 주요 기능

### 필수 과제 기능
1. **게시판 기능**
   - 게시판 목록 보기
   - 특정 게시판에 속한 게시글 목록 보기
   - 전체 게시판 보기 기능
   - 자유 게시판, 개발 게시판, 일상 게시판, 사건사고 게시판 등 다양한 주제의 게시판 제공

2. **게시글 기능**
   - 게시글 작성 및 게시판 선택
   - 게시글 조회 (단일 게시글 페이지)
   - 게시글 수정 (비밀번호 확인 후 수정 가능)
   - 게시글 삭제 (비밀번호 확인 후 삭제 가능)
   - 게시글 목록에서 최신 게시글이 최상단에 위치

3. **댓글 기능**
   - 댓글 작성 (비밀번호 확인 후 작성 가능)
   - 댓글 목록 조회
   - 댓글 삭제 (비밀번호 확인 후 삭제 가능)

### 도전 과제 기능
1. **게시글 추가 기능**
   - 이전글, 다음글 기능: 현재 게시글의 이전/다음 게시글로 이동

2. **해시태그 기능**
   - 게시글 작성 시 해시태그 자동 추출
   - 해시태그별 게시글 목록 조회
   - 게시글 페이지에 해시태그 목록 표시 및 해시태그를 클릭해 관련 게시글 조회

3. **검색 기능**
   - 게시글 제목 및 내용 검색 기능
   - 선택한 게시판 내 검색 또는 전체 게시글 검색 가능

## 프로젝트 실행 방법

1. **프로젝트 클론**  
   먼저, 해당 프로젝트를 로컬 환경으로 클론합니다.

2. **IntelliJ IDEA에서 프로젝트 열기**  
   클론 받은 프로젝트 폴더를 IntelliJ IDEA에서 엽니다.

3. **애플리케이션 실행**  
   `KtechSpringbootProjectApplication.java` 파일에서 `main` 메서드를 실행하여 애플리케이션을 시작합니다.

## 사용 기술 스택
- **Java 8** (JDK 17.0.9)
- **HTML, CSS**
- **Spring Framework**
- **JPA Framework**
- **SQLite DataBase**
