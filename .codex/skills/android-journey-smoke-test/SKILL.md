---
name: android-journey-smoke-test
description: Use when running an Android user journey or smoke test with Android CLI layout, screen capture, screen resolve, and scripted interactions against a connected device.
---

# Android Journey Smoke Test

Use this skill for step-by-step validation of a running Android app.

## Journey Format

Write short XML-style journeys:

```xml
<journey name="Calendar smoke">
  <description>Verify the app opens to Calendar.</description>
  <actions>
    <action>Verify that "Image Calendar" is visible</action>
    <action>Verify that "내 폰에만 저장됨" is visible</action>
    <action>Tap the "Add" tab</action>
    <action>Verify that "오늘의 기록" is visible</action>
  </actions>
</journey>
```

## Evaluation Flow

1. Install and launch with `android run`.
2. Inspect current state:

```bash
android layout --device=<serial> --pretty
```

3. For visual-only targets:

```bash
android screen capture --annotate --output=artifacts/android-cli/annotated.png
android screen resolve --screenshot=artifacts/android-cli/annotated.png --string="input tap #<label>"
```

4. Use resolved coordinates for interaction only when layout data is insufficient.

## Reporting

Return JSON-like results with:

- action
- status: `PASSED`, `FAILED`, or `SKIPPED`
- commands
- comment

## Rules

- Evaluate each action exactly as written.
- Stop after the first failed required action.
- Do not debug or fix code during journey evaluation unless the user asks.
- If app installation fails, mark the journey failed with the exact Android CLI error.
