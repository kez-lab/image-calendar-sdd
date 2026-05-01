---
type: project
status: active
owner: llm
updated: 2026-05-01
sources:
  - raw/sources/0002-claude-design-prompt.md
  - ../../raw/verification/2026-05-01-mvp-completion/README.md
---

# Release Plan

## MVP Release Definition

MVP 릴리즈는 “사진으로 하루를 기록하고, 날짜별로 다시 찾고, 내 폰에만 안전하게 보관된다”는 경험을 검증하는 릴리즈다.

## Release Must-Haves

- 로그인 없는 시작
- 온보딩의 로컬 저장 안내
- 날짜별 캘린더 조회
- 사진/메모/감정 태그 기록 작성
- 날짜별 기록 상세
- 아카이브 검색/필터
- 설정의 로컬 저장 안내
- 백업 파일 내보내기
- 가져오기/복원 후속 안내
- 전체 데이터 삭제 확인 모달

## Release Blockers

- 서버 업로드나 외부 공유로 오해될 수 있는 문구
- 기록 작성 플로우가 과도하게 긴 상태
- 캘린더에서 기록 유무가 불명확한 상태
- 삭제/백업 내보내기 결과가 불명확한 상태
- 접근성상 핵심 버튼 사용이 어려운 상태
- Room persistence 없이 in-memory 상태만 사용하는 상태
- Archive 또는 Settings에 placeholder/internal development copy가 남아 있는 상태
- Android CLI smoke test로 핵심 기록 루프를 재현할 수 없는 상태

## Current Readiness

Status: internal QA MVP feature-complete.

Completed on 2026-05-01:

- Onboarding
- Calendar empty-date add CTA
- Add Record local save
- Day Detail edit/delete
- Archive search/filter/month grouping
- Settings backup export and delete-all confirmation
- Android CLI MVP completion evidence

Before public release:

- External designer review against implementation screenshots is recommended.
- Privacy/legal review of backup/delete copy is recommended.
- Android CLI journey should be converted into a repeatable release checklist or script.
