---
type: llm
status: active
owner: llm
updated: 2026-05-01
sources:
  - https://developer.android.com/tools/agents/android-cli
  - https://developer.android.com/tools/agents/android-skills
---

# Android CLI Custom Skills

The project includes Codex custom skills for using the official Android CLI in focused workflows.

## Install Locations

Version-controlled project copies:

- `.codex/skills/android-cli-setup`
- `.codex/skills/android-project-create-describe`
- `.codex/skills/android-app-runner`
- `.codex/skills/android-ui-inspector`
- `.codex/skills/android-sdk-emulator-manager`
- `.codex/skills/android-docs-skills-research`
- `.codex/skills/android-journey-smoke-test`
- `.codex/skills/android-mvp-verification`

Active local Codex copies:

- `~/.codex/skills/android-cli-setup`
- `~/.codex/skills/android-project-create-describe`
- `~/.codex/skills/android-app-runner`
- `~/.codex/skills/android-ui-inspector`
- `~/.codex/skills/android-sdk-emulator-manager`
- `~/.codex/skills/android-docs-skills-research`
- `~/.codex/skills/android-journey-smoke-test`
- `~/.codex/skills/android-mvp-verification`

Codex may need to be restarted to discover newly added skills.

## Skill Split

- `android-cli-setup`: `android -V`, `android update`, `android init`, `android info`, `.androidrc`.
- `android-project-create-describe`: `android create`, `android create list`, `android describe`.
- `android-app-runner`: `android run` for APK install/launch.
- `android-ui-inspector`: `android layout`, `android screen capture`, `android screen resolve`.
- `android-sdk-emulator-manager`: `android sdk` and `android emulator`.
- `android-docs-skills-research`: `android docs` and `android skills`.
- `android-journey-smoke-test`: stepwise Android UI smoke journeys using Android CLI inspection.
- `android-mvp-verification`: project-specific end-to-end MVP verification with durable raw evidence and wiki/SDD updates.

## Rules

- Use official `android` commands first.
- Do not create alternate Android CLI binaries or wrapper tools.
- Treat Android CLI failures as blockers to report.
- Use Android CLI official skills separately from Codex custom skills.
