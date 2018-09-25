const assessmentService = require(process.cwd() + "/service/AssessmentService.js");
const localDBModule = require('LocalDBModule');
const assessment = localDBModule.assessment;

describe("Test assessment service API", function() {
    beforeEach(function(){
        spyOn(assessment,"findAll").and.callFake(function(assessmentObj) {
            console.log("Hello from assessment findAll()");
            return new Promise(function(resolve, reject){
                let returnData;
                if(assessmentObj.where.id == 1){
                    returnData = [
                        {
                            dataValues:{ 
                                id: '1',
                                assessment_name: 'This is first placement assessment',
                                assessment_description: 'Desciption for placement assessment',
                                version: '1',
                                created_by: 1,
                                modified_by: 1,
                                date_created: "2018-08-27T02:41:11.277Z",
                                date_modified: "2018-08-27T02:41:11.277Z",
                                is_active: true,
                                assessment_type_id: '2'
                            },
                            "update" : function(){
                                return new Promise(function(resolve, reject){
                                    var returnDataUpdate = {
                                       dataValues:{ 
                                            id: '1',
                                            assessment_name: 'first assessment modified',
                                            assessment_description: 'Desciption for placement assessment',
                                            version: '1',
                                            created_by: 1,
                                            modified_by: 1,
                                            date_created: "2018-08-27T02:41:11.277Z",
                                            date_modified: "2018-08-27T02:41:11.277Z",
                                            is_active: true,
                                            assessment_type_id: '2'
                                       } 
                                    };
                                    resolve(returnDataUpdate);
                                });
                            }
                        }
                    ];
                    resolve(returnData);
                } 
                if(assessmentObj.where.id == 2){
                    reject("error")
                }
            });
        });

        spyOn(assessment,"create").and.callFake(function(assessmentObj) {
            return new Promise(function(resolve, reject){
                let returnData;
                if(assessmentObj.assessment_name === 'first assessment'){
                    returnData = {
                        dataValues:
                            { id: '1',
                                assessment_name: 'first assessment',
                                assessment_description: 'Desciption for placement assessment',
                                version: '1',
                                created_by: 1,
                                modified_by: 1,
                                date_created: "2018-08-27T02:41:11.277Z",
                                date_modified: "2018-08-27T02:41:11.277Z",
                                is_active: true,
                                assessment_type_id: '2'
                        }
                    };
                    resolve(returnData);
                }
                else if(assessmentObj.assessment_name === 'first assessment error'){
                    reject("error")
                }
            });
        });
    });

    it("save assessment", function(done) {
        let body = {
            "assessment_name": "first assessment",
            "assessment_description": "Description for assessment",
            "assessment_type_id": 1,
            "version": 1,
            "created_by": 1,
            "modified_by": 1
        };
        assessmentService.saveAssessment(body).then(function(data){
            let assessmentData = data.dataValues;
            expect(assessmentData.id).toBe('1');
            done();
        },function(error){
            console.log("===test error====:",error);
            done();
        });
    });

    it("save assessment => Error", function(done) {
        let body = {
            "assessment_name": "first assessment error",
            "assessment_description": "Description for assessment",
            "assessment_type_id": 1,
            "version": 1,
            "created_by": 1,
            "modified_by": 1
        };
        assessmentService.saveAssessment(body).then(function(data){
            let assessmentData = data.dataValues;
        },function(error){
            console.log("===assessment error====:",error);
            expect(error).toBe('error');
            done();
        });
    });

    it("update assessment", function(done) {
        let assessmentId = 1;
        let body = {
            "assessment_name": "first assessment modified",
            "assessment_description": "Description for assessment",
            "assessment_type_id": 1,
            "version": 1,
            "created_by": 1,
            "modified_by": 1
        };
        assessmentService.updateAssessment(assessmentId,body).then(function(data){
            let assessmentData = data.dataValues;
            expect(assessmentData.assessment_name).toBe('first assessment modified');
            done();
        },function(error){
            console.log("===assessment error====:",error);
        });
    });

    it("update assessment => error", function(done) {
        let assessmentId = 2;
        let body = {
            "assessment_name": "first assessment modified error",
            "assessment_description": "Description for assessment",
            "assessment_type_id": 1,
            "version": 1,
            "created_by": 1,
            "modified_by": 1
        };
        assessmentService.updateAssessment(assessmentId,body).then(function(data){
            let assessmentData = data.dataValues;
        },function(error){
            console.log("===assessment update error====:",error);
            expect(error).toBe('error');
            done();
        });
    });
});