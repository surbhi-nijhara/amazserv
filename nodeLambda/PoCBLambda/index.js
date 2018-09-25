let questionService = require(process.cwd() + "/service/QuestionService.js");
const logger = require('LoggingModule');

exports.handler = function(event, context, callback) {  
	switch(event.httpMethod){
        case "POST":
            if(event.path === "/question"){
                if (event.body !== null && event.body !== undefined) {
                    let body = JSON.parse(event.body);
                    logger.info("POST /question input body : ",body);
                    questionService.saveQuestion(body).then(function(data){
                        logger.debug("POST /question API reponse : ",data.dataValues);
                        let response = {
                            "statusCode": 201,
                            "body": JSON.stringify(data.dataValues)
                        };
                        context.succeed(response);
                        
                    },function(error){
                        logger.error("POST /question API error : ",error);
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
			else if(event.path === "/tagQuestion"){
                if (event.body !== null && event.body !== undefined) {
                    let body = JSON.parse(event.body);
                    logger.info("POST /tagQuestion API input body : ",body);
                    questionService.tagQuestion(body).then(function(data){
                        logger.debug("POST /tagQuestion API reponse : ",data.dataValues);
                        let response = {
                            "statusCode": 201,
                            "body": JSON.stringify(data.dataValues)
                        };
                        //callback(null,"success");
                        //var responseBody = JSON.stringify(response)
                        context.succeed(response);
                        //callback(null,response);
                        
                    },function(error){
                        logger.error("POST /tagQuestion API error : ",error);
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
		case "GET":
        if(event.path === "/tags"){
            if (event.queryStringParameters !== null && event.queryStringParameters !== undefined) {
                if (event.queryStringParameters.questionId !== undefined && 
                    event.queryStringParameters.questionId !== null && 
                    event.queryStringParameters.questionId !== "") {
                    logger.info("GET /tags API input question id : ",event.queryStringParameters.questionId);
                    let questionId = event.queryStringParameters.questionId;
                    questionService.getAllTagsOfQuestion(questionId).then(function(data){
                        logger.debug("GET /tags API response : ",data.dataValues);
                        const response = {
                            "statusCode": 200,
                            "body": JSON.stringify(data.dataValues)
                        };
                        context.succeed(response);
                    },function(error){
                        logger.error("GET /tags API error : ",error);
                        context.succeed({
                            statusCode: 400,
                            body: "Error"
                        });
                    });
                }
            }
        }
        break;
        case "DELETE":
        if(event.resource === "/question/{question_id}"){
            if (event.pathParameters !== null && event.pathParameters !== undefined) {
                if (event.pathParameters.question_id !== undefined && 
                    event.pathParameters.question_id !== null && 
                    event.pathParameters.question_id !== "") {
                    logger.info("DELETE /question/{question_id} API input question id : " + event.pathParameters.question_id);
                    let questionId = event.pathParameters.question_id;
                    questionService.deleteQuestion(questionId).then((data)=>{
                        logger.debug("DELETE /question/{question_id} API response : ",data.dataValues);
                        const response = {
                            "statusCode": 202
                        };
                        context.succeed(response);
                    },(error)=>{
                        logger.error("DELETE /question/{question_id} API error : ",error);
                        context.succeed({
                            statusCode: 400,
                            body: "Error"
                        });
                    });
                }
            }
        }  
        break;
        case "PUT":
        if(event.resource === "/question/{question_id}"){
            if (event.pathParameters !== null && event.pathParameters !== undefined) {
                if (event.pathParameters.question_id !== undefined && 
                    event.pathParameters.question_id !== null && 
                    event.pathParameters.question_id !== "") {
                    logger.info("PUT /question/{question_id} API input question id : " + event.pathParameters.question_id);
                    let questionId = event.pathParameters.question_id;
                    let requestBody = JSON.parse(event.body);
                    questionService.updateQuestion(questionId,requestBody).then((data)=>{
                        logger.debug("PUT /question/{question_id} API response : ",data.dataValues);
                        const response = {
                            "statusCode": 200,
                            "body": JSON.stringify(data.dataValues)
                        };
                        context.succeed(response);
                    },(error)=>{
                        logger.error("PUT /question/{question_id} API error : ",error);
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