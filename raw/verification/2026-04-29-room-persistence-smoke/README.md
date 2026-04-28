# Room Persistence Smoke Verification

Date: 2026-04-29

## Purpose

Verify the first P0 implementation slice after the weekly app review:

- Room metadata persistence.
- Repository-owned image copy, thumbnail generation, DB insert, and rollback cleanup contract.
- Debug-only deterministic fixture for Android CLI save verification.
- Calendar marker and Day Detail after save.
- App relaunch persistence.

## Commands

- `./gradlew assembleDebug`
- `android describe --project_dir=.`
- `android run --apks=app/build/outputs/apk/debug/app-debug.apk --activity=.app.MainActivity`
- `android layout --pretty --output=artifacts/android-cli/2026-04-29-room-persistence-smoke/*.json`
- `android screen capture --output=artifacts/android-cli/2026-04-29-room-persistence-smoke/*.png`
- `android screen capture --annotate --output=artifacts/android-cli/2026-04-29-room-persistence-smoke/*.png`
- `android screen resolve --screenshot=... --string="input tap #..."`

Android CLI was used for APK install/launch and evidence capture. Device input was used only for UI interactions resolved from the captured Android CLI screens.

## Results

| Check | Result |
| --- | --- |
| Build debug APK | Passed |
| Install and launch with official Android CLI | Passed |
| Debug fixture can be selected on Add screen | Passed |
| Save opens Day Detail | Passed |
| Day Detail shows `이 기기에 저장된 기록 1개` | Passed |
| Calendar marker appears on saved date | Passed |
| Relaunch preserves saved record from Room | Passed |
| Layout exposes `local_storage_badge` and `calendar_day_2026-04-29` resource IDs | Passed |
| Saved date cell exposes `2026-04-29, today, 1 record` content description | Passed |
| Forbidden network/account/social wording scan | Passed |

## Evidence Assets

- [01 launch calendar](assets/01-launch-calendar.png)
- [01 launch calendar layout](assets/01-launch-calendar-layout.json)
- [02 add annotated](assets/02-add-annotated.png)
- [04 after save detail](assets/04-after-save-detail.png)
- [04 after save detail layout](assets/04-after-save-detail-layout.json)
- [05 calendar marker](assets/05-calendar-marker.png)
- [05 calendar marker layout](assets/05-calendar-marker-layout.json)
- [06 relaunch calendar](assets/06-relaunch-calendar.png)
- [06 relaunch calendar layout](assets/06-relaunch-calendar-layout.json)
- [07 final calendar](assets/07-final-calendar.png)
- [07 final layout](assets/07-final-layout.json)

## Notes

- Verification artifacts were copied from local ignored `artifacts/android-cli/2026-04-29-room-persistence-smoke/` into this raw folder for persistence.
- The debug fixture is gated by `BuildConfig.DEBUG`; it is not a release user flow.
- Archive and Settings remain placeholders and are still release blockers.
