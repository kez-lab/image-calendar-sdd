# Spec: Calendar Daily Record

Status: draft  
Source wiki:

- [Project Overview](../../wiki/00-context/project-overview.md)
- [PRD](../../wiki/01-product/prd.md)
- [Screen Inventory](../../wiki/02-design/screen-inventory.md)
- [Privacy Model](../../wiki/04-privacy-security/privacy-model.md)
- [Local Storage Model](../../wiki/04-privacy-security/local-storage-model.md)

## User Story

As a user, I want to see my local photo records on a monthly calendar, so that I can revisit personal memories by date without using an account or server.

## Functional Requirements

- The app shows a monthly calendar as the default home experience.
- The current day is visually highlighted.
- Dates with records show a visible marker, using a dot or mini stack for MVP.
- Selecting a date opens the day record detail for that date.
- Empty dates communicate that no record exists yet and provide a path to add one.
- The calendar includes no public feed, friend activity, or account state.
- The calendar can surface a lightweight local-storage reassurance message when appropriate.

## Local Data Requirements

- Calendar summaries are derived from local data only.
- Date grouping uses local calendar dates.
- Thumbnail markers use locally stored thumbnails or generated previews.

## Acceptance Criteria

- Given the user opens the app, when the home screen loads, then the monthly calendar is visible without requiring login.
- Given today is in the current month, when the calendar renders, then today is visually distinct.
- Given a date has at least one record, when the calendar renders, then that date has a marker.
- Given a date has no record, when the calendar renders, then it does not show a record marker.
- Given the user taps a date, when records exist, then the day record detail opens for that date.
- Given the user taps an empty date, then the app offers a clear path to add a local record for that date.

## Open Questions

- Can a day have unlimited photo entries or a practical display limit?
- Is the calendar month based on device locale/timezone?
