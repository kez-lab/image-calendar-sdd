# Image Calendar SDD

Local-first planning, design, and specification workspace for a private mobile image calendar app.

## Product Direction

The app is a personal image calendar for recording daily photos, short notes, and emotion tags by date. It has no server, login, friends, feed, likes, comments, or public sharing. All user data is stored on the device.

## Repository Structure

- [AGENTS.md](AGENTS.md): Operating rules for Codex and other LLM agents.
- [raw/](raw/README.md): Immutable source materials, design outputs, and meeting raw records.
- [wiki/](wiki/README.md): Persistent LLM-maintained project wiki.
- [specs/](specs/README.md): SDD-style feature specifications and implementation plans.
- [app/](app): Android Native Kotlin + Jetpack Compose app.

## Current Status

- Product concept and MVP scope are documented in the wiki.
- Design v2 has been ingested and reviewed.
- CEO orchestration loop is documented under `wiki/08-meetings/`.
- Android app scaffold builds with `./gradlew assembleDebug`.
- Current implementation has Calendar/Add/Day Detail prototype with local internal image copy and generated thumbnails.
- Next recommended step is Room persistence for `PhotoEntry` and `LocalAsset`.
