---
type: qa
status: draft
owner: llm
updated: 2026-05-01
sources:
  - raw/sources/0002-claude-design-prompt.md
  - raw/verification/2026-05-01-day-detail-edit-delete/README.md
---

# Manual Test Checklist

## Onboarding

- 3단계 메시지가 순서대로 이해된다.
- 로컬 저장 메시지가 명확하다.
- 시작 후 Calendar 홈으로 자연스럽게 이동한다.

## Calendar

- 오늘 날짜가 보인다.
- 기록이 있는 날짜가 구분된다.
- 날짜 선택 시 해당 날짜 상세로 이동한다.
- 서버/업로드/공유처럼 보이는 요소가 없다.
- Android CLI layout에서 날짜 셀이 full date, today state, record count를 읽을 수 있다.

## Record Creation

- 첫 구현 슬라이스에서는 갤러리 선택 진입점이 명확하다.
- 카메라 촬영은 후속 기능으로 분리되어 있다.
- 날짜 선택이 쉽다.
- 메모 입력과 감정 태그 선택이 빠르다.
- 저장 버튼이 충분히 크다.
- 로컬 저장 안내가 보인다.
- 저장 후 해당 날짜 Day Detail로 이동한다.
- Calendar로 돌아오면 기록 날짜 marker가 보인다.
- 사진 미선택 저장 시 사진 영역 근처에 inline validation이 표시된다.
- 앱 재시작 후에도 저장한 기록이 유지된다.

## Day Detail

- 여러 기록 카드가 자연스럽게 표시된다.
- 수정/삭제 동작이 명확하다.
- 수정은 날짜, 메모, 감정 태그를 저장하고 사진 교체를 요구하지 않는다.
- 삭제는 되돌릴 수 없는 로컬 삭제 확인 모달을 거친다.
- 삭제 후 해당 날짜는 빈 상태가 되고 Calendar marker가 제거된다.
- 빈 날짜는 빈 상태와 추가 CTA를 제공한다.

## Archive

- 최근 월 우선의 월별 그룹으로 기록을 탐색할 수 있다.
- 감정 태그 필터가 명확하다.
- 검색바 사용 목적이 이해된다.

## Settings

- 로컬 저장 안내가 명확하다.
- 백업 파일 내보내기 설명이 친절하다.
- 가져오기/복원은 후속 기능임이 혼동 없이 이해된다.
- 전체 데이터 삭제는 확인 모달을 거친다.
- 앱 정보가 확인 가능하다.

## Android CLI Weekly Gate

- `./gradlew assembleDebug`가 통과한다.
- 공식 `android run`으로 APK 설치와 앱 실행이 성공한다.
- Calendar, Add, Archive, Settings, validation 화면을 `android layout`과 `android screen capture`로 저장한다.
- 캡처 결과가 `raw/verification/{date-topic}/assets/`에 raw evidence로 보관된다.
- `app/src/main`과 Gradle 파일에 `INTERNET`, login, upload, sync, cloud, share, social/feed/like/comment/follow 표현이 없다.
- 핵심 액션은 좌표 의존 없이 layout semantics 또는 안정적인 test tag로 식별 가능하다.
