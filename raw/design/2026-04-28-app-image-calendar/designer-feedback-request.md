# Designer Feedback Request: App Image Calendar v2

Date: 2026-04-28  
Source: 2026-04-28 App Image Calendar design output  
Purpose: v2 디자인 수정 요청 및 디자인 시스템 산출물 요청

## 전체 평가

현재 디자인은 전체 방향이 좋습니다. 따뜻한 아이보리/라벤더 계열, 둥근 카드, 넉넉한 여백, 로컬 저장 trust card, 6개 주요 화면 구성은 제품 방향과 잘 맞습니다.

다만 MVP 기획 회의에서 확정된 몇 가지와 다르게 보이는 부분이 있어 v2에서 수정이 필요합니다.

## 제품 핵심 기준

- 제품 약속: `내 폰 안에만 쌓이는 하루 사진 캘린더`
- 서버 없음
- 로그인 없음
- 친구 공유 없음
- 공개 피드 없음
- 모든 기록은 디바이스 로컬 저장
- 기록 작성은 30초 안에 끝나는 흐름
- 사진은 필수, 메모와 감정 태그는 선택 입력
- 백업은 `내보내기` 우선, 가져오기/복원은 후속 기능 또는 낮은 위계

## 수정 요청

### 1. Onboarding CTA

현재:

- 메인 버튼: `시작하기`
- 보조 버튼: `이미 데이터가 있어요 →`

수정 요청:

- 메인 버튼을 `첫 기록 만들기`로 변경해주세요.
- 보조 버튼을 `건너뛰고 캘린더 보기`로 변경해주세요.
- `이미 데이터가 있어요`는 가져오기/복원을 암시하므로 이번 MVP에서는 제거해주세요.

### 2. Calendar Marker

현재:

- 기록이 있는 날짜가 작은 이미지 썸네일로 표시됩니다.
- 온보딩에도 “캘린더 위에 썸네일”이라는 표현이 있습니다.

수정 요청:

- MVP에서는 날짜 셀에 사진 썸네일 대신 `점` 또는 `미니 스택`을 기본으로 사용해주세요.
- 날짜 숫자의 가독성을 우선해주세요.
- 사진 썸네일은 Day Detail과 Archive에서 중심적으로 보여주세요.

### 3. Copy Guardrails

현재:

- `서버 업로드 없이`, `외부 서버로 전송되지 않아요` 문구가 있습니다.

수정 요청:

- 핵심 UI에서는 `업로드`, `동기화`, `클라우드`, `공유`, `게시`, `피드` 표현을 피해주세요.
- 추천 문구:
  - `이 기기에만 저장돼요`
  - `서버로 보내지 않아요`
  - `계정 없이 사용할 수 있어요`
  - `내 폰에만 저장됨`

### 4. Add Record Optionality

현재:

- 감정 태그가 기본 선택되어 있어 필수처럼 보일 수 있습니다.

수정 요청:

- 사진만 필수 입력처럼 보여주세요.
- 메모와 감정 태그는 선택 입력으로 보이게 해주세요.
- 감정 태그는 기본 선택 없음 또는 `선택 안 함` 상태를 제공해주세요.

### 5. Settings Data Management

현재:

- `데이터 내보내기`와 `데이터 가져오기`가 같은 위계로 보입니다.

수정 요청:

- MVP에서는 `백업 파일 내보내기`를 우선 CTA로 보여주세요.
- `가져오기/복원`은 후속 기능, 준비 중, 또는 낮은 위계의 안내로 표현해주세요.
- 전체 데이터 삭제는 별도 위험 영역으로 분리해주세요.

### 6. Delete Confirmation

현재:

- 전체 데이터 삭제 row는 있으나 삭제 확인 모달 디자인이 필요합니다.

수정 요청:

- 개별 기록 삭제 확인 모달
- 전체 데이터 삭제 확인 모달
- 전체 삭제는 “백업이 없으면 복구할 수 없음”을 명확히 보여주세요.

## 추가 요청: 디자인 시스템 산출물

v2와 함께 아래 디자인 시스템 문서를 별도로 제공해주세요.

### Color Tokens

필수:

- Primary background
- Secondary background
- Card background
- Border/divider
- Primary text
- Secondary text
- Muted text
- Primary accent
- Accent light background
- Local-safe/success color
- Warning/danger color
- Disabled state

각 토큰은 이름, HEX 값, 사용처를 포함해주세요.

### Typography

필수:

- Display/title font
- Body font
- Caption font
- Font size scale
- Line height
- Weight
- Android 적용 시 대체 폰트 또는 사용 권장 방식

### Spacing and Radius

필수:

- 기본 spacing scale
- 화면 horizontal padding
- Card radius
- Image radius
- Chip radius
- Button radius
- Modal radius

### Components

아래 컴포넌트별 상태와 사용 규칙을 정리해주세요.

- Bottom tab bar
- Primary button
- Secondary/text button
- Floating Add Button
- Monthly calendar
- Date marker: none / dot / mini stack / today
- Local storage badge
- Local storage trust card
- Image record card
- Emotion tag chip: default / selected / disabled
- Memo input
- Search bar
- Archive month group
- Empty state
- Settings row
- Backup export button
- Danger button
- Delete confirmation modal

### States

필수 상태:

- Empty
- Loading
- Error
- Disabled
- Selected
- Danger/destructive
- Success/saved

### Accessibility

확인 요청:

- 주요 텍스트 대비
- 버튼 터치 영역
- danger action 구분
- 색상만으로 상태를 전달하지 않는지

## 산출물 형태

가능하면 아래 형태로 전달해주세요.

- 6개 주요 화면 v2
- 디자인 시스템 페이지
- 컴포넌트 페이지
- 사용된 색상/폰트/spacing/radius 토큰
- 수정 전후 변경 요약
