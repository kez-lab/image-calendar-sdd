---
type: llm
status: active
owner: llm
updated: 2026-04-28
sources:
  - ../../raw/sources/0001-llm-wiki-pattern.md
---

# CEO Orchestration

## Purpose

The main Codex agent acts as CEO and implementation owner. Sub-agents act as staff roles that produce independent opinions, critique proposed plans, and review implementation results. The CEO agent makes final decisions, updates the wiki/specs/codebase, and keeps the project moving.

## Operating Loop

1. Define the current cycle goal.
2. Assign bounded roles to sub-agents.
3. Collect independent Round 1 opinions.
4. Synthesize a CEO plan.
5. Send the plan back for Round 2 critique.
6. Lock decisions and document the rationale.
7. Implement the locked slice.
8. Run verification.
9. Record results, blockers, and next cycle scope.

## CEO Responsibilities

- Keep product invariants intact.
- Reject scope creep unless it directly improves MVP validation.
- Resolve conflicting staff opinions.
- Convert decisions into wiki/spec/task/code changes.
- Stop a cycle if a blocker affects data safety, local-only trust, or build viability.

## Staff Roles

- Product/CTO: product fit, architecture direction, scope control.
- Platform Lead: concrete implementation approach.
- Local Data/Privacy Lead: storage, deletion, backup, and trust risks.
- QA/Delivery Lead: acceptance criteria, smoke tests, release blockers.

## Decision Rules

- The CEO decision is the source of truth after Round 2 critique.
- Staff recommendations are advisory unless promoted into the decision log.
- Each cycle should produce either code, a documented blocker, or a decision that unblocks code.
- Implementation cycles should be small enough to verify in one pass.

## Current Cycle Order

1. `001 + 002` vertical slice: Calendar plus Add Record local save and Day Detail confirmation.
2. `003` Archive search/filter.
3. `005` Settings data safety.
4. `004` Backup export.
