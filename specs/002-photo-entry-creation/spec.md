# Spec: Photo Entry Creation

Status: draft  
Source wiki:

- [PRD](../../wiki/01-product/prd.md)
- [MVP Scope](../../wiki/01-product/mvp-scope.md)
- [User Flows](../../wiki/01-product/user-flows.md)
- [Screen Inventory](../../wiki/02-design/screen-inventory.md)
- [Local Storage Model](../../wiki/04-privacy-security/local-storage-model.md)
- [MVP Decision Lock](../../wiki/08-meetings/2026-04-28-mvp-decision-lock.md)

## User Story

As a user, I want to quickly save a daily photo with an optional note and emotion tag, so that my day is recorded privately on my device.

## Functional Requirements

- The Add Record screen provides gallery selection in the first vertical slice.
- Camera capture is deferred from the first vertical slice.
- A photo is required before saving.
- The record date defaults to today and can be changed before saving.
- Memo input is optional and short-form.
- Emotion tag selection is optional and uses a fixed MVP list.
- The screen clearly states that the record is saved on this device only.
- Saving creates a local record without login, server upload, sync, or sharing.
- After a successful save, the app navigates to the saved date's Day Detail.
- The saved record appears immediately on Day Detail.

## Local Data Requirements

- The record stores local date, image asset reference/path, optional memo, optional emotion tag, and timestamps.
- The app creates or updates the local date summary used by Calendar markers.
- The save flow must not require network access.
- The selected image is copied into app-specific internal storage.
- The source gallery URI is not stored as the long-term record reference.
- The database stores relative original/thumbnail asset paths.
- Save failure cleans up partial files when possible.

## Acceptance Criteria

- Given no photo is selected, when the user tries to save, then the app blocks saving and explains that a photo is required.
- Given a photo is selected, when the user saves without memo or emotion, then a valid local record is created.
- Given the user changes the date, when the record is saved, then it appears under the selected local date.
- Given save succeeds, then the app opens Day Detail for the saved local date.
- Given save succeeds, then the saved card is visible without requiring a manual refresh.
- Given the Add Record screen is visible, then no upload, sync, account, or sharing language is shown.

## Open Questions

- What exact thumbnail dimensions and compression format should be used?

## Locked Decisions

- Fixed MVP emotion tag list: `차분`, `기쁨`, `피곤`, `포근`, `바쁨`.
