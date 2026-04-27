# Wiki Log

## [2026-04-27] ingest | LLM Wiki pattern

- Source: [raw/sources/0001-llm-wiki-pattern.md](../raw/sources/0001-llm-wiki-pattern.md)
- Action: 프로젝트용 LLM Wiki 운영 모델로 정규화.
- Updated: `AGENTS.md`, `wiki/README.md`, `wiki/index.md`, `wiki/07-llm/*`.

## [2026-04-27] ingest | Local-only image calendar prompt

- Source: [raw/sources/0002-claude-design-prompt.md](../raw/sources/0002-claude-design-prompt.md)
- Action: 제품 방향을 서버 없는 개인 이미지 캘린더로 정규화하고, SNS/친구 공유/로그인 전제를 제거.
- Updated: `wiki/00-context/*`, `wiki/01-product/*`, `wiki/02-design/*`, `wiki/03-engineering/*`, `wiki/04-privacy-security/*`, `wiki/05-qa/*`.

## [2026-04-27] sdd | Replace sharing specs with local-only feature specs

- Source: [Feature Map](01-product/feature-map.md)
- Action: 초기 SDD 후보를 캘린더, 기록 작성, 아카이브, 백업/복원, 설정/데이터 안전으로 재구성.
- Updated: `specs/001-calendar-daily-record/*`, `specs/002-photo-entry-creation/README.md`, `specs/003-archive-search-filter/README.md`, `specs/004-local-backup-restore/README.md`, `specs/005-settings-data-safety/README.md`.

## [2026-04-28] meeting-system | Add raw/wiki meeting pipeline

- Source: [Meeting Brief](../raw/meetings/2026-04-28-product-planning-kickoff/brief.md)
- Action: 회의 원문은 `raw/meetings`, 정리된 회의 지식은 `wiki/08-meetings`에 저장하는 운영 구조를 추가.
- Updated: `AGENTS.md`, `raw/README.md`, `wiki/README.md`, `wiki/index.md`, `wiki/08-meetings/*`.

## [2026-04-28] meeting | Product planning kickoff

- Source: [Meeting Minutes](08-meetings/2026-04-28-product-planning-kickoff.md)
- Action: 기획자 회의를 통해 MVP 타깃, 제품 약속, 첫 사용 흐름, 캘린더 표시, 아카이브 기본값, 백업 범위를 결정.
- Updated: `raw/meetings/2026-04-28-product-planning-kickoff/*`, `wiki/08-meetings/2026-04-28-product-planning-kickoff.md`, `wiki/01-product/*`, `wiki/02-design/*`, `wiki/04-privacy-security/*`, `wiki/06-project/decision-log.md`.

## [2026-04-28] ingest | App Image Calendar design output

- Source: [Raw Design](../raw/design/2026-04-28-app-image-calendar/README.md)
- Action: Claude Design 결과물을 `raw/design`에 보관하고, 채택할 디자인 방향과 구현 전 수정 요구사항을 design wiki에 정리.
- Updated: `raw/design/2026-04-28-app-image-calendar/*`, `wiki/02-design/design-output-review-2026-04-28.md`, `wiki/02-design/design-system.md`, `wiki/index.md`.

## [2026-04-28] design-handoff | External designer v2 request

- Source: [Designer Feedback Request](../raw/design/2026-04-28-app-image-calendar/designer-feedback-request.md)
- Action: 외주 디자이너에게 전달할 v2 수정 요청과 디자인 시스템 산출물 요구사항을 정리.
- Updated: `raw/design/2026-04-28-app-image-calendar/designer-feedback-request.md`, `wiki/02-design/external-designer-handoff.md`, `wiki/02-design/design-system.md`, `wiki/index.md`.

## [2026-04-28] ingest | App Image Calendar design output v2

- Source: [Raw Design v2](../raw/design/2026-04-28-app-image-calendar-v2/README.md)
- Action: v2 디자인과 디자인 시스템 산출물을 보관하고, 구현 기준으로 채택 가능한 항목과 잔여 결정 사항을 정리.
- Updated: `raw/design/2026-04-28-app-image-calendar-v2/*`, `wiki/02-design/design-output-review-2026-04-28-v2.md`, `wiki/02-design/design-system.md`, `wiki/02-design/screen-inventory.md`, `wiki/index.md`.
