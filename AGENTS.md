# AGENTS.md

이 저장소는 LLM Wiki를 장기 기억 계층으로 사용하고, 그 위에 Spec-Driven Development(SDD)를 구축한다. Codex와 다른 LLM 에이전트는 이 파일을 프로젝트 운영 스키마로 따른다.

## Core Model

- `raw/`는 원천 자료다. 사용자가 제공한 기획 메모, 디자인 프롬프트, 리서치, 회의록, 외부 문서를 보관한다.
- `wiki/`는 LLM이 유지하는 영속성 세컨 브레인이다. 원천 자료를 읽고, 구조화하고, 상호 링크하고, 현재 합의를 반영한다.
- `specs/`는 기능별 SDD 실행 명세 영역이다. wiki의 제품/디자인/로컬 데이터/기술 합의를 근거로 작성한다.
- 코드는 최종 산출물이다. 코드와 wiki가 충돌하면 먼저 wiki를 갱신하고, 그 다음 spec과 코드를 조정한다.

## Product Invariants

- 앱은 개인용 이미지 캘린더다.
- 서버, 로그인, 친구 공유, 공개 피드는 없다.
- 모든 사진, 메모, 감정 태그, 설정 데이터는 사용자 디바이스 로컬에 저장된다.
- 사용자가 외부 업로드나 공유를 오해하지 않도록 UI에서 로컬 저장 구조를 명확히 전달한다.
- 백업/복원/전체 삭제는 안전하고 되돌릴 수 없는 동작을 명확히 설명해야 한다.

## Operating Rules

- 작업 시작 시 `wiki/index.md`, `wiki/00-context/current-state.md`, `wiki/00-context/open-questions.md`를 먼저 확인한다.
- 새로운 원천 자료가 들어오면 `raw/sources/`에 보관하고, 관련 wiki 페이지를 갱신하고, `wiki/log.md`에 기록한다.
- 회의를 진행하면 원문은 `raw/meetings/`에 보관하고, 정리된 회의록과 회의 운영 지식은 `wiki/08-meetings/`에 기록한다.
- 단순 답변으로 끝내기 아까운 분석, 결정, 비교표, 설계 판단은 wiki 페이지로 남긴다.
- 구현 가능한 기능은 바로 코드로 가지 말고, 관련 wiki 페이지를 근거로 `specs/`에 명세를 만든 뒤 진행한다.
- 로컬 저장, 데이터 보존, 백업/복원, 삭제, 권한, 외부 업로드 오해 방지 정책은 `wiki/04-privacy-security/`를 먼저 갱신한다.
- 디자인 변경은 `wiki/02-design/`에 반영하고, Claude Design 프롬프트와 확정 디자인 요구사항을 구분한다.
- 결정 사항은 `wiki/06-project/decision-log.md`에 append-only로 기록한다.
- wiki 페이지는 짧고 링크 가능한 단위로 유지한다. 한 파일이 너무 커지면 주제별 페이지로 분리한다.

## Wiki Page Convention

모든 wiki 페이지는 가능하면 아래 frontmatter를 사용한다.

```yaml
---
type: product|design|engineering|privacy|qa|project|llm|context
status: draft|active|superseded
owner: llm
updated: YYYY-MM-DD
sources:
  - raw/sources/example.md
---
```

본문 규칙:

- 링크는 Obsidian 호환 Markdown 링크를 우선한다. 예: wiki 내부에서는 `../01-product/prd.md`, repo 루트에서는 `wiki/01-product/prd.md`를 기준으로 링크한다.
- 확정된 내용과 미정인 내용을 분리한다.
- 추측은 `Assumption` 또는 `가정`으로 표시한다.
- 충돌하는 정보는 덮어쓰지 말고 `Contradictions` 섹션에 남긴다.
- 구현에 영향을 주는 내용은 `Implications for SDD` 섹션에 연결한다.

## Operations

### Ingest

1. 원천 자료를 `raw/sources/`에 추가한다.
2. 자료의 핵심 주장, 요구사항, 제약, 열린 질문을 추출한다.
3. `wiki/index.md`에 관련 페이지를 추가하거나 갱신한다.
4. 영향을 받는 제품/디자인/기술/보안/QA/프로젝트 페이지를 갱신한다.
5. `wiki/log.md`에 `## [YYYY-MM-DD] ingest | Title` 형식으로 기록한다.

### Query

1. `wiki/index.md`에서 관련 페이지를 찾는다.
2. 관련 페이지를 읽고 답한다.
3. 답변이 향후 재사용 가치가 있으면 새 wiki 페이지로 저장하거나 기존 페이지에 반영한다.
4. 변경이 있었다면 `wiki/log.md`에 기록한다.

### Lint

주기적으로 다음을 점검한다.

- 고아 페이지
- 깨진 링크
- 오래된 주장
- 서로 충돌하는 요구사항
- 구현에 필요한데 비어 있는 로컬 데이터 정책
- spec으로 내려가야 하는데 wiki에만 남아 있는 기능

## SDD Workflow

SDD 작업은 아래 순서로 진행한다.

1. `wiki/01-product/`에서 제품 요구사항 확인
2. `wiki/02-design/`에서 화면/컴포넌트/접근성 요구사항 확인
3. `wiki/04-privacy-security/`에서 로컬 저장/백업/삭제 정책 확인
4. `specs/{number}-{feature}/spec.md` 작성
5. `plan.md` 작성
6. `tasks.md` 작성
7. 구현
8. QA 문서와 decision log 갱신

Spec은 wiki의 하위 산출물이다. wiki에 없는 중대한 요구사항을 spec에서 새로 만들지 않는다.

## Team-Like Documentation Areas

- Product: 문제, 사용자, MVP, 기능 범위, 성공 기준
- Design: 화면, 플로우, 컴포넌트, 톤앤매너, 접근성
- Engineering: 기술 스택, 로컬 아키텍처, 데이터 모델, 저장소 계약
- Privacy/Security: 로컬 저장, 백업/복원, 삭제, 데이터 보존, 위협 모델
- QA: 인수 기준, 테스트 전략, 수동 테스트 체크리스트
- Project: 로드맵, 마일스톤, 릴리즈, 의사결정 기록
- Meetings: 회의 시스템, 역할, 회의록, 회의에서 승격할 결정
- LLM Ops: 컨텍스트 로딩, wiki 유지보수, SDD 운영법
