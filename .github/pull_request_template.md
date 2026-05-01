# Pull Request

## Purpose

Describe the user-facing or operational outcome of this change.

## Scope

- [ ] Product/wiki/spec updated before implementation
- [ ] Code or documentation changed
- [ ] Raw evidence added when behavior or UI changed
- [ ] External review need identified, if any

External review:

- Design:
- Privacy/legal:
- QA:
- Other:

## Verification

Commands run:

```bash
# Example
./gradlew assembleDebug
android describe --project_dir=.
android run --apks=app/build/outputs/apk/debug/app-debug.apk --activity=.app.MainActivity
```

Android evidence:

- `raw/verification/...`

Local-only guard:

```bash
rg -n "INTERNET|업로드|동기화|공유|클라우드|로그인|피드|좋아요|댓글|팔로우|\\b(upload|sync|share|cloud|login|feed|like|comment|follow)\\b" app/src/main app/build.gradle.kts build.gradle.kts settings.gradle.kts
```

## Documentation

- [ ] `wiki/00-context/current-state.md`
- [ ] `wiki/00-context/open-questions.md`
- [ ] `wiki/06-project/decision-log.md`
- [ ] `wiki/06-project/implementation-log.md`
- [ ] `wiki/log.md`
- [ ] Relevant `specs/*/tasks.md`
- [ ] `README.md`, if status or workflow changed

## Risks And Follow-ups

- Risk:
- Follow-up:
