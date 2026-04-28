---
name: android-cli-setup
description: Use when checking, updating, initializing, or configuring the official Android CLI for agent workflows, including android update, android init, android info, command discovery, and .androidrc SDK configuration.
---

# Android CLI Setup

Use the official `android` command only. Do not download alternate binaries or create fallback wrappers.

## Workflow

1. Confirm the CLI is installed:

```bash
command -v android
android -V
```

2. Confirm SDK configuration:

```bash
android info
android --sdk="$HOME/Library/Android/sdk" info
```

3. Update the CLI when requested or when troubleshooting CLI behavior:

```bash
android update
```

4. Install official Android CLI agent support:

```bash
android init
```

5. If repeated SDK flags are needed, configure `~/.androidrc` with:

```text
--sdk=/absolute/path/to/Android/sdk
```

## Rules

- Treat failed `android` commands as blockers to report, not as permission to bypass Android CLI.
- Do not modify `~/.androidrc` unless the user asks or the current task requires persistent SDK configuration.
- Prefer `android <command> -h` over memory when a command shape is unclear.
