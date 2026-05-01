# Spec: Onboarding

Status: implemented  
Source wiki:

- [User Flows](../../wiki/01-product/user-flows.md)
- [Screen Inventory](../../wiki/02-design/screen-inventory.md)
- [Local Storage Model](../../wiki/04-privacy-security/local-storage-model.md)

## User Story

As a first-time user, I want to understand that Image Calendar is a private local photo record app, so that I can start recording without thinking it is a social or server-backed service.

## Functional Requirements

- Onboarding appears on first launch before the tabbed app shell.
- Onboarding explains three values:
  - 하루를 사진으로 기록하세요.
  - 날짜별로 캘린더에 정리하세요.
  - 내 폰에만 저장되는 프라이빗 기록.
- Primary CTA opens Add Record.
- Secondary CTA opens Calendar.
- Completion is stored locally so onboarding is not shown every launch.
- Onboarding contains no account setup, public profile, friend, feed, or sharing concept.

## Acceptance Criteria

- Given the app is first opened, then onboarding is visible.
- Given the user taps `첫 기록 만들기`, then Add Record opens.
- Given the user taps `건너뛰고 캘린더 보기`, then Calendar opens.
- Given onboarding has been completed, then relaunch opens the tabbed app without showing onboarding again.
- Given onboarding is visible, then local-only copy is clear.
