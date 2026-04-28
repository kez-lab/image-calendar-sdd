---
type: project
status: active
owner: llm
updated: 2026-04-28
sources:
  - raw/sources/0001-llm-wiki-pattern.md
  - raw/sources/0002-claude-design-prompt.md
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
