---
type: design
status: active
owner: llm
updated: 2026-04-28
sources:
  - raw/design/2026-04-28-app-image-calendar/ImageCalendar.html
  - raw/design/2026-04-28-app-image-calendar/design-canvas.jsx
  - raw/design/2026-04-28-app-image-calendar/ios-frame.jsx
---

# Design Output Review: App Image Calendar

## Source

Raw design source: [2026-04-28 App Image Calendar](../../raw/design/2026-04-28-app-image-calendar/README.md)

## Overall Assessment

이 디자인 산출물은 MVP의 큰 방향과 잘 맞는다. 6개 핵심 화면이 모두 있고, 따뜻한 아이보리/라벤더 계열, 둥근 카드, 여백, 로컬 저장 배지, 설정의 로컬 저장 안내 카드가 제품 톤과 맞다.

다만 회의에서 확정한 일부 결정과 충돌하는 부분이 있다. 구현 전 디자인 수정 또는 구현 해석 단계에서 보정해야 한다.

Superseded by: [Design Output Review v2](design-output-review-2026-04-28-v2.md)

## What Works

- 6개 주요 화면이 모두 포함되어 있다: Onboarding, Calendar, Day Detail, Add Record, Archive, Settings.
- 컬러 방향은 제품 의도와 맞다: warm ivory, soft gray, lavender, sage/teal accent.
- `내 폰에만 저장됨` 로컬 저장 배지가 명확하다.
- Settings 상단의 로컬 저장 trust card는 채택 가치가 높다.
- Day Detail은 같은 날짜 여러 기록을 카드 리스트로 잘 표현한다.
- Add 화면은 카메라/갤러리, 날짜, 메모, 감정 태그, 저장 흐름을 한 화면에 담고 있다.
- Archive는 최근 월 우선 월별 그룹과 이미지 그리드로 탐색성을 보여준다.

## Required Corrections Before Implementation

디자이너에게 전달할 수정 요청서는 [Designer Feedback Request](../../raw/design/2026-04-28-app-image-calendar/designer-feedback-request.md)에 정리했다.

### Onboarding CTA

Current:

- 마지막 버튼이 `시작하기`.
- 보조 버튼이 `이미 데이터가 있어요 →`.

Required:

- 기본 CTA는 `첫 기록 만들기`.
- 보조 CTA는 `건너뛰고 캘린더 보기`.
- `이미 데이터가 있어요`는 가져오기/복원을 암시하므로 현재 MVP 방향과 맞지 않는다.

### Calendar Marker

Current:

- 기록이 있는 날짜에 38x38 이미지 썸네일을 사용한다.
- 온보딩 문구도 “캘린더 위에 썸네일”이라고 설명한다.

Required:

- MVP에서는 점 또는 미니 스택을 기본 마커로 사용한다.
- 날짜 셀 사진 썸네일은 후속 실험으로 둔다.
- 날짜 숫자 가독성을 우선한다.

### Copy Guardrails

Current:

- `서버 업로드 없이`, `외부 서버로 전송되지 않아요` 문구가 있다.
- Settings에는 가져오기 row가 실제 기능처럼 보인다.

Required:

- 핵심 UI에서는 `업로드`, `동기화`, `클라우드`, `공유`, `게시`, `피드` 표현을 피한다.
- 추천 문구: `서버로 보내지 않아요`, `이 기기에만 저장돼요`, `계정 없이 사용할 수 있어요`.
- 가져오기/복원은 후속 기능 또는 정책 확정 전 안내로 표현한다.

### Add Record Optionality

Current:

- 감정 태그가 기본 선택되어 있어 필수처럼 보일 수 있다.

Required:

- 사진은 필수, 메모와 감정 태그는 선택 입력으로 보여야 한다.
- 감정 태그는 `선택 안 함` 상태를 허용하거나 기본 선택을 제거한다.

### Settings Backup Scope

Current:

- `데이터 내보내기`와 `데이터 가져오기`가 같은 위계로 보인다.

Required:

- MVP에서는 `백업 파일 내보내기`를 우선 CTA로 둔다.
- `가져오기/복원`은 후속 기능, 준비 중, 또는 정책 확정 필요 상태로 낮은 위계에 둔다.

## Adopted Design Decisions

- Design tokens from this output can seed the first implementation.
- Archive direction can start as recent-month grouped image grid.
- Settings should include a prominent local storage trust card.
- Bottom tab structure remains Calendar, Add, Archive, Settings.
- Day Detail uses card-based records with photo, time, memo, emotion tag, edit/delete actions.

## Design Tokens Extracted

Colors:

- Background: `#FAFAF7`
- Secondary background: `#F3F0EB`
- Card: `#FFFFFF`
- Border: `#E8E4DD`
- Primary text: `#2C2825`
- Secondary text: `#7A746C`
- Muted text: `#B0A99F`
- Lavender accent: `#7C6FC4`
- Lavender light: `#EAE7F8`
- Warm accent: `#C8815A`
- Teal accent: `#5A9EA0`
- Local-safe green: `#6BAE8C`

Typography:

- Display: `DM Serif Display`
- Body/UI: `DM Sans`
- Native implementation should map these to available bundled fonts or platform typography tokens.

Shape:

- Cards: 16-18px radius
- Image cards: 10-14px radius
- Chips/badges: 20px radius
- iPhone artboard: 393 x 852

## Implementation Notes

- The HTML uses external CDN scripts and Google Fonts. Treat it as a design reference, not production source.
- The source includes an icon named `upload`; implementation should avoid user-facing upload wording.
- The design is iOS-framed but the eventual Android implementation should translate visual tokens into platform-native components.
