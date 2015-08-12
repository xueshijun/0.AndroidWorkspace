/**主要用来编写当鼠标经过某处的时候弹出一个div ，主要用在显示有些说明信息。鼠标移移出之后弹出的div消失
 * 如果使用ajax方式，则ajax的返回值即为div里面显示的内容
 * 主要利用jquery插件编写
 * 作者： wolf
 */
(function($){
	$.fn.extend({
		mousePop : function(options){
			var defaults = {
				id : 'drag',              //鼠标移入是弹出的小层的div的id
				html : '',                //弹出层中填充的html代码
				color : '',               //弹出层显示背景色
				alpha : '',               //弹出层的透明度
				divClass : '',            //弹出层的css样式类
				url : '',                 //调用的后台的ajax的路径
				date : '',                //调用ajax时传入的数据，传入的格式和jquery的ajax方法的格式相同
				async : true,             //是否采用同步的方式
				error : '',               //后台ajax报错是显示的内容  
				type : '',                //ajax提交的方式，post或者是get
				page : ''                 //需要加载的其它页面         
			};
			var options = $.extend(defaults, options);
			return this.each(function(){
				var o =options;
				var obj = $(this);
				var pop = $("<div id=\""+o.id+"\"></div>");     //创建一个用于弹出的div层
				obj.append(pop);
				pop.css("display", "none");
				if(o.color != ""){ //更换弹出层的背景颜色
					pop.css("background-color", o.color);
				}
				if(o.alpha != ""){ //更改弹出层的透明度
					pop.css("alpha", o.alpha);
				}
				pop.addClass(o.divClass);
				/*判断是在div中填充传入的html代码还是调用后台ajax返回的html代码*/
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
								pop.append("会话已过期");
							}else{
								pop.append(datas);
							}
						},
						error : function(){
							pop.append(o.error);
						}
					});
				}
				/*给要使用的对象绑定鼠标移入事件*/
				obj.bind("mouseover", function(event){
					pop.css({"left":event.x, "top":event.y, "display":"block"});
				});
				/*给要使用的对象绑定鼠标移出事件*/
				obj.bind("mouseout", function(event){
					pop.css({"display":"none"});
				});
			});	
		}
	});	
})(jQuery);