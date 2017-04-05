var pointItems = new Array(); 
 //获取条目列表供项点添加 
 function getItemList(){
     	   $.ajax({
			     url:"../../item/pointGetItemList.do",
				 type:"GET",
				 data:{"page":page},
				 dataType:"JSON",
				 success:function(result){
					    console.log(result);
						//console.log("1111");
					    changeTable(result);
					 }
			  });
 }
//填充供选择的条目
function changeTable(result){
	items = result.data;
	function changeTable(page, result, callback) {
	// 设置 table
	$("#list-table tbody").empty();
	for (var i = 0; i < items.length; i++) {
		$("#items-table tbody").append('<tr>' +
			'<td>' + (i+1) + '</td>' +
			'<td>' + items[i].itemInfo + '</td>' +
			'<td>选择' +
			'<input name="item" class="item-check" type="checkbox" value="'+items[i].itemCode+' onchange="checkChanges(this)">'+
			'</td>' +
			'</tr>');
	}
}
}
//全选被点击时
function checkAlls(checkBox){
if($(checkBox).is(':checked')){
	//console.log("all checked");
	for(var i=0;i<$("[name = item]:checkbox").length;i++){
		if($("[name = item]:checkbox").eq(i).is(":checked") == false){
			$("[name = item]:checkbox").eq(i).click();
		}	
	}
	//console.log(pointItem);
}else{
	//console.log("all unchecked");
	for(var i=0;i<$("[name = item]:checkbox").length;i++){
		$("[name = item]:checkbox").eq(i).click();
		}
	//$("[name = point]:checkbox").eq(0).click();
	//console.log(pointItem); 
}
}
 //条目的checkBox被点击时
 function checkChanges(checkBox) {
	 	if($(checkBox).is(':checked')){
	 		//console.log("checked");
	 		pointItems.push($(checkBox).val());
	 		//console.log(pointItem);
	 	}else{
	 		pointItems.splice(jQuery.inArray($(checkBox).val(),pointItems),1); 
	 		$('#check-all').attr("checked",false);
	 		
	 		//console.log(pointItem);
	 	}
	 }
