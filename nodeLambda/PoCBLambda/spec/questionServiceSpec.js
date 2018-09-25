const questionService = require(process.cwd() + "/service/QuestionService.js");
const localDBModule = require('LocalDBModule');
const question = localDBModule.question;
const tag = localDBModule.tag;
const assessment = localDBModule.assessment;
describe("Test question service API", function() {
        beforeEach(function(){
            spyOn(question,"create").and.callFake(function(questionObj) {
                console.log("Hello from question create() ", questionObj);
                return new Promise(function(resolve, reject){
                    let returnData;
                    if(questionObj.question_title === 'What is pre calculas 9?'){
                        returnData = {
                            dataValues:
                             { id: '37',
                               question_title: 'question from code test',
                               question_text: 'text  for pre calculas 9',
                               question_type_id: '1',
                               question_category_id: '1',
                               is_answer_visible: false,
                               created_by: 1,
                               modified_by: 1,
                               date_created: "2018-08-16T04:41:53.071Z",
                               date_modified: "2018-08-16T04:41:53.071Z",
                               is_active: true
                            }
                        };
                        resolve(returnData);
                    }
                    if(questionObj.question_title === 'question with assessment'){
                        returnData = {
                            dataValues:
                             { id: '37',
                               question_title: 'question with assessment',
                               question_text: 'text question with assessment',
                               question_type_id: '1',
                               question_category_id: '1',
                               is_answer_visible: false,
                               created_by: 1,
                               modified_by: 1,
                               date_created: "2018-08-16T04:41:53.071Z",
                               date_modified: "2018-08-16T04:41:53.071Z",
                               is_active: true
                            },
                            "addAssessment" : function(){
                                console.log("Hello from addAssessment()");
                                return new Promise(function(resolve, reject){
                                    var returnDataAssessment = [
                                        [
                                            {
                                                dataValues : [
                                                    {
                                                        "id": "1",
                                                        "assessment_id": "1",
                                                        "questions_id": "37"
                                                    }
                                                ]
                                            }
                                        ]
                                    ];
                                    resolve(returnDataAssessment);
                                });
                               }
                        };
                        resolve(returnData);
                    }
                    if(questionObj.question_title === 'question with assessment error'){
                        returnData = {
                            dataValues:
                             { id: '37',
                               question_title: 'question with assessment error',
                               question_text: 'text question with assessment error',
                               question_type_id: '1',
                               question_category_id: '1',
                               is_answer_visible: false,
                               created_by: 1,
                               modified_by: 1,
                               date_created: "2018-08-16T04:41:53.071Z",
                               date_modified: "2018-08-16T04:41:53.071Z",
                               is_active: true
                            },
                            "addAssessment" : function(){
                                console.log("Hello from addAssessment()");
                                return new Promise(function(resolve, reject){
                                    reject('error');
                                });
                            }
                        };
                        resolve(returnData);
                    }
                    if(questionObj.question_title === 'question with tag'){
                        returnData = {
                            dataValues:
                             { id: '37',
                               question_title: 'question with tag',
                               question_text: 'text question with tag',
                               question_type_id: '1',
                               question_category_id: '1',
                               is_answer_visible: false,
                               created_by: 1,
                               modified_by: 1,
                               date_created: "2018-08-16T04:41:53.071Z",
                               date_modified: "2018-08-16T04:41:53.071Z",
                               is_active: true
                            },
                            addTag : function(){
                                console.log("Hello from addTag()");
                                return new Promise(function(resolve, reject){
                                    var returnDataAT = [
                                        [
                                            {
                                                dataValues : [
                                                    {
                                                        "id": "91",
                                                        "question_id": "113",
                                                        "tag_id": "1851"
                                                    }
                                                ]
                                            }
                                        ]
                                    ];
                                    resolve(returnDataAT);
                                });
                            }
                        };
                        resolve(returnData);
                    }
                    if(questionObj.question_title === 'question with tag error'){
                        returnData = {
                            dataValues:
                             { id: '37',
                               question_title: 'question with tag error',
                               question_text: 'text question with tag error',
                               question_type_id: '1',
                               question_category_id: '1',
                               is_answer_visible: false,
                               created_by: 1,
                               modified_by: 1,
                               date_created: "2018-08-16T04:41:53.071Z",
                               date_modified: "2018-08-16T04:41:53.071Z",
                               is_active: true
                            },
                            addTag : function(){
                                console.log("Hello from addTag()");
                                return new Promise(function(resolve, reject){
                                    reject('error');
                                });
                            }
                        };
                        resolve(returnData);
                    }
                });
            });
            spyOn(question,"findAll").and.callFake(function(questionObj) {
                console.log("Hello from question findAll()");
                return new Promise(function(resolve, reject){
                    let returnData;
                    if(questionObj.where.id == 113){
                        returnData = [
                            {
                                dataValues:{
                                    id: '113',
                                    question_title: 'question from code test',
                                    question_text: 'text  for pre calculas 9',
                                    question_type_id: '1',
                                    question_category_id: '1',
                                    is_answer_visible: false,
                                    created_by: 1,
                                    modified_by: 1,
                                    date_created: "2018-08-16T04:41:53.071Z",
                                    date_modified: "2018-08-16T04:41:53.071Z",
                                    is_active: true ,
                                    "tags": [
                                        {
                                          "id": "1851",
                                          "name": "tag 2",
                                          "created_by": 1,
                                          "modified_by": 1,
                                          "date_created": "2018-08-10T08:58:05.304Z",
                                          "date_modified": "2018-08-10T08:58:05.304Z",
                                          "is_active": true,
                                          "subject_id": "9",
                                          "parent_tag_id": "1850",
                                          "question_tag": {
                                            "id": "91",
                                            "question_id": "113",
                                            "tag_id": "1851"
                                          }
                                        }
                                    ]
                                },
                                addTag : function(){
                                 console.log("Hello from find question addTag()");
                                 return new Promise(function(resolve, reject){
                                     var returnDataAT = [
                                         [
                                             {
                                                 dataValues : [
                                                     {
                                                         "id": "92",
                                                         "question_id": "113",
                                                         "tag_id": "1851"
                                                     }
                                                 ]
                                             }
                                         ]
                                     ];
                                     resolve(returnDataAT);
                                 });
                                }
                            }
                        ]
                        resolve(returnData);
                    }
                    else if(questionObj.where.id == 45){
                        returnData = [
                            {
                                dataValues:{
                                    id: '45',
                                    question_title: 'question from code test',
                                    question_text: 'text  for pre calculas 9',
                                    question_type_id: '1',
                                    question_category_id: '1',
                                    is_answer_visible: false,
                                    created_by: 1,
                                    modified_by: 1,
                                    date_created: "2018-08-16T04:41:53.071Z",
                                    date_modified: "2018-08-16T04:41:53.071Z",
                                    is_active: true
                                },
                                update : function(){
                                 console.log("Hello from find question addTag()");
                                 return new Promise(function(resolve, reject){
                                     var returnDataUpdate = {
                                        dataValues:{
                                            id: '45',
                                            question_title: 'question from code test',
                                            question_text: 'text  for pre calculas 9',
                                            question_type_id: '1',
                                            question_category_id: '1',
                                            is_answer_visible: false,
                                            created_by: 1,
                                            modified_by: 1,
                                            date_created: "2018-08-16T04:41:53.071Z",
                                            date_modified: "2018-08-16T04:41:53.071Z",
                                            is_active: false
                                        }
                                     };
                                     resolve(returnDataUpdate);
                                 });
                                }
                            }
                        ]
                        resolve(returnData);
                    }
                    else if(questionObj.where.id == 46){
                        reject("error");
                    }
                    else if(questionObj.where.id == 55){
                        returnData = [
                            {
                                dataValues:{
                                    id: '55',
                                    question_title: 'updated title',
                                    question_text: 'updated text',
                                    question_type_id: '1',
                                    question_category_id: '1',
                                    is_answer_visible: true,
                                    created_by: 1,
                                    modified_by: 1,
                                    date_created: "2018-08-16T04:41:53.071Z",
                                    date_modified: "2018-08-16T04:41:53.071Z",
                                    is_active: true
                                },
                                update : function(){
                                 console.log("Hello from find question addTag()");
                                 return new Promise(function(resolve, reject){
                                     var returnDataUpdate = {
                                        dataValues:{
                                            id: '55',
                                            question_title: 'updated title',
                                            question_text: 'updated text',
                                            question_type_id: '1',
                                            question_category_id: '1',
                                            is_answer_visible: true,
                                            created_by: 1,
                                            modified_by: 1,
                                            date_created: "2018-08-16T04:41:53.071Z",
                                            date_modified: "2018-08-16T04:41:53.071Z",
                                            is_active: true
                                        },
                                        "addAssessment" : function(){
                                            console.log("Hello from addAssessment()");
                                            return new Promise(function(resolve, reject){
                                                var returnDataAssessment = [
                                                    [
                                                        {
                                                            dataValues : [
                                                                {
                                                                    "id": "1",
                                                                    "assessment_id": "1",
                                                                    "questions_id": "55"
                                                                }
                                                            ]
                                                        }
                                                    ]
                                                ];
                                                resolve(returnDataAssessment);
                                            });
                                           }
                                     };
                                     resolve(returnDataUpdate);
                                 });
                                }
                            }
                        ]
                        resolve(returnData);
                    }
                    else if(questionObj.where.id == 56){
                        reject("error");
                    }


                });
            });
            spyOn(tag,"findAll").and.callFake(function(tagObj) {
                console.log("Hello from findAll()");
                return new Promise(function(resolve, reject){
                    let returnData;
                    if(tagObj.where.id === 1850){
                        returnData = [
                            {
                                dataValues:{
                                    id: '1850',
                                    name: 'update tag from aws',
                                    created_by: 1,
                                    modified_by: 1,
                                    date_created: "2018-08-10T03:27:45.018Z",
                                    date_modified: "2018-08-10T03:27:45.018Z",
                                    is_active: true,
                                    subject_id: '9',
                                    parent_tag_id: null
                                }
                            }
                        ];
                        resolve(returnData);
                    }
                    else if(tagObj.where.id === 1851){
                        returnData = [
                            {
                                dataValues:{
                                    id: '1851',
                                    name: 'update tag from aws',
                                    created_by: 1,
                                    modified_by: 1,
                                    date_created: "2018-08-10T03:27:45.018Z",
                                    date_modified: "2018-08-10T03:27:45.018Z",
                                    is_active: true,
                                    subject_id: '9',
                                    parent_tag_id: null
                                }
                            }
                        ];
                        resolve(returnData);
                    }
                    else{
                        reject("error");
                    }
                });
            });

            spyOn(assessment,"findAll").and.callFake(function(assessmentObj) {
                console.log("Hello from assessment findAll()");
                return new Promise(function(resolve, reject){
                    let returnData;
                    if(assessmentObj.where.id == 1){
                        returnData = [
                            {
                                dataValues:{
                                    id: '1',
                                    assessment_name: 'This is first practice assessment',
                                    assessment_description: 'Desciption for practice assessment',
                                    version: '1',
                                    created_by: 1,
                                    modified_by: 1,
                                    date_created: "2018-08-27T02:41:11.277Z",
                                    date_modified: "2018-08-27T02:41:11.277Z",
                                    is_active: true,
                                    assessment_type_id: '1'
                                }
                            }
                        ];
                        resolve(returnData);
                    }
                    else if(assessmentObj.where.id == 2){
                        reject("error");
                    }
                    else if(assessmentObj.where.id == 3){
                        returnData = [
                            {
                                dataValues:{
                                    id: '3',
                                    assessment_name: 'This is first placement assessment',
                                    assessment_description: 'Desciption for placement assessment',
                                    version: '1',
                                    created_by: 1,
                                    modified_by: 1,
                                    date_created: "2018-08-27T02:41:11.277Z",
                                    date_modified: "2018-08-27T02:41:11.277Z",
                                    is_active: true,
                                    assessment_type_id: '2'
                                }
                            }
                        ];
                        resolve(returnData);
                    }

                });
            });

        });

        it("save question", function(done) {
            let body = {
                "question_title" : "What is pre calculas 9?",
                "question_text" : "text  for pre calculas 9",
                "question_type_id" : 1,
                "question_category_id" : 1,
                "createdBy" : 1,
                "modifiedBy" : 1
            };
            questionService.saveQuestion(body).then(function(data){
                let questionData = data.dataValues;
                expect(questionData.id).toBe('37');
                done();
            },function(error){
                console.log("===test error====:",error);
                done();
            });
        });

        it("save question with assessment", function(done) {
            let body = {
                "question_title" : "question with assessment",
                "question_text" : "text question with assessment",
                "question_type_id" : 1,
                "question_category_id" : 1,
                "createdBy" : 1,
                "modifiedBy" : 1,
                "assessmentId" : 1
            };
            questionService.saveQuestion(body).then(function(data){
                let questionData = data.dataValues;
                expect(questionData.id).toBe('37');
                done();
            },function(error){
                console.log("===test error====:",error);
                done();
            });
        });

        it("save question with assessment => Error condition", function(done) {
            let body = {
                "question_title" : "question with assessment error",
                "question_text" : "text question with assessment error",
                "question_type_id" : 1,
                "question_category_id" : 1,
                "createdBy" : 1,
                "modifiedBy" : 1,
                "assessmentId" : 2
            };
            questionService.saveQuestion(body).then(function(data){
                let questionData = data.dataValues;
                expect(questionData.id).toBe('37');
                done();
            },function(error){
                console.log("===assessment save error====:",error);
                expect(error).toBe('error');
                done();
            });
        });

        it("save question with tag", function(done) {
            let body = {
                "question_title" : "question with tag",
                "question_text" : "text question with tag",
                "question_type_id" : 1,
                "question_category_id" : 1,
                "createdBy" : 1,
                "modifiedBy" : 1,
                "tagId" : 1850
            };
            questionService.saveQuestion(body).then(function(data){
                let questionData = data.dataValues;
                expect(questionData.id).toBe('37');
                done();
            },function(error){
                console.log("===test error with tag====:",error);
                done();
            });
        });

        it("save question with tag => Error condition", function(done) {
            let body = {
                "question_title" : "question with tag error",
                "question_text" : "text question with tag error",
                "question_type_id" : 1,
                "question_category_id" : 1,
                "createdBy" : 1,
                "modifiedBy" : 1,
                "tagId" : 1854
            };
            questionService.saveQuestion(body).then(function(data){
                let questionData = data.dataValues;
                expect(questionData.id).toBe('37');
                done();
            },function(error){
                console.log("===test error with tag====:",error);
                expect(error).toBe('error');
                done();
            });
        });

        it("tag question", function(done) {
            let body = {
                "questionId" : 113,
                "tagId" : 1851
            };
            questionService.tagQuestion(body).then(function(data){
                let questionData = data.dataValues;
                expect(questionData.id).toBe('113');
                done();
            },function(error){
                console.log("===tag question error ====:",error);
                done();
            });
        });

        it("get tags of question", function(done) {
            let questionId = 113;
            questionService.getAllTagsOfQuestion(questionId).then(function(data){
                let questionData = data.dataValues;
                expect(questionData.tags.length).toBe(1);
                done();
            },function(error){
                console.log("===get tags of question error ====:",error);
                done();
            });
        });

        it("delete question ", function(done) {
            let questionId = 45;
            questionService.deleteQuestion(questionId).then(function(data){
                let questionData = data.dataValues;
                expect(questionData.is_active).toBe(false);
                done();
            },function(error){
                console.log("===delete question error ====:",error);
                done();
            });
        });
        it("delete question with error", function(done) {
            let questionId = 46;
            questionService.deleteQuestion(questionId).then(function(data){
                let questionData = data.dataValues;
                expect(questionData.is_active).toBe(false);
                done();
            },function(error){
                console.log("===delete question error ====:",error);
                done();
            });
        });

        it("update question ", function(done) {
            let questionId = 55;
            let requestBody = {
                questionTitle : 'updated title',
                questionText : 'updated text',
                question_type_id : 1,
                question_category_id :1,
                isAnswerVisible : true
            };
            questionService.updateQuestion(questionId,requestBody).then(function(data){
                let questionData = data.dataValues;
                expect(questionData.question_title).toBe('updated title');
                done();
            },function(error){
                console.log("===update question error ====:",error);
            });
        });

        it("update question with assessment", function(done) {
            let questionId = 55;
            let requestBody = {
                questionTitle : 'updated title',
                questionText : 'updated text',
                question_type_id : 1,
                question_category_id :1,
                isAnswerVisible : true,
                assessmentId : 3
            };
            questionService.updateQuestion(questionId,requestBody).then(function(data){
                let questionData = data.dataValues;
                expect(questionData.question_title).toBe('updated title');
                done();
            },function(error){
                console.log("===update question error ====:",error);
            });
        });

        it("update question with error", function(done) {
            let questionId = 56;
            let requestBody = {
                questionTitle : 'updated title',
                questionText : 'updated text',
                question_type_id : 1,
                question_category_id :1,
                isAnswerVisible : true
            };
            questionService.updateQuestion(questionId,requestBody).then(function(data){
                let questionData = data.dataValues;
                expect(questionData.question_title).toBe('updated title');
            },function(error){
                console.log("===update question error ====:",error);
                done();
            });
        });
});
