---
type: engineering
status: draft
owner: llm
updated: 2026-04-27
sources:
  - raw/sources/0002-claude-design-prompt.md
---

# Architecture

## Initial Direction

앱은 서버 없는 로컬 전용 구조를 전제로 한다. 캘린더 중심 기록 도메인, 로컬 저장 도메인, 아카이브 탐색 도메인을 분리한다.

## Candidate Layers

- Presentation: 화면, 디자인 시스템, 내비게이션
- Domain: 기록 생성, 날짜별 조회, 검색/필터, 삭제, 백업/복원
- Data: 로컬 DB, 이미지 파일 저장소, 썸네일 캐시, 백업 패키지 생성/복원

## No-Server Rule

- MVP에는 원격 API, 사용자 계정, 클라우드 동기화, 외부 공유 엔드포인트를 만들지 않는다.
- 외부 파일 선택/내보내기는 사용자의 명시적 OS-level action으로만 발생한다.

## Key Architectural Risks

- 사진 원본 저장 방식과 앱 삭제 시 데이터 손실 안내
- 썸네일 생성과 캘린더 렌더링 성능
- 백업/복원 중 데이터 무결성
- 전체 데이터 삭제의 원자성
- 날짜/타임존 처리

## MVP Strategy

초기에는 로컬 repository 인터페이스를 먼저 정의하고, 화면은 목업 데이터에서 실제 로컬 저장소로 자연스럽게 전환 가능하게 설계한다. 원격 데이터 레이어는 만들지 않는다.
