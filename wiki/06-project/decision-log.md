---
type: project
status: active
owner: llm
updated: 2026-04-29
sources:
  - raw/sources/0001-llm-wiki-pattern.md
  - raw/sources/0002-claude-design-prompt.md
  - raw/meetings/2026-04-29-weekly-app-review/final-notes.md
---

# Decision Log

## [2026-04-27] Use Markdown LLM Wiki as persistent second brain

Decision:

- 프로젝트 지식은 `raw/`, `wiki/`, `specs/` 계층으로 관리한다.
- Codex 운영 스키마는 `AGENTS.md`에 둔다.

Rationale:

- Markdown은 Git diff, Obsidian, LLM 편집, Spec Kit 연동에 유리하다.
- SDD 전에 장기 기억을 구축해야 요구사항이 누락되지 않는다.

## [2026-04-27] Use wiki as parent layer of SDD

Decision:

- Spec Kit 명세는 wiki의 합의 내용을 기능별 실행 계약으로 변환한 산출물로 취급한다.

Rationale:

- PRD/디자인/로컬 데이터 정책이 흩어지면 spec이 쉽게 왜곡된다.
- 이 앱은 로컬 저장 신뢰가 제품, UI, 데이터 모델 모두에 영향을 준다.

## [2026-04-27] Pivot product to local-only personal image calendar

Decision:

- 제품 방향을 서버 없는 개인용 이미지 캘린더로 확정한다.
- 서버, 로그인, 친구 공유, 공개 피드, 좋아요, 댓글, 팔로우는 범위에서 제외한다.

Rationale:

- 사용자의 최신 원천 프롬프트가 “오직 나만 사용하는 프라이빗 기록 앱”을 명시했다.
- 핵심 신뢰 가치는 선택 공유가 아니라 디바이스 로컬 저장이다.

## [2026-04-27] Device-only storage is a product invariant

Decision:

- 모든 기록 데이터는 현재 디바이스에 저장한다.
- 외부로 데이터가 나가는 동작은 사용자가 명시적으로 실행하는 백업 내보내기뿐이다.

Rationale:

- 사용자가 외부 업로드를 오해하지 않아야 한다는 요구사항이 핵심 UX 원칙이다.

## [2026-04-28] Product planning kickoff decisions

Decision:

- 제품 약속은 `내 폰 안에만 쌓이는 하루 사진 캘린더`로 정한다.
- 1차 MVP 타깃은 하루 1-3장의 사진을 기록하는 Daily Photo Keeper다.
- 첫 사용 흐름은 온보딩 후 첫 기록 만들기 CTA로 연결하되, Calendar로 건너뛰는 선택지를 제공한다.
- 기록 추가는 사진 필수, 날짜 기본값 오늘, 메모/감정 태그 선택 입력으로 둔다.
- 캘린더 기록 표시는 점 또는 미니 스택으로 시작한다.
- 같은 날짜 기록은 생성 시간 오름차순으로 표시한다.
- 아카이브는 최근 월 우선의 월별 그룹을 기본으로 한다.
- 감정 태그는 MVP에서 고정 목록으로 시작한다.
- 백업은 내보내기 우선으로 MVP에 포함하고, 가져오기/복원은 정책과 품질이 확정될 때까지 후순위로 둔다.

Rationale:

- 회의에서 모든 기획 역할이 첫 기록 생성, 캘린더 누적감, 로컬 저장 신뢰를 MVP 핵심 루프로 합의했다.
- 가져오기/복원은 사용자 데이터 손실 리스크가 크므로 export-first 접근이 더 안전하다.

## [2026-04-28] MVP decision lock before implementation

Decision:

- Archive 검색은 메모와 로컬 날짜만 대상으로 한다.
- 감정 태그는 검색어 대상이 아니라 명시적 칩 필터로만 다룬다.
- Calendar에는 compact `내 폰에만 저장됨` 배지를 상시 노출한다.
- Settings MVP에서 `기본 사진 품질`과 `한 주 시작 요일` 설정은 제외한다.
- 첫 기록 저장 후에는 저장된 날짜의 Day Detail로 이동한다.

Rationale:

- 검색과 필터 역할을 분리하면 사용자가 예측하기 쉽고 구현/QA 범위가 줄어든다.
- 로컬 저장은 제품 신뢰의 핵심 약속이므로 일회성 안내가 아니라 지속적 reassurance로 다룬다.
- 사진 품질과 주 시작 요일은 이미지 정책과 캘린더 계산 범위를 넓히므로 핵심 루프 검증 후로 미룬다.
- Day Detail은 저장 직후 결과를 가장 명확하게 보여주는 화면이다.

## [2026-04-28] Android native stack and first vertical slice

Decision:

- MVP 구현은 Android Native Kotlin + Jetpack Compose로 진행한다.
- MVP는 single `:app` module과 package `com.kezlab.imagecalendar`로 시작한다.
- Room을 로컬 메타데이터 저장소로 사용한다.
- `PhotoEntry`와 `LocalAsset`을 핵심 테이블로 두고 `DayRecord` 테이블은 만들지 않는다.
- `localDate`는 `YYYY-MM-DD` 문자열로 저장한다.
- 사진은 Android Photo Picker로 선택하고, 앱 내부 저장소에 복사한다.
- DB에는 source URI나 절대경로가 아니라 relative asset path를 저장한다.
- 저장 시 썸네일을 생성한다.
- 첫 구현은 `001 + 002` 수직 슬라이스로 진행한다.
- 첫 슬라이스에서는 Camera를 제외하고 Gallery 선택만 완성한다.
- MVP manifest에는 `INTERNET` permission을 넣지 않는다.

Rationale:

- Android native가 사진 선택, 앱 내부 저장, 삭제, 백업 내보내기, 권한 UX를 가장 명확하게 제어한다.
- 앱 내부 복사본은 원본 갤러리 삭제/권한 만료와 무관하게 로컬 기록을 유지할 수 있다.
- relative path와 `PhotoEntry` 중심 모델은 추후 백업/복원 포맷으로 확장하기 쉽다.
- 첫 수직 슬라이스는 기록 저장부터 캘린더 반영까지 핵심 루프를 가장 빠르게 검증한다.

## [2026-04-29] Treat current app as prototype and prioritize persistence gate

Decision:

- 현재 앱은 release-ready MVP가 아니라 directionally valid prototype으로 분류한다.
- 다음 구현 사이클의 P0는 Room persistence, repository save contract, deterministic Android CLI QA fixture다.
- `createPhotoEntry`는 이미지 복사, 썸네일 생성, Room insert, 실패 시 cleanup rollback을 하나의 계약으로 책임진다.
- Archive/Settings placeholder는 release blocker로 분류한다.
- Android CLI smoke test를 weekly review와 release candidate 검증 gate로 둔다.
- Accessibility semantics, content descriptions, selected states, stable test tags는 각 feature 구현 시 필수로 넣는다.

Rationale:

- in-memory 상태에서는 앱 재시작 후 기록 유지, 아카이브 탐색, 설정 데이터 액션을 신뢰성 있게 검증할 수 없다.
- placeholder 화면은 로컬 저장 신뢰와 출시 가능한 MVP 인상을 훼손한다.
- Android CLI layout evidence에서 주요 clickable node의 라벨/description이 부족해 자동 회귀 검증이 좌표 의존으로 굳어질 위험이 있다.

## [2026-05-01] Day Detail mutation boundaries

Decision:

- MVP의 기록 수정은 날짜, 메모, 감정 태그 metadata만 다룬다.
- 사진 교체는 MVP 수정 범위에서 제외한다.
- 홈 Calendar의 최근 기록 요약 카드는 읽기 전용으로 유지한다.
- 수정/삭제 같은 변경 동작은 기록이 있는 날짜 셀에서 진입한 full Day Detail 화면에만 둔다.
- 삭제는 반드시 되돌릴 수 없는 로컬 삭제 확인 모달을 거친다.

Rationale:

- 사진 교체는 picker 재진입, 기존 파일 교체/rollback, 썸네일 재생성, 삭제 실패 처리까지 범위가 커져 Archive/Settings보다 우선순위가 낮다.
- 홈 요약 카드에 destructive action을 섞으면 Calendar 탐색 화면의 정보 구조가 흐려진다.
- Full Day Detail에 mutation을 모으면 사용자가 어떤 날짜/기록을 바꾸는지 더 명확하게 이해할 수 있다.

## [2026-05-01] MVP completion decisions

Decision:

- Archive는 in-memory filtering으로 구현하고, 대용량 최적화/FTS는 후속으로 둔다.
- Archive 검색은 메모, `YYYY-MM-DD`, `YYYY-MM`, `YYYY.MM`를 지원한다.
- 백업 내보내기는 MVP v1 zip package로 구현한다.
- 백업 zip은 `manifest.json`, original images, thumbnails를 포함한다.
- 가져오기/복원은 `준비 중`으로 표시하고 MVP 동작으로 제공하지 않는다.
- 전체 데이터 삭제는 Room records/assets와 앱 내부 entry files만 삭제한다.
- 전체 데이터 삭제는 onboarding completion과 app preferences를 유지한다.

Rationale:

- MVP는 "기록, 선택한 날짜로 다시 보기, 로컬 보관 신뢰"를 검증하는 릴리즈이므로 restore/import와 camera capture보다 Archive/Settings 완성도가 우선이다.
- 백업 export는 사용자가 직접 파일 위치를 고르는 방식이 로컬 전용 제품 약속과 가장 잘 맞는다.
- 온보딩 완료 상태는 사용자의 개인 기록이 아니므로 전체 기록 삭제 후에도 유지하는 편이 UX 혼란이 적다.
