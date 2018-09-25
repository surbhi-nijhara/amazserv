const baseDAO = require("../base/BaseDAO.js");
const sequelize = baseDAO.init();
const Sequelize = require("sequelize");
let questionCategory = sequelize.define('question_category', {
    id: {type : Sequelize.BIGINT, primaryKey : true, autoIncrement: true},
    name: Sequelize.TEXT
  },{
    freezeTableName: true
  });

  exports.questionCategory = questionCategory;