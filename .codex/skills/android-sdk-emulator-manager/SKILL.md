---
name: android-sdk-emulator-manager
description: Use when listing, installing, updating, or removing Android SDK packages, or creating, listing, starting, and stopping Android emulators with official Android CLI commands.
---

# Android SDK And Emulator Manager

Use official `android sdk` and `android emulator` commands.

## SDK Packages

```bash
android sdk list <package-pattern>
android sdk list --all <package-pattern>
android sdk install <package[@version]>
android sdk update [<package-name>]
android sdk remove <package-name>
```

Examples:

```bash
android sdk list "platforms/android-36"
android sdk install platforms/android-36 build-tools/36.0.0
```

## Emulators

```bash
android emulator list
android emulator create --list-profiles
android emulator create --profile=<profile-name>
android emulator start <device-name>
android emulator stop <device-serial-number>
```

## Rules

- Do not install, update, or remove SDK packages unless the task requires it.
- When an emulator has insufficient storage, report the blocker and recommend creating a fresh emulator profile rather than hiding the failure.
- On non-Windows systems, prefer Android CLI emulator commands over direct emulator binary invocation.
