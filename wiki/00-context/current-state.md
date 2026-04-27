---
type: context
status: active
owner: llm
updated: 2026-04-28
sources:
  - raw/sources/0001-llm-wiki-pattern.md
  - raw/sources/0002-claude-design-prompt.md
  - raw/design/2026-04-28-app-image-calendar/ImageCalendar.html
  - raw/design/2026-04-28-app-image-calendar-v2/ImageCalendar v2.html
---

# Current State

## Repository

- 현재 저장소는 LLM Wiki/SDD 문서 구조를 먼저 구축하고 있다.
- 아직 애플리케이션 코드, 빌드 시스템, 실제 Spec Kit 산출물은 없다.
- git repository 초기화 여부는 아직 결정되지 않았다.

## Documentation

- `raw/`에 LLM Wiki 패턴과 로컬 전용 이미지 캘린더 디자인 프롬프트를 원천 자료로 보관했다.
- `raw/design/2026-04-28-app-image-calendar/`에 첫 Claude Design 결과물을 보관했다.
- `raw/design/2026-04-28-app-image-calendar-v2/`에 v2 디자인과 디자인 시스템 산출물을 보관했다.
- `wiki/`에 회사 팀형 문서 구조를 만들었다.
- `specs/`에 초기 SDD 후보 기능 폴더를 만들었다.
- `001-calendar-daily-record`는 `spec.md`, `plan.md`, `tasks.md` 초안을 가진다.

## Product

- 제품 방향은 서버 없는 개인용 이미지 캘린더로 정의되었다.
- MVP의 핵심은 기록, 날짜별 탐색, 아카이브, 로컬 데이터 신뢰다.
- SNS, 친구 공유, 로그인, 공개 피드는 범위에서 제외한다.

## Design

- Claude Design 첫 MVP 결과물이 생성되어 raw design source로 저장되었다.
- v2 디자인은 주요 수정 요청을 대부분 반영했고, 현재 구현 기준 디자인으로 사용할 수 있다.
- 디자인 기준은 iPhone 15 Pro 비율의 6개 주요 화면이다.
- iOS 스타일을 기본으로 하되 Android에도 무리 없이 적용 가능한 UI를 목표로 한다.
- 현재 design reference는 8개 artboard다: Onboarding, Calendar, Day Detail, Add Record, Archive, Settings, Delete Record Confirmation, Delete All Confirmation.

## Engineering

- 서버 없이 로컬 저장만 사용하는 구조를 전제로 한다.
- 기술 스택은 아직 확정되지 않았다.
- 백업은 로컬 파일 내보내기 우선으로 다루고, 가져오기/복원은 정책 확정 후 후속으로 다룬다.

## Next Actions

1. 고정 감정 태그 목록, 첫 저장 후 이동 위치, Calendar local badge persistence, Settings extras 포함 여부를 결정한다.
2. Spec Kit을 도입하고 `001-calendar-daily-record` 초안을 템플릿에 맞춰 정제한다.
3. 로컬 저장 기술 스택과 앱 아키텍처를 확정한다.
