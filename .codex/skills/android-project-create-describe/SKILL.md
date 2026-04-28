---
name: android-project-create-describe
description: Use when creating Android projects from official Android CLI templates or describing an existing Android project to find variants, build targets, and APK output artifacts.
---

# Android Project Create And Describe

Use the official `android create` and `android describe` commands.

## Template Discovery

```bash
android create list
android create -h
```

For safe exploration, use dry-run:

```bash
android create --dry-run --verbose --name=<app-name> --output=<dest-path> <template-name>
```

## Existing Project Analysis

Run from the project root unless a different path is needed:

```bash
android describe --project_dir=.
```

Use the output to identify:

- Gradle wrapper availability.
- Build variants.
- APK output paths.
- Missing release/debug artifacts.

## Rules

- `android describe` may run Gradle model tasks; report build failures clearly.
- Do not create a new project inside an existing repo unless explicitly requested.
- Prefer `--dry-run` before writing template files.
