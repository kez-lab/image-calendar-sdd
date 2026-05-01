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

## [2026-04-28] meeting | MVP decision lock

- Source: [Meeting Minutes](08-meetings/2026-04-28-mvp-decision-lock.md)
- Action: 구현 태스크 전환 전 Archive 검색 범위, Calendar 로컬 배지, Settings 부가 항목, 첫 저장 후 이동 위치를 확정.
- Updated: `raw/meetings/2026-04-28-mvp-decision-lock/*`, `wiki/08-meetings/2026-04-28-mvp-decision-lock.md`, `wiki/06-project/decision-log.md`, `wiki/00-context/current-state.md`, `wiki/02-design/design-output-review-2026-04-28-v2.md`, `specs/*`.

## [2026-04-28] meeting | Tech implementation council

- Source: [Meeting Minutes](08-meetings/2026-04-28-tech-implementation-council.md)
- Action: CEO 오케스트레이션 루프를 문서화하고 Android Native 기술 스택, Room/local asset 저장 전략, `001+002` 첫 구현 수직 슬라이스를 확정.
- Updated: `raw/meetings/2026-04-28-tech-implementation-council/*`, `wiki/08-meetings/*`, `wiki/03-engineering/*`, `wiki/06-project/decision-log.md`, `specs/001-calendar-daily-record/*`, `specs/002-photo-entry-creation/*`.

## [2026-04-28] implementation | Android scaffold and local-save prototype

- Source: [Implementation Log](06-project/implementation-log.md)
- Action: Android Gradle/Compose 프로젝트를 생성하고 Calendar/Add/Day Detail 중심의 첫 로컬 저장 prototype을 구현.
- Verification: `./gradlew assembleDebug` passed; static string check found no `INTERNET`, account, upload, sync, sharing, cloud, or social UI terms in `app/src/main`.
- Updated: `app/*`, `build.gradle.kts`, `settings.gradle.kts`, `gradle.properties`, `gradle/*`, `wiki/06-project/implementation-log.md`, `wiki/00-context/current-state.md`, `specs/*/tasks.md`.

## [2026-04-29] llm-ops | Add Android CLI custom skills

- Source: [Android CLI Custom Skills](07-llm/skills/android-cli-custom-skills.md)
- Action: 공식 Android CLI 명령군을 setup, project describe, app run, UI inspect, SDK/emulator, docs/skills, journey smoke test 용도로 나눈 Codex 커스텀 skills를 추가.
- Updated: `.codex/skills/android-*`, `~/.codex/skills/android-*`, `wiki/07-llm/skills/android-cli-custom-skills.md`, `wiki/index.md`.

## [2026-04-29] meeting | Weekly Android app review

- Source: [Meeting Minutes](08-meetings/2026-04-29-weekly-app-review.md)
- Evidence: [Screen Evidence](../raw/meetings/2026-04-29-weekly-app-review/screen-evidence.md)
- Action: 공식 Android CLI로 Calendar/Add/Archive/Settings/validation 화면을 캡처하고, 회의를 통해 현재 앱을 prototype으로 분류한 뒤 다음 P0 범위를 Room persistence, repository, deterministic QA fixture로 확정.
- Updated: `raw/meetings/2026-04-29-weekly-app-review/*`, `wiki/08-meetings/2026-04-29-weekly-app-review.md`, `wiki/06-project/decision-log.md`, `wiki/06-project/implementation-log.md`, `wiki/00-context/current-state.md`, `wiki/05-qa/*`, `specs/*/tasks.md`.

## [2026-04-29] implementation | Room persistence and debug fixture

- Source: [Implementation Log](06-project/implementation-log.md)
- Evidence: [Room Persistence Smoke Verification](../raw/verification/2026-04-29-room-persistence-smoke/README.md)
- Action: Room/KSP, `photo_entries`/`local_assets` schema, repository save contract, debug fixture, repository-backed Compose state, and Android CLI persistence verification were added.
- Updated: `app/*`, `app/schemas/*`, `raw/verification/2026-04-29-room-persistence-smoke/README.md`, `wiki/03-engineering/*`, `wiki/06-project/implementation-log.md`, `wiki/00-context/current-state.md`, `specs/001-calendar-daily-record/tasks.md`, `specs/002-photo-entry-creation/tasks.md`.

## [2026-04-29] verification | Overall behavior and design sync review

- Source: [Verification Report](05-qa/verification-report-2026-04-29.md)
- Evidence: [Overall Behavior and Design Review](../raw/verification/2026-04-29-overall-behavior-design-review/README.md)
- Action: Calendar, Day Detail, Add, Add validation, Archive, and Settings were captured with screenshot/layout/annotated evidence; design v2 sync was reviewed and release blockers were recorded.
- Updated: `raw/verification/2026-04-29-overall-behavior-design-review/*`, `wiki/05-qa/verification-report-2026-04-29.md`, `wiki/index.md`, `wiki/log.md`, `raw/README.md`.

## [2026-05-01] implementation | Day Detail edit/delete lifecycle

- Source: [Implementation Log](06-project/implementation-log.md)
- Evidence: [Day Detail Edit/Delete Verification](../raw/verification/2026-05-01-day-detail-edit-delete/README.md)
- Action: Day Detail metadata edit, destructive delete confirmation, repository update/delete operations, and local image cleanup invocation were added and verified with official Android CLI evidence.
- Updated: `app/*`, `raw/verification/2026-05-01-day-detail-edit-delete/*`, `wiki/05-qa/verification-report-2026-05-01.md`, `wiki/03-engineering/api-contracts.md`, `wiki/06-project/implementation-log.md`, `wiki/06-project/decision-log.md`, `wiki/00-context/current-state.md`, `specs/001-calendar-daily-record/tasks.md`, `specs/002-photo-entry-creation/tasks.md`, `README.md`.

## [2026-05-01] planning | Archive next work plan

- Source: [Next Work Plan 2026-05-01](06-project/next-work-plan-2026-05-01.md)
- Action: Archive MVP scope, date query formats, navigation target, empty state copy, external manpower assessment, and implementation TODO were locked before coding.
- Updated: `wiki/06-project/next-work-plan-2026-05-01.md`, `wiki/00-context/open-questions.md`, `wiki/index.md`, `wiki/log.md`, `specs/003-archive-search-filter/*`.
