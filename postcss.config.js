const purgecss = require('@fullhuman/postcss-purgecss')({
    content: [
        './assets/index.html',
        './src/**/*.cljs',
        './src/**/*.cljc',
    ],

    // Include any special characters you're using in this regular expression
    defaultExtractor: content => content.match(/[\w-][\w-:]*/g) || []
});

module.exports = {
    plugins: [
        require('postcss-import'),
        require('tailwindcss'),
        require('postcss-preset-env')({
            stage: 1,
            // Temporarily disabled, until
            // https://github.com/jonathantneal/postcss-focus-within/pull/5 is
            // released. See
            // https://github.com/tailwindcss/tailwindcss/issues/1190
            features: {
                'focus-within-pseudo-class': false
            }
        }),
        ...(process.env.NODE_ENV === 'production' ? [purgecss] : [])
    ]
};
