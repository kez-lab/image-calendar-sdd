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

## Notes

- Verification artifacts remain under local ignored `artifacts/android-cli/2026-04-29-room-persistence-smoke/`.
- The debug fixture is gated by `BuildConfig.DEBUG`; it is not a release user flow.
- Archive and Settings remain placeholders and are still release blockers.
