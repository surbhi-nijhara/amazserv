const baseDAO = require("../base/BaseDAO.js");
const sequelize = baseDAO.init();
const Sequelize = require("sequelize");
let questionType = sequelize.define('question_type', {
    id: {type : Sequelize.BIGINT, primaryKey : true, autoIncrement: true},
    name: Sequelize.TEXT
  },{
    freezeTableName: true
  });

exports.questionType = questionType;