---
type: design
status: active
owner: llm
updated: 2026-04-28
sources:
  - raw/design/2026-04-28-app-image-calendar/designer-feedback-request.md
  - wiki/02-design/design-output-review-2026-04-28.md
---

# External Designer Handoff

## Position

외주 디자이너가 있는 경우 Codex는 최종 시각 디자인을 대체하지 않는다. Codex의 역할은 제품 요구사항, 회의 결정, 구현 제약, 디자인 시스템 요구사항을 정리해 디자이너에게 전달하는 것이다.

## Handoff Source

전달 문안:

- [Designer Feedback Request](../../raw/design/2026-04-28-app-image-calendar/designer-feedback-request.md)

## What To Ask From Designer

- v2 6개 주요 화면
- 수정 요청 반영
- 디자인 시스템 페이지
- 컴포넌트별 상태 정의
- 색상/타이포/spacing/radius 토큰
- 접근성 확인
- Figma 링크 또는 편집 가능한 원본

## Must-Fix Before Implementation

- Onboarding CTA: `첫 기록 만들기`, `건너뛰고 캘린더 보기`
- Calendar marker: 점/미니 스택 우선
- Copy: `업로드`, `동기화`, `클라우드`, `공유`, `게시`, `피드` 회피
- Add Record: 메모/감정 태그 선택 입력으로 표현
- Settings: 백업 내보내기 우선, 가져오기/복원 낮은 위계
- Delete confirmation modal 추가

## Design System Required

디자이너에게 반드시 요청할 디자인 시스템 범위:

- Color tokens
- Typography scale
- Spacing scale
- Radius scale
- Buttons
- Bottom tab bar
- Calendar/date marker
- Local storage badge/trust card
- Image record card
- Emotion chip
- Memo input
- Search bar
- Archive month group
- Empty state
- Settings row
- Danger action/modal

## Codex Role After v2

v2가 도착하면 Codex는 다음을 수행한다.

1. 원본을 `raw/design/{date-topic}`에 저장한다.
2. 디자인 변경 요약을 `wiki/02-design`에 반영한다.
3. 확정 토큰을 [Design System](design-system.md)에 반영한다.
4. 화면별 구현 요구사항을 `specs/`에 연결한다.
5. 디자인과 PRD가 충돌하면 회의 또는 open question으로 올린다.
