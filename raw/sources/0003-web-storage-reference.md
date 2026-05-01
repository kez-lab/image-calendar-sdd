# Web Storage Reference

Date captured: 2026-05-01

## Purpose

Reference material for evaluating whether Image Calendar can have a local-only web/PWA version without violating the product invariant.

## Sources

- MDN IndexedDB API: https://developer.mozilla.org/en-US/docs/Web/API/IndexedDB_API
- MDN Origin private file system: https://developer.mozilla.org/en-US/docs/Web/API/File_System_API/Origin_private_file_system
- MDN Storage quotas and eviction criteria: https://developer.mozilla.org/en-US/docs/Web/API/Storage_API/Storage_quotas_and_eviction_criteria

## Extracted Facts

- IndexedDB is suitable for client-side structured data and can store files/blobs.
- OPFS is a browser-managed file system private to a page origin, not directly visible as user files.
- Browser storage is scoped by origin.
- Browser storage is generally best-effort by default.
- Persistent storage can be requested with `navigator.storage.persist()`, but user/browser policy still matters.
- Private browsing can have different quotas and stored data is usually removed when the private session ends.
- Browser storage data can be removed by site data clearing, storage pressure, browser policy, or user action.
- OPFS is also subject to browser quota restrictions and is deleted when site storage data is cleared.

## Implications

- A web version can be local-only in the sense that records are stored in browser storage and not uploaded to an application data server.
- A web version cannot honestly promise the same data durability semantics as Android app-specific internal storage.
- Web copy must say `이 브라우저에만 저장됨` or stronger, not `내 폰에만 저장됨`.
- Backup/export and clear data warnings must be first-class UX, not secondary settings polish.
