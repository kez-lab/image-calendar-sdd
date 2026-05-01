---
type: project
status: active
owner: llm
updated: 2026-05-01
sources:
  - ../../raw/meetings/2026-05-01-web-feasibility-council/brief.md
  - ../../raw/meetings/2026-05-01-web-feasibility-council/round-1-opinions.md
  - ../../raw/meetings/2026-05-01-web-feasibility-council/round-2-feedback.md
  - ../../raw/meetings/2026-05-01-web-feasibility-council/final-notes.md
  - ../../raw/sources/0003-web-storage-reference.md
---

# 2026-05-01 Web Feasibility Council

## Topic

로컬 전용 개인 이미지 캘린더를 웹으로도 만들 수 있는지 검토했다.

## Participants

- Product Planner
- Design Lead
- Web/Platform Engineer
- QA & Privacy Lead
- CEO Facilitator

## Final Decision

웹은 만들 수 있다. 그러나 지금은 full web product로 착수하지 않는다.

공식 결론:

- Short-term web work is a `local-only web feasibility spike`.
- Most useful first artifact is a browser storage durability prototype or read-only Android backup viewer.
- Full local-only PWA MVP is deferred until feasibility, privacy, security, backup, and browser QA gates pass.

## Rationale

- IndexedDB can store structured data and files/blobs.
- OPFS can store origin-private files and directories, but remains browser-managed and is removed with site data.
- Browser storage is origin-scoped and usually best-effort by default.
- Browser storage can be affected by private browsing, storage pressure, user site-data deletion, PWA uninstall, and browser-specific policies.
- Android app-specific storage communicates `내 폰 안에만 저장됨` more simply than web.
- Web copy must avoid promising Android-equivalent durability.

## Locked Copy Direction

Do not use:

- `내 폰에만 저장됨`
- Broad `서버 없음` without explanation
- Any copy that implies cross-device sync or account continuity

Use:

- `현재 기기의 이 브라우저에만 저장됨`
- `기록 데이터는 서버에 저장되거나 전송되지 않아요`
- `브라우저 데이터 삭제, 시크릿 모드, 앱 제거 시 기록이 사라질 수 있어요`
- `백업 파일은 사용자가 직접 보관해요`

## Recommended Sequence

1. Finish Android MVP stabilization and external review.
2. Lock Android backup zip format.
3. Run web storage durability spike.
4. Build read-only Android backup viewer prototype.
5. Create web landing/intro page only if it collects no user records.
6. Consider full PWA MVP after gates pass.

## Release Blockers For Web

- No backup/export and restore strategy.
- No browser matrix verification.
- No private browsing warning or save restriction.
- No proof that record payloads stay local.
- Analytics, session replay, crash upload, third-party scripts, or remote fonts without review.
- No CSP/XSS risk review.
- No privacy/legal review of web local-only copy.

## Implications for SDD

Do not implement a web product directly. If web work is approved, create a new spec first:

- `007-web-storage-feasibility-spike`

The first spec should verify storage durability, backup zip compatibility, network non-leakage, and browser data deletion behavior before any full PWA UI commitment.
