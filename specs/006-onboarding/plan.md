# Plan: Onboarding

Status: implemented

## Technical Approach

Render onboarding before the main `Scaffold` when local preference `onboarding_complete` is false. Persist completion in app-local shared preferences.

## Data Impact

- Adds one local preference: `onboarding_complete`.
- Full record deletion preserves this preference because it is not user record data.

## UI Impact

Screens involved:

- Onboarding
- Add Record
- Calendar

Components involved:

- Value cards
- Primary CTA
- Secondary CTA

## Verification

Official Android CLI layout/screenshot evidence verifies first launch onboarding and CTA visibility.
