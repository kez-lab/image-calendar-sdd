# Final Notes: Web Feasibility Council

Date: 2026-05-01

## CEO Decision

웹은 만들 수 있다. 단, 지금 결정할 수 있는 것은 full web product 착수가 아니라 `local-only web feasibility spike`다.

공식 정의:

- Short-term: 브라우저 로컬 저장 검증과 Android backup viewer 후보.
- Not yet: Android 대체 웹앱, cloud-like web service, cross-device companion.

## Locked Decisions

- 웹에서도 서버 로그인, 계정, 동기화, 친구 공유, 공개 피드는 만들지 않는다.
- 웹에서는 `서버 없음`보다 `기록 데이터는 서버에 저장되거나 전송되지 않음`으로 표현한다.
- 웹 trust copy는 `내 폰에만 저장됨`을 사용하지 않는다.
- 우선 문구는 `현재 기기의 이 브라우저에만 저장됨`으로 한다.
- Web storage candidate는 IndexedDB metadata + OPFS assets + IndexedDB Blob fallback이다.
- Android backup zip format compatibility is a strategic requirement for future web work.
- Public web release is blocked until storage, backup, privacy, security, and browser matrix gates are passed.

## Recommended Sequence

1. Android MVP 안정화와 외부 디자인/privacy review.
2. Android backup zip format 확정.
3. Web storage durability spike.
4. Read-only Android backup viewer prototype.
5. Web landing/intro page only if it collects no photos or records.
6. Full local-only PWA MVP after feasibility gates pass.

## Required Web Feasibility Gates

- Save records to browser storage and survive refresh/restart/PWA relaunch.
- Export and import backup zip without record loss.
- Delete all data and verify empty state after reload.
- Site data deletion returns app to safe empty state.
- Private browsing warning or save restriction exists.
- Network inspection proves no photo, memo, emotion tag, or date record payload leaves the browser.
- No analytics, session replay, crash upload, third-party scripts, or remote fonts without review.
- CSP/XSS risk reviewed before real user data is allowed.
- Browser matrix covers Chrome desktop, Safari macOS, Safari iOS, Chrome Android, and Firefox desktop.

## External Manpower Needed

- Design: responsive web/PWA layout and web local-only trust UX.
- Privacy/legal: wording for `업로드 없음`, `서버 저장 없음`, browser data loss, backup responsibility.
- Security: CSP, XSS, third-party script policy, local record exposure risk.
- QA: real device/browser matrix, private browsing, PWA install/uninstall, storage pressure.

## CEO Conclusion For User

웹은 가능하지만 지금 full web app을 만들면 제품 신뢰와 QA 범위가 흔들린다. 다음에 웹을 한다면 `웹앱 출시`가 아니라 `브라우저 로컬 저장 검증 + Android 백업 뷰어 prototype`부터 시작하는 것이 맞다.
