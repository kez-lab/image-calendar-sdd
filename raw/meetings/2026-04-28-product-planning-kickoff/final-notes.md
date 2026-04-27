# Final Notes: Product Planning Kickoff

Date: 2026-04-28

## Consensus

- 핵심 제품 약속은 `내 폰 안에만 쌓이는 하루 사진 캘린더`로 정리한다.
- 보조 포지셔닝은 `사진첩보다 정리되고 다이어리보다 가벼운 개인 기록 도구`로 사용한다.
- MVP 타깃은 하루 1-3장의 사진을 개인적으로 기록하는 Daily Photo Keeper다.
- 첫 사용 경험은 온보딩 후 첫 기록 만들기로 연결하되 건너뛰기 선택지를 제공한다.
- 기록 추가는 사진 필수, 날짜 기본값 오늘, 메모와 감정 태그는 선택 입력으로 둔다.
- 캘린더 기록 표시는 MVP에서 점 또는 미니 스택을 기본으로 한다.
- 같은 날짜 기록은 생성 시간 오름차순으로 보여준다.
- 아카이브는 최근 월 우선의 월별 그룹을 기본으로 하고, 검색과 감정 태그 필터는 보조 탐색으로 둔다.
- 감정 태그는 MVP에서 고정 목록으로 시작한다.
- 로컬 저장 안내는 온보딩, Calendar, Add, Settings에 강도를 나누어 반복한다.
- UI 문구에서 업로드, 동기화, 클라우드, 공유, 게시, 피드 같은 표현을 피한다.
- 개별 삭제와 전체 삭제는 분리하고, 전체 삭제는 강한 확인 모달을 사용한다.

## Resolved Product Decisions

- Product promise: `내 폰 안에만 쌓이는 하루 사진 캘린더`
- Primary user: Daily Photo Keeper
- First-run flow: onboarding, first-record CTA, Add screen, optional skip to Calendar
- Add minimum path: photo, date, save
- Optional inputs: memo, emotion tag
- Calendar marker: dot or mini stack
- Day detail order: created time ascending
- Archive default: monthly groups, recent month first
- Emotion tags: fixed list in MVP
- Backup MVP: export backup should be included if technically feasible; import/restore is deferred unless quality and policy are confirmed

## Remaining Open Questions

- 고정 감정 태그 목록과 개수
- 백업 파일 포맷
- 가져오기 MVP 포함 여부와 정책
- 전체 데이터 삭제 시 앱 설정과 온보딩 완료 상태까지 초기화할지 여부
- 첫 기록 저장 후 Calendar로 돌아갈지 Day Detail로 이동할지 여부
- 로컬 저장 배지를 Calendar에서 항상 보여줄지 초기 사용 구간에만 강조할지 여부
- 로컬 알림을 MVP에서 제외할지 선택형으로 둘지 여부

## Wiki Promotion Needed

- PRD에 제품 약속, 1차 타깃, 기록 추가 최소 경로, 성공 기준을 반영한다.
- MVP Scope에서 가져오기는 실제 기능이 아니라 후속/정책 확정 항목으로 조정한다.
- User Personas에서 Daily Photo Keeper를 1차 타깃으로 명확히 한다.
- User Flows에 first-record CTA와 skip 흐름을 반영한다.
- Feature Map에서 backup/restore 범위를 export-first로 조정한다.
- Design 문서에 캘린더 마커, local copy 위치, Add 최소 입력 경로를 반영한다.
- Local Storage Model에 금지 표현과 export-first 정책을 반영한다.
- Open Questions에 남은 미결 사항을 반영한다.
