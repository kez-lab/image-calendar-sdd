---
type: context
status: active
owner: llm
updated: 2026-05-01
sources:
  - raw/sources/0002-claude-design-prompt.md
  - raw/meetings/2026-04-29-weekly-app-review/final-notes.md
  - ../06-project/next-work-plan-2026-05-01.md
---

# Open Questions

## Product

- 고정 감정 태그 5개(`차분`, `기쁨`, `피곤`, `포근`, `바쁨`)를 MVP 최종 목록으로 확정할 것인가?
- 로컬 알림은 MVP에서 제외할지 선택형으로 둘 것인가?

## Local Data

- 앱 삭제 시 모든 데이터가 삭제된다는 점을 어디에서 안내할 것인가?
- 백업 파일 포맷은 zip, JSON+assets, 플랫폼별 백업 중 무엇인가?
- 가져오기를 포함한다면 비어 있는 앱에만 복원할 것인가, 기존 데이터를 지우고 복원할 것인가?
- 전체 데이터 삭제 시 앱 설정과 온보딩 완료 상태까지 초기화할 것인가?

## Design

- 디자인 결과물은 현재 HTML/JSX raw source로 관리한다. 추후 Figma로 옮길지 결정해야 하는가?
- 디자인 시스템의 최종 컬러 토큰과 타이포그래피는 무엇인가?
- Android 구현 시 iPhone 15 Pro 디자인 비율을 어떻게 변환할 것인가?
- Archive의 기록 표현은 현재 디자인처럼 이미지 그리드 중심으로 확정할 것인가?
- Onboarding의 최종 CTA와 보조 CTA 문구를 디자인 v2에 어떻게 반영할 것인가?

## Engineering

- Android CLI 검증용 fixture는 debug-only app behavior로 둘 것인가, test-code-only setup으로 둘 것인가?
- 파일 복사 성공 후 Room insert 실패 시 orphan file cleanup 재시도 정책은 어떻게 둘 것인가?

## SDD

- Spec Kit을 언제 설치하고 어떤 integration 옵션으로 초기화할 것인가?

## Resolved

- Day Detail 수정 범위는 MVP에서 날짜, 메모, 감정 태그 metadata로 제한한다. 사진 교체는 후속으로 둔다.
- Archive MVP date query는 `YYYY-MM-DD`, `YYYY-MM`, `YYYY.MM`만 지원한다.
- Archive record tap은 기존 Day Detail을 해당 `localDate`로 여는 방식으로 처리한다.
- Archive empty copy는 `아직 저장된 기록이 없어요` / `사진 한 장으로 하루를 캘린더에 남겨보세요.`로 시작한다.
