---
name: android-ui-inspector
description: Use when inspecting Android UI with official Android CLI layout and screen commands, including JSON layout dumps, screenshots, annotated screenshots, and coordinate resolution.
---

# Android UI Inspector

Use `android layout` as the primary inspection tool and `android screen` as the visual fallback.

## Layout Inspection

```bash
android layout --pretty
android layout --device=<serial> --pretty
android layout --device=<serial> --pretty --output=artifacts/android-cli/layout.json
android layout --device=<serial> --diff --pretty
```

Check for:

- Expected visible text.
- Accessibility content descriptions.
- Clickable/focusable/scrollable interactions.
- Bounds and centers for interaction planning.

## Screenshot Inspection

```bash
android screen capture --output=artifacts/android-cli/screen.png
android screen capture --annotate --output=artifacts/android-cli/screen-annotated.png
```

Always visually inspect screenshots before drawing conclusions from them.

## Coordinate Resolution

```bash
android screen resolve --screenshot=artifacts/android-cli/screen-annotated.png --string="input tap #5"
```

## Rules

- Prefer `layout --diff` after an interaction to reduce noise.
- If the current screen is not the app under test, report that state instead of pretending app verification passed.
- Store temporary inspection artifacts under `artifacts/android-cli/`.
