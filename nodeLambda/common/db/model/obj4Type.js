const Sequelize = require("sequelize");
const baseDAO = require("../base/BaseDAO.js");
const sequelize = baseDAO.init();
let collegeType = sequelize.define('college_type', {
    id: {type : Sequelize.BIGINT, primaryKey : true, autoIncrement: true},
    name: Sequelize.TEXT
  },{
    freezeTableName: true
  });

exports.collegeType = collegeType;