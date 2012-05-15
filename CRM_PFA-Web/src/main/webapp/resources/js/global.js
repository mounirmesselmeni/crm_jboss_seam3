function pageLoad() {
	sideBarScreenWidth();
	$j(jsfId('toolbarForm:searchForm:searchInput')).focus();
}

function sideBarScreenWidth() {
	if (document.width < 1900) {
		$j('#left-sidebar').css('display', 'none');
	} else {
		$j('#left-sidebar').css('display', 'block');
	}
}

function sideBarScreenWidthResize() {
	sideBarScreenWidth();
}

function jsfId(id) {
	return "#" + id.replace(/:/g,"\\:");
}