## Hack

### HTML

```bash
mkdir -p target
cp assets/index.html target/
```

### JS

```bash
npx shadow-cljs watch app
```

### CSS

```bash
mkdir -p target
NODE_ENV=development npx postcss -c postcss.config.js -o target/styles.css assets/css/styles.css
```

### Test

Load dependencies into build server:

```bash
npx shadow-cljs -A:shadow:test server
```

Run tests in browser:

```bash
npx shadow-cljs watch browser-test
```

Then, open http://localhost:8021

## Deploy

### HTML

```bash
mkdir -p target
cp assets/index.html target/
```

### JS

```bash
npx shadow-cljs compile app
```

### CSS

```bash
mkdir -p target
NODE_ENV=production npx postcss -c postcss.config.js -o target/styles.css assets/css/styles.css
```
