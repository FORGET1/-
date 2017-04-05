var callback;
function sslAlert(obj) {
	// body...
	this.titleName = obj.titleName || "通知";
	this.cancleBtn = obj.cancleBtn || "取消";
	this.okBtn = obj.okBtn || "确认";
	// this.callback = obj.callback || null;
	callback = obj.callback || null;
	

}
sslAlert.prototype.show = function() {
	// body...
	
	$("body").append('<div class="dialog-ct" id="dialog-ct">'+
            '<div class="alert-dialog">'+
                '<div class="title-bar">'+
                    '<h3 id="title">'+this.titleName+'</h3>'+
                '</div>'+
                '<div class="dialog-btn">'+
                    '<button class="cancel-btn" onclick="cancle()">'+this.cancleBtn+'</button>'+
                    '<button class="blue-btn" onclick = "ok()">'+this.okBtn+'</button>'+
                '</div>'+
            '</div>'+
    '</div>');
};

function cancle() {
	
	$("#dialog-ct").remove();
}
function ok(){
	$("#dialog-ct").remove();
	if (callback != null) 
		callback();
}