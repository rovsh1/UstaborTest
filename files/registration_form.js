(function(form){
	(function(siteInput, categoriesInput, el){
		var val = [];
		if (categoriesInput.val())
			val = explode(',', categoriesInput.val());
		siteInput.change(function(){
			el.html('');
			if (!$(this).val())
				return;
			el.addClass('loading');
			$.getJSON('/catalog/getcategories/', {site_id: siteInput.val()}, function(r){
				var s = '', i, c, l = r.categories.length;
				for (i = 0; i < l; i++) {
					c = r.categories[i];
					s += '<div class="item' + (in_array(c.id, val) ? ' selected' : '') + '"><a href="#" data-id="' + c.id + '"><img src="/file/' + c.icon + '/">' + c.name + '</a></div>';
				}
				el.html(s).removeClass('loading');
				el.find('a').click(function(e){
					e.preventDefault();
					$(this).toggleClass('selected');
					val = [];
					el.find('a.selected').each(function(){
						val[val.length] = $(this).data('id');
					});
					categoriesInput.val(val.join(','));
				});
			});
		}).change();
	})($('#form_registration_master_site_id'), $('#form_registration_master_categories'), $('#rfm-categories'));

	$('#form_registration_master_image').change(function(){
		var reader = new FileReader();
		reader.onload = function(e) {
			$('#user-image img').attr('src', e.target.result);
		};
		reader.readAsDataURL(this.files[0]);
	});
	$('#form_registration_master_phone').elementPhone();
	$('#form_registration_master_country_id').elementCountry({
		cities: '#form_registration_master_city_id',
		city_id: null,
		phone: '#form_registration_master_phone'
	});
	$('#form_registration_master_city_id').elementCity({districts: '#form_registration_master_district_id', district_id: null});

	form.submit(function() { AnalyticsEvents.send('registrationSubmit'); });
})($('#form-registration-customer'));