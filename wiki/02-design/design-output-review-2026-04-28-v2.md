---
type: design
status: active
owner: llm
updated: 2026-04-28
sources:
  - raw/design/2026-04-28-app-image-calendar-v2/ImageCalendar v2.html
  - raw/design/2026-04-28-app-image-calendar-v2/Design System.html
  - raw/design/2026-04-28-app-image-calendar-v2/design-canvas.jsx
  - raw/design/2026-04-28-app-image-calendar-v2/ios-frame.jsx
---

# Design Output Review: App Image Calendar v2

## Source

Raw design source: [2026-04-28 App Image Calendar v2](../../raw/design/2026-04-28-app-image-calendar-v2/README.md)

## Overall Assessment

v2는 v1 피드백을 대부분 반영했다. 현재 기준으로는 구현 기준 디자인으로 사용할 수 있는 수준이다. 특히 온보딩 CTA, 캘린더 마커, Add 입력 필수/선택 구분, Settings 백업 위계, 삭제 확인 모달, 디자인 시스템 페이지가 명확히 개선되었다.

## What Improved

- Onboarding 메인 CTA가 `첫 기록 만들기`로 변경되었다.
- 보조 CTA가 `건너뛰고 캘린더 보기`로 변경되어 가져오기/복원 오해를 줄였다.
- Calendar 날짜 표시는 사진 썸네일에서 dot/mini stack으로 변경되었다.
- Add Record에서 사진은 `필수`, 메모와 감정 태그는 `선택`으로 구분된다.
- 감정 태그는 기본 선택이 제거되어 선택 입력처럼 보인다.
- Local copy가 `이 기기에만 저장돼요`, `서버로 보내지 않아요`로 정리되었다.
- Settings에서 `백업 파일 내보내기`가 우선 CTA가 되었다.
- `백업에서 복원하기`는 `준비 중`으로 낮은 위계가 되었다.
- 위험 영역이 분리되었다.
- 개별 기록 삭제 확인과 전체 데이터 삭제 확인 모달이 추가되었다.
- 디자인 시스템 페이지가 별도로 추가되었다.

## Remaining Issues

### Search Scope Copy

Archive 검색 placeholder는 `메모, 날짜로 검색하기`로 되어 있다. 현재 PRD는 감정 태그 필터를 별도로 제공하므로 큰 문제는 아니지만, 검색 범위 open question이 남아 있다.

Decision needed:

- 검색이 메모+날짜만인지, 감정 태그까지 포함하는지 확정해야 한다.

### Calendar Local Badge Persistence

Calendar에 `내 폰에만 저장됨` 배지가 표시된다. 현재 기획에는 “항상 표시 vs 초기 사용 구간 강조”가 open question으로 남아 있다.

Decision needed:

- Calendar local badge를 상시 노출할지, 첫 사용 후 축소/숨김 처리할지 결정해야 한다.

### App Settings Scope

Settings에 `기본 사진 품질`, `한 주 시작 요일`이 포함되어 있다. 둘 다 유용하지만 MVP scope에 명시되어 있지는 않다.

Decision needed:

- MVP 설정에 포함할지, 후속으로 둘지 결정해야 한다.

## Adopted As Implementation Reference

v2에서 아래 항목은 구현 기준으로 채택 가능하다.

- 8개 아트보드 구성: Onboarding, Calendar, Day Detail, Add Record, Archive, Settings, Delete Record Modal, Delete All Modal
- Calendar dot/mini stack marker
- Local storage badge
- Settings local trust card
- Backup export primary CTA
- Restore/import muted coming-soon row
- Danger zone separation
- Delete confirmation bottom sheets
- Emotion chip optional state
- Archive month-grouped image grid

## Implementation Readiness

Status: usable as first implementation reference.

Before code implementation:

- Extract final design tokens into [Design System](design-system.md).
- Reflect 8 artboards in [Screen Inventory](screen-inventory.md).
- Decide whether Settings extras are MVP.
- Decide Calendar local badge persistence.
