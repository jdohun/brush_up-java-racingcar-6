# 구현 목록 설계

## 도메인
### model
- ### CarName
  - 멤버
    - `String name`

  - 메소드
    - 생성 전 파라미터 검증
      - [x] 전달 받은 이름이 `null` 이면 예외 처리한다.
      - [x] 전달 받은 이름이 `빈 문자열`이면 `IllegalArgumentException` 예외 처리한다.
      - [x] 전달 받은 이름의 길이가 5 를 초과하면 `IllegalArgumentException` 예외 처리한다.
    - [x] 저장한 이름을 문자열로 반환한다.

- ### CarLocation
  - 멤버
    - `int currentLocation` : 현재 위치 (생성 시 0으로 초기화)

  - 메소드
    - [x] +1 전진한다.
    - [x] 현재 위치를 하이픈(-)을 이용한 문자열로 반환한다.
    - [x] 비교 후 더 큰 값을 반환한다.
    - [x] equals(), hashCode() Override : 우승자 기록과 비교하기 위한

- ### RandomMovingStrategy
  - 멤버
    - `int START_OF_RANDOM_RANGE` : 랜덤 숫자의 시작
    - `int END_OF_RANDOM_RANGE` : 랜덤 숫자의 마지막
    - `int PASSING_CRITERIA` : 랜덤 숫자의 통과 기준
    - `IntSupplier DEFAULT_RANDOM_NUMBER_GENERATOR` : 기본 생성 시 랜덤 숫자 생성기 
    - `IntSupplier randomNumberGenerator` : 주입된 랜덤 숫자 생성기(테스트 및 용도 변환용)

  - 메소드
    - [x] 기본 랜덤 생성기를 통해 생성한다.
    - [x] 주입된 랜덤 생성기를 통해 생성한다.
    - [x] 통과 기준을 충족하는지 반환한다.
      - [x] 생성된 값이 범위를 벗어나면 `IllegalArgumentException` 예외 처리한다.

- ### Car
  - 멤버
    - `CarName name`
    - `CarLocation currentLocation` : 현재 위치 (생성 시 0으로 초기화)

  - 메소드
    - 전진 시도
      - [x] 0에서 9까지의 정수 중 한 개의 정수를 반환한다.
      - [x] 반환된 값이 4 이상일 경우 `+1` 전진한다.
    - [x] 현재 위치를 `-`의 개수로 표현하며 이름과 같이 반환한다.
      - (e.g. `pobi : -` )

- ### LineUp
  - 멤버
    - `List<Car> cars`

  - 메소드
    - 주입 받은 이름들을 통해 자동차 객체를 생성하여 저장한다. 
      - 생성 전 파라미터 검증
        - [x] 전달 받은 리스트가 `null` 이면 예외 처리한다.
        - [x] 전달 받은 리스트가 `빈 리스트`이면 `IllegalArgumentException` 예외 처리한다.
    - [x] 저장된 자동차 객체들을 모두 전진 시도한다.
    - [x] 저장된 자동차 객체들의 이름과 현재 위치 반환 값을 반환한다.
    - [x] 우승기록을 가진 모든 자동차를 반환한다.
      - [x] 가장 많이 전진한 자동차를 반환한다.
      - [x] 같은 기록을 가진 자동차를 모두 반환한다. 

---
## 어플리케이션 서비스
### 비즈니스 (Handler)
- ### InputHandler
  - ### InputNames
  - 입력받은 자동차 이름의 형식을 검증한다.
    - [x] null 을 입력한다.
    - [x] 빈 값(공백) 을 입력한다.
    - [x] 0회 이상의 공백 이후 쉼표(,)로 시작한다.
    - [x] 쉼표(,) 사이에 0회 이상의 공백만 존재한다
    - [x] 쉼표(,) 이후 0회 이상의 공백으로 끝난다.  
      - 위 조건에 해당하면 `IllegalArgumentException` 예외 처리한다.

  - ### InputNumberOfAttempts
  - 이동 시도 횟수의 형식을 검증한다.
    - [x] null 을 입력한다.
    - [x] 빈 값(공백) 을 입력한다. 
    - [x] 정수가 아니다.
      - 위 조건에 해당하면 `IllegalArgumentException` 예외 처리한다.

### UI (View)
- ### InputView
    - [x] n대 이상의 자동차 이름을 입력 받는다.
    - [x] 이동 시도 횟수를 입력 받는다.

- ### OutputView
  - [x] 시도별 결과를 출력한다.
  - [x] 가장 많이 전진한 우승자를 출력한다.