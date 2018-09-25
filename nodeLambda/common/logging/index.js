let log4js = require('log4js');
const configFile = require("./config/logConfig.json");
log4js.configure(configFile);
const logger = log4js.getLogger();

module.exports = logger;