---
type: llm
status: active
owner: llm
updated: 2026-04-27
sources:
  - raw/sources/0001-llm-wiki-pattern.md
---

# Wiki Maintenance

## Ingest Checklist

- raw source를 추가했는가?
- source에서 요구사항, 제약, 결정, 열린 질문을 추출했는가?
- 관련 wiki 페이지를 갱신했는가?
- index를 갱신했는가?
- log에 기록했는가?

## Query Checklist

- index를 먼저 읽었는가?
- 답변에 사용한 wiki 페이지를 확인했는가?
- 재사용 가치가 있는 답변을 wiki에 반영했는가?
- 새 결정 또는 열린 질문을 기록했는가?

## Lint Checklist

- 깨진 링크가 있는가?
- index에 누락된 페이지가 있는가?
- open questions가 오래 방치되었는가?
- local storage 문서와 product 문서가 충돌하는가?
- 서버/로그인/공유 전제가 실수로 들어갔는가?
- specs로 내려가야 할 요구사항이 wiki에만 남아 있는가?

## Log Format

`wiki/log.md`는 append-only로 유지한다.

```md
## [YYYY-MM-DD] ingest | Title

- Source: path
- Action: summary
- Updated: paths
```

```md
## [YYYY-MM-DD] query | Question

- Answered from: paths
- Filed back: paths
```

```md
## [YYYY-MM-DD] lint | Scope

- Findings: summary
- Fixed: paths
- Remaining: paths
```
