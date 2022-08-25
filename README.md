# Backwords

Tools for finding palindromic numbers and a static web site for viewing them.

## Hack

First, load dependencies into build server:

```bash
npm run js-server
```

Then

```bash
npm run html     # optional, only needs to be run once
npm run watch-css
npm run watch-js
```

Then, open http://localhost:8081

### Test

Run test server:

```bash
npm run test-js
```

Then, open http://localhost:8021

## Deploy

```bash
npm --prod run clean
npm --prod run html
npm --prod run compile-css
npm --prod run compile-js
```
