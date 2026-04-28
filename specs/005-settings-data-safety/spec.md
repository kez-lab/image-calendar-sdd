# Spec: Settings Data Safety

Status: draft  
Source wiki:

- [Privacy Model](../../wiki/04-privacy-security/privacy-model.md)
- [Local Storage Model](../../wiki/04-privacy-security/local-storage-model.md)
- [Acceptance Criteria](../../wiki/05-qa/acceptance-criteria.md)
- [MVP Decision Lock](../../wiki/08-meetings/2026-04-28-mvp-decision-lock.md)

## User Story

As a user, I want Settings to clearly explain where my records are stored and provide safe data actions, so that I trust the app with private daily photos.

## Functional Requirements

- Settings clearly states that records are stored on the current device only.
- Settings provides a backup export action.
- Settings may show import/restore as a disabled or coming-soon row, not as a working MVP action.
- Settings provides a full data deletion action in a separate danger zone.
- Full data deletion requires a clear confirmation modal.
- MVP Settings excludes `Default photo quality`.
- MVP Settings excludes `Week start day`.
- Settings contains no login, account, sync, cloud, sharing, or social controls.

## Local Data Requirements

- Data deletion removes local records and associated local assets according to the deletion policy.
- Backup export is user-initiated and produces a local file/package.
- No settings field is needed for photo quality or week-start day in MVP.

## Acceptance Criteria

- Given Settings is visible, then the user can read that records are saved on this device only.
- Given the user taps backup export, then the app starts a user-initiated export flow.
- Given import/restore is shown, then it is clearly marked as not available in MVP.
- Given the user starts full data deletion, then a confirmation modal explains deletion scope and irreversibility.
- Given the user cancels deletion, then no data is removed.
- Given Settings is visible, then no photo quality or week-start setting is shown in MVP.
- Given Settings is visible, then no account, login, sync, cloud, or sharing controls are shown.

## Open Questions

- What exact backup file format should be used?
- Should full data deletion reset onboarding completion and local app preferences?
