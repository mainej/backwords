## Hack

First, load dependencies into build server:

```bash
yarn run js-server
```

Then

```bash
yarn run html     # optional, only needs to be run once
yarn run tailwind # optional, only needs to be run when styles change
yarn run watch-js
```

Then, open http://localhost:8081

### Test

Run test server:

```bash
yarn run test-js
```

Then, open http://localhost:8021

## Deploy

```bash
yarn --prod run clean
yarn --prod run html
yarn --prod run css
yarn --prod run compile-js
```
