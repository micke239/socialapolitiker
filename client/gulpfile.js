var gulp = require('gulp'),
    browserify = require('gulp-browserify'),
    uglify = require('gulp-uglify'),
    rev = require('gulp-rev'),
    revNapkin = require('gulp-rev-napkin');

var jsAliases = [
//    ['../../bower_components/jquery/dist/jquery.js','jquery']
    //['../../bower_components/backbone/backbone.js','backbone']
];


var buildJs = function(doUglify) {
    var browserifyBuild = function() {
        return browserify({
            debug: false
        }).on('prebundle', function(bundler) {
            for (var i = 0; i < jsAliases.length; i++) {
                bundler.require(jsAliases[i][0], { expose: jsAliases[i][1]});
            }
        });
    };

    var build = gulp.src('src/js/app.js').pipe(browserifyBuild());
    
    if (doUglify) {
    	build.pipe(uglify());
    }
    
    build.pipe(gulp.dest('dist/js'));
}

gulp.task('static-files', function() {
    return gulp.src('src/static/*')
        .pipe(gulp.dest('dist'));
});

gulp.task('fonts', function() {
    return gulp.src('src/fonts/*')
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
	buildJs(true);
});

gulp.task('js-dev', function() {
	buildJs(false);
});

gulp.task('build', ['static-files', 'fonts', 'css', 'js'], function() {
	gulp.src('dist/**/*.+(css|js)')
		.pipe(rev())
		.pipe(gulp.dest('dist'))
		.pipe(revNapkin())
		.pipe(rev.manifest())
		.pipe(gulp.dest('dist'));
});

gulp.task('build-dev', ['static-files', 'fonts', 'css', 'js'], function() {
});