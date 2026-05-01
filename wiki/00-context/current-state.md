---
type: context
status: active
owner: llm
updated: 2026-05-01
sources:
  - raw/sources/0001-llm-wiki-pattern.md
  - raw/sources/0002-claude-design-prompt.md
  - raw/design/2026-04-28-app-image-calendar/ImageCalendar.html
  - raw/design/2026-04-28-app-image-calendar-v2/ImageCalendar v2.html
  - raw/meetings/2026-04-29-weekly-app-review/final-notes.md
  - raw/verification/2026-05-01-day-detail-edit-delete/README.md
  - raw/verification/2026-05-01-mvp-completion/README.md
---

# Current State

## Repository

- 현재 저장소는 LLM Wiki/SDD 문서 구조를 먼저 구축하고 있다.
- Android 애플리케이션 코드와 Gradle 빌드 시스템이 초기화되었다.
- 실제 Spec Kit 산출물은 아직 없다.
- GitHub private repository는 `kez-lab/image-calendar-sdd`로 생성되어 있다.

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
- Archive 검색은 메모와 로컬 날짜만 대상으로 하며, 감정 태그는 칩 필터로만 처리한다.
- 첫 기록 저장 후에는 저장된 날짜의 Day Detail로 이동한다.

## Design

- Claude Design 첫 MVP 결과물이 생성되어 raw design source로 저장되었다.
- v2 디자인은 주요 수정 요청을 대부분 반영했고, 현재 구현 기준 디자인으로 사용할 수 있다.
- 디자인 기준은 iPhone 15 Pro 비율의 6개 주요 화면이다.
- iOS 스타일을 기본으로 하되 Android에도 무리 없이 적용 가능한 UI를 목표로 한다.
- 현재 design reference는 8개 artboard다: Onboarding, Calendar, Day Detail, Add Record, Archive, Settings, Delete Record Confirmation, Delete All Confirmation.
- Calendar의 `내 폰에만 저장됨` 배지는 compact 형태로 상시 노출한다.
- Settings의 `기본 사진 품질`과 `한 주 시작 요일`은 MVP에서 제외한다.

## Engineering

- 서버 없이 로컬 저장만 사용하는 구조를 전제로 한다.
- 기술 스택은 Android Native Kotlin + Jetpack Compose로 확정되었다.
- 로컬 메타데이터는 Room으로 저장한다.
- 사진은 Android Photo Picker로 선택해 앱 내부 저장소에 복사한다.
- `localDate`는 `YYYY-MM-DD`로 저장한다.
- MVP는 `INTERNET` permission 없이 구현한다.
- 백업은 로컬 파일 내보내기 우선으로 다루고, 가져오기/복원은 정책 확정 후 후속으로 다룬다.
- 현재 앱은 Compose app shell, Calendar, Add Record, Day Detail, Archive placeholder, Settings placeholder를 가진다.
- `./gradlew assembleDebug`는 통과했다.
- 공식 Android CLI로 debug APK 설치/실행, layout dump, screen capture가 확인되었다.
- 기록 상태는 Room `photo_entries`/`local_assets`와 repository-backed Flow로 전환되었다.
- Android CLI debug fixture로 저장한 기록이 앱 재실행 후 Calendar와 Day Detail에 유지되는 것을 확인했다.
- Day Detail에서 기록의 날짜/메모/감정 태그 수정과 삭제 확인 모달을 구현했다.
- 삭제 후 해당 날짜가 빈 상태로 전환되고 Calendar marker가 제거되는 것을 Android CLI로 검증했다.
- Onboarding, Calendar empty-date CTA, Archive 검색/감정 필터/월별 목록, Settings 백업 zip 내보내기/복원 준비중/전체 삭제 확인을 구현했다.
- 2026-05-01 MVP completion 검증에서 Android CLI로 onboarding, add, day detail, archive, settings, backup export, delete-all, restart empty state를 확인했다.
- 2026-04-29 주간 앱 리뷰에서 현재 앱은 release-ready MVP가 아니라 directionally valid prototype으로 분류되었다.
- 2026-05-01 기준으로는 internal QA용 MVP feature-complete 상태로 승격되었다.
- Archive와 Settings placeholder는 제거되었다.
- Android CLI smoke test는 weekly/release verification gate로 사용한다.

## Next Actions

1. Android CLI MVP journey를 재사용 가능한 스크립트 또는 문서화된 릴리즈 체크리스트로 고정한다.
2. 구현 화면 스크린샷을 외부 디자이너에게 전달해 v2 디자인 싱크를 검수한다.
3. 백업/삭제 문구는 public release 전에 privacy/legal 관점 검토를 받는다.
4. 다음 제품 increment를 Camera capture 또는 restore/import 중에서 선택한다.
