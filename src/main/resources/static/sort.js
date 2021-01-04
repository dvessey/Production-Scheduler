$(function () {
	var oldMachine = null;
$(".sortable").sortable({
    connectWith: ".sortable",
	start: function(event, ui) 
       {
            ui.item.startPos = ui.item.index();
			 oldMachine = this.id;
			
					
       },
	recieve: function(event, ui){
		//var oldMachine = ui.sender[0].id;
		
		
	},
	update: function(event, ui){
		//alert(ui.sender[0].id); // Just in case I need the machine id of where the project originally came from
				
		//var machineId = this.id;
		//alert(this.id) //- this is showing the correct machine id
				
		//alert(ui.item[0].id); //this is showing the correct project id
		
		let timeStamp = new Date(Date.now()).toISOString();
		
		var projectId = ui.item[0].id;
		var projectIds = $(this).sortable('toArray');
		var droppedIndex = ui.item.index();
		var startPosition = ui.item.startPos;
		var oldMachineId = oldMachine;
		
		if (this === ui.item.parent()[0]){ //Jquery was firing the update function twice without this
			console.log("projectId: " + projectId);
			console.log('droppedIndex: ' + droppedIndex);
			console.log("startPosition: " + startPosition);
			console.log("oldMachineId: " + oldMachineId);
			console.log("timeStamp: " + timeStamp);
			
			var data = {
				'machineId': this.id,
				'projectId': projectId,
				'startPosition': startPosition,
				'droppedIndex': droppedIndex,
				'projectIds': projectIds,
				'timeStamp': timeStamp,
				'oldMachineId': oldMachineId
			};
				
				
			$.ajax({
				data: JSON.stringify(data),
				type: "POST",
				contentType: "application/json",
				url: '/projects/saveOrder',
				success: function() {   
        			location.reload();  
   				 }
			});
				

		}
	
	}
});
});

