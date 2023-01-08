let mainBannerArr = ["/img/booking/test06_banner1.jpg", "/img/booking/test06_banner2.jpg", "/img/booking/test06_banner3.jpg", "/img/booking/test06_banner4.jpg"]
let currentIndex = 1;
setInterval(function() {
	$("#bannerImg").attr("src", mainBannerArr[currentIndex++]);
	console.log(mainBannerArr);
	console.log(currentIndex);
	if (currentIndex >= mainBannerArr.length) {
		currentIndex = 0;
	}
}, 3000);