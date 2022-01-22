module.exports = {
    content: {
        extract: {
            // default: /[^<>"'`\s.(){}[\]#=%]*[^<>"'`\s.(){}[\]#=%:]/g
            // In CLJS classes are either
            // - hiccup style: :div.variant-a:class-a.variant-b:class-b
            // - keyword style: :variant-a:class-a
            // - string style: "variant-a:class-a variant-b:class-b"
            // The default fails to remove the leading colon from the keyword style
            DEFAULT: content => content.match(/[^<>"'`\s.(){}[\]#=%:][^<>"'`\s.(){}[\]#=%]*[^<>"'`\s.(){}[\]#=%:]/g) || []
        },
        files: [
            './assets/index.html',
            './src/**/*.cljs',
            './src/**/*.cljc',
        ]
    },
    theme: {
        extend: {},
    },
    plugins: [],
}
