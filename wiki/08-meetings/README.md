---
type: project
status: active
owner: llm
updated: 2026-04-28
sources: []
---

# Meetings

회의는 프로젝트 지식을 생산하는 별도 프로세스다. 원문은 `raw/meetings/`에 보관하고, 정리된 회의록과 회의 시스템 지식은 `wiki/08-meetings/`에 보관한다.

## Core Rule

회의 발언은 확정된 제품 지식이 아니다. 회의록에서 합의된 결정만 프로젝트 wiki로 승격한다.

## Storage Model

- `raw/meetings/{date-topic}/brief.md`: 회의 요청과 목적
- `raw/meetings/{date-topic}/context-snapshot.md`: 회의 시점의 입력 컨텍스트
- `raw/meetings/{date-topic}/round-1-opinions.md`: 역할별 1차 의견 원문
- `raw/meetings/{date-topic}/round-2-feedback.md`: 교차 피드백 원문
- `raw/meetings/{date-topic}/final-notes.md`: 최종 정리 전 원자료 요약
- `wiki/08-meetings/{date-topic}.md`: 정리된 회의록

## Promotion Targets

회의 후 확정된 내용은 필요에 따라 아래 파일로 승격한다.

- [Decision Log](../06-project/decision-log.md)
- [Open Questions](../00-context/open-questions.md)
- [PRD](../01-product/prd.md)
- [MVP Scope](../01-product/mvp-scope.md)
- [Feature Map](../01-product/feature-map.md)
- [Local Storage Model](../04-privacy-security/local-storage-model.md)
