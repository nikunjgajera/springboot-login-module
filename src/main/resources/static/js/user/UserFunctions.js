//-------------user functions goes here
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>PROJECT FUNCTION<<<<<<<<<<<<<<<<<<
/**
 * this function will load project task to content area
 * @param projectId
 */
function showProjectTask(projectId){
		
		blockUI();
	
		clearTaskInfo();
	
		projectId = getCurrentProjectId(projectId);
		
		updateTeamMemberView(projectId);
		
		var url = '/project/'+projectId;

	    $("#task-result").load(url, function(data){
	    	
	    	
	    	unblockUI()
	    	
	    });
	    
	    
}

/**
 * show create project form
 */
function showCreateProjectForm(){
	
	$.blockUI({ message: $('#project-form'), css: { top:'25%',left:'25%',width: '50%' } });
	
}

/**
 * show edit project form
 */
function showEditProjectForm(){
	
	blockUI();
	
	projectId = getCurrentProjectId();
	
	var url = '/project/update/'+projectId;

    $("#update-project-form").load(url);
    
    $.blockUI({ message: $('#update-project-form'), css: { top:'25%',left:'25%',width: '50%' } });
	
}

/**
 * submit update project form
 */
function submitUpdateProjectForm(){
	
	var validation = $('#UpdateProjectForm').valid();
	
	if(validation == true){
	
		blockUI();
		
		$('#UpdateProjectForm').submit();
		
	}else{
		
		alert("Update Project Form is not valid");
		
	}
	
}

/**
 * submit create project form
 */
function submitProjectForm(){
	
	var validation = $('#projectForm').valid();
	
	if(validation == true){
	
		blockUI();
		
		var formUrl = $('#projectForm').attr('action');
		
		$.ajax({
		    
			type: 'post',
	        url: formUrl,
	        data: $('#projectForm').serialize(),
	        success: function (data) {
	        	
	        	if(data == true) {
	        		
	        		window.location.reload();
	        	
	        	} else {
	        		
	        		ShowErrorNotification();
	        		
	        	}
	        
	        }
	      });
		
		unblockUI();
		
	}
	
}

//>>>>>>>>>>>>>>>>>>>>>>>>>MEMBER FUNCTIONS<<<<<<<<<<<<<<<<<
/**
 * this function will load members task to content area
 * @param projectId
 */
function showMemberTask(memberId, projectId){
	
	blockUI();
	
	clearTaskInfo();
	
	memberId = getCurrentMemberId(memberId);
	
	projectId = getCurrentProjectId(projectId);
	
	if (memberId != '' && memberId != undefined && projectId != '' && projectId != undefined) {
		
		var url = '/task/all/member/'+projectId+"/"+memberId;

	    $("#task-result").load(url, function(){unblockUI()});
		
	}
	
}


/**
 * this function will load my task to content area
 * @param projectId
 */
function showMyTasks(){
	
	blockUI();
	
	clearTaskInfo();
	
	clearSelection();
	
	var url = '/task/all';

    $("#task-result").load(url, function(){unblockUI()});
	
}
//>>>>>>>>>>>>>>>>>>>>>>>>>>>TASK FUNCTION<<<<<<<<<<<<<<<<<<
/**
 * this function will show task information
 * @param taskId
 */
function showTaskInfo(taskId){
	
	if (taskId != '' && taskId != undefined) {
		
		blockUI();
		
		var url = '/task/'+taskId;

	    $("#task-info").load(url, function(){unblockUI()});
		
	}
	
}

/**
 * list all active task by project id
 * @param projectId
 */
function listActiveTask(projectId, memberId){
		
		clearTaskInfo();
	
		projectId = getCurrentProjectId(projectId);
		
		if(memberId != '' && memberId != undefined){
			
			blockUI();
			
			var url = '/task/active/member/'+projectId+"/"+memberId;
			
		}else{
			
			blockUI();
			
			var url = '/task/active/project/'+projectId;
			
		}
		
		$("#task-result").load(url, function(){unblockUI()});
	
}

/**
 * list all completed task by project id
 * @param projectId
 */
function listCompletedTask(projectId, memberId){
	
	clearTaskInfo();
	
	projectId = getCurrentProjectId(projectId);
	
	if(memberId != '' && memberId != undefined){
	
		blockUI();
		
		var url = '/task/completed/member/'+projectId+"/"+memberId;
		
	}else{
		
		blockUI();
		
		var url = '/task/completed/project/'+projectId;
		
	}
	
	$("#task-result").load(url, function(){unblockUI()});
}
	
/**
 * list all due task by project id
 * @param projectId
 */
function listDueTask(projectId, memberId){
	
	clearTaskInfo();
	
	projectId = getCurrentProjectId(projectId);
	
	if(memberId != '' && memberId != undefined){
		
		blockUI();
		
		var url = '/task/due/member/'+projectId+"/"+memberId;
		
	}else{
		
		blockUI();
		
		var url = '/task/due/project/'+projectId;
		
	}
	
	$("#task-result").load(url, function(){unblockUI()});
}

/**
 * list all Late task by project id
 * @param projectId
 */
function listLateTask(projectId, memberId){
	
	clearTaskInfo();
	
	projectId = getCurrentProjectId(projectId);
	
	if(memberId != '' && memberId != undefined){
		
		blockUI();
		
		var url = '/task/late/member/'+projectId+"/"+memberId;
		
	}else{
		
		blockUI();
		
		var url = '/task/late/project/'+projectId;
		
	}
	
	$("#task-result").load(url, function(){unblockUI()});
}

/**
 * show create task form
 */
function createTaskForm(){
	
	$.blockUI({ message: $('#task-form'), css: { top:'25%',left:'25%',width: '50%' } });
	
}

/**
 * show update task form
 */
function editTaskForm(taskId){
	
	if(taskId != '' && taskId != undefined){
		
		blockUI();
		
		var url = '/task/update/'+taskId;
		
		$("#update-task-form").load(url);
		
		$.blockUI({ message: $('#update-task-form'), css: { top:'25%',left:'25%',width: '50%' } });
		
	}else{
		
		$.blockUI({ message: $('#update-task-form'), css: { top:'25%',left:'25%',width: '50%' } });
		
	}
	
}

/**
 * submit create task form
 */
function submitTaskForm(){
	
	var validation = $('#taskForm').valid();
	
	if(validation == true){
	
		blockUI();
		
		var formUrl = $('#taskForm').attr('action');
		
		$.ajax({
		    
			type: 'post',
	        url: formUrl,
	        data: $('#taskForm').serialize(),
	        success: function (data) {
	        	
	        	$("#task-result").html(data);
	        
	        }
	      });
		
		unblockUI();
		
	}
	
}

/**
 * submit create task form
 */
function submitUpdateTaskForm(){
	
	var validation = $('#UpdateTaskForm').valid();
	
	if(validation == true){
	
		blockUI();
		
		$('#UpdateTaskForm').submit();
		
	}else{
		
		alert("form not valid");
		
	}
	
}


//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>UTILITY FUNCTIONS<<<<<<<<<<<<<<<<<<<
/**
 * this will get final current project id
 * @param projectId
 */
function getCurrentProjectId(projectId){
	
	if(projectId != '' && projectId != undefined){
		
		/*remove active class*/
		$("#sidebar").find("ul.project").find("li.active").removeClass("active");
		
		/*add active class*/
		$("#sidebar").find("ul.project").find("#"+projectId+"").parent().addClass("active");
		
		return projectId
		
	}else{
		
		return Number($("#sidebar").find("ul.project").find("li.active").find("a").attr("id"));
		
	}
}

/**
 * this will get final current member id
 * @param projectId
 */
function getCurrentMemberId(memberId){
	
	if(memberId != '' && memberId != undefined){
		
		/*remove active class*/
		$("#sidebar").find("ul.team").find("li.active").removeClass("active");
		
		/*add active class*/
		$("#sidebar").find("ul.team").find("#"+memberId+"").parent().addClass("active");
		
		return memberId
		
	}else{
		
		return Number($("#sidebar").find("ul.team").find("li.active").find("a").attr("id"));
		
	}
}

/**
 * this function will clear Task info screen
 */
function clearTaskInfo(){
	
	$("#task-info").html("");
	
}

/**
 * this function will clear selected project and member
 */
function clearSelection(){
	
	/*remove active class*/
	$("#sidebar").find("ul.project").find("li.active").removeClass("active");
	
	$("#sidebar").find("ul.team").find("li.active").removeClass("active");
	
}

/**
 * update side bar with team member for current project
 * @param projectId
 */
function updateTeamMemberView(projectId){
	
	var url = '/project/team/'+projectId;

    $("#team-result").load(url);
	
}

/**
 * blockui for ajax load
 */
function blockUI(){
	
	$.blockUI({css: { 
        border: 'none', 
        padding: '15px', 
        backgroundColor: 'transparent', 
        opacity: 0.5, 
        color: '#fff' 
    }, message: '<img src="/img/loading.gif" />' });
	
}

/**
 * unblock ui
 */
function unblockUI(){
	
	$.unblockUI();

}
/**
 * show error notification
 */
function showErrorNotification(errorMessage){
	
	$.blockUI({ css: { 
        border: 'none', 
        padding: '15px', 
        backgroundColor: '#000', 
        '-webkit-border-radius': '10px', 
        '-moz-border-radius': '10px', 
        opacity: .5, 
        color: '#99cc33',
        fontSize:'initial',
        fontWeight:'bold'
    },message:errorMessage });
	setTimeout($.unblockUI, 3000);
}
