//$(document).ready(function(){
//    $(".txt")[0].onkeydown=function(event){
//        var kc=0;
//        if(window.event){
//            kc=event.keyCode;
//        }else if(event.which){
//            kc=event.which;
//        }
//        //48-57 96-105
//        if((kc >= 48 && kc <= 57) || (kc >= 96 && kc <= 105)){
//            //console.log(kc);
//        }else{
//            alert("is number?");
//            this.value=this .value.substring(0,this.value.length-1);
//            //return false;
//            
//        }  
//    }
//});
//alert 不要使用自带的
//输入框逻辑问题，只有在提交的时候验证
function test(ob){
	var len=ob.value.length;
	if(len>18) { 
		ob.value=ob.value.substr(0,18);
		Lobibox.alert('warning', {
			 title:"提示",
         msg: "不能超过18个字符",
         buttons:{
        	 ok:{
        		 text:"确定"
        	 }
         }
     });
	}
	
}

function figure(obj) {
	var len=obj.value.length;
	if((!/^\d+$/.test(obj.value))&&len!=0) {
		Lobibox.alert('warning', {
			 title:"提示",
          msg: "只能输入数字",
          buttons:{
         	 ok:{
         		 text:"确定"
         	 }
          }
      });
	}	
		
	obj.value=obj.value.replace(/[^\d]+/g,'');
}