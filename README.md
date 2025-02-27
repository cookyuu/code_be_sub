## 도서 관리 시스템 CRUD 과제

## 목차
> #### 1. [프로젝트 실행 방법](#1-프로젝트-실행-방법)
> #### 2. [API 사용법](#2-api-사용법)
> ##### 2.1 [POSTMAN](#21-postman)
> ##### 2.2 [Curl](#22-curl)
> #### 4. [Swagger 문서 접근 방법](#3-swagger-문서-접근-방법)
> #### 5. [기타 주의 사항](#4-기타-주의-사항)

<br>

## 1. 프로젝트 실행 방법
> #### 1. 원하는 폴더에 프로젝트를 클론
> - 명령어 :  `git clone https://github.com/cookyuu/code_be_sub.git`
> #### 2. H2 Database 생성
> Mac 환경
> - Database Path : /Users/{사용자명}/code-local
> - Username : sa
> - password : test
> <img src="https://github.com/user-attachments/assets/c2142808-ecb6-4964-8372-523f9433aeba" width="500" height="300"/>

> #### 3. IDE를 통해 프로젝트 Run

## 2. API 사용법
#### 2.1. POSTMAN
> - 저자 등록
>    - HTTP Method : `POST`
>    - URL : `http://localhost:8080/authors`
>    - Body : `{ "name": "홍길동", "email": "hong9@example.com" }`

> - 저자 수정
>    - HTTP Method : `PUT`
>    - URL : `http://localhost:8080/authors/1`
>    - Body : `{ "name": "홍길동수정", "email": "hong99@example.com" }`

> - 저자 상세조회
>    - HTTP Method : `GET`
>    - URL : `http://localhost:8080/authors/1`

> - 저자 리스트조회
>    - HTTP Method : `GET`
>    - URL : `http://localhost:8080/authors`
>    - RequestParam : `page : 0`, `size : 10`  `<Default>`

> - 저자 삭제
>    - HTTP Method : `DELETE`
>    - URL : `http://localhost:8080/authors/1`

> - 도서 등록
>    - HTTP Method : `POST`
>    - URL : `http://localhost:8080/books`
>    - Body : `{ "title" : "예제 도서", "description" : "도서에 대한 설명", "isbn" : "1234567890", "publication_date" : "2025-06-01", "author_id" : 1 }`

> - 도서 수정
>    - HTTP Method : `PUT`
>    - URL : `http://localhost:8080/books/1`
>    - Body : `{ "title" : "예제 도서 수정", "description" : "도서에 대한 설명 수정", "isbn" : "1345667890", "publication_date" : "2025-05-01", "author_id" : 1 }`

> - 도서 상세조회
>    - HTTP Method : `GET`
>    - URL : `http://localhost:8080/books/1`

> - 도서 리스트조회
>    - HTTP Method : `GET`
>    - URL : `http://localhost:8080/books`
>    - RequestParam : `page : 0`, `size : 10`  `<Default>`

> - 도서 삭제
>    - HTTP Method : `DELETE`
>    - URL : `http://localhost:8080/books/1`

#### 2.2. Curl
> - 저자 등록
>    - `curl -X POST http://localhost:8080/authors -H "Content-Type: application/json" -d '{"name": "홍길동", "email": "hong9@example.com"}'`

> - 저자 수정
>    - `curl -X PUT http://localhost:8080/authors/1 -H "Content-Type: application/json" -d '{"name": "홍길동수정", "email": "hong99@example.com"}'`

> - 저자 상세조회
>    - `curl -X GET http://localhost:8080/authors/1`

> - 저자 리스트조회
>    - `curl -X GET http://localhost:8080/authors`
>    - `curl -X GET http://localhost:8080/authors?page=0&size=10`

> - 저자 삭제
>    - `curl -X DELETE http://localhost:8080/authors/1`

> - 도서 등록
>    - `curl -X POST http://localhost:8080/books -H "Content-Type: application/json" -d '{ "title" : "예제 도서", "description" : "도서에 대한 설명", "isbn" : "1234567890", "publication_date" : "2025-06-01", "author_id" : 1 }'`

> - 도서 수정
>    - `curl -X PUT http://localhost:8080/books/1 -H "Content-Type: application/json" -d '{ "title" : "예제 도서 수정", "description" : "도서에 대한 설명 수정", "isbn" : "1345667890", "publication_date" : "2025-05-01", "author_id" : 1 }'`

> - 도서 상세조회
>    - `curl -X GET http://localhost:8080/books/1`

> - 도서 리스트조회
>    - `curl -X GET http://localhost:8080/books`
>    - `curl -X GET http://localhost:8080/books?page=0&size=10`

> - 도서 삭제
>    - `curl -X DELETE http://localhost:8080/books/1`


## 3. Swagger 문서 접근 방법
> - Swagger 문서 접근 URL : `http://localhost:8080/swagger-ui/index.html`
   
## 4. 기타 주의 사항
> #### - H2 DB 생성 (application.yml 설정 데이터)
>> Windows 환경에서 생성 시 DB 생성 문제
>> - https://nyximos.tistory.com/73 참조
> #### - 리스트 조회 시 사용 데이터 생성 필요
>> 저자 : 저자 등록 API email 값 변경하여 여러 번 호출
>> <br> 도서 : 도서 등록 API isbn 값 변경하여 여러 번 호출
