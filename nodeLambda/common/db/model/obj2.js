const Sequelize = require("sequelize");
const baseDAO = require("../base/BaseDAO.js");
const sequelize = baseDAO.init();
const assessmentType = require("./assesmentType.js").assessmentType;
const assessmentQuestion = require("./assessmentQuestion.js").assessmentQuestion;
const question = require("./question.js").question;
const assessment = sequelize.define('assessment', {
    id: {type : Sequelize.BIGINT, primaryKey : true,  autoIncrement: true},
    assessment_name: Sequelize.TEXT,
    assessment_description : Sequelize.TEXT,
    version : Sequelize.TEXT,
    created_by : Sequelize.BIGINT,
    modified_by : Sequelize.BIGINT,
    date_created : Sequelize.DATE,
    date_modified : Sequelize.DATE,
    is_active : Sequelize.BOOLEAN,
    assessment_type_id : {
        type : Sequelize.BIGINT,
        references : {
            model : assessmentType,
            key  : "id",
            deferrable :  Sequelize.Deferrable.INITIALLY_IMMEDIATE
        }
    }

    
  },{
    freezeTableName: true,
     // I don't want createdAt
    createdAt: false,
    // I want updatedAt to actually be called updateTimestamp
    updatedAt: false,
    // And deletedAt to be called destroyTime (remember to enable paranoid for this to work)
    deletedAt: false
  });
  assessment.belongsToMany(question, {"through":assessmentQuestion, foreignKey: 'assessment_id',otherKey: 'questions_id'});
  question.belongsToMany(assessment, {"through":assessmentQuestion,foreignKey: 'questions_id',otherKey: 'assessment_id'});
  module.exports.assessment = assessment;