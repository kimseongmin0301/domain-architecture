도메인 기반 계층구초 프로토타입
- domain : admin, order ... 각각 하나의 도메인
- global : 공통 유틸, 예외, 설정 등을 관리

- 공통 반환 타입을 구현 global-common-ResponseDto.java

```
com.example.proto
├── domain
│   ├── admin
│   │   ├── controller
│   │   ├── domain
│   │   │   └── Admin.java
│   │   ├── service
│   │   ├── repository
│   │   └── dto
│   │       └── AdminResDto.java
│   └── order
│      ├── controller
│      ├── domain
│      │   ├── Order.java
│      │   ├── OrderStatus.java
│      ├── service
│      ├── repository
│      └── dto
│          ├── OrderRequestDto.java
│          └── OrderResponseDto.java
└── global
    ├── common
    │   └── ResponseDto.java
    ├── error
    │   ├── NotFoundException.java
    │   └── GlobalExceptionHandler.java
    ├── security
    └── util
```
