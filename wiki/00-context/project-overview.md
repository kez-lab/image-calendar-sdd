---
type: context
status: active
owner: llm
updated: 2026-04-28
sources:
  - raw/sources/0002-claude-design-prompt.md
---

# Project Overview

## One-Liner

서버와 로그인 없이, 사용자의 디바이스 안에서만 사진, 메모, 감정 태그를 날짜별로 기록하는 개인용 이미지 캘린더 앱.

## Core Concept

“내 하루의 이미지를 캘린더 위에 조용히 쌓아가는 개인 기록 앱”

## Product Promise

“내 폰 안에만 쌓이는 하루 사진 캘린더”

## Product Pillars

- Calendar-first memory: 날짜별로 기록을 찾고 정리한다.
- Local-only trust: 모든 데이터는 현재 디바이스에만 저장된다.
- No account required: 로그인 없이 바로 사용할 수 있다.
- Fast capture: 사진, 메모, 감정 태그 기록은 30초 안에 끝나야 한다.
- Calm archive: 오래된 기록을 월별, 리스트, 검색, 감정 태그로 쉽게 다시 찾는다.

## Boundaries

- 서버를 사용하지 않는다.
- 로그인, 계정, 프로필 동기화가 없다.
- SNS 기능, 좋아요, 댓글, 팔로우, 공개 피드가 없다.
- 친구 공유나 외부 업로드가 없다.
- 백업/복원은 사용자가 명시적으로 파일을 내보내거나 가져오는 방식으로만 다룬다.

## Implications for SDD

초기 SDD는 로컬 데이터 신뢰와 날짜별 기록 경험을 중심으로 분해한다.

- 캘린더 기반 하루 기록 조회
- 사진/메모/감정 태그 기록 작성
- 아카이브 검색/필터
- 백업 파일 내보내기 우선
- 설정과 데이터 안전
