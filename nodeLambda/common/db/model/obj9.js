const Sequelize = require("sequelize");
const baseDAO = require("../base/BaseDAO.js");
const sequelize = baseDAO.init();
let questionTag = sequelize.define('question_tag', {
    id: {type : Sequelize.BIGINT, primaryKey : true, autoIncrement: true}
    },{
    freezeTableName: true,
      // I don't want createdAt
      createdAt: false,
      // I want updatedAt to actually be called updateTimestamp
      updatedAt: false,
      // And deletedAt to be called destroyTime (remember to enable paranoid for this to work)
      deletedAt: false
});

exports.questionTag = questionTag;