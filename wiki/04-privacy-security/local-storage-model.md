---
type: privacy
status: active
owner: llm
updated: 2026-05-01
sources:
  - raw/sources/0002-claude-design-prompt.md
  - ../../raw/verification/2026-05-01-mvp-completion/README.md
---

# Local Storage Model

## Core Rule

모든 기록 데이터는 디바이스 로컬에 저장한다. 서버, 로그인, 동기화는 MVP에 존재하지 않는다.

## Stored Data

- 사진 원본 또는 앱 내부 복사본
- 썸네일
- 짧은 메모
- 감정 태그
- 기록 날짜
- 앱 설정
- 백업 메타데이터

## Local Storage Copy

추천 문구:

- `내 폰에만 저장됨`
- `서버로 업로드되지 않아요`
- `로그인 없이 사용할 수 있어요`
- `백업 파일은 사용자가 직접 보관해요`

## Backup and Restore

- 내보내기는 사용자가 명시적으로 실행한다.
- MVP는 백업 파일 내보내기를 구현한다.
- MVP 백업 파일은 zip package이며 `manifest.json`, 원본 이미지 복사본, 썸네일을 포함한다.
- 가져오기/복원은 기존 데이터와의 병합/덮어쓰기 정책을 먼저 설명해야 하며, 정책과 품질이 확정되기 전까지 후순위로 둔다.
- 백업 파일은 앱 밖으로 나갈 수 있으므로 사용자가 보관 책임을 이해해야 한다.

## Copy Guardrails

MVP UI에서 피해야 할 표현:

- 업로드
- 동기화
- 클라우드
- 공유
- 게시
- 피드

## Deletion

- 개별 기록 삭제는 연결된 사진/썸네일까지 삭제해야 한다.
- 전체 데이터 삭제는 확인 모달과 명확한 결과 설명을 필요로 한다.
- MVP 전체 데이터 삭제는 로컬 기록, 메모, 감정 태그, 앱 내부 사진 복사본/썸네일을 삭제한다.
- MVP 전체 데이터 삭제는 온보딩 완료 상태와 앱 preference를 유지한다.
- 삭제 후 복구 가능 여부는 백업 존재 여부에 따라 달라진다.

## SDD Implications

`004-local-backup-restore`는 export-first로 설계하고, `005-settings-data-safety`는 데이터 무결성과 사용자 오해 방지 문구를 함께 다뤄야 한다.
