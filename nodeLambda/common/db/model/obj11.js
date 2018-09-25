const Sequelize = require("sequelize");
const discipline = require("./discipline.js").discipline;
const collegeType = require("./collegeType.js").collegeType;
const baseDAO = require("../base/BaseDAO.js");
const sequelize = baseDAO.init();
let subject = sequelize.define('subject', {
    id: {type : Sequelize.BIGINT, primaryKey : true,  autoIncrement: true},
    name: Sequelize.TEXT,
    discipline_id : {
        type : Sequelize.BIGINT,
        references : {
            model : discipline,
            key  : "id",
            deferrable :  Sequelize.Deferrable.INITIALLY_IMMEDIATE
        }
    },
    college_type_id : {
        type : Sequelize.BIGINT,
        references : {
            model : collegeType,
            key  : "id",
            deferrable :  Sequelize.Deferrable.INITIALLY_IMMEDIATE
        }
    }

    
  },{
    freezeTableName: true
  });
  exports.subject = subject;