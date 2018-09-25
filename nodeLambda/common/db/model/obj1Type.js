const Sequelize = require("sequelize");
const baseDAO = require("../base/BaseDAO.js");
const sequelize = baseDAO.init();
let assessmentType = sequelize.define('assesment_type', {
    id: {type : Sequelize.BIGINT, primaryKey : true, autoIncrement: true},
    name: Sequelize.TEXT
  },{
    freezeTableName: true
  }
);

module.exports.assessmentType = assessmentType;
