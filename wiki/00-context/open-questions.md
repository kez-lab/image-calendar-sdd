---
type: context
status: active
owner: llm
updated: 2026-04-28
sources:
  - raw/sources/0002-claude-design-prompt.md
---

# Open Questions

## Product

- 고정 감정 태그 목록과 개수는 무엇인가?
- 첫 기록 저장 후 Calendar로 돌아갈지 Day Detail로 이동할 것인가?
- 로컬 알림은 MVP에서 제외할지 선택형으로 둘 것인가?
- 검색은 메모 텍스트만 대상으로 하는가, 감정 태그와 날짜도 포함하는가?

## Local Data

- 사진 원본을 앱 내부 저장소에 복사할 것인가, 시스템 사진 라이브러리 URI를 참조할 것인가?
- 썸네일은 별도로 생성해 저장할 것인가?
- 앱 삭제 시 모든 데이터가 삭제된다는 점을 어디에서 안내할 것인가?
- 백업 파일 포맷은 zip, JSON+assets, 플랫폼별 백업 중 무엇인가?
- 가져오기/복원을 첫 MVP에 포함할 것인가?
- 가져오기를 포함한다면 비어 있는 앱에만 복원할 것인가, 기존 데이터를 지우고 복원할 것인가?
- 전체 데이터 삭제 시 앱 설정과 온보딩 완료 상태까지 초기화할 것인가?

## Design

- 디자인 결과물은 현재 HTML/JSX raw source로 관리한다. 추후 Figma로 옮길지 결정해야 하는가?
- 디자인 시스템의 최종 컬러 토큰과 타이포그래피는 무엇인가?
- Android 구현 시 iPhone 15 Pro 디자인 비율을 어떻게 변환할 것인가?
- 로컬 저장 배지는 Calendar에서 항상 보여줄 것인가, 초기 사용 구간에만 강조할 것인가?
- Archive의 기록 표현은 현재 디자인처럼 이미지 그리드 중심으로 확정할 것인가?
- Onboarding의 최종 CTA와 보조 CTA 문구를 디자인 v2에 어떻게 반영할 것인가?

## Engineering

- 구현 플랫폼은 Android native, Kotlin Multiplatform, React Native, Flutter 중 무엇인가?
- 로컬 DB는 Room, SQLite, 파일 기반 JSON 중 무엇을 사용할 것인가?
- 이미지 저장 경로와 권한 처리는 플랫폼별로 어떻게 설계할 것인가?
- 가져오기/복원은 첫 MVP에 포함할 것인가, 후속 안내로 둘 것인가?

## SDD

- Spec Kit을 언제 설치하고 어떤 integration 옵션으로 초기화할 것인가?
- 첫 구현 spec은 캘린더 조회부터 시작할 것인가, 기록 작성부터 시작할 것인가?
