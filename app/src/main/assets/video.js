<script>
    var video = document.getElementsByTagName('video')[0];
    if(video != undefined) {
        video.addEventListener("play", onPlay);
        video.addEventListener("pause", onPause);
        video.addEventListener("reset", onReset);
        video.addEventListener("ended", onEnded);
        //video.addEventListener("play", fullscreen);
    }
    function onPlay() {
        java2js.notifyVideoPlay();
    }
    function onPause() {
        java2js.notifyVideoPause();
    }
    function onReset() {
        java2js.notifyVideoReset();
    }
    function onEnded() {
        java2js.notifyVideoEnded();
    }
    function enterFullscreen() {
        video.webkitEnterFullscreen();
    }
</script>