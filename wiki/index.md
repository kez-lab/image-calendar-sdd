# Wiki Index

Updated: 2026-04-28

## Context

- [Project Overview](00-context/project-overview.md): 로컬 전용 개인 이미지 캘린더 앱의 방향성과 핵심 제약.
- [Current State](00-context/current-state.md): 현재 저장소와 문서 구축 상태.
- [Open Questions](00-context/open-questions.md): 제품/디자인/기술에서 아직 확정되지 않은 질문.
- [Glossary](00-context/glossary.md): 프로젝트 용어 정의.

## Product

- [PRD](01-product/prd.md): 제품 요구사항 원본 합의 문서.
- [MVP Scope](01-product/mvp-scope.md): MVP 포함/제외 범위.
- [User Personas](01-product/user-personas.md): 주요 사용자 유형.
- [User Flows](01-product/user-flows.md): 핵심 사용자 흐름.
- [Feature Map](01-product/feature-map.md): 기능 영역과 SDD 분해 후보.

## Design

- [Claude Design Brief](02-design/claude-design-brief.md): Claude Design에 전달할 디자인 프롬프트.
- [Design Output Review](02-design/design-output-review-2026-04-28.md): 첫 Claude Design 결과물 평가와 수정 요청.
- [Design Output Review v2](02-design/design-output-review-2026-04-28-v2.md): v2 디자인 결과물 평가와 구현 채택 기준.
- [Design System](02-design/design-system.md): 첫 디자인 결과물에서 추출한 색상/타입/형태 토큰 초안.
- [External Designer Handoff](02-design/external-designer-handoff.md): 외주 디자이너에게 전달할 수정 요청과 디자인 시스템 요청 범위.
- [Design Principles](02-design/design-principles.md): 톤앤매너와 UX 기준.
- [Screen Inventory](02-design/screen-inventory.md): MVP 화면 목록.
- [Component Guidelines](02-design/component-guidelines.md): UI 컴포넌트 기준.
- [Accessibility](02-design/accessibility.md): 접근성 요구사항.

## Engineering

- [Tech Stack](03-engineering/tech-stack.md): 미정 기술 스택과 선택 기준.
- [Architecture](03-engineering/architecture.md): 로컬 전용 초기 아키텍처 방향.
- [Data Model](03-engineering/data-model.md): 핵심 엔티티 후보.
- [API Contracts](03-engineering/api-contracts.md): 로컬 저장소 계약 초안.
- [Coding Conventions](03-engineering/coding-conventions.md): 개발 규칙 초안.

## Privacy & Security

- [Privacy Model](04-privacy-security/privacy-model.md): 서버 없는 로컬 프라이버시 모델.
- [Local Storage Model](04-privacy-security/local-storage-model.md): 로컬 저장, 백업, 복원, 삭제 정책.
- [Threat Model](04-privacy-security/threat-model.md): 주요 위험과 완화 방향.
- [Data Retention](04-privacy-security/data-retention.md): 데이터 보존/삭제 정책 초안.

## QA

- [Test Strategy](05-qa/test-strategy.md): 테스트 전략 초안.
- [Acceptance Criteria](05-qa/acceptance-criteria.md): MVP 인수 기준.
- [Manual Test Checklist](05-qa/manual-test-checklist.md): 수동 테스트 체크리스트.

## Project

- [Roadmap](06-project/roadmap.md): 프로젝트 단계.
- [Milestones](06-project/milestones.md): 마일스톤 초안.
- [Release Plan](06-project/release-plan.md): 릴리즈 기준.
- [Decision Log](06-project/decision-log.md): 의사결정 기록.
- [Implementation Log](06-project/implementation-log.md): CEO 구현 사이클 결과 기록.

## Meetings

- [Meetings README](08-meetings/README.md): 회의 원문과 회의 wiki의 분리 원칙.
- [Meeting Index](08-meetings/index.md): 회의 시스템과 회의록 목록.
- [Meeting System](08-meetings/meeting-system.md): 서브 에이전트 회의 운영 절차.
- [CEO Orchestration](08-meetings/ceo-orchestration.md): CEO 메인 에이전트가 회의-결정-구현-검증을 반복하는 운영 루프.
- [Meeting Roles](08-meetings/roles.md): 회의 참여 역할 정의.
- [2026-04-28 Product Planning Kickoff](08-meetings/2026-04-28-product-planning-kickoff.md): 로컬 전용 개인 이미지 캘린더 MVP 기획 고도화 회의.
- [2026-04-28 MVP Decision Lock](08-meetings/2026-04-28-mvp-decision-lock.md): 구현 태스크 전환 전 남은 제품/디자인 결정을 확정한 회의.
- [2026-04-28 Tech Implementation Council](08-meetings/2026-04-28-tech-implementation-council.md): 기술 스택과 첫 구현 수직 슬라이스를 확정한 회의.

## LLM Ops

- [Agent Instructions](07-llm/agent-instructions.md): LLM 에이전트 운영 규칙.
- [Context Loading Guide](07-llm/context-loading-guide.md): 작업 시작 시 읽을 문서 순서.
- [SDD Workflow](07-llm/sdd-workflow.md): wiki 기반 SDD 절차.
- [Wiki Maintenance](07-llm/wiki-maintenance.md): ingest/query/lint 운영법.

## Specs

- [Specs README](../specs/README.md): 기능별 SDD 명세 운영 방식.
- [001 Calendar Daily Record](../specs/001-calendar-daily-record/spec.md): 캘린더 기반 하루 기록 조회 spec 초안.
- [002 Photo Entry Creation](../specs/002-photo-entry-creation/spec.md): 사진 기록 작성 spec 초안.
- [003 Archive Search Filter](../specs/003-archive-search-filter/spec.md): 아카이브 검색/필터 spec 초안.
- [004 Local Backup Restore](../specs/004-local-backup-restore/README.md): 백업 파일 내보내기 우선 후보 spec.
- [005 Settings Data Safety](../specs/005-settings-data-safety/spec.md): 설정과 데이터 안전 spec 초안.

## Raw Sources

- [LLM Wiki Pattern](../raw/sources/0001-llm-wiki-pattern.md)
- [Claude Design Prompt](../raw/sources/0002-claude-design-prompt.md)
- [App Image Calendar Design Raw](../raw/design/2026-04-28-app-image-calendar/README.md)
- [App Image Calendar Design v2 Raw](../raw/design/2026-04-28-app-image-calendar-v2/README.md)
- [Designer Feedback Request](../raw/design/2026-04-28-app-image-calendar/designer-feedback-request.md)
- [Product Planning Kickoff Meeting Raw](../raw/meetings/2026-04-28-product-planning-kickoff/brief.md)
- [MVP Decision Lock Meeting Raw](../raw/meetings/2026-04-28-mvp-decision-lock/brief.md)
- [Tech Implementation Council Meeting Raw](../raw/meetings/2026-04-28-tech-implementation-council/brief.md)
