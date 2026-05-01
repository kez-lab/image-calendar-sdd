# Git Conventions

Updated: 2026-05-01

This project uses Git as an operating log for the LLM Wiki, SDD specs, Android implementation, and verification evidence.

## Branches

Default branch pattern:

```text
codex/<short-topic>
```

Examples:

```text
codex/archive-search-filter
codex/backup-export
codex/android-mvp-verification
```

Rules:

- Use one branch per coherent feature, verification pass, or documentation operation.
- Prefer PRs for implementation work.
- Direct `main` commits are acceptable only for explicit user-directed solo maintenance or fast documentation updates.
- Never rewrite shared history or amend commits unless the user explicitly requests it.

## Commits

Use Conventional Commits:

```text
<type>: <summary>
```

Allowed types:

- `feat`: user-facing feature
- `fix`: bug fix
- `docs`: documentation, wiki, specs, raw evidence indexing
- `test`: tests, fixtures, verification scripts
- `refactor`: behavior-preserving code restructuring
- `chore`: build, tooling, dependency, repository maintenance

Examples:

```text
feat: add archive emotion filters
fix: clean up orphan image files on save failure
docs: add mvp verification evidence
test: add android cli smoke checklist
chore: add pr template
```

Commit body guidance:

```text
Verification:
- ./gradlew assembleDebug
- android run --apks=app/build/outputs/apk/debug/app-debug.apk --activity=.app.MainActivity

Evidence:
- raw/verification/2026-05-01-mvp-completion/README.md
```

## Pull Requests

Each PR should answer:

- What user or project outcome changed?
- Which wiki/spec pages were updated first?
- Which raw evidence supports the behavior?
- Which Android CLI commands were run?
- Does the change preserve local-only product invariants?
- Is external design, privacy/legal, or QA review needed?

For UI or behavior changes, attach or link durable evidence under `raw/verification/`.

## Local-Only Guard

Before merging Android behavior changes, run a source scan for concepts that violate or confuse the product invariant:

```bash
rg -n "INTERNET|업로드|동기화|공유|클라우드|로그인|피드|좋아요|댓글|팔로우|\\b(upload|sync|share|cloud|login|feed|like|comment|follow)\\b" app/src/main app/build.gradle.kts build.gradle.kts settings.gradle.kts
```

Expected result:

- No Android `INTERNET` permission.
- No login/account/social UI copy.
- No cloud/upload/sync/share behavior unless explicitly documented as local export.

False positives must be documented in the PR.

## Documentation Sync

If implementation state changes, update:

- `wiki/00-context/current-state.md`
- `wiki/06-project/implementation-log.md`
- `wiki/log.md`
- Relevant `specs/*/tasks.md`
- Relevant QA or privacy pages

If a decision changes product behavior, append to:

- `wiki/06-project/decision-log.md`

Do not silently overwrite old decisions. Add a new dated entry that supersedes the prior decision.
