# Meeting Brief: Web Feasibility Council

Date: 2026-05-01

## Topic

로컬 전용 개인 이미지 캘린더를 웹으로도 만들 수 있는지 검토한다.

## Goal

- 웹 버전의 제품 가치와 우선순위를 판단한다.
- 모바일 디자인을 웹/PWA로 확장할 때 필요한 UX 변경을 판단한다.
- 서버 없이 웹에서 사진, 메모, 감정 태그를 로컬 저장할 수 있는지 검토한다.
- 웹 로컬 저장의 데이터 보존, 프라이버시, QA 리스크를 판단한다.
- CEO 결론안을 확정한다.

## Inputs

- `wiki/01-product/prd.md`
- `wiki/02-design/design-system.md`
- `wiki/03-engineering/architecture.md`
- `wiki/04-privacy-security/local-storage-model.md`
- `raw/sources/0003-web-storage-reference.md`

## Participants

- Product Planner
- Design Lead
- Web/Platform Engineer
- QA & Privacy Lead
- CEO Facilitator

## Decisions Needed

- 웹 버전을 만들 수 있는가.
- 웹을 Android 대체 앱, companion, prototype, backup viewer 중 무엇으로 정의할 것인가.
- 웹의 로컬 저장 문구와 사용자 신뢰 UX는 어떻게 달라져야 하는가.
- 지금 구현에 착수할지, feasibility spike로 둘지 판단한다.
- public release 전 blocker를 정한다.

## Non-Goals

- 이 회의에서 웹 코드를 구현하지 않는다.
- 서버, 로그인, 동기화, 친구 공유, 공개 피드는 검토 범위에 넣지 않는다.
- Android MVP 안정화 우선순위를 뒤집지 않는다.
