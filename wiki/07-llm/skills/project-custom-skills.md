---
type: llm
status: active
owner: llm
updated: 2026-05-01
sources:
  - .codex/skills/android-mvp-verification/SKILL.md
  - .codex/skills/wiki-sdd-sync/SKILL.md
  - docs/conventions/git.md
---

# Project Custom Skills

This project uses custom Codex skills to reduce repeated operational work around Android verification and LLM Wiki/SDD maintenance.

## Skills

Version-controlled copies live in `.codex/skills/`.

Active local copies also live in `~/.codex/skills/` so Codex can discover them across sessions.

### `android-mvp-verification`

Use when running an end-to-end MVP verification pass on a connected Android device or emulator.

It standardizes:

- Official Android CLI build/run/layout/screenshot workflow.
- Raw evidence folder structure under `raw/verification/`.
- MVP journey checklist.
- Local-only guard scan.
- QA/wiki/spec update expectations.

### `wiki-sdd-sync`

Use after product, design, implementation, verification, decision, or LLM-ops changes.

It standardizes:

- Which context pages to read first.
- Which wiki/spec/raw files to update by change type.
- Append-only decision and operation logging.
- Index maintenance and frontmatter updates.

## Relationship To Android CLI Skills

The existing Android CLI skills remain command-family skills:

- `android-cli-setup`
- `android-project-create-describe`
- `android-app-runner`
- `android-ui-inspector`
- `android-sdk-emulator-manager`
- `android-docs-skills-research`
- `android-journey-smoke-test`

`android-mvp-verification` sits above them as a project-specific release/QA workflow.

## When To Add More Skills

Create another project skill only when the task is repeated enough that a checklist prevents real mistakes.

Good future candidates:

- Backup/export package audit.
- Restore/import migration verification.
- Design sync review against screenshot evidence.
- Release readiness review.
