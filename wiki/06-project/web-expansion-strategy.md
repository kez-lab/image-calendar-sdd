---
type: project
status: active
owner: llm
updated: 2026-05-01
sources:
  - ../08-meetings/2026-05-01-web-feasibility-council.md
  - ../../raw/sources/0003-web-storage-reference.md
---

# Web Expansion Strategy

## Decision

웹 확장은 가능하지만, 현재 단계에서 full web app으로 착수하지 않는다.

The next acceptable web step is:

- `local-only web feasibility spike`, or
- read-only Android backup viewer prototype.

## Product Position

The web version must not be positioned as:

- Android replacement.
- Synced companion.
- Account-based cloud product.
- Public web diary service.

Acceptable positioning:

- Local-only browser prototype.
- Backup file viewer.
- Desktop review tool for user-owned backup files.

## Web Local-Only Definition

For web, `서버 없음` must be expressed more precisely:

- The app shell may be served from static hosting.
- Record data must not be stored in an application server.
- Photo, memo, emotion tag, date record payloads must not be sent to a network endpoint.
- Browser storage is tied to the current origin/browser profile.

## Candidate Architecture

- Static SPA/PWA.
- IndexedDB for metadata.
- OPFS for original and thumbnail assets.
- IndexedDB Blob fallback when OPFS is unavailable.
- Service Worker and Cache Storage for app shell.
- Android-compatible backup zip export/import path.
- `navigator.storage.persist()` and `navigator.storage.estimate()` as progressive enhancement.

## UX Requirements

- Replace Android `내 폰에만 저장됨` with web-specific copy.
- Explain browser storage deletion risk during onboarding and settings.
- Treat backup as a core web UX, not a secondary option.
- Do not collect real photos or records on a marketing landing page.
- Avoid any social/account/cloud language.

## First Feasibility Scope

The first web spike should prove:

- Create a local record with image, memo, emotion tag, date.
- Persist after refresh and browser restart.
- Delete all browser-stored records.
- Export Android-compatible backup zip.
- Import backup zip into empty browser profile.
- Handle OPFS failure or fallback.
- Show private browsing warning or save restriction.
- Prove via network inspection that record payloads stay local.

## External Review Needed

- Design: responsive local-only trust UX.
- Privacy/legal: browser storage copy and backup responsibility.
- Security: CSP, XSS, third-party script policy.
- QA: real browser/device matrix.

## Open Questions

- Should the first web artifact be storage durability spike or read-only backup viewer?
- Should Android backup zip manifest become a formal cross-platform contract now?
- Which static hosting origin will be used for prototype testing?
- What minimum browser matrix is required before public release?
