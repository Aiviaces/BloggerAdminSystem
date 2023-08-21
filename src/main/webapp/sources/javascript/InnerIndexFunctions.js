//欢迎页时钟
function updateClock() {
    const currentTime = new Date();
    let hours = currentTime.getHours();
    let minutes = currentTime.getMinutes();
    let seconds = currentTime.getSeconds();

    hours = (hours < 10 ? "0" : "") + hours;
    minutes = (minutes < 10 ? "0" : "") + minutes;
    seconds = (seconds < 10 ? "0" : "") + seconds;

    $("#clock-number").text(hours + ":" + minutes + ":" + seconds);
}
$(document).ready(updateClock);
setInterval(updateClock, 1000);
