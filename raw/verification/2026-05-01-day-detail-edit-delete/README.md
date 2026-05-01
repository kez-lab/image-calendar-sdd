# 2026-05-01 Day Detail Edit/Delete Verification

## Purpose

Verify the Day Detail record lifecycle after implementing edit/delete:

- Open a saved record from Calendar.
- Edit date metadata fields without replacing the photo.
- Persist updated memo and emotion tag.
- Show a destructive delete confirmation.
- Delete the record and return the date to an empty state.
- Remove the Calendar marker for the deleted date.

## Environment

- Date: 2026-05-01
- Device: Android emulator `Pixel_9`
- Serial: `emulator-5554`
- App package: `com.kezlab.imagecalendar`
- APK: `app/build/outputs/apk/debug/app-debug.apk`

Precondition note:

- `Medium_Phone_API_35` was attempted first but official `android run` failed with `INSTALL_FAILED_INSUFFICIENT_STORAGE: Failed to override installation location`.
- Verification continued on the registered `Pixel_9` emulator through the same official Android CLI workflow.

## Commands

```bash
./gradlew assembleDebug
android describe --project_dir=.
android emulator start Pixel_9
android run --device=emulator-5554 --apks=app/build/outputs/apk/debug/app-debug.apk --activity=.app.MainActivity
android layout --device=emulator-5554 --pretty --output=raw/verification/2026-05-01-day-detail-edit-delete/assets/01-launch-layout.json
android screen capture --output=raw/verification/2026-05-01-day-detail-edit-delete/assets/01-launch.png
android screen capture --annotate --output=raw/verification/2026-05-01-day-detail-edit-delete/assets/01-launch-annotated.png
```

Interactions used `adb shell input` against coordinates identified from Android CLI layout centers. Install, launch, layout inspection, screenshots, and annotated screenshots were all produced by the official Android CLI.

## Journey Result

| Step | Result | Evidence |
| --- | --- | --- |
| Launch app with official `android run` | Passed | `01-launch-*` |
| Move to April 2026 and find `2026-04-29, 1 record` | Passed | `05-april-*` |
| Open Day Detail for `2026-04-29` | Passed | `06-open-detail-*` |
| Verify edit/delete actions are visible in Day Detail | Passed | `06-open-detail-layout.json` |
| Open edit dialog | Passed | `07-edit-dialog-*` |
| Save edited memo and emotion tag | Passed | `08-edited-detail-*` |
| Open destructive delete dialog | Passed | `09-delete-dialog-*` |
| Confirm delete and show empty date state | Passed | `10-after-delete-*` |
| Return to April calendar and verify `2026-04-29, no records` | Passed | `12-april-after-delete-*` |

## Findings

- Day Detail edit/delete works through repository-backed Room data.
- Edit dialog intentionally keeps the original photo and allows date, memo, and emotion changes only.
- Delete dialog clearly states that the record is removed from this device and cannot be restored.
- After deletion, the selected date shows the empty state and the Calendar date cell announces `no records`.
- The home Calendar summary card does not expose edit/delete actions. This is acceptable for MVP because destructive actions live in the full Day Detail screen opened from a populated date cell.

## Evidence Assets

- `01-launch.png`, `01-launch-layout.json`, `01-launch-annotated.png`
- `05-april.png`, `05-april-layout.json`, `05-april-annotated.png`
- `06-open-detail.png`, `06-open-detail-layout.json`, `06-open-detail-annotated.png`
- `07-edit-dialog.png`, `07-edit-dialog-layout.json`, `07-edit-dialog-annotated.png`
- `08-edited-detail.png`, `08-edited-detail-layout.json`, `08-edited-detail-annotated.png`
- `09-delete-dialog.png`, `09-delete-dialog-layout.json`, `09-delete-dialog-annotated.png`
- `10-after-delete.png`, `10-after-delete-layout.json`, `10-after-delete-annotated.png`
- `12-april-after-delete.png`, `12-april-after-delete-layout.json`, `12-april-after-delete-annotated.png`

Intermediate scroll/state captures are also preserved in `assets/` for traceability.

## Residual Risks

- Gallery-picker edit flow is not in scope because edit does not replace photos in the MVP.
- File deletion is invoked after metadata deletion; a filesystem failure could leave orphaned internal files. This is not user-visible but should be hardened before release.
- A reusable Android CLI smoke script is still not checked in.
