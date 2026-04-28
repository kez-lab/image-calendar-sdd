---
type: meeting
status: accepted
owner: llm
updated: 2026-04-28
sources:
  - ../../raw/meetings/2026-04-28-tech-implementation-council/brief.md
  - ../../raw/meetings/2026-04-28-tech-implementation-council/context-snapshot.md
  - ../../raw/meetings/2026-04-28-tech-implementation-council/round-1-opinions.md
  - ../../raw/meetings/2026-04-28-tech-implementation-council/round-2-critique.md
  - ../../raw/meetings/2026-04-28-tech-implementation-council/final-notes.md
---

# 2026-04-28 Tech Implementation Council

Date: 2026-04-28  
Topic: Lock implementation stack and first vertical slice  
Raw Folder: [raw/meetings/2026-04-28-tech-implementation-council](../../raw/meetings/2026-04-28-tech-implementation-council/)

## Context

The MVP product/design decisions were locked. The next blocker was implementation stack and first build slice.

## Participants

- CTO
- Android Lead
- Local Data/Privacy Lead
- QA/Delivery Lead

## Round 1 Summary

All roles converged on Android Native Kotlin + Jetpack Compose. Room and app-internal image copies were recommended to protect local-only trust, deletion safety, and backup feasibility.

## Round 2 Summary

No blockers were raised. The critique round added concrete implementation constraints: Photo Picker first, Camera deferred, relative asset paths, `YYYY-MM-DD` local dates, Room indexes, rollback cleanup, and no `INTERNET` permission.

## Decisions

1. Use Android Native Kotlin + Jetpack Compose.
2. Use a single `:app` module for MVP.
3. Use package `com.kezlab.imagecalendar`.
4. Use Room for local metadata.
5. Use `PhotoEntry` and `LocalAsset`; do not create a `DayRecord` table in MVP.
6. Store `localDate` as `YYYY-MM-DD`.
7. Copy selected images into app-specific internal storage.
8. Store relative asset paths in the database.
9. Generate thumbnails at save time.
10. Use Android Photo Picker for the first Add Record slice.
11. Defer Camera from the first vertical slice.
12. Do not include `INTERNET` permission.

## First Implementation Cycle

Implement `001 + 002` as a vertical slice:

- App shell and Compose theme.
- Calendar with local badge and record markers.
- Add Record with gallery image selection and local save.
- Day Detail confirmation after save.
- Calendar marker update after returning.

## Verification Gate

- Build succeeds.
- App launches without login.
- Calendar local badge is visible.
- Gallery image can be saved locally.
- Day Detail shows the saved record.
- Calendar marker updates.
- Manifest has no `INTERNET` permission.
- No social/account/upload/sync UI is present.

## Wiki Updates

- [CEO Orchestration](ceo-orchestration.md)
- [Tech Stack](../03-engineering/tech-stack.md)
- [Architecture](../03-engineering/architecture.md)
- [Data Model](../03-engineering/data-model.md)
- [Decision Log](../06-project/decision-log.md)
