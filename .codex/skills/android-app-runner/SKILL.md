---
name: android-app-runner
description: Use when deploying or launching an Android APK on a connected device or emulator with the official android run command, including debug APK verification and install failure diagnosis.
---

# Android App Runner

Use `android run` for deployment. It does not build; build the APK first with the project build system.

## Standard Flow

1. Build the APK:

```bash
./gradlew assembleDebug
```

2. Identify APK path with Android CLI:

```bash
android describe --project_dir=.
```

3. Run on the default device:

```bash
android run --apks=app/build/outputs/apk/debug/app-debug.apk
```

4. Run on a specific device:

```bash
android run --device=<serial> --apks=app/build/outputs/apk/debug/app-debug.apk --activity=.app.MainActivity
```

## Failure Handling

- If install fails because of device storage, report the device blocker and do not mask it.
- If multiple devices are connected, rerun with `--device=<serial>`.
- If the wrong activity launches, rerun with `--activity=<activity-name>`.

## Rules

- Do not replace `android run` with `adb install` unless the user explicitly asks for an ADB fallback.
- Do not add permissions or dependencies to fix deployment tooling issues.
- Capture the exact `android run` error in the final report.
