---
type: llm
status: active
owner: llm
updated: 2026-04-27
sources:
  - raw/sources/0001-llm-wiki-pattern.md
---

# SDD Workflow

## Principle

SDD는 wiki의 현재 합의를 기능별 실행 계약으로 변환하는 과정이다.

## Workflow

1. Wiki context를 로드한다.
2. 기능 범위를 하나로 좁힌다.
3. `specs/{number}-{feature}/spec.md`를 작성한다.
4. 사용자 가치, functional requirements, acceptance criteria를 명확히 한다.
5. `plan.md`에서 기술 접근, 데이터 모델 영향, 위험을 정리한다.
6. `tasks.md`에서 구현 가능한 작업 단위로 나눈다.
7. 구현한다.
8. 테스트하고 결과를 wiki log와 QA 문서에 반영한다.

## First Specs

- `001-calendar-daily-record`
- `002-photo-entry-creation`
- `003-archive-search-filter`
- `004-local-backup-restore`
- `005-settings-data-safety`

## Guardrails

- wiki에 없는 큰 요구사항을 spec에서 새로 발명하지 않는다.
- 로컬 저장 정책과 충돌하는 spec은 작성하지 않는다.
- 서버, 로그인, 공개 피드, 친구 공유를 암시하는 모델이나 UI를 추가하지 않는다.
- 미정 사항은 spec에서 가정으로 표시하고 `open-questions.md`에도 반영한다.
