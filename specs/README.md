# Specs

`specs/`는 wiki의 합의 내용을 기능별 SDD 명세로 변환하는 영역이다.

추천 초기 spec:

1. `001-calendar-daily-record`: 캘린더 기반 하루 기록 조회
2. `002-photo-entry-creation`: 사진, 메모, 선택형 감정 태그 기록 작성
3. `003-archive-search-filter`: 아카이브, 메모/날짜 검색, 감정 태그 필터
4. `004-local-backup-restore`: 백업 파일 내보내기 우선, 가져오기/복원 후속 검토
5. `005-settings-data-safety`: 설정, 로컬 저장 안내, 전체 데이터 삭제
6. `006-onboarding`: 첫 실행 가치 설명과 로컬 저장 안내

각 spec은 최소한 아래 파일을 가진다.

- `spec.md`: 사용자 가치, 요구사항, 인수 기준
- `plan.md`: 기술 접근, 데이터 영향, 위험
- `tasks.md`: 구현 태스크

Spec 작성 전에는 관련 wiki 페이지를 먼저 갱신한다.

Current implementation-ready drafts:

- `001-calendar-daily-record`
- `002-photo-entry-creation`
- `003-archive-search-filter`
- `005-settings-data-safety`
- `006-onboarding`
