const assessmentType = require("./model/assesmentType.js").assessmentType;
const assessment = require("./model/assessment.js").assessment;
const assessmentQuestion = require("./model/assessmentQuestion.js").assessmentQuestion;
const collegeType = require("./model/collegeType.js").collegeType;
const discipline = require("./model/discipline.js").discipline;
const question = require("./model/question.js").question;
const questionCategory = require("./model/questionCategory.js").questionCategory;
const questionTag = require("./model/questionTag.js").questionTag;
const questionType = require("./model/questionType.js").questionType;
const subject = require("./model/subject.js").subject;
const tag = require("./model/tag.js").tag;

const modelCollection = {
    "assessmentType" : assessmentType,
    "assessment" : assessment,
    "assessmentQuestion" : assessmentQuestion,
    "collegeType" : collegeType,
    "discipline" : discipline,
    "question" : question,
    "questionCategory" : questionCategory,
    "questionTag" : questionTag,
    "questionType" :questionType,
    "subject" : subject,
    "tag" : tag
}

module.exports = modelCollection;