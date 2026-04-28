# Meeting Brief: Weekly App Review

Date: 2026-04-29

## Topic

Android CLI 기반 화면 캡처와 동작 검증 결과를 사용해 현재 앱의 개선 우선순위를 정한다.

## Goal

- 실제 연결 기기/에뮬레이터에서 앱이 실행되는지 확인한다.
- Calendar, Add, Archive, Settings, Add validation 화면을 캡처한다.
- 화면별 MVP 적합성과 접근성/검증 가능성을 검토한다.
- 다음 구현 사이클에서 수정할 항목을 P0/P1/P2로 분류한다.

## Inputs

- Android app source under `app/`
- MVP specs under `specs/001` through `specs/005`
- Design reference under `raw/design/2026-04-28-app-image-calendar-v2/`
- Android CLI evidence under `raw/meetings/2026-04-29-weekly-app-review/assets/`

## Participants

- Product Lead
- UX/Design Reviewer
- Android QA/Accessibility Reviewer
- Engineering Implementation Reviewer
- CEO Facilitator

## Decisions Needed

- 현재 구현을 MVP로 볼 수 있는지, 또는 prototype으로 분류할지
- 다음 구현 사이클의 최우선 범위
- Archive/Settings placeholder 허용 여부
- Android CLI smoke test를 release gate로 둘지 여부
- 테스트용 fixture/seed 전략 필요 여부

## Non-Goals

- 이 회의에서 앱 코드를 수정하지 않는다.
- 카메라 촬영, 클라우드 백업, 로그인, 공유, 소셜 기능은 검토 범위에 넣지 않는다.
