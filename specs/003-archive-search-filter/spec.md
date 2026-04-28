# Spec: Archive Search Filter

Status: draft  
Source wiki:

- [PRD](../../wiki/01-product/prd.md)
- [User Flows](../../wiki/01-product/user-flows.md)
- [Screen Inventory](../../wiki/02-design/screen-inventory.md)
- [MVP Decision Lock](../../wiki/08-meetings/2026-04-28-mvp-decision-lock.md)

## User Story

As a user, I want to browse and search my saved records by month, memo, date, and emotion filter, so that I can find old daily memories without a social feed.

## Functional Requirements

- Archive shows saved records grouped by recent month first.
- Archive provides image-centered record browsing.
- Search covers memo text and local date only.
- Emotion tags are handled through explicit chip filters, not free-text search.
- Search and emotion filters can be combined.
- Selecting an archived record opens its Day Detail or record detail context.
- Archive contains no infinite social feed, public activity, likes, comments, or friend content.

## Local Data Requirements

- Archive results are derived from local records only.
- Search query matches note text and local date labels.
- Emotion filtering uses `emotionTagId`.

## Acceptance Criteria

- Given records exist across multiple months, when Archive opens, then recent months appear first.
- Given the user searches memo text, then matching local records appear.
- Given the user searches a date label, then records from matching local dates appear.
- Given the user selects an emotion chip, then records with that emotion tag appear.
- Given the user combines search and emotion filter, then results satisfy both conditions.
- Given no result matches, then an empty state is shown.
- Given Archive is visible, then no social feed, upload, account, or sharing UI is shown.

## Open Questions

- What date query formats should MVP support?
- Should Archive open a Day Detail anchored to the selected record or a single-record detail view?
