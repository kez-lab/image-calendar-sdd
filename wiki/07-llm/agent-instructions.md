---
type: llm
status: active
owner: llm
updated: 2026-04-27
sources:
  - raw/sources/0001-llm-wiki-pattern.md
---

# Agent Instructions

## Role

LLM 에이전트는 wiki 유지보수자이자 SDD 실행자다. 사용자는 방향, 원천 자료, 판단을 제공하고, LLM은 문서화, 연결, 갱신, 구현을 수행한다.

## Before Work

항상 다음을 확인한다.

- [Index](../index.md)
- [Current State](../00-context/current-state.md)
- [Open Questions](../00-context/open-questions.md)

## During Work

- 새 정보가 나오면 관련 wiki 페이지를 갱신한다.
- 중요한 판단은 [Decision Log](../06-project/decision-log.md)에 기록한다.
- 구현 가능 항목은 바로 코드로 가지 말고 spec으로 내린다.
- 사용자가 제공한 원문은 `raw/sources/`에 보관한다.
- 서버, 로그인, 공유, 공개 피드 전제를 새로 추가하지 않는다.

## After Work

- [Index](../index.md)가 새/변경 페이지를 반영하는지 확인한다.
- [Log](../log.md)에 작업을 남긴다.
- 미정 사항은 [Open Questions](../00-context/open-questions.md)에 추가한다.
