# Overall Behavior and Design Review

Date: 2026-04-29

## Purpose

Verify the current Android app after Room persistence work, preserve all screen/action evidence in raw, and compare the implemented screens against the v2 design reference.

## Environment

- Project: `Android-SDD`
- App package: `com.kezlab.imagecalendar`
- APK: `app/build/outputs/apk/debug/app-debug.apk`
- Tooling: official Android CLI for install/launch/layout/screenshot capture
- Existing test data: one debug fixture record saved on `2026-04-29`

## Commands

- `./gradlew assembleDebug`
- `android run --apks=app/build/outputs/apk/debug/app-debug.apk --activity=.app.MainActivity`
- `android layout --pretty --output=raw/verification/2026-04-29-overall-behavior-design-review/assets/*.json`
- `android screen capture --output=raw/verification/2026-04-29-overall-behavior-design-review/assets/*.png`
- `android screen capture --annotate --output=raw/verification/2026-04-29-overall-behavior-design-review/assets/*-annotated.png`
- `android screen resolve --screenshot=... --string="input tap #..."`

Device input was used only for UI interactions after Android CLI had launched the app and captured the target screens.

## Evidence Assets

| Screen | Screenshot | Layout |
| --- | --- | --- |
| Calendar | [01-calendar.png](assets/01-calendar.png) | [01-calendar-layout.json](assets/01-calendar-layout.json) |
| Day Detail | [02-day-detail.png](assets/02-day-detail.png) | [02-day-detail-layout.json](assets/02-day-detail-layout.json) |
| Add Record | [03-add.png](assets/03-add.png) | [03-add-layout.json](assets/03-add-layout.json) |
| Add Validation | [04-add-validation.png](assets/04-add-validation.png) | [04-add-validation-layout.json](assets/04-add-validation-layout.json) |
| Archive | [05-archive.png](assets/05-archive.png) | [05-archive-layout.json](assets/05-archive-layout.json) |
| Settings | [06-settings.png](assets/06-settings.png) | [06-settings-layout.json](assets/06-settings-layout.json) |

Annotated screenshots are also stored in `assets/` for coordinate/action audit.

## Action Log

| Step | Action | Result |
| --- | --- | --- |
| 1 | Build debug APK | Passed |
| 2 | Launch app with official Android CLI | Passed |
| 3 | Capture Calendar | Passed |
| 4 | Tap populated date `2026-04-29` | Opened Day Detail |
| 5 | Capture Day Detail | Passed |
| 6 | Tap Add tab | Opened Add Record |
| 7 | Capture Add Record | Passed |
| 8 | Tap Save without photo | Inline validation displayed |
| 9 | Capture Add Validation | Passed |
| 10 | Tap Archive tab | Opened Archive |
| 11 | Capture Archive | Passed |
| 12 | Tap Settings tab | Opened Settings |
| 13 | Capture Settings | Passed |
| 14 | Scan forbidden network/account/social wording | Passed |

## Behavior Results

Passed:

- App builds and launches without login.
- Calendar shows local-only badge.
- Room-backed record remains visible after previous relaunch verification.
- Calendar shows a marker on `2026-04-29`.
- Tapping the populated date opens Day Detail.
- Day Detail shows `이 기기에 저장된 기록 1개`.
- Add Record shows photo required, memo optional, emotion optional.
- Save without photo shows `사진을 먼저 선택해주세요.` under the photo section.
- Layout exposes useful test identifiers such as `local_storage_badge` and `calendar_day_2026-04-29`.
- Saved date cell exposes `2026-04-29, today, 1 record`.
- Static source scan found no network/account/social/share/login concepts.

Not verified in this pass:

- Real gallery picker save on a physical media item.
- Manual camera capture, because Camera is deferred.
- Edit/delete, because the feature is not implemented.
- Backup export/delete flows, because Settings is still placeholder-level.

## Design Sync Review

| Area | v2 Design Requirement | Current Implementation | Status |
| --- | --- | --- | --- |
| Onboarding | 3-step onboarding with first-record CTA and skip option | Not implemented | Missing |
| Calendar | Month grid, local badge, today highlight, dot markers, search/settings icons, fast add | Grid, badge, today, marker, add CTA present; search/settings icons absent | Partial |
| Day Detail | Photo cards, memo, emotion tag, edit/delete, empty state | Photo card and count present; edit/delete absent; fixture has no note/emotion | Partial |
| Add Record | Camera, gallery, date selection, optional memo/emotion, save, local copy | Gallery, date text validation, memo/emotion, save, local copy present; camera/date picker absent | Partial |
| Archive | Search bar, emotion filters, monthly grouping/grid/list | Placeholder text and record card only | Blocked |
| Settings | Trust card, backup export, restore row, app info, danger zone, delete confirmation | Trust copy and placeholder backup card only | Blocked |
| Delete Modals | Delete record and delete all confirmation modals | Not implemented | Missing |
| Accessibility | Text contrast, touch targets, non-color-only states, labels | Main touched controls now have labels/test tags; full app pass still needed | Partial |

## Bugs and Risks

Release blockers:

- Archive still contains internal development copy: `메모, 날짜로 검색하기는 다음 슬라이스에서 연결합니다.`
- Settings still contains internal development copy: `다음 구현 슬라이스에서 로컬 파일로 내보내기를 연결합니다.`
- Settings lacks backup export action, restore follow-up row, app info, danger zone, and delete confirmation.
- Day Detail lacks record edit/delete controls.
- Onboarding is missing.

Design mismatches:

- Bottom tab uses text abbreviations (`Cal`, `+`, `Arc`, `Set`) instead of proper icons from design v2.
- Calendar top bar lacks search/settings icon actions.
- Add screen has debug fixture visible in debug builds; acceptable for QA but not design-matching.
- Add screen uses text date input with validation rather than a designed date picker.
- Calendar uses a full-width add CTA rather than the raised Add/FAB visual treatment in the design system.

Runtime issues observed:

- No crash observed in tested flows.
- No persistence regression observed in tested flows.
- No forbidden network/account/social wording observed in source scan.

## Decision

The app is functionally healthier after Room persistence, but it is still not design-synced enough for release. Next implementation should target Day Detail edit/delete, Archive MVP, and Settings data-safety flows before visual polish.
