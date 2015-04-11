var gulp = require('gulp'),
    browserify = require('gulp-browserify'),
    uglify = require('gulp-uglify');

var jsAliases = [
//    ['../../bower_components/jquery/dist/jquery.js','jquery']
    //['../../bower_components/backbone/backbone.js','backbone']
];

gulp.task('static-files', function() {
    return gulp.src('src/static/*')
        .pipe(gulp.dest('dist'));
});

gulp.task('fonts', function() {
    return gulp.src('bower_components/fontawesome/fonts/*')
        .pipe(gulp.dest('dist/fonts'));
});

gulp.task('css', ['img'], function() {
    return gulp.src('src/css/*')
        .pipe(gulp.dest('dist/css'));
});

gulp.task('img', function() {
    return gulp.src('src/img/**')
        .pipe(gulp.dest('dist/img'));
});

gulp.task('js', function() {
    var browserifyBuild = function() {
        return browserify({
            debug: false
        }).on('prebundle', function(bundler) {
            for (var i = 0; i < jsAliases.length; i++) {
                bundler.require(jsAliases[i][0], { expose: jsAliases[i][1]});
            }
        });
    };

    gulp.src('src/js/app.js')
        .pipe(browserifyBuild())
       // .pipe(uglify())
        .pipe(gulp.dest('dist/js'));
});

gulp.task('build', ['static-files', 'fonts', 'css', 'js'], function() {});