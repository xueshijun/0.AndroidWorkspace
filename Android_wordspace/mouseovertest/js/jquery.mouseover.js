/**��Ҫ������д����꾭��ĳ����ʱ�򵯳�һ��div ����Ҫ������ʾ��Щ˵����Ϣ��������Ƴ�֮�󵯳���div��ʧ
 * ���ʹ��ajax��ʽ����ajax�ķ���ֵ��Ϊdiv������ʾ������
 * ��Ҫ����jquery�����д
 * ���ߣ� wolf
 */
(function($){
	$.fn.extend({
		mousePop : function(options){
			var defaults = {
				id : 'drag',              //��������ǵ�����С���div��id
				html : '',                //������������html����
				color : '',               //��������ʾ����ɫ
				alpha : '',               //�������͸����
				divClass : '',            //�������css��ʽ��
				url : '',                 //���õĺ�̨��ajax��·��
				date : '',                //����ajaxʱ��������ݣ�����ĸ�ʽ��jquery��ajax�����ĸ�ʽ��ͬ
				async : true,             //�Ƿ����ͬ���ķ�ʽ
				error : '',               //��̨ajax��������ʾ������  
				type : '',                //ajax�ύ�ķ�ʽ��post������get
				page : ''                 //��Ҫ���ص�����ҳ��         
			};
			var options = $.extend(defaults, options);
			return this.each(function(){
				var o =options;
				var obj = $(this);
				var pop = $("<div id=\""+o.id+"\"></div>");     //����һ�����ڵ�����div��
				obj.append(pop);
				pop.css("display", "none");
				if(o.color != ""){ //����������ı�����ɫ
					pop.css("background-color", o.color);
				}
				if(o.alpha != ""){ //���ĵ������͸����
					pop.css("alpha", o.alpha);
				}
				pop.addClass(o.divClass);
				/*�ж�����div����䴫���html���뻹�ǵ��ú�̨ajax���ص�html����*/
				if(o.html != ""){
					pop.append(o.html);
				}else if(o.page != ""){
					pop.load(o.page);
				}else{
					$.ajax({
						type : o.type,
						url : o.url,
						async : o.async,
						data : o.data,
						success : function(datas){
							if(datas.indexOf("<html>") != -1){
								pop.append("�Ự�ѹ���");
							}else{
								pop.append(datas);
							}
						},
						error : function(){
							pop.append(o.error);
						}
					});
				}
				/*��Ҫʹ�õĶ������������¼�*/
				obj.bind("mouseover", function(event){
					pop.css({"left":event.x, "top":event.y, "display":"block"});
				});
				/*��Ҫʹ�õĶ��������Ƴ��¼�*/
				obj.bind("mouseout", function(event){
					pop.css({"display":"none"});
				});
			});	
		}
	});	
})(jQuery);