//dynamically create table when adding a new project
//via jquery and thymeleaf templating which will uncomment below code and evaluate it
$(".add-row").on("click", function(){
	/*[+
	 let machineName = [[${machine.name}]];
	 let priorityNum = [[${project.priorityNum}]];
	 let projectName = [[${project.name}]];
	 let operationNum = [[${project.operationNum}]];
	 let description = [[${project.description}]];
	 let markup = "<tr><td>" + priorityNum + "</tr><td>" + 
	 	projectName + "</td><td>" + operationNum + "</td><td>" +
	 	description + "</td><td>
              <a th:href="[[@{/projects/update(id=${project.projectId})}]]"
                class="btn btn-info btn-sm">
                Update
              </a>

              <a th:href="[[@{/projects/delete(id=${project.projectId})}]]"
                class="btn btn-danger btn-sm"
                onClick="if (!(confirm('Are you sure you want to delete this part?'))) return false">
                Delete
             </a>
             </td>;
	 $("table tbody").append(markup);
	 
	 
	 
	 
	 
	 
	 +]*/
	
});