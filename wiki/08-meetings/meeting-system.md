---
type: llm
status: active
owner: llm
updated: 2026-04-28
sources:
  - raw/sources/0001-llm-wiki-pattern.md
---

# Meeting System

## Purpose

LLM 서브 에이전트를 회의 참여자처럼 사용해 제품 기획, 설계 판단, 리스크 검토를 구조화한다. 회의는 원문 보존과 정리된 지식화를 모두 수행한다.

## Pipeline

1. 회의 주제와 결정해야 할 질문을 정의한다.
2. `raw/meetings/{date-topic}/brief.md`를 작성한다.
3. 회의 시점의 관련 wiki 내용을 `context-snapshot.md`로 고정한다.
4. 역할별 에이전트가 독립적으로 1차 의견을 낸다.
5. 1차 의견을 모든 역할에 공유하고 교차 피드백을 받는다.
6. 진행자가 합의, 이견, 결정, 액션 아이템을 정리한다.
7. 원문은 `raw/meetings/`에 보관한다.
8. 최종 회의록은 `wiki/08-meetings/`에 작성한다.
9. 확정 결정만 프로젝트 wiki와 decision log로 승격한다.

## Rounds

### Round 1: Independent Opinion

각 역할은 다른 역할의 의견을 보기 전에 독립적으로 판단한다.

Required output:

- 핵심 판단
- MVP에 반영할 제안
- 제외하거나 미룰 항목
- 가장 큰 리스크
- 결정이 필요한 질문

### Round 2: Cross-Feedback

각 역할은 취합된 Round 1 요약을 보고 보완, 반박, 합의 가능 지점을 제시한다.

Required output:

- 동의하는 의견
- 반대하거나 수정해야 할 의견
- 새로 드러난 리스크
- 최종 결정 제안

## Decision Rules

- 제품 불변성과 충돌하는 의견은 채택하지 않는다.
- MVP 범위를 늘리는 제안은 사용자 가치와 구현 비용을 함께 제시해야 한다.
- 로컬 저장 신뢰를 약화시키는 제안은 기본적으로 거부한다.
- 미정 사항은 억지로 결정하지 않고 open question으로 남긴다.

## Output Rules

- 회의록은 회의 원문을 전부 반복하지 않고 합의와 쟁점을 중심으로 정리한다.
- 원문 발언은 raw에 보존한다.
- 프로젝트 wiki에는 확정된 결정만 반영한다.
- 회의로 생긴 미결 질문은 [Open Questions](../00-context/open-questions.md)에 반영한다.
