const Sequelize = require('sequelize');
const packagejson = require("../package.json");
const sequelize = new Sequelize(packagejson.sql_con_params.DB_NAME, packagejson.sql_con_params.RDS_USERNAME, packagejson.sql_con_params.RDS_PASSWORD, {
  host: packagejson.sql_con_params.RDS_HOSTNAME,
  dialect: 'postgres',
  operatorsAliases: false,
  pool: {
    max: 5,
    min: 0,
    acquire: 30000,
    idle: 10000
  }
});

const init = function() {
	return sequelize;
}
module.exports = {
  "init" : init
};
