# Round 2 Feedback: Web Feasibility Council

Date: 2026-05-01

## CEO Provisional Conclusion Reviewed

- 웹 버전은 만들 수 있다.
- 단, Android 대체 앱이 아니라 정적 호스팅 기반 로컬 전용 PWA companion으로 정의한다.
- 기록 데이터는 서버에 저장/전송하지 않고, IndexedDB + OPFS, fallback IndexedDB Blob을 검토한다.
- 웹의 trust copy는 `내 폰에만 저장됨`이 아니라 `이 브라우저에만 저장됨`으로 바꾼다.
- public release는 비추천. 먼저 internal web feasibility prototype과 브라우저별 저장/삭제/백업 검증을 진행한다.
- 우선순위는 Android MVP 안정화, 웹 랜딩/소개, web backup viewer/prototype, full PWA MVP 순서다.

## Feedback

### Product Planner

- `PWA companion`이라는 표현은 Android와 웹 데이터가 자연스럽게 연결된다는 오해를 만들 수 있다.
- 초기 공식 정의는 `독립형 로컬 웹 실험` 또는 `백업 파일 뷰어`가 더 안전하다.
- 웹 랜딩과 기술 prototype은 목적이 다르므로 분리해야 한다.
- 권장 순위는 Android 안정화, 백업 zip 포맷 고정, web feasibility prototype, 랜딩/소개, 내부용 backup viewer, full PWA 보류다.

### Design Lead

- `웹 버전`이라는 표현은 아직 이르다.
- 공식 정의는 `정적 호스팅 기반 local-only web feasibility prototype`이 적절하다.
- `이 브라우저에만 저장됨`보다 `현재 기기의 이 브라우저 프로필에만 저장됨`이 더 정확하다.
- 온보딩부터 로그인 없음, 서버 없음, 동기화 없음을 명시해야 한다.
- 설정에는 저장 위치, 삭제될 수 있는 경우, 백업 권장을 카드형으로 분리해야 한다.

### Web/Platform Engineer

- 웹에서는 `서버 없음`보다 `데이터 서버 없음`과 `기록 데이터 전송 없음`이 정확하다.
- XSS가 곧 로컬 기록 유출이므로 CSP, no third-party scripts, no analytics, no remote font 원칙이 필요하다.
- 첫 단계는 full PWA가 아니라 storage durability spike로 제한해야 한다.
- 2단계는 Android backup viewer가 적절하다.
- 사진 추가/편집이 되는 full web MVP는 Android 안정화 후로 둬야 한다.

### QA & Privacy Lead

- `서버에 저장/전송하지 않음`은 구현, 네트워크 검증, 문구, 정책을 묶어 release gate로 둬야 한다.
- analytics/session replay/crash logging은 금지 또는 별도 review가 필요하다.
- 백업/복원 없이 full PWA MVP 공개는 불가하다.
- private browsing 경고 또는 저장 제한 없이 공개는 불가하다.
- 웹 랜딩/소개 단계에서는 사진 업로드나 샘플 기록 입력을 받으면 안 된다.

## CEO Adjustment

The CEO conclusion is updated:

- Do not call it a full web product yet.
- First web work should be a feasibility spike and/or read-only Android backup viewer.
- Use `데이터 서버 없음`, not broad `서버 없음`, when describing web.
- Public web release remains blocked until storage durability, backup/restore, privacy copy, and browser matrix are verified.
