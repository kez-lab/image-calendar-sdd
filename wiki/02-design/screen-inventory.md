---
type: design
status: active
owner: llm
updated: 2026-04-28
sources:
  - raw/sources/0002-claude-design-prompt.md
---

# Screen Inventory

Current design reference: [Design Output Review v2](design-output-review-2026-04-28-v2.md)

## 1. Onboarding

Purpose:

- 앱의 핵심 가치와 로컬 저장 원칙을 3단계로 설명한다.

Required content:

- 하루를 사진으로 기록하세요.
- 날짜별로 캘린더에 정리하세요.
- 내 폰에만 저장되는 프라이빗 기록.
- 마지막 화면의 첫 기록 만들기 버튼
- 건너뛰고 캘린더 보기 옵션

## 2. Calendar Home

Purpose:

- 월간 캘린더에서 날짜별 기록 존재 여부를 확인한다.

Required elements:

- 월 이동
- 검색 아이콘
- 설정 아이콘
- 오늘 날짜 강조
- 기록 날짜 점 또는 미니 스택
- 빠른 기록 추가 버튼
- 하단 탭바
- compact `내 폰에만 저장됨` 상시 배지

## 3. Day Record Detail

Purpose:

- 선택 날짜의 사진 기록을 카드로 확인한다.

Required elements:

- 선택 날짜 헤더
- 사진 카드 목록
- 짧은 메모
- 감정 태그
- 수정 버튼
- 삭제 버튼
- 빈 상태 UI

## 4. Add Record

Purpose:

- 사진, 날짜, 메모, 감정 태그를 빠르게 저장한다.

Required elements:

- 카메라 촬영 버튼
- 갤러리 선택 버튼
- 날짜 선택
- 선택형 메모 입력
- 선택형 고정 감정 태그 선택
- 저장 버튼
- 로컬 저장 안내 문구

## 5. Archive

Purpose:

- 저장된 기록을 월별 또는 리스트로 모아보고 검색한다.

Required elements:

- 최근 월 우선 월별 그룹
- 검색바
- 메모/날짜 검색바
- 감정 태그 칩 필터
- 이미지 중심 기록 그리드/리스트
- 오래된 기록 탐색 구조

## 6. Settings

Purpose:

- 로컬 저장 구조와 데이터 관리 기능을 제공한다.

Required elements:

- 로컬 저장 안내
- 데이터 내보내기 버튼
- 데이터 가져오기/복원 안내 또는 후속 진입점
- 전체 데이터 삭제 버튼
- 삭제 확인 모달 진입
- 앱 정보

Excluded from MVP Settings:

- 기본 사진 품질
- 한 주 시작 요일

## 7. Delete Record Confirmation

Purpose:

- 개별 기록 삭제 전 삭제 대상과 결과를 확인한다.

Required elements:

- 삭제 대상 미리보기
- 삭제 결과 안내
- 취소 버튼
- 삭제 버튼

## 8. Delete All Confirmation

Purpose:

- 전체 데이터 삭제 전 위험과 복구 불가능성을 명확히 전달한다.

Required elements:

- 위험 아이콘
- 삭제 범위 안내
- 백업이 없으면 복구 불가 안내
- 먼저 백업 파일 내보내기 CTA
- 취소 버튼
- 전체 삭제 버튼
