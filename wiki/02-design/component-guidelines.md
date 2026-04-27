---
type: design
status: active
owner: llm
updated: 2026-04-28
sources:
  - raw/sources/0002-claude-design-prompt.md
---

# Component Guidelines

Current design reference: [Design System](design-system.md)

## Monthly Calendar

- 월간 레이아웃을 홈의 중심에 둔다.
- 오늘 날짜는 명확히 강조한다.
- 기록이 있는 날짜는 MVP에서 점 또는 미니 스택으로 표시한다.
- 밀도가 높아질 때도 날짜 숫자가 읽혀야 한다.

## Date Cell Thumbnail

- 기록이 있는 날짜를 빠르게 인지하게 한다.
- MVP에서는 날짜 가독성을 위해 점/미니 스택을 우선한다.
- 날짜 셀 사진 썸네일은 후속 실험으로 둔다.

## Image Card

- 사진, 메모, 감정 태그를 함께 보여준다.
- 수정/삭제 동작은 명확하되 카드의 주인공인 사진을 방해하지 않는다.

## Emotion Tag Chip

- 감정 태그는 빠르게 선택 가능한 짧은 라벨이어야 한다.
- 선택 상태와 비선택 상태가 명확해야 한다.
- MVP에서는 고정 목록으로 제공한다.

## Memo Input Field

- 짧은 메모 입력에 최적화한다.
- 긴 글쓰기 앱처럼 무겁게 보이지 않게 한다.
- 입력하지 않아도 저장할 수 있어야 한다.

## Floating Action Button

- 빠른 기록 추가 진입점이다.
- Calendar 홈에서 주요 CTA로 사용한다.

## Search Bar

- Archive에서 오래된 기록을 찾는 핵심 컴포넌트다.
- 검색 대상이 메모/날짜/태그 중 무엇인지 힌트를 제공한다.

## Empty State

- 빈 날짜에는 “아직 기록이 없어요”처럼 차분한 메시지를 사용한다.
- 바로 기록 추가로 이어지는 CTA를 제공한다.

## Local Storage Badge

- `내 폰에만 저장됨`처럼 짧고 명확한 문구를 사용한다.
- 온보딩, Add, Settings에서 특히 중요하다.

## Backup and Restore Buttons

- MVP에서는 백업 파일 내보내기를 우선한다.
- 가져오기/복원은 정책이 확정될 때까지 안내 또는 후속 진입점으로 둔다.
- 전체 데이터 삭제 버튼과 시각적으로 구분한다.

## Delete Confirmation Modal

- 삭제 대상과 되돌릴 수 없는 결과를 명확히 설명한다.
- 위험 버튼은 다른 주요 버튼과 색상/위계를 구분한다.

## Bottom Tab Bar

탭 순서:

1. Calendar
2. Add
3. Archive
4. Settings
