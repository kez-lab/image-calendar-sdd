---
type: design
status: active
owner: llm
updated: 2026-04-28
sources:
  - raw/sources/0002-claude-design-prompt.md
---

# Claude Design Brief

이 페이지는 Claude Design에 전달할 디자인 의도를 정규화한 문서다. 원문 프롬프트는 [raw source](../../raw/sources/0002-claude-design-prompt.md)에 보관한다.

## Goal

iPhone 15 Pro 기준 비율로 6개 주요 화면을 한 번에 보여주는 로컬 전용 개인 이미지 캘린더 MVP 디자인을 생성한다.

## Product Concept

서버, 로그인, 친구 공유 없이 사진, 메모, 감정 태그를 날짜별로 기록하고 다시 보는 프라이빗 기록 앱.

Product promise:

“내 폰 안에만 쌓이는 하루 사진 캘린더”

## Required Screens

1. Onboarding
2. Calendar Home
3. Day Record Detail
4. Add Record
5. Archive
6. Settings

## Visual Direction

- 따뜻하고 조용한 분위기
- 감성적이지만 과하지 않은 미니멀 UI
- 아이보리, 라이트 그레이, 연한 블루 또는 라벤더 계열
- 둥근 카드
- 적당한 여백
- 부드러운 그림자
- 사진이 주인공이 되는 차분한 구성

## UX Must-Haves

- 서버 업로드나 외부 공유로 오해되지 않아야 한다.
- 로컬 저장 상태를 명확하게 전달해야 한다.
- 첫 사용자는 첫 기록 만들기로 자연스럽게 유도되어야 한다.
- 기록 작성은 30초 안에 끝날 수 있어야 한다.
- 날짜별 탐색과 아카이브 탐색이 쉬워야 한다.
- 삭제, 백업, 복원은 안전하고 친절하게 보여야 한다.
- 터치 영역과 텍스트 대비를 고려해야 한다.

## After Claude Output

Claude Design 결과물이 나오면 아래 문서를 갱신한다.

- [Design Principles](design-principles.md)
- [Screen Inventory](screen-inventory.md)
- [Component Guidelines](component-guidelines.md)
- [Accessibility](accessibility.md)
