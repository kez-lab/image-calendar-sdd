---
name: android-docs-skills-research
description: Use when searching official Android Knowledge Base docs through Android CLI or finding/installing official Android skills such as edge-to-edge, navigation, AGP upgrades, R8 analyzer, and Compose migration.
---

# Android Docs And Skills Research

Use Android CLI as the first source for Android-specific guidance.

## Docs Search

```bash
android docs search "<query>"
android docs fetch <kb-url>
```

Use this for:

- API usage.
- Migration guides.
- Best practices.
- Platform behavior changes.
- Official examples.

## Android Skills

```bash
android skills list
android skills list --long
android skills find "<keyword>"
android skills add --agent=codex --skill=<skill-name>
android skills remove --agent=codex --skill=<skill-name>
```

## Rules

- Prefer `android docs search` before web search for Android implementation details.
- Use `android skills find` before installing official Android skills.
- Do not install all skills by default; install only the skill relevant to the current task unless the user requests `--all`.
- After installing new skills, tell the user a Codex restart may be needed for discovery.
