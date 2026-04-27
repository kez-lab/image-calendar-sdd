---
type: privacy
status: active
owner: llm
updated: 2026-04-28
sources:
  - raw/sources/0002-claude-design-prompt.md
---

# Privacy Model

## Principle

이 제품의 프라이버시는 서버나 접근 제어가 아니라 로컬 전용 저장 구조에서 나온다. 데이터는 사용자의 현재 디바이스에만 저장된다.

## Privacy States

- Device-only: 앱 내부 로컬 저장소에만 존재한다.
- Exported by user: 사용자가 명시적으로 백업 파일을 내보낸 상태다.
- Deleted locally: 앱 데이터와 연결된 로컬 assets가 삭제된 상태다.

## Not Supported

- Account login
- Server upload
- Cloud sync
- Public publishing
- Friend sharing
- Link sharing

## UI Requirements

- 온보딩에서 “내 폰에만 저장됨”을 명확히 설명한다.
- 기록 추가 화면에서 저장 대상이 로컬임을 짧게 안내한다.
- 설정 화면에서 로컬 저장, 백업, 가져오기, 전체 삭제를 설명한다.
- 외부로 데이터가 나가는 동작은 내보내기뿐이며 사용자가 명시적으로 실행해야 한다.
- Calendar 화면에서는 작은 로컬 저장 배지로 신뢰를 보강하되, 기술 설명처럼 보이지 않게 한다.

## Data Requirements

- 기본 데이터 경로는 앱 로컬 저장소다.
- 네트워크 전송을 전제로 한 모델을 만들지 않는다.
- 내보내기 파일은 사용자가 직접 관리한다는 점을 UI에서 설명한다.
