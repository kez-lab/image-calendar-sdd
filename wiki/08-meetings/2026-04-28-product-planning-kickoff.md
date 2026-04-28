---
type: project
status: active
owner: llm
updated: 2026-04-28
sources:
  - raw/meetings/2026-04-28-product-planning-kickoff/brief.md
  - raw/meetings/2026-04-28-product-planning-kickoff/context-snapshot.md
  - raw/meetings/2026-04-28-product-planning-kickoff/round-1-opinions.md
  - raw/meetings/2026-04-28-product-planning-kickoff/round-2-feedback.md
  - raw/meetings/2026-04-28-product-planning-kickoff/final-notes.md
---

# Meeting: Product Planning Kickoff

Date: 2026-04-28  
Topic: 로컬 전용 개인 이미지 캘린더 MVP 기획 고도화  
Raw Folder: [raw/meetings/2026-04-28-product-planning-kickoff](../../raw/meetings/2026-04-28-product-planning-kickoff/brief.md)

## Context

현재 제품은 서버, 로그인, 친구 공유, 공개 피드 없이 디바이스 로컬에 사진, 메모, 감정 태그를 날짜별로 기록하는 개인용 이미지 캘린더다. 회의 목적은 MVP 기획을 실제 출시 가능한 수준으로 좁히는 것이다.

## Participants

- Product Lead
- User Advocate
- UX Planner
- Local-First Strategist
- Retention Planner
- Facilitator

## Round 1 Summary

- Product Lead는 로컬 저장 신뢰와 날짜별 사진 회고를 포지셔닝 중심으로 제안했다.
- User Advocate는 첫 타깃을 하루 1-3장의 사진을 남기는 사용자로 좁히고, 온보딩 직후 첫 기록 유도가 필요하다고 봤다.
- UX Planner는 Calendar, Add, Archive, Settings의 역할 분리를 확인하고, 캘린더 마커는 썸네일보다 점/미니 스택이 적합하다고 제안했다.
- Local-First Strategist는 백업/복원이 유일하게 데이터가 앱 밖으로 나가는 흐름이므로 용어와 정책을 엄격히 관리해야 한다고 지적했다.
- Retention Planner는 소셜 없는 리텐션은 캘린더 누적감과 아카이브 회수 가치에서 나와야 한다고 봤다.

## Round 2 Summary

공통 합의는 명확했다. 제품은 “내 폰 안에만 쌓이는 하루 사진 캘린더”로 설명하고, MVP는 첫 기록 생성, 캘린더 누적감, 날짜별 회고, 월별 아카이브, 로컬 저장 신뢰에 집중한다.

의견이 갈린 지점은 백업/복원이다. 결론은 내보내기는 로컬 전용 앱의 신뢰를 보완하므로 MVP에 포함할 가치가 있지만, 가져오기는 데이터 손실 리스크가 높으므로 정책과 품질이 확인되기 전까지 후순위로 둔다.

## Decisions

- 제품 약속은 `내 폰 안에만 쌓이는 하루 사진 캘린더`로 정한다.
- 보조 포지셔닝은 `사진첩보다 정리되고 다이어리보다 가벼운 개인 기록 도구`로 사용한다.
- 1차 타깃은 하루 1-3장의 사진을 개인적으로 기록하는 Daily Photo Keeper다.
- 첫 사용 흐름은 온보딩 후 첫 기록 만들기 CTA로 연결하되, 건너뛰고 Calendar를 볼 수 있게 한다.
- 기록 추가의 최소 완료 경로는 사진, 날짜, 저장이다.
- 메모와 감정 태그는 MVP에서 선택 입력이다.
- 감정 태그는 MVP에서 고정 목록으로 시작한다.
- 캘린더 기록 표시는 점 또는 미니 스택으로 시작하고, 날짜 셀 썸네일은 후속 실험으로 둔다.
- 같은 날짜의 기록은 생성 시간 오름차순으로 표시한다.
- 아카이브는 최근 월 우선의 월별 그룹을 기본으로 하고, 검색과 감정 태그 필터는 보조 탐색으로 둔다.
- 로컬 저장 문구는 온보딩, Calendar, Add, Settings에 강도를 나누어 반복 노출한다.
- UI 문구에서 업로드, 동기화, 클라우드, 공유, 게시, 피드 표현을 피한다.
- 백업은 내보내기 우선으로 MVP에 포함하는 방향이다.
- 가져오기/복원은 정책과 품질이 확정되기 전까지 후순위로 둔다.
- 개별 삭제와 전체 삭제는 분리하고, 전체 삭제에는 강한 확인 모달을 사용한다.

## Open Questions

- 고정 감정 태그 목록과 개수는 무엇인가?
- 백업 파일 포맷은 무엇인가?
- 가져오기/복원을 첫 MVP에 포함할지, 포함한다면 어떤 정책으로 제한할 것인가?
- 전체 데이터 삭제 시 앱 설정과 온보딩 완료 상태까지 초기화할 것인가?
- 첫 기록 저장 후 Calendar로 돌아갈지 Day Detail로 이동할 것인가?
- 로컬 저장 배지를 Calendar에서 항상 보여줄지 초기 사용 구간에만 강조할 것인가?
- 로컬 알림은 MVP에서 제외할지 선택형으로 둘 것인가?

Resolution note:

- 첫 기록 저장 후 이동과 Calendar 로컬 저장 배지 노출 방식은 [2026-04-28 MVP Decision Lock](2026-04-28-mvp-decision-lock.md)에서 확정되었다.

## Action Items

- PRD에 제품 약속, 1차 타깃, 기록 추가 최소 경로, 측정 가능한 성공 기준을 반영한다.
- MVP Scope에서 가져오기는 실제 기능이 아니라 후속/정책 확정 항목으로 조정한다.
- User Flows에 first-record CTA와 skip 흐름을 반영한다.
- Design 문서에 점/미니 스택 마커, local copy 위치, Add 최소 입력 경로를 반영한다.
- Local Storage Model에 금지 표현과 export-first 정책을 반영한다.
- Open Questions에 남은 미결 사항을 반영한다.

## Wiki Updates

- [PRD](../01-product/prd.md)
- [MVP Scope](../01-product/mvp-scope.md)
- [User Personas](../01-product/user-personas.md)
- [User Flows](../01-product/user-flows.md)
- [Feature Map](../01-product/feature-map.md)
- [Screen Inventory](../02-design/screen-inventory.md)
- [Component Guidelines](../02-design/component-guidelines.md)
- [Local Storage Model](../04-privacy-security/local-storage-model.md)
- [Open Questions](../00-context/open-questions.md)
- [Decision Log](../06-project/decision-log.md)
