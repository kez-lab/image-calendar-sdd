---
type: design
status: active
owner: llm
updated: 2026-04-28
sources:
  - raw/design/2026-04-28-app-image-calendar-v2/Design System.html
  - raw/design/2026-04-28-app-image-calendar-v2/ImageCalendar v2.html
---

# Design System

## Status

Active draft extracted from v2 design output. This is the current implementation reference unless v3 supersedes it.

## Color Tokens

| Token | Value | Usage |
| --- | --- | --- |
| `--color-bg` | `#FAFAF7` | 기본 화면 배경, 카드 외부 영역 |
| `--color-bg2` | `#F3F0EB` | 보조 배경, 입력 필드, 비활성 탭 배경 |
| `--color-bg3` | `#EAE6DF` | 강조 구분선, hover state, disabled 배경 |
| `--color-card` | `#FFFFFF` | 카드 컴포넌트, 모달, bottom sheet 배경 |
| `--color-border` | `#E8E4DD` | 구분선, 카드 테두리, 입력 필드 테두리 |
| `--color-text` | `#2C2825` | 주요 텍스트 |
| `--color-text2` | `#7A746C` | 보조 텍스트 |
| `--color-text3` | `#B0A99F` | 힌트, placeholder, muted label |
| `--color-accent` | `#7C6FC4` | 주요 액션, 활성 탭, 포커스, 선택 상태 |
| `--color-accent-light` | `#EAE7F8` | 선택 칩 배경, 약한 accent surface |
| `--color-good` | `#6BAE8C` | 로컬 저장 배지, 신뢰 카드, 저장 성공 |
| `--color-good-light` | `#E4F2EB` | 로컬 저장 배지 배경 |
| `--color-danger` | `#D94F3D` | 삭제 버튼, 위험 영역, 에러 상태 |
| `--color-danger-light` | `#FDECEA` | 위험 영역 배경, 삭제 확인 모달 보조 배경 |
| `--color-disabled` | `#D8D3CC` | 비활성 버튼 텍스트, 비활성 아이콘 |
| `--color-disabled-bg` | `#F0EDE8` | 비활성 버튼 배경 |
| `--color-warm` | `#C8815A` | 캘린더 날짜 marker, warm accent |
| `--color-warm-light` | `#F5EBE4` | warm chip background |
| `--color-teal` | `#5A9EA0` | calm/emotion accent |
| `--color-teal-light` | `#E4F2F2` | calm/emotion chip background |

## Typography

| Token | Font | Size | Weight | Line Height | Usage |
| --- | --- | --- | --- | --- | --- |
| `Display` | DM Serif Display | 28 | 400 | 1.25 | Onboarding headline |
| `Title L` | DM Serif Display | 22 | 400 | 1.3 | Month title, section title |
| `Title M` | DM Serif Display | 18 | 400 | 1.35 | Date header |
| `Body L` | DM Sans | 16 | 400 | 1.6 | Button label, input value |
| `Body M` | DM Sans | 14 | 400 | 1.65 | Card body, setting row, memo |
| `Body S` | DM Sans | 13 | 400 | 1.7 | Support copy, emotion chip |
| `Label` | DM Sans | 12 | 600 | 1.4 | Section label |
| `Caption` | DM Sans | 11 | 400 | 1.5 | Timestamp, metadata |
| `Micro` | DM Sans | 10 | 500 | 1.4 | Tab label, badge text |
| `Mono` | DM Mono | 11 | 400 | 1.6 | Token/code display |

Android font mapping:

- DM Serif Display -> Noto Serif KR, weight 400
- DM Sans -> Pretendard or Noto Sans KR
- DM Mono -> JetBrains Mono or Noto Sans Mono

## Spacing

| Token | Value | Usage |
| --- | --- | --- |
| `--sp-1` | 4px | Icon gap, micro spacing |
| `--sp-2` | 8px | Chip padding, icon/text gap |
| `--sp-3` | 12px | Small card padding, section gap |
| `--sp-4` | 16px | Card padding, settings row horizontal padding |
| `--sp-5` | 20px | Default screen horizontal padding |
| `--sp-6` | 24px | Modal padding, large section gap |
| `--sp-7` | 28px | CTA horizontal padding |
| `--sp-8` | 32px | Onboarding spacing, large separation |

## Radius

| Token | Value | Usage |
| --- | --- | --- |
| `--r-chip` | 20px | Emotion chip, filter button, badge |
| `--r-btn` | 16px | Primary CTA |
| `--r-btn-sm` | 20px | Small pill button |
| `--r-card` | 16px | General card, settings group |
| `--r-card-lg` | 18px | Photo record card |
| `--r-img` | 12px | Archive thumbnail, preview image |
| `--r-img-sm` | 8px | Small image, future compact marker |
| `--r-modal` | 24px | Bottom sheet top radius |
| `--r-input` | 12px | Text input field |
| `--r-icon` | 10px | Settings row icon container |

## Components

## Primary Button

- Height: 52px
- Radius: `--r-btn`
- Background: `--color-accent`
- Text: white, 16px, 600
- Minimum touch target: 44px

## Secondary / Text Button

- No background
- Text color: `--color-text3`
- Minimum touch target: 44px through padding

## Floating Add Button

- Diameter: 46px
- Radius: 23px
- Background: `--color-accent`
- Shadow: accent tinted
- Position: centered in bottom tab, visually raised

## Calendar Date Marker

- No record: date number only
- One record: one 4px dot
- Multiple records: up to three 4px dots
- Today: `--color-accent-light` background and bold accent date number
- Rule: no photo thumbnail in MVP calendar cell

## Local Storage Badge

- Text: `내 폰에만 저장됨`
- Icon: lock
- Background: `--color-good-light`
- Border: `--color-good` with low opacity
- Used in Onboarding, Calendar, Add

## Local Storage Trust Card

- Used at top of Settings
- Gradient from `--color-good-light` to `--color-teal-light`
- Includes lock icon, title, explanatory copy

## Emotion Chip

- Default: background `--color-bg2`, text `--color-text2`
- Selected: background `--color-accent-light`, outline `--color-accent`
- Disabled: background `--color-bg3`, text `--color-disabled`
- MVP rule: no default selection

## Record Card

- Background: `--color-card`
- Radius: `--r-card-lg`
- Image height reference: 180px
- Body padding: 12px vertical, 14px horizontal
- Includes time, edit/delete actions, memo, optional emotion chip

## Search Bar

- Background: `--color-bg2`
- Radius: `--r-input`
- Height: 44px
- Placeholder color: `--color-text3`

## Archive Month Group

- Recent month first
- Month label: Title style
- Record count: 11px muted text
- Grid: 3 columns, 6px gap
- Image radius: `--r-img`

## Empty State

- Illustration placeholder
- Short explanation
- Secondary CTA: `첫 기록 추가`

## Settings Row

- Minimum height: 50px
- Icon container: 32x32, radius `--r-icon`
- Label: 14px, 500
- Subcopy: 11px muted

## Backup Export Button

- Primary settings CTA
- Accent border
- Icon + title + subcopy
- Higher hierarchy than normal settings row

## Delete Confirmation Modal

- Bottom sheet
- Top radius: `--r-modal`
- Drag handle
- Clear delete target preview
- Cancel + danger confirm buttons

## Delete All Confirmation Modal

- Bottom sheet
- Warning icon
- Checklist warning
- Includes `먼저 백업 파일 내보내기`
- Final `전체 삭제` danger button

## States

- Loading: neutral surface, loading copy
- Error: danger light surface, danger text/icon
- Success/Saved: good light surface, good text/icon
- Disabled: disabled background and disabled text
- Selected: accent light background and accent outline/text
- Danger: danger color and explicit destructive copy

## Accessibility

Contrast notes from design system:

- `text` on `bg`: 13.6:1
- `text2` on card: 4.7:1
- `text3` on bg: 2.5:1, use only for hints/placeholders
- `accent` on white: 4.6:1
- white on accent: 4.6:1
- `danger` on white: 4.5:1
- white on danger: 4.5:1
- `good` on `good-light`: 3.1:1, use with icon and avoid critical small-only text

Touch target rules:

- Primary buttons: 52px high
- Text buttons: minimum 44px target via padding
- Small icon buttons must add invisible padding to reach 44px
