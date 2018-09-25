const Sequelize = require("sequelize");
const baseDAO = require("../base/BaseDAO.js");
const subject = require("./subject.js").subject;
const questionTag = require("./questionTag.js").questionTag;
const question = require("./question.js").question;
const sequelize = baseDAO.init();
var tag = sequelize.define('tag', {
    id: {type : Sequelize.BIGINT, primaryKey : true,  autoIncrement: true},
    name: Sequelize.TEXT,
    created_by : Sequelize.BIGINT,
    modified_by : Sequelize.BIGINT,
    date_created : Sequelize.DATE,
    date_modified : Sequelize.DATE,
    is_active : Sequelize.BOOLEAN,
    subject_id : {
        type : Sequelize.BIGINT,
        references : {
            model : subject,
            key  : "id",
            deferrable :  Sequelize.Deferrable.INITIALLY_IMMEDIATE
        }
    },
    parent_tag_id : {
        type : Sequelize.BIGINT,
        references : {
            model : tag,
            key  : "id",
            deferrable :  Sequelize.Deferrable.INITIALLY_IMMEDIATE
        }
    }

    
  },{
    freezeTableName: true,
       // I don't want createdAt
    createdAt: false,
    // I want updatedAt to actually be called updateTimestamp
    updatedAt: false,
    // And deletedAt to be called destroyTime (remember to enable paranoid for this to work)
    deletedAt: false
  });
  
  exports.tag = tag;
