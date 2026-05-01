# Round 1 Opinions: Web Feasibility Council

Date: 2026-05-01

## Product Planner

Conclusion:

- 웹 버전은 만들 수 있다.
- 지금 바로 full web app MVP로 착수하는 것은 후순위다.
- 웹은 Android 대체재가 아니라 보조 도구로 시작해야 한다.

Key points:

- 웹은 설치 없이 데스크톱에서 기록을 탐색하거나 Android 백업 파일을 열어보는 문제를 풀 수 있다.
- 장기 보관 신뢰는 Android 네이티브 앱이 더 적합하다.
- 웹의 첫 목적은 랜딩/소개, 백업 파일 뷰어, 데스크톱 아카이브 브라우저가 더 안전하다.

Risks:

- 브라우저 데이터 삭제 시 기록이 사라질 수 있다.
- `내 폰 안에만 저장`이라는 제품 신뢰를 웹에서 그대로 말하면 오해가 생긴다.
- Android QA, 디자인 검수, 백업/복원 정책 확정이 지연될 수 있다.

## Design Lead

Conclusion:

- 웹/PWA 확장은 가능하지만 Android UI를 그대로 늘리면 안 된다.
- 웹의 핵심 디자인 과제는 로컬 저장 신뢰 UX를 웹 문법으로 다시 설계하는 것이다.

Key points:

- Desktop/tablet은 좌측 월간 캘린더, 우측 선택 날짜 상세 패널이 적합하다.
- Mobile web/PWA는 Android와 유사한 하단 탭 구조를 유지할 수 있다.
- Add Record는 웹에서 모달 또는 우측 패널이 적합하다.
- Settings는 `Local Data Center` 성격으로 격상해야 한다.

Required copy:

- `이 브라우저에만 저장됨`
- `로그인, 서버 업로드, 외부 공유 없이 현재 브라우저에만 기록이 저장됩니다.`
- `브라우저 데이터 삭제, 시크릿 모드, 앱 제거 시 기록이 사라질 수 있습니다. 백업을 권장합니다.`

## Web/Platform Engineer

Conclusion:

- 조건부 가능.
- 정적 SPA/PWA + IndexedDB + OPFS + backup zip 구조가 가능하다.
- Android Native와 같은 장기 보존 신뢰를 웹에서 동일하게 보장할 수는 없다.

Proposed architecture:

- Static SPA/PWA.
- No backend API, login, sync, analytics upload.
- Service Worker + Cache Storage for app shell.
- IndexedDB for metadata: `photo_entries`, `local_assets`, `app_settings`.
- OPFS for original/thumbnail blobs, fallback to IndexedDB Blob.
- Browser-generated zip export with Android-compatible `manifest.json`, originals, thumbnails.
- `navigator.storage.persist()` and `navigator.storage.estimate()` as storage quality tools.

Risks:

- OPFS and IndexedDB behavior differs by browser.
- iOS Safari/PWA needs separate verification.
- Large zip export/import can hit memory limits.
- Static deploy updates can conflict with local schema migrations.

## QA & Privacy Lead

Conclusion:

- 웹은 가능하지만 일반 웹 SaaS처럼 출시하면 안 된다.
- public web release before storage/privacy verification is not recommended.

Required gates:

- No record payload in network requests.
- No analytics, session replay, crash upload, or third-party scripts without privacy review.
- IndexedDB/OPFS only for records and images; no localStorage/cookie for user records.
- Private browsing warning or save restriction.
- Browser matrix verification: Chrome desktop, Safari macOS, Safari iOS, Chrome Android.
- Site data deletion, PWA uninstall, storage pressure, quota exceeded, backup/export/delete flows.

Required external review:

- Privacy/legal copy review.
- Security review for XSS and local record access.
- Design review for web local-only trust UX.
- QA review on real browser/device matrix.
