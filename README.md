# Image Calendar SDD

Local-first Android product workspace for a private image calendar app. This repo is operated as an LLM-maintained second brain plus SDD implementation project.

## Product

Image Calendar is a personal mobile app for recording daily photos, short notes, and emotion tags by date.

Core constraints:

- No server
- No login
- No social graph, feed, likes, comments, follows, or sharing
- All records stay on the current device
- Backup/export is always an explicit user action

Product promise:

> 내 폰 안에만 조용히 쌓이는 하루 사진 캘린더

## Current Status

| Area | Status |
| --- | --- |
| Product/Wiki | MVP scope and local-only rules are documented |
| Design | Claude Design v2 ingested as implementation reference |
| Android App | Kotlin + Jetpack Compose app builds and runs |
| Persistence | Room-backed `PhotoEntry` + `LocalAsset` persistence implemented |
| Record Lifecycle | Day Detail edit/delete implemented and Android CLI verified |
| Verification | Android CLI build/run/layout/screenshot evidence is stored in `raw/verification` |
| Release Readiness | Not ready: Archive, Settings, Onboarding, backup/delete-all flows remain blockers |

Latest verified slice:

- 2026-05-01 - Day Detail edit/delete and Calendar marker removal

## App Implementation

Implemented:

- Calendar tab with month grid, today highlight, local-only badge, and record marker
- Add tab with gallery picker entry, debug QA fixture, required photo validation, date validation, memo, emotion tags, and local save
- Day Detail view after save
- Day Detail edit dialog for date, memo, and emotion metadata
- Day Detail delete confirmation and repository-backed record deletion
- Room database schema export under `app/schemas/`
- Internal image copy and thumbnail generation
- Repository rollback cleanup on metadata insert failure
- Android CLI-friendly content descriptions and test tags for touched controls

Not yet implemented:

- Onboarding
- Archive search/filter/month grouping
- Settings backup/delete data-safety flows
- Delete-all confirmation modal
- Real backup export package
- Camera capture

## Repository Map

| Path | Purpose |
| --- | --- |
| `app/` | Android Native Kotlin + Jetpack Compose app |
| `raw/` | Source-of-truth materials: prompts, designs, meetings, verification evidence |
| `wiki/` | Persistent LLM-maintained project wiki |
| `specs/` | SDD-style feature specifications and implementation tasks |
| `.codex/skills/` | Project-versioned Codex skills, including Android CLI workflows |
| `AGENTS.md` | Agent operating rules and wiki maintenance protocol |

## Key Documents

- [Project Overview](wiki/00-context/project-overview.md)
- [Current State](wiki/00-context/current-state.md)
- [MVP Scope](wiki/01-product/mvp-scope.md)
- [Design Output Review v2](wiki/02-design/design-output-review-2026-04-28-v2.md)
- [Architecture](wiki/03-engineering/architecture.md)
- [Data Model](wiki/03-engineering/data-model.md)
- [Test Strategy](wiki/05-qa/test-strategy.md)
- [Implementation Log](wiki/06-project/implementation-log.md)
- [Meeting Index](wiki/08-meetings/index.md)
- [Wiki Index](wiki/index.md)

## Latest Verification

| Verification | Evidence |
| --- | --- |
| Room persistence smoke | [raw/verification/2026-04-29-room-persistence-smoke](raw/verification/2026-04-29-room-persistence-smoke/README.md) |
| Overall behavior/design review | [raw/verification/2026-04-29-overall-behavior-design-review](raw/verification/2026-04-29-overall-behavior-design-review/README.md) |
| Day Detail edit/delete | [raw/verification/2026-05-01-day-detail-edit-delete](raw/verification/2026-05-01-day-detail-edit-delete/README.md) |
| QA summary | [wiki/05-qa/verification-report-2026-04-29.md](wiki/05-qa/verification-report-2026-04-29.md) |
| QA summary | [wiki/05-qa/verification-report-2026-05-01.md](wiki/05-qa/verification-report-2026-05-01.md) |

Verification rules:

- Every meaningful UI verification must store screenshots, layout JSON, annotated screenshots, action log, and findings under `raw/verification/`.
- Android CLI is the official install/launch/layout/screenshot tool for device verification.
- Local ignored `artifacts/` can be used temporarily, but durable evidence must be copied into `raw/`.

## Build And Run

```bash
./gradlew assembleDebug
android describe --project_dir=.
android run --apks=app/build/outputs/apk/debug/app-debug.apk --activity=.app.MainActivity
```

Capture evidence:

```bash
android layout --pretty --output=raw/verification/<date-topic>/assets/screen-layout.json
android screen capture --output=raw/verification/<date-topic>/assets/screen.png
android screen capture --annotate --output=raw/verification/<date-topic>/assets/screen-annotated.png
```

## Quality Gates

Before a feature is considered done:

- `./gradlew assembleDebug` passes.
- Official `android run` can install and launch the app.
- Screen evidence is saved under `raw/verification/`.
- Layout JSON exposes useful content descriptions or test tags for key actions.
- Source scan shows no `INTERNET`, account, upload, sync, cloud, share, or social concepts.
- The relevant `specs/*/tasks.md`, `wiki/06-project/implementation-log.md`, and `wiki/log.md` are updated.

## Next Work

P0 sequence:

1. Replace Archive placeholder with search, emotion filters, monthly grouping/list, and empty states.
2. Replace Settings placeholder with local trust card, backup export shell, restore follow-up row, danger zone, and delete-all confirmation.
3. Harden record deletion so file cleanup failures are surfaced or retried.
4. Re-run overall behavior and design sync verification with raw evidence.
