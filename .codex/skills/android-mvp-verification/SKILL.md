---
name: android-mvp-verification
description: Use when verifying the Image Calendar Android MVP with official Android CLI, preserving screenshots/layouts/action logs under raw/verification, and updating QA evidence.
---

# Android MVP Verification

Use this skill for end-to-end Image Calendar MVP verification on a connected Android device or emulator.

## Rules

- Use the official `android` CLI for project description, app deployment, layout capture, and screenshots.
- Do not replace `android run` with `adb install`.
- `adb` may be used only for device diagnostics, shell input after coordinates are known, or reading files that Android CLI does not expose.
- Store durable verification evidence under `raw/verification/YYYY-MM-DD-topic/`.
- Do not leave important verification evidence only in ignored `artifacts/`.
- Treat local-only product invariants as release gates.

## Context Load

Read these first:

```bash
sed -n '1,220p' wiki/index.md
sed -n '1,220p' wiki/00-context/current-state.md
sed -n '1,220p' wiki/00-context/open-questions.md
```

Then inspect the relevant spec and QA pages for the target journey.

## Standard Flow

1. Build the app.

```bash
./gradlew assembleDebug
```

2. Describe the project and locate the APK.

```bash
android describe --project_dir=.
```

3. Confirm device state.

```bash
adb devices
```

4. Install and launch with Android CLI.

```bash
android run --apks=app/build/outputs/apk/debug/app-debug.apk --activity=.app.MainActivity
```

5. Create an evidence folder.

```bash
mkdir -p raw/verification/YYYY-MM-DD-topic/assets
```

6. Capture each important screen.

```bash
android layout --pretty --output=raw/verification/YYYY-MM-DD-topic/assets/<screen>-layout.json
android screen capture --output=raw/verification/YYYY-MM-DD-topic/assets/<screen>.png
android screen capture --annotate --output=raw/verification/YYYY-MM-DD-topic/assets/<screen>-annotated.png
```

7. Use annotated screenshot labels or layout bounds to derive tap coordinates.

```bash
android screen resolve --screenshot=raw/verification/YYYY-MM-DD-topic/assets/<screen>-annotated.png --string="input tap #<label>"
```

8. Run the local-only guard.

```bash
rg -n "INTERNET|업로드|동기화|공유|클라우드|로그인|피드|좋아요|댓글|팔로우|\\b(upload|sync|share|cloud|login|feed|like|comment|follow)\\b" app/src/main app/build.gradle.kts build.gradle.kts settings.gradle.kts
```

9. Write `raw/verification/YYYY-MM-DD-topic/README.md`.

10. Update wiki QA pages, `wiki/log.md`, and relevant spec tasks.

## MVP Journey Checklist

- Onboarding shows local-only value proposition and can be completed.
- Calendar shows monthly layout, today state, local-only badge, and empty-date CTA.
- Add creates a record in under 30 seconds with photo, memo, emotion tag, and date.
- Day Detail shows saved records, supports metadata edit, and supports guarded delete.
- Archive lists records by month, supports memo/date search, emotion filters, empty state, and navigation to Day Detail.
- Settings explains local storage, exports backup zip, shows restore as follow-up if not implemented, and guards full data deletion.
- Restart preserves saved records until explicit deletion.
- Delete-all removes records/assets and returns Calendar/Archive to empty state.

## Evidence README Format

```markdown
# Verification Title

Date: YYYY-MM-DD

## Purpose

## Environment

- Device:
- Android CLI:
- APK:

## Commands

## Journey Results

| Step | Result | Evidence |
| --- | --- | --- |

## Findings

## Residual Risks
```

## Completion Criteria

Verification is complete only when:

- Build passes.
- Android CLI launch succeeds.
- Required screens have screenshot, annotated screenshot, and layout JSON evidence.
- Action results are summarized in raw verification README.
- Local-only scan has no unresolved blocker.
- Wiki/SDD status reflects the result.
