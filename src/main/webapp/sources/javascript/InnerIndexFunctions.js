//欢迎页时钟
function updateClock(cn) {

    const currentTime = new Date();
    let hours = currentTime.getHours();
    let minutes = currentTime.getMinutes();
    let seconds = currentTime.getSeconds();

    hours = (hours < 10 ? "0" : "") + hours;
    minutes = (minutes < 10 ? "0" : "") + minutes;
    seconds = (seconds < 10 ? "0" : "") + seconds;

    cn.text(hours + ":" + minutes + ":" + seconds);

}

$(document).ready(() => {
    let cn = $("#clock-number");
    cn.css({opacity: 0});
    updateClock(cn);
    cn.animate({opacity: 1}, 300);
    setInterval(() => updateClock(cn), 1000);
});

