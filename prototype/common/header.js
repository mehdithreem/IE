menuHTML = "<div class='header clearfix'>\
		<nav>\
		<ul class='nav nav-pills pull-left'>\
			<li role='presentation'><a href='../start.html'>خروج</a></li>\
			<li class='dropdown user-active'>\
				<a class='dropdown-toggle' data-toggle='dropdown' href='#' role='button' aria-haspopup='true' aria-expanded='false'>کاربران  <span class='caret'></span></a>\
				<ul class='dropdown-menu'>\
					<li><a href='../users/req.html'>مشاهده‌ی درخواست‌های جدید</a></li>\
					<li><a href='../users/new.html'>کاربر جدید</a></li>\
					<li><a href='../users/list.html'>مدیریت کاربران</a></li>\
				</ul>\
			</li>\
			<li class='dropdown rules-active'>\
				<a class='dropdown-toggle' data-toggle='dropdown' href='#' role='button' aria-haspopup='true' aria-expanded='false'>قوانین  <span class='caret'></span></a>\
				<ul class='dropdown-menu'>\
					<li><a href='../ghanoon/new.html'>قانون جدید</a></li>\
					<li><a href='../ghanoon/list.html'>مدیریت قوانین</a></li>\
				</ul>\
			</li>\
			<li class='dropdown licen-active'>\
				<a class='dropdown-toggle' data-toggle='dropdown' href='#' role='button' aria-haspopup='true' aria-expanded='false'>مجوز  <span class='caret'></span></a>\
				<ul class='dropdown-menu'>\
					<li><a href='../mojavez/sodoor.html'>صدور مجوز</a></li>\
					<li><a href='../mojavez/list.html'>درخواست‌های مجوز موجود</a></li>\
				</ul>\
			</li>\
			<li class='dropdown ezhar-active'>\
				<a class='dropdown-toggle' data-toggle='dropdown' href='#' role='button' aria-haspopup='true' aria-expanded='false'>اظهارنامه  <span class='caret'></span></a>\
				<ul class='dropdown-menu'>\
					<li><a href='../ezhar/new.html'>اظهارنامه‌ی جدید</a></li>\
					<li><a href='../ezhar/find.html'>پیگیری اظهارنامه</a></li>\
				</ul>\
			</li>\
			<li class='dropdown tajer-active'>\
				<a class='dropdown-toggle' data-toggle='dropdown' href='#' role='button' aria-haspopup='true' aria-expanded='false'>تجار  <span class='caret'></span></a>\
				<ul class='dropdown-menu'>\
					<li><a href='../tajer/new.html'>تاجر جدید</a></li>\
					<li><a href='../tajer/list.html'>لیست تاجر</a></li>\
				</ul>\
			</li>\
			<li role='presentation' class='home-active'><a href='../start/index.html'>خانه</a></li>\
		</ul>\
		<h3 class='text-muted'>گمرک</h3>\
		</nav>\
	</div>";

function replaceHeader(page) {
	document.getElementById('myheader').innerHTML = menuHTML.replace(page, 'active');
}








