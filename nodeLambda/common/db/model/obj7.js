const Sequelize = require("sequelize");
const baseDAO = require("../base/BaseDAO.js");
const sequelize = baseDAO.init();
const questionType = require("./questionType.js").questionType;
const questionCategory = require("./questionCategory.js").questionCategory;
const questionTag = require("./questionTag.js").questionTag;
const tag = require("./tag.js").tag;
const subject = require("./subject.js").subject;
const question = sequelize.define('question', {
    id: {type : Sequelize.BIGINT, primaryKey : true,  autoIncrement: true},
    question_title: Sequelize.TEXT,
    question_text : Sequelize.TEXT,
    is_answer_visible : Sequelize.BOOLEAN,
    created_by : Sequelize.BIGINT,
    modified_by : Sequelize.BIGINT,
    date_created : Sequelize.DATE,
    date_modified : Sequelize.DATE,
    is_active : Sequelize.BOOLEAN,
    question_type_id : {
        type : Sequelize.BIGINT,
        references : {
            model : questionType,
            key  : "id",
            deferrable :  Sequelize.Deferrable.INITIALLY_IMMEDIATE
        }
    },
    question_category_id : {
        type : Sequelize.BIGINT,
        references : {
            model : questionCategory,
            key  : "id",
            deferrable :  Sequelize.Deferrable.INITIALLY_IMMEDIATE
        }
    },
    subject_id : {
        type : Sequelize.BIGINT,
        references : {
            model : subject,
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

  //question.belongsTo(questionType, {foreignKey: 'question_type_id'});
  //question.belongsTo(questionCategory, {foreignKey: 'question_category_id'});
  question.belongsToMany(tag, {"through":questionTag, foreignKey: 'question_id',otherKey: 'tag_id'});
  tag.belongsToMany(question, {"through":questionTag,foreignKey: 'tag_id',otherKey: 'question_id'});
  module.exports.question = question;