const logger = require('LoggingModule');
let assessmentService = require(process.cwd() + "/service/AssessmentService.js");

exports.handler = function(event, context, callback) {  
	switch(event.httpMethod){
        case "POST":
            if(event.path === "/assessment"){
                if (event.body !== null && event.body !== undefined) {
                    let body = JSON.parse(event.body);
                    logger.info("POST /assessment input body : ",body);
                    assessmentService.saveAssessment(body).then(function(data){
                        logger.debug("POST /assessment API response : ",data.dataValues);
                        let response = {
                            "statusCode": 201,
                            "body": JSON.stringify(data.dataValues)
                        };
                        context.succeed(response);
                        
                    },function(error){
                        logger.error("POST /assessment API error : ",error);
                        context.succeed({
                            statusCode: 400,
                            body: "Error"
                        });
                    });
                    
                }
                else{
                    context.succeed({statusCode: 400,"body": "No Data Found"});
                }
            }
			break;
        case "PUT":
        if(event.resource === "/assessment/{id}"){
            if (event.pathParameters !== null && event.pathParameters !== undefined) {
                if (event.pathParameters.id !== undefined && 
                    event.pathParameters.id !== null && 
                    event.pathParameters.id !== "") {
                    logger.info("PUT /assessment/{id} API id : " + event.pathParameters.id);
                    let assessmentId = event.pathParameters.id;
                    let requestBody = JSON.parse(event.body);
                    assessmentService.updateAssessment(assessmentId,requestBody).then((data)=>{
                        logger.debug("PUT /assessment/{id} API response : ", data.dataValues);
                        const response = {
                            "statusCode": 200,
                            "body": JSON.stringify(data.dataValues)
                        };
                        context.succeed(response);
                    },(error)=>{
                        logger.error("PUT /assessment/{id} API error : ",error);
                        context.succeed({
                            statusCode: 400,
                            body: "Error"
                        });
                    });
                }
            }
        }
        break;	
	}
}