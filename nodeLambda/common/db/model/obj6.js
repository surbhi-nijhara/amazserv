const Sequelize = require("sequelize");
const baseDAO = require("../base/BaseDAO.js");
const sequelize = baseDAO.init();
let discipline = sequelize.define('discipline', {
    id: {type : Sequelize.BIGINT, primaryKey : true, autoIncrement: true},
    name: Sequelize.TEXT
  },{
    freezeTableName: true
  });

exports.discipline = discipline;