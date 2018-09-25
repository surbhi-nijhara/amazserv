const localDBModule = require('LocalDBModule');
const question = localDBModule.question;
const tag = localDBModule.tag;
const assessment = localDBModule.assessment;
const logger = require('LoggingModule');

const saveQuestion = function(questionJson) {
    logger.info("QuestionService::saveQuestion, received question object : ",questionJson);
    return new Promise((resolve, reject)=>{
        let tagJson;
        let assessmentJson;
        if(questionJson.tagId != null && questionJson.tagId != undefined){
            tag.findAll({
                where: {
                  id: questionJson.tagId,
                  is_active: true
                }
              }).then(function(tagData){
                logger.debug("QuestionService::saveQuestion, tag data : ",tagData[0].dataValues);
                tagJson = tagData;
              },function(error){
                  logger.error("QuestionService::saveQuestion, error while fetching tag data : ",error);
                  reject(error);
              });
        }
        if(questionJson.assessmentId != null && questionJson.assessmentId != undefined){
            assessment.findAll({
                where: {
                  id: questionJson.assessmentId,
                  is_active: true
                }
              }).then(function(assessmentData){
                logger.debug("QuestionService::saveQuestion, assessment data : ",assessmentData[0].dataValues);
                assessmentJson = assessmentData;
              },function(error){
                  logger.debug("QuestionService::saveQuestion, error while fetching assessment : ",error);
                  reject(error);
              });
        }
   
        question.create({
            "question_title" : questionJson.question_title,
            "question_text" : questionJson.question_text, 
            "question_type_id" : questionJson.question_type_id,
            "question_category_id" : questionJson.question_category_id,
            "is_answer_visible" : false,
            "created_by" : questionJson.createdBy,
            "modified_by" : questionJson.modifiedBy
        }).then(function(data){
            logger.debug("QuestionService::saveQuestion, SAVED question object : ",data.dataValues);
            let isTagOrAssessmentPresent = false;
            if(tagJson != null && tagJson != undefined){
                isTagOrAssessmentPresent = true;
                data.addTag(tagJson).then(function(success){
                    logger.debug("QuestionService::saveQuestion, SAVED tag object : ",success);
                    resolve(data);
                },function(error){
                    logger.error("QuestionService::saveQuestion, Error while adding tag : ",error);
                    reject(error);
                })
            }
            if(assessmentJson != null && assessmentJson != undefined){
                isTagOrAssessmentPresent = true;
                data.addAssessment(assessmentJson,{ through: { question_order: questionJson.question_order }}).then(function(success){
                    logger.debug("QuestionService::saveQuestion, SAVED assessment object : ",success);
                    resolve(data);
                },function(error){
                    logger.error("QuestionService::saveQuestion, Error while adding assessment : ",error);
                    reject(error);
                })
            }
            if(!isTagOrAssessmentPresent){
                resolve(data);
            }
            
        },function(error){
            logger.error("QuestionService::saveQuestion, Error while creating question : ",error);
            reject(error);
        });
        
    });
}

const tagQuestion = function(inputJson) {
    logger.info("QuestionService::tagQuestion, received object : ",inputJson);
    return new Promise((resolve, reject)=>{
        let tagJson;
        let questionJson;
        tag.findAll({
            where: {
            id: inputJson.tagId,
            is_active: true
            }
        }).then(function(tagData){
            logger.debug("QuestionService::tagQuestion, tag data : ",tagData[0].dataValues);
            tagJson = tagData;
            return question.findAll({
                where: {
                id: inputJson.questionId,
                is_active: true
                },include: [{
                    model: tag
                }]
            })
        }).then(function(questionData){
            logger.debug("QuestionService::tagQuestion, question data : ",questionData[0].dataValues);
            questionJson = questionData;
            return questionData[0].addTag(tagJson);
        }).then(function(success){
            logger.debug("QuestionService::tagQuestion, tag data associated with question successfully : ",success);
            resolve(questionJson[0]);
        }).catch(function(error){
            logger.error("QuestionService::tagQuestion, error : ",error);
            reject(error);
        });
    });
};

const getAllTagsOfQuestion = function(questionId) {
    logger.info("QuestionService::getAllTagsOfQuestion, received question id : ",questionId);
    return new Promise((resolve, reject)=>{
        question.findAll({
            where: {
              id: questionId,
              is_active: true
            },include: [{
                model: tag
            }]
          }).then(function(questionData){
            logger.debug("QuestionService::getAllTagsOfQuestion, question object : ",questionData[0].dataValues);
            resolve(questionData[0]);
          }).catch(function(error){
            logger.error("QuestionService::getAllTagsOfQuestion, error : ",error);
            reject(error);
          });
    });
    
}

const deleteQuestion = function(questionId) {
    logger.info("QuestionService::deleteQuestion, received question id : ",questionId);
    return new Promise((resolve, reject)=>{
        question.findAll({
            where: {
              id: questionId,
              is_active: true
            }
          }).then(function(questionData){
            logger.debug("QuestionService::deleteQuestion, question obj : ",questionData[0].dataValues);
            let questionObj = questionData[0];
            return questionObj.update({
                is_active : false,
                date_modified : new Date()
            });
          }).then(function(updatedObj){
                logger.debug("QuestionService::deleteQuestion, updated question obj : ",updatedObj.dataValues);
                resolve(updatedObj);
          }).catch(function(error){
            logger.error("QuestionService::deleteQuestion, Error : ",error);
            reject(error);
          });
    });
    
}

const updateQuestion = function(questionId, requestBody) {
    logger.info("QuestionService::updateQuestion, received questionId %s and question object : ",questionId, requestBody);
    return new Promise((resolve, reject)=>{
        let updatedQuestionObj;
        question.findAll({
            where: {
              id: questionId,
              is_active: true
            }
          }).then(function(questionData){
            logger.debug("QuestionService::updateQuestion, question object : ",questionData[0].dataValues);
            let questionObj = questionData[0];
            return questionObj.update({
                question_title : requestBody.questionTitle,
                question_text : requestBody.questionText,
                question_type_id : requestBody.question_type_id,
                question_category_id : requestBody.question_category_id,
                is_answer_visible : requestBody.isAnswerVisible,
                date_modified : new Date()
            });
          }).then(function(updatedObj){
                logger.debug("QuestionService::updateQuestion, updated question object : ",updatedObj.dataValues);
                updatedQuestionObj = updatedObj;
                if(requestBody.assessmentId != null && requestBody.assessmentId != undefined){
                    return assessment.findAll({
                        where: {
                          id: requestBody.assessmentId,
                          is_active: true
                        }
                      });
                }
                else{
                    return resolve(updatedObj);
                }
          }).then(function(dataObject){
            if(requestBody.assessmentId != null && requestBody.assessmentId != undefined){
                logger.debug("QuestionService::updateQuestion, assessment object : ",dataObject.dataValues);
                return updatedQuestionObj.addAssessment(dataObject,{ through: { question_order: requestBody.question_order }});
            }
            else{
                return resolve(dataObject);
            }
          }).then(function(responseObject){
             resolve(updatedQuestionObj);
          }).catch(function(error){
            logger.error("QuestionService::updateQuestion, error : ",error);
            reject(error);
          });
    });
    
}

module.exports = {
    "getAllTagsOfQuestion" : getAllTagsOfQuestion,
    "tagQuestion" : tagQuestion,
    "saveQuestion" : saveQuestion,
    "deleteQuestion" : deleteQuestion,
    "updateQuestion" : updateQuestion
};