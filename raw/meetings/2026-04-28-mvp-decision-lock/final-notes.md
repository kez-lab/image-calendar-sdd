# Final Notes: MVP Decision Lock

Date: 2026-04-28

## Consensus

- Calendar local-storage badge should be always visible as a compact, low-noise reassurance.
- Settings extras should be deferred from MVP.
- First record save should navigate to Day Detail.

## Resolved Conflict

Archive search had one UX objection:

- UX concern: users may expect visible emotion chips to be searchable.
- Product/Engineering counterpoint: MVP should keep search and filter roles distinct.

Final decision:

- MVP search covers memo and date.
- Emotion tags are handled only through explicit chip filters.
- Copy should make this clear with `메모, 날짜로 검색하기`.

## Locked Decisions

1. Archive search scope: memo + date only.
2. Emotion discovery: chip filter only.
3. Calendar local badge: always visible compact badge.
4. Settings extras: defer `Default photo quality` and `Week start day`.
5. First record save destination: Day Detail.

## Implementation Implications

- No dismiss state is needed for the Calendar local badge.
- `AppSettings` does not need photo quality or week-start fields for MVP.
- Archive search implementation should query note text and local date labels only.
- Archive emotion filter can be combined with text/date search.
- Save success flow should route to the saved record's local date Day Detail.
