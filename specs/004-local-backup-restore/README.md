# 004 Local Backup Restore

Planned SDD feature for exporting local app data in a user-controlled backup package. Import/restore is deferred until policy and quality are confirmed.

Source wiki:

- [Local Storage Model](../../wiki/04-privacy-security/local-storage-model.md)
- [Data Retention](../../wiki/04-privacy-security/data-retention.md)
- [Threat Model](../../wiki/04-privacy-security/threat-model.md)

Primary invariant:

- Data leaves the app only when the user explicitly exports a backup.

MVP direction:

- Include backup file export as a user-selected local zip package.
- MVP zip package contains `manifest.json`, original images, and thumbnails.
- Treat import/restore as a later phase unless a safe, simple restore policy is confirmed.
