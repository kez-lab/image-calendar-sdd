# Meeting Brief: MVP Decision Lock

Date: 2026-04-28  
Topic: MVP decision lock before implementation tasks  
Facilitator: Codex  

## Purpose

Resolve the remaining product/design decisions that block conversion from wiki/spec drafts into implementation tasks.

## Context

- Product is a local-only personal image calendar.
- No server, login, social graph, friend sharing, public feed, likes, comments, or follows.
- Design v2 is considered usable as the first implementation reference.
- Open decisions remain around archive search, local-storage reassurance, settings scope, and post-save navigation.

## Product Invariants

- All records are stored on the current device only.
- The app must not imply upload, sync, account, or external sharing.
- Recording should remain simple enough to complete within about 30 seconds.
- MVP should prioritize the core loop: add record, see it on calendar, revisit by date/archive.

## Questions To Decide

1. Should Archive search cover memo/date only, or include emotion tags?
2. Should the Calendar local-storage badge remain always visible, or become initial/occasional reassurance?
3. Should Settings include `Default photo quality` and `Week start day` in MVP, or defer them?
4. After saving the first record, should the app navigate to Calendar or Day Detail?

## Participants

- Product Lead
- UX Planner
- Local-first Engineering/QA Strategist

## Expected Outputs

- Raw role recommendations
- Meeting minutes
- Final decisions
- Wiki updates
- Spec/task updates
