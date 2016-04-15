var video = document.getElementsByTagName('video');
for(var i = 0 ; i < video.length; i++){
    video[i].addEventListener('play', onPlay);
    video[i].addEventListener('pause', onPause);
    video[i].addEventListener('reset', onReset);
    video[i].addEventListener('ended', onEnded);
}
function onPlay() {
    java2js_laole918.notifyVideoPlay();
}
function onPause() {
    java2js_laole918.notifyVideoPause();
}
function onReset() {
    java2js_laole918.notifyVideoReset();
}
function onEnded() {
    java2js_laole918.notifyVideoEnded();
}
function enterFullscreen() {
    for(var i = 0 ; i < video.length; i++){
        launchFullscreen(video[i]);
    }
}
function launchFullscreen(element)
{
    if (element.requestFullscreen) {
        element.requestFullscreen();
        java2js_laole918.supportFullScreen("requestFullscreen");
    } else if(element.mozRequestFullScreen) {
        element.mozRequestFullScreen();
        java2js_laole918.supportFullScreen("mozRequestFullScreen");
    } else if(element.msRequestFullscreen) {
        element.msRequestFullscreen();
        java2js_laole918.supportFullScreen("msRequestFullscreen");
    } else if(element.oRequestFullscreen) {
        element.oRequestFullscreen();
        java2js_laole918.supportFullScreen("oRequestFullscreen");
    } else if(element.webkitRequestFullscreen) {
        element.webkitRequestFullScreen();
        java2js_laole918.supportFullScreen("webkitRequestFullScreen");
    } else {
        java2js_laole918.notSupportFullScreen();
    }
}
