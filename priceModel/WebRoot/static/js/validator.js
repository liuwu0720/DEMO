//扩大easyui表单的验证
$(function(){
	$.extend($.fn.validatebox.defaults.rules, {    
	    number: {    
	        validator: function(value){    
	        	 return /^\d+(\.{0,1}\d+){0,1}$/.test(value);   
	        },    
	        message: '请输入数字，不能为负数'   
	    }    
	});
})
  