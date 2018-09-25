const Sequelize = require("sequelize");
const baseDAO = require("../base/BaseDAO.js");
const sequelize = baseDAO.init();
let assessmentQuestion = sequelize.define('assessment_question', {
    id: {type : Sequelize.BIGINT, primaryKey : true, autoIncrement: true},
    question_order :  Sequelize.INTEGER
    },{
    freezeTableName: true,
      // I don't want createdAt
      createdAt: false,
      // I want updatedAt to actually be called updateTimestamp
      updatedAt: false,
      // And deletedAt to be called destroyTime (remember to enable paranoid for this to work)
      deletedAt: false
});

exports.assessmentQuestion = assessmentQuestion;