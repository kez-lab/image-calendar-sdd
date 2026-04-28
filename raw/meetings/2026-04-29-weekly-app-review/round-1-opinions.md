# Round 1 Opinions

Date: 2026-04-29

## Product Lead

핵심 판단:

- 현재 화면은 로컬 전용 개인 이미지 캘린더 방향은 보이지만, MVP라고 보기엔 핵심 루프가 닫히지 않았다.
- 기록 저장 성공, 날짜별 상세, 아카이브 탐색, 백업/삭제 안전 UX가 증거상 미완성이다.

MVP에 반영할 제안:

- P0: 사진 선택, 저장, 해당 날짜 Day Detail 이동, Calendar marker 반영을 한 번에 검증 가능하게 만든다.
- P0: Archive placeholder를 제거하고 검색바, 감정 태그 필터, 월별 목록/빈 상태를 구현한다.
- P0: Settings에 백업 파일 내보내기, 가져오기/복원 안내, 전체 데이터 삭제 진입과 확인 모달을 넣는다.
- P1: Onboarding에서 로컬 저장 원칙과 첫 기록 CTA를 보여준다.
- P1: 하단 탭 `C A A S` 문자 아이콘을 실제 아이콘이나 명확한 심볼로 바꾼다.
- P1: 날짜 자유 입력 대신 날짜 선택 UX 또는 검증을 추가한다.

가장 큰 리스크:

- 사용자가 첫 기록을 저장한 뒤 실제로 다시 찾을 수 있다는 신뢰가 아직 증거로 확인되지 않는다.

## UX/Design Reviewer

핵심 판단:

- Calendar/Add의 골격은 맞지만, v2 디자인 기준의 출시 가능한 MVP에는 못 미친다.
- Archive/Settings가 placeholder이고, bottom nav의 Add/Archive 구분성이 낮다.
- Android CLI layout 기준으로 클릭 요소와 텍스트 라벨 연결이 약하다.

MVP에 반영할 제안:

- Calendar의 추가 진입 방식을 FAB 또는 중앙 Add 탭 중 하나로 통일한다.
- Bottom nav 문자 아이콘을 제거하고 명확한 아이콘으로 교체한다.
- Add 화면에서 사진은 `필수`, 메모/감정 태그는 `선택` 라벨을 표시한다.
- `사진을 먼저 선택해주세요.` 오류는 사진 섹션 하단 inline error로 옮긴다.
- Archive/Settings의 내부 개발 문구를 제거하고 실제 제품 언어와 최소 기능 UI로 교체한다.
- 버튼/칩/탭/날짜 셀에 contentDescription, role, selected state, testTag를 부여한다.

가장 큰 리스크:

- 사용자에게 placeholder와 내부 개발 문구가 노출되어 제품 완성도가 크게 떨어진다.

## Android QA/Accessibility Reviewer

핵심 판단:

- 실행, 설치, 기본 탭 이동, 무사진 저장 validation은 검증됐다.
- 접근성 트리는 약하다. `android layout`에 `contentDescription`이 거의 없고, 버튼/칩/탭의 클릭 타깃이 텍스트 없는 별도 노드로 노출된다.
- Archive/Settings는 placeholder라 MVP 기능 검증이 불가능하다.

MVP에 반영할 제안:

- 월 이동, 날짜 셀, 사진 선택, 저장, 감정 태그, 하단 탭에 Compose semantics를 추가한다.
- 날짜 셀은 “2026년 4월 29일, 오늘, 기록 없음/있음”처럼 읽히게 만든다.
- Add 화면은 사진 선택 성공, validation, 메모 입력, 감정 선택, 저장 후 상세/캘린더 반영까지 Android CLI smoke test로 고정한다.
- Archive는 검색바, 월별 리스트, 감정 필터, 빈 상태까지 최소 구현한다.
- Settings는 내보내기, 가져오기/복원 안내, 전체 삭제 확인 모달을 검증 대상으로 승격한다.

가장 큰 리스크:

- 현재 상태로 기능이 늘어나면 Android CLI 기반 회귀 검증이 좌표 의존으로 굳어질 가능성이 크다.

## Engineering Implementation Reviewer

핵심 판단:

- 현재 구현은 동작 가능한 Compose prototype이지 MVP 구현 완료 상태가 아니다.
- Calendar/Add/Day Detail의 1차 흐름은 in-memory로만 연결되어 있고 Room persistence가 없다.
- Archive/Settings는 placeholder이며, 온보딩/수정/삭제/백업/전체삭제도 미구현이다.

MVP에 반영할 제안:

- 다음 슬라이스는 Room `PhotoEntry`/`LocalAsset`/DAO/Repository와 최소 QA fixture를 같이 구현한다.
- 그 다음 Day Detail의 날짜별 DB 조회, 여러 기록 카드, 수정/삭제, 로컬 파일 정리를 연결한다.
- 이후 Archive, Settings, accessibility pass 순서로 진행한다.
- Accessibility는 마지막 후처리가 아니라 각 화면 구현 시 같이 넣는다.

가장 큰 리스크:

- Room 없이 Archive/Settings/Day Detail을 더 만들면 가짜 상태 위에 기능을 얹는 기술부채가 된다.
- 파일 복사와 DB insert 사이의 원자성을 repository 계약으로 묶어야 한다.
