# Screen Evidence

Date: 2026-04-29

## Environment

- Android CLI: `0.7.15232955`
- Android SDK: `/Users/kwak-euijin/Library/Android/sdk`
- APK: `app/build/outputs/apk/debug/app-debug.apk`
- Package: `com.kezlab.imagecalendar`
- Launch activity: `.app.MainActivity`

## Commands Used

- `android -V`
- `android info`
- `android describe --project_dir=.`
- `./gradlew assembleDebug`
- `android run --apks=app/build/outputs/apk/debug/app-debug.apk --activity=.app.MainActivity`
- `android layout --pretty --output=...`
- `android screen capture --output=...`

Interactions were performed from Android CLI layout evidence using device input coordinates. This was used only to move through visible UI states after official Android CLI launch/capture.

## Captured Assets

- [Calendar screenshot](assets/01-calendar.png)
- [Calendar layout JSON](assets/01-calendar-layout.json)
- [Add screenshot](assets/02-add.png)
- [Add layout JSON](assets/02-add-layout.json)
- [Archive screenshot](assets/03-archive.png)
- [Archive layout JSON](assets/03-archive-layout.json)
- [Settings screenshot](assets/04-settings.png)
- [Settings layout JSON](assets/04-settings-layout.json)
- [Add validation screenshot](assets/05-add-validation.png)
- [Add validation layout JSON](assets/05-add-validation-layout.json)

## Journey Results

| Check | Result | Evidence |
| --- | --- | --- |
| Build debug APK | Passed | `./gradlew assembleDebug` |
| Install and launch app without login | Passed | `android run` success |
| Calendar screen visible | Passed | `01-calendar.png` |
| Local-only badge visible | Passed | `내 폰에만 저장됨` appears |
| Navigate to Add tab | Passed | `02-add.png` |
| Save without photo validation | Passed | `05-add-validation.png` |
| Archive screen MVP readiness | Failed | Placeholder copy only |
| Settings screen MVP readiness | Failed | Placeholder backup card only |
| Full photo save and Day Detail verification | Skipped | Needs deterministic image fixture or manual picker interaction |
| Forbidden network/social language scan | Passed | No matches in source/Gradle scan |

## Screen Notes

### Calendar

- Shows `Image Calendar`, `내 폰에만 저장됨`, month header `2026.04`, weekday grid, today highlight, add button, and empty state.
- Bottom navigation uses letter icons `C`, `A`, `A`, `S`; Add and Archive are visually ambiguous.
- Date cells are visible and tappable, but accessibility labeling needs improvement so dates read as full dates with today/record count state.

### Add

- Shows local badge, photo selection button, date input, memo input, emotion chips, and save button.
- Save without photo correctly shows `사진을 먼저 선택해주세요.`
- The error appears near the save button, not directly under the photo section where the cause exists.
- The date field is free text; invalid date handling or a date picker is not implemented.

### Archive

- Current screen says `메모, 날짜로 검색하기는 다음 슬라이스에서 연결합니다.`
- This is internal development copy and cannot remain in MVP.
- Search bar, monthly grouping, emotion filters, and no-result/empty states are not yet implemented.

### Settings

- Local-only trust copy is visible.
- Backup export is still an empty card with next-slice wording.
- Import/restore, app information, danger zone, and delete confirmation are not implemented.

### Accessibility/Testability

- Android CLI layout output exposes many clickable/focusable nodes, but `contentDescription` is mostly null.
- Several clickable nodes are textless in the layout tree, making automated Android CLI smoke tests more coordinate-dependent than necessary.
- Main actions need explicit Compose semantics, roles, selected states, content descriptions, and stable test tags.
