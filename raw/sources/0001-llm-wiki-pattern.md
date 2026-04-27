# LLM Wiki Pattern

Source type: user-provided concept document  
Imported: 2026-04-27  
Status: immutable source

## Core Idea

LLM Wiki는 RAG처럼 매번 원천 문서 조각을 다시 찾아 답하는 방식이 아니라, LLM이 지속적으로 유지하는 구조화된 Markdown wiki를 프로젝트의 중간 지식 계층으로 두는 패턴이다.

원천 자료를 추가하면 LLM은 단순 인덱싱에 그치지 않고, 내용을 읽고 기존 wiki에 통합한다. 엔티티 페이지, 개념 페이지, 요약, 비교, 충돌 사항, 최신 합의를 갱신한다. 핵심은 wiki가 계속 축적되는 영속적 산출물이라는 점이다.

## Architecture

세 계층으로 구성한다.

- Raw sources: 원천 자료. 불변이며 LLM은 읽기만 한다.
- Wiki: LLM이 생성하고 유지하는 Markdown 지식 베이스.
- Schema: LLM에게 wiki 구조, 규칙, 워크플로우를 알려주는 설정 문서. Codex에서는 `AGENTS.md`를 사용한다.

## Operations

### Ingest

새 자료를 raw collection에 추가하고 LLM이 읽는다. LLM은 요약 페이지를 만들고, index를 갱신하고, 관련 엔티티/개념 페이지를 업데이트하고, log에 기록한다.

### Query

질문을 받으면 LLM은 wiki index를 먼저 읽고 관련 페이지를 찾아 종합한다. 좋은 답변은 다시 wiki에 저장해 지식이 누적되게 한다.

### Lint

주기적으로 wiki를 점검한다. 충돌, stale claim, orphan page, 누락된 cross-reference, 데이터 공백, 추가 조사가 필요한 질문을 찾는다.

## Indexing and Logging

- `index.md`: 콘텐츠 중심 카탈로그. 모든 wiki 페이지의 링크와 한 줄 요약을 유지한다.
- `log.md`: 시간순 append-only 기록. ingest, query, lint, 결정 사항을 남긴다.

## Project Adaptation

이 저장소에서는 LLM Wiki를 소프트웨어 제품 개발용 세컨 브레인으로 사용한다. 제품, 디자인, 엔지니어링, 프라이버시/보안, QA, 프로젝트 관리, LLM 운영 문서를 팀별 영역처럼 분리한다.

LLM Wiki는 SDD보다 상위 계층이다. Spec Kit 명세는 wiki의 합의 내용을 기능 단위로 실행 가능한 계약서로 변환한 결과물이다.
