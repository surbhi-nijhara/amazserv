const localDBModule = require('LocalDBModule');
const assessment = localDBModule.assessment;

const logger = require('LoggingModule');

const saveAssessment = function(assessmentJson) {
    logger.info("AssessmentService::saveAssessment - assessmentJson : ",assessmentJson);
    return new Promise((resolve, reject)=>{
        assessment.create({
            "assessment_name" : assessmentJson.assessment_name,
            "assessment_description" : assessmentJson.assessment_description, 
            "assessment_type_id" : assessmentJson.assessment_type_id,
            "version" : assessmentJson.version,
            "created_by" : assessmentJson.created_by,
            "modified_by" : assessmentJson.modified_by,
            "is_active" : true
        }).then(function(data){
            logger.debug("AssessmentService::saveAssessment - SAVED assessment object : ", data.dataValues);
            resolve(data);
        }).catch(function(error){
            logger.error("AssessmentService::saveAssessment - Error while saving assessment : ", error);
            reject(error)
        })
    });
}

const updateAssessment = function(assessmentId, assessmentJson) {
    logger.info("AssessmentService::updateAssessment - assessmentId - %s and assessmentJson : ",assessmentId,assessmentJson);
    return new Promise((resolve, reject)=>{
        assessment.findAll({
            where: {
              id: assessmentId,
              is_active: true
            }
          }).then(function(assessmentData){
            let assessmentObj = assessmentData[0];
            return assessmentObj.update({
                "assessment_name" : assessmentJson.assessment_name,
                "assessment_description" : assessmentJson.assessment_description, 
                "assessment_type_id" : assessmentJson.assessment_type_id,
                "version" : assessmentJson.version,
                "created_by" : assessmentJson.created_by,
                "modified_by" : assessmentJson.modified_by,
                "date_modified" : new Date()
            });
          }).then(function(updatedObj){
                logger.debug("AssessmentService::updateAssessment - UPDATED assessment object : ", updatedObj.dataValues);
                resolve(updatedObj);
          }).catch(function(error){
                logger.error("AssessmentService::updateAssessment - Error while updating assessment : ", error);
                reject(error);
          });
    });
}

module.exports = {
    "saveAssessment" : saveAssessment,
    "updateAssessment" : updateAssessment
};