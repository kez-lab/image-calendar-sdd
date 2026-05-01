---
name: wiki-sdd-sync
description: Use after product, design, implementation, verification, or decision changes to synchronize raw evidence, LLM Wiki pages, SDD specs, and project logs.
---

# Wiki SDD Sync

Use this skill whenever project knowledge should persist beyond the chat thread.

## Context Load

Always read:

```bash
sed -n '1,220p' wiki/index.md
sed -n '1,220p' wiki/00-context/current-state.md
sed -n '1,220p' wiki/00-context/open-questions.md
```

Then read the relevant wiki area:

- Product: `wiki/01-product/`
- Design: `wiki/02-design/`
- Engineering: `wiki/03-engineering/`
- Privacy/security: `wiki/04-privacy-security/`
- QA: `wiki/05-qa/`
- Project: `wiki/06-project/`
- LLM Ops: `wiki/07-llm/`
- Meetings: `wiki/08-meetings/`

## Change Classification

Classify the change before editing:

- `ingest`: new raw source, design file, meeting note, or external document
- `decision`: product, design, engineering, privacy, or QA decision
- `planning`: roadmap, tasks, or implementation plan
- `implementation`: code behavior changed
- `verification`: Android CLI, test, screenshot, or manual QA evidence
- `llm-ops`: agent workflow, skill, convention, or wiki maintenance change

## Sync Rules

- Raw sources are source-of-truth and should not be rewritten except to add indexes, READMEs, or newly captured evidence.
- Wiki pages should be short, linkable, and current.
- Specs must not invent major requirements that are absent from wiki. Update wiki first, then specs.
- Append decisions to `wiki/06-project/decision-log.md`; do not overwrite prior decisions.
- Append chronological operations to `wiki/log.md`.
- Update `wiki/index.md` whenever a durable page is added or renamed.
- Use frontmatter on wiki pages where practical.
- Mark assumptions explicitly as `Assumption` or `가정`.
- Record contradictions instead of silently deleting them.

## Standard Sync Targets

For implementation changes:

- `wiki/00-context/current-state.md`
- `wiki/06-project/implementation-log.md`
- Relevant `specs/*/tasks.md`
- Relevant engineering, privacy, QA pages
- `wiki/log.md`

For verification changes:

- `raw/verification/YYYY-MM-DD-topic/README.md`
- `raw/README.md`
- `wiki/05-qa/verification-report-YYYY-MM-DD*.md`
- `wiki/00-context/current-state.md`
- `wiki/log.md`

For product/design decisions:

- Relevant `wiki/01-product/` or `wiki/02-design/` page
- `wiki/06-project/decision-log.md`
- Affected specs
- `wiki/00-context/open-questions.md`
- `wiki/log.md`

For LLM operations:

- Relevant `wiki/07-llm/` page
- `wiki/index.md`
- `wiki/log.md`

## Log Entry Format

Use this format in `wiki/log.md`:

```markdown
## [YYYY-MM-DD] <classification> | <Title>

- Source: <link or file path>
- Action: <what changed>
- Updated: `<path>`, `<path>`.
```

## Completion Checklist

- Added or updated durable source, wiki, spec, or log files.
- Updated dates in frontmatter for touched wiki pages.
- Added index links for new durable pages.
- Preserved local-only product invariants.
- Listed unresolved follow-ups in `open-questions.md` when needed.
