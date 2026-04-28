# Round 2 Feedback

Date: 2026-04-29

## Product Lead

- MVP 관점에서 placeholder 제거와 실제 핵심 루프 완성이 최우선이라는 데 동의한다.
- 최종 P0는 Room persistence, 사진 저장 fixture, 저장 후 Day Detail 확인, Calendar marker 반영이다.
- 그 다음 P0는 Archive/Settings placeholder 제거다.
- P1 accessibility semantics/contentDescription/testTag는 화면 구현과 동시에 넣어야 한다.
- Android CLI smoke test를 release gate로 두는 데 동의한다.

## UX/Design Reviewer

- P0는 예쁜 화면 보강이 아니라 핵심 기록 루프가 실제로 저장되고 다시 보이는 신뢰를 만드는 것이다.
- 최종 순서는 Room persistence와 사진 저장 fixture, 저장 후 Day Detail 이동, Calendar marker 반영, Archive/Settings placeholder 제거가 맞다.
- Archive/Settings는 최소 UI라도 즉시 제품 언어로 교체해야 한다.
- Bottom nav 문자 아이콘, 내부 개발 문구, Add/Archive 구분성, validation 위치는 즉시 수정 대상이다.

## Android QA/Accessibility Reviewer

- P0는 핵심 루프를 실제 저장소 기준으로 검증 가능하게 만드는 것이다.
- Room persistence와 QA fixture 없이는 Day Detail/Archive/Settings 검증이 좌표 기반 임시 확인에 머문다.
- Archive/Settings placeholder 제거도 MVP 신뢰를 위해 P0다.
- Accessibility는 나중 작업이 아니라 구현 중 함께 들어가야 한다.
- 최종 순서는 Room + fixture, 핵심 루프 E2E, Day Detail 수정/삭제, Archive/Settings MVP, 접근성/테스트 태그 전면 적용이다.

## Engineering Implementation Reviewer

- Engineering 기준 P0는 Room persistence + repository + QA fixture다.
- 이 기반이 없으면 핵심 루프, Archive, Settings, smoke test가 모두 in-memory 검증에 머문다.
- Archive/Settings placeholder 제거가 P0라는 점에는 동의하지만 persistence보다 먼저 하면 다시 갈아엎을 가능성이 높다.
- Add validation UI 개선은 UX 품질상 필요하지만, 저장 루프 안정화 이후 P1로 둔다.
- 가장 큰 기술 리스크는 파일 복사와 DB insert 사이의 원자성이다.
