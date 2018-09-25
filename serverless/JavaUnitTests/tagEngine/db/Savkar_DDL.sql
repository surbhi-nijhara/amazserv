-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2018-07-31 15:12:50.808

-- tables
-- Table: Account
CREATE TABLE Account (
    id bigserial  NOT NULL,
    name varchar(50)  NOT NULL,
    CONSTRAINT Account_pk PRIMARY KEY (id)
);

-- Table: Account_User
CREATE TABLE Account_User (
    id int  NOT NULL,
    Account_account_id bigint  NOT NULL,
    User_user_id bigint  NOT NULL,
    CONSTRAINT Account_User_pk PRIMARY KEY (id)
);

-- Table: Answer
CREATE TABLE Answer (
    id bigserial  NOT NULL,
    questions_Id bigint  NOT NULL,
    answer_formula text  NOT NULL,
    is_correct boolean  NOT NULL,
    CONSTRAINT Answer_pk PRIMARY KEY (id)
);

-- Table: Answer_Tag
CREATE TABLE Answer_Tag (
    id bigserial  NOT NULL,
    answer_id bigint  NOT NULL,
    tag_id bigint  NOT NULL,
    CONSTRAINT Answer_Tag_pk PRIMARY KEY (id)
);

-- Table: Assesment_Tag
CREATE TABLE Assesment_Tag (
    id bigserial  NOT NULL,
    assesment_id bigint  NOT NULL,
    tag_id bigint  NOT NULL,
    CONSTRAINT Assesment_Tag_pk PRIMARY KEY (id)
);

-- Table: Assesment_Type
CREATE TABLE Assesment_Type (
    id bigserial  NOT NULL,
    name varchar(50)  NOT NULL,
    CONSTRAINT Assesment_Type_pk PRIMARY KEY (id)
);

-- Table: Assessment
CREATE TABLE Assessment (
    id bigserial  NOT NULL,
    assessment_name varchar(50)  NOT NULL,
    assessment_description text  NULL,
    assessment_type_id bigint  NOT NULL,
    version varchar(10)  NOT NULL,
    created_by int  NOT NULL,
    modified_by int  NOT NULL,
    date_created timestamp  NOT NULL DEFAULT now(),
    date_modified timestamp  NOT NULL DEFAULT now(),
    is_active boolean  NOT NULL,
    CONSTRAINT Assessment_pk PRIMARY KEY (id)
);

-- Table: Assessment_Question
CREATE TABLE Assessment_Question (
    id bigserial  NOT NULL,
    assessment_id bigint  NOT NULL,
    questions_id bigint  NOT NULL,
    CONSTRAINT Assessment_Question_pk PRIMARY KEY (id)
);

-- Table: Cohort
CREATE TABLE Cohort (
    id bigserial  NOT NULL,
    name varchar(50)  NOT NULL,
    start_date date  NOT NULL,
    end_date date  NOT NULL,
    acount_id int  NOT NULL,
    semester char(7)  NOT NULL,
    CONSTRAINT Cohort_pk PRIMARY KEY (id)
);

-- Table: Cohort_Enrollment
CREATE TABLE Cohort_Enrollment (
    id bigserial  NOT NULL,
    Cohort_id bigint  NOT NULL,
    User_id bigint  NOT NULL,
    enrollment_date date  NOT NULL,
    CONSTRAINT Cohort_Enrollment_pk PRIMARY KEY (id)
);

-- Table: College_Type
CREATE TABLE College_Type (
    id bigserial  NOT NULL,
    name varchar(30)  NOT NULL,
    CONSTRAINT College_Type_pk PRIMARY KEY (id)
);

-- Table: Contact_Point
CREATE TABLE Contact_Point (
    id bigserial  NOT NULL,
    Account_id bigint  NOT NULL,
    user_id bigint  NOT NULL,
    contact_point_type_id bigint  NOT NULL,
    CONSTRAINT Contact_Point_pk PRIMARY KEY (id)
);

-- Table: Contact_Point_Type
CREATE TABLE Contact_Point_Type (
    id bigserial  NOT NULL,
    name varchar(50)  NOT NULL,
    CONSTRAINT Contact_Point_Type_pk PRIMARY KEY (id)
);

-- Table: Discipline
CREATE TABLE Discipline (
    id bigserial  NOT NULL,
    name varchar(30)  NOT NULL,
    CONSTRAINT Discipline_ak_1 UNIQUE (name) NOT DEFERRABLE  INITIALLY IMMEDIATE,
    CONSTRAINT Discipline_pk PRIMARY KEY (id)
);

-- Table: Email
CREATE TABLE Email (
    Contact_Point_id bigint  NOT NULL,
    email varchar(100)  NOT NULL,
    CONSTRAINT Email_pk PRIMARY KEY (Contact_Point_id)
);

-- Table: Learning_Module
CREATE TABLE Learning_Module (
    id bigserial  NOT NULL,
    name varchar(50)  NOT NULL,
    created_by int  NOT NULL,
    modified_by int  NOT NULL,
    date_created timestamp  NOT NULL DEFAULT now(),
    date_modified timestamp  NOT NULL DEFAULT now(),
    is_active boolean  NOT NULL DEFAULT true,
    CONSTRAINT Learning_Module_pk PRIMARY KEY (id)
);

-- Table: Learning_Module_Component
CREATE TABLE Learning_Module_Component (
    id bigserial  NOT NULL,
    display_order int  NOT NULL,
    learning_module_id bigint  NOT NULL,
    Video_id bigint  NOT NULL,
    Assessment_id bigint  NOT NULL,
    CONSTRAINT Learning_Module_Component_pk PRIMARY KEY (id)
);

-- Table: Learning_Module_Tag
CREATE TABLE Learning_Module_Tag (
    id bigserial  NOT NULL,
    learning_module_id bigint  NOT NULL,
    Tag_id bigint  NOT NULL,
    CONSTRAINT Learning_Module_Tag_pk PRIMARY KEY (id)
);

-- Table: Mailing_Address
CREATE TABLE Mailing_Address (
    Contact_Point_id bigint  NOT NULL,
    street varchar(50)  NOT NULL,
    city varchar(50)  NOT NULL,
    state varchar(50)  NOT NULL,
    zipcode varchar(20)  NOT NULL,
    country varchar(50)  NOT NULL,
    CONSTRAINT Mailing_Address_pk PRIMARY KEY (Contact_Point_id)
);

-- Table: Phone
CREATE TABLE Phone (
    Contact_Point_id bigint  NOT NULL,
    phone_no varchar(50)  NOT NULL,
    CONSTRAINT Phone_pk PRIMARY KEY (Contact_Point_id)
);

-- Table: Placement_Course
CREATE TABLE Placement_Course (
    id bigserial  NOT NULL,
    test_series_id bigint  NOT NULL,
    course_name varchar(50)  NOT NULL,
    cooling_period varchar(25)  NOT NULL,
    retake_number smallint  NOT NULL,
    pretest_content_review boolean  NOT NULL,
    CONSTRAINT Placement_Course_pk PRIMARY KEY (id)
);

-- Table: Placement_Course_Enrollment
CREATE TABLE Placement_Course_Enrollment (
    id bigserial  NOT NULL,
    Cohort_Enrollment_id bigint  NOT NULL,
    Placement_Course_id bigint  NOT NULL,
    CONSTRAINT Placement_Course_Enrollment_pk PRIMARY KEY (id)
);

-- Table: Placement_Learning_Module
CREATE TABLE Placement_Learning_Module (
    id bigserial  NOT NULL,
    course_id bigint  NOT NULL,
    learning_module_id bigint  NOT NULL,
    display_order bigint  NOT NULL,
    CONSTRAINT Placement_Learning_Module_pk PRIMARY KEY (id)
);

-- Table: Privilege
CREATE TABLE Privilege (
    id bigserial  NOT NULL,
    name varchar(50)  NULL,
    CONSTRAINT Privilege_pk PRIMARY KEY (id)
);

-- Table: Question
CREATE TABLE Question (
    id bigserial  NOT NULL,
    question_title text  NULL,
    question_text text  NOT NULL,
    question_type_id bigint  NOT NULL,
    question_category_id bigint  NOT NULL,
    is_answer_visible boolean  NOT NULL,
    created_by int  NOT NULL,
    modified_by int  NOT NULL,
    date_created timestamp  NOT NULL DEFAULT now(),
    date_modified timestamp  NOT NULL DEFAULT now(),
    is_active boolean  NOT NULL DEFAULT true,
	subject_id bigint,
    CONSTRAINT Question_pk PRIMARY KEY (id)
);

-- Table: Question_Category
CREATE TABLE Question_Category (
    id bigserial  NOT NULL,
    name varchar(50)  NULL,
    CONSTRAINT Question_Category_pk PRIMARY KEY (id)
);

-- Table: Question_Tag
CREATE TABLE Question_Tag (
    id bigserial  NOT NULL,
    question_Id bigint  NOT NULL,
    tag_id bigint  NOT NULL,
    CONSTRAINT Question_Tag_pk PRIMARY KEY (id)
);

-- Table: Question_Type
CREATE TABLE Question_Type (
    id bigserial  NOT NULL,
    name varchar(50)  NULL,
    CONSTRAINT Question_Type_pk PRIMARY KEY (id)
);

-- Table: Role
CREATE TABLE Role (
    id bigserial  NOT NULL,
    name varchar(50)  NULL,
    CONSTRAINT Role_pk PRIMARY KEY (id)
);

-- Table: Role_Privilege
CREATE TABLE Role_Privilege (
    id bigserial  NOT NULL,
    privilege_id bigint  NOT NULL,
    role_id bigint  NOT NULL,
    CONSTRAINT Role_Privilege_pk PRIMARY KEY (id)
);

-- Table: Subject
CREATE TABLE Subject (
    id bigserial  NOT NULL,
    name varchar(50)  NOT NULL,
    discipline_id bigint  NOT NULL,
    college_type_id bigint  NOT NULL,
    CONSTRAINT Subject_pk PRIMARY KEY (id)
);

-- Table: Subject_Prerequisite
CREATE TABLE Subject_Prerequisite (
    id bigserial  NOT NULL,
    subject_id bigint  NOT NULL,
    prerequisite_subject_id bigint  NOT NULL,
    CONSTRAINT Subject_Prerequisite_ak_1 UNIQUE (subject_id, prerequisite_subject_id) NOT DEFERRABLE  INITIALLY IMMEDIATE,
    CONSTRAINT Subject_Prerequisite_pk PRIMARY KEY (id)
);

-- Table: Tag
CREATE TABLE Tag (
    id bigserial  NOT NULL,
    name varchar(100)  NOT NULL,
    subject_id bigint  NOT NULL,
    parent_tag_id bigint  NULL,
    created_by int  NOT NULL,
    modified_by int  NOT NULL,
    date_created timestamp  NOT NULL DEFAULT now(),
    date_modified timestamp  NOT NULL DEFAULT now(),
    is_active boolean  NOT NULL DEFAULT true,
    CONSTRAINT Tag_pk PRIMARY KEY (id)
);

-- get the sequence name - select pg_get_serial_sequence('tableName','columnName');
--ALTER SEQUENCE public.tag_id_seq RESTART with 5; 

-- Table: Test_Series
CREATE TABLE Test_Series (
    Id bigserial  NOT NULL,
    test_series_name varchar(50)  NOT NULL,
    CONSTRAINT Test_Series_pk PRIMARY KEY (Id)
);

-- Table: Test_Series_Assessment
CREATE TABLE Test_Series_Assessment (
    Id bigserial  NOT NULL,
    Assessment_Id bigint  NOT NULL,
    Test_Series_Id bigint  NOT NULL,
    CONSTRAINT Test_Series_Assessment_pk PRIMARY KEY (Id)
);

-- Table: User
CREATE TABLE "User" (
    id bigserial  NOT NULL,
    login_id varchar(50)  NOT NULL,
    prefix varchar(50)  NULL,
    first_name varchar(50)  NOT NULL,
    middle_name varchar(50)  NOT NULL,
    last_name varchar(50)  NOT NULL,
    suffix varchar(50)  NULL,
    short_description varchar(150)  NULL,
    long_description varchar(300)  NULL,
    isDeleted boolean  NOT NULL,
    CONSTRAINT User_pk PRIMARY KEY (id)
);

-- Table: User_Group
CREATE TABLE User_Group (
    id bigserial  NOT NULL,
    name varchar(50)  NOT NULL,
    isDeleted boolean  NULL,
    CONSTRAINT User_Group_pk PRIMARY KEY (id)
);

-- Table: User_Group_Role
CREATE TABLE User_Group_Role (
    id bigserial  NOT NULL,
    user_group_id bigint  NOT NULL,
    role_id bigint  NOT NULL,
    CONSTRAINT User_Group_Role_pk PRIMARY KEY (id)
);

-- Table: User_Group_User
CREATE TABLE User_Group_User (
    id bigserial  NOT NULL,
    user_id bigint  NOT NULL,
    user_group_id bigint  NOT NULL,
    CONSTRAINT User_Group_User_pk PRIMARY KEY (id)
);

-- Table: Video
CREATE TABLE Video (
    id bigserial  NOT NULL,
    external_Id int  NULL,
    file_name varchar(50)  NOT NULL,
    file_location varchar(250)  NOT NULL,
    video_thumbnail varchar(250)  NULL,
    duration int  NOT NULL,
    author_id bigint  NULL,
    uploaded_user_id bigint  NOT NULL,
    deleted_user_id bigint  NULL,
    creation_date timestamp  NOT NULL,
    upload_date timestamp  NOT NULL,
    deletion_date timestamp  NULL,
    CONSTRAINT Video_pk PRIMARY KEY (id)
);

-- Table: Video_Question
CREATE TABLE Video_Question (
    id bigserial  NOT NULL,
    questions_id bigint  NOT NULL,
    videos_id bigint  NOT NULL,
    popup_time int  NOT NULL,
    CONSTRAINT Video_Question_pk PRIMARY KEY (id)
);

-- Table: Video_Tag
CREATE TABLE Video_Tag (
    id bigserial  NOT NULL,
    video_Id bigint  NOT NULL,
    start_time bigint  NOT NULL,
    end_time bigint  NOT NULL,
    Tag_id bigint  NOT NULL,
    CONSTRAINT Video_Tag_pk PRIMARY KEY (id)
);

-- Table: Website
CREATE TABLE Website (
    contact_point_id bigint  NOT NULL,
    website varchar(50)  NOT NULL,
    CONSTRAINT Website_pk PRIMARY KEY (contact_point_id)
);

-- foreign keys
-- Reference: Account_User_Account (table: Account_User)
ALTER TABLE Account_User ADD CONSTRAINT Account_User_Account
    FOREIGN KEY (Account_account_id)
    REFERENCES Account (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Account_User_User (table: Account_User)
ALTER TABLE Account_User ADD CONSTRAINT Account_User_User
    FOREIGN KEY (User_user_id)
    REFERENCES "User" (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Answer_Tag_Answer (table: Answer_Tag)
ALTER TABLE Answer_Tag ADD CONSTRAINT Answer_Tag_Answer
    FOREIGN KEY (answer_id)
    REFERENCES Answer (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Answer_Tag_Tag (table: Answer_Tag)
ALTER TABLE Answer_Tag ADD CONSTRAINT Answer_Tag_Tag
    FOREIGN KEY (tag_id)
    REFERENCES Tag (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Answers_Questions (table: Answer)
ALTER TABLE Answer ADD CONSTRAINT Answers_Questions
    FOREIGN KEY (questions_Id)
    REFERENCES Question (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Assesment_Assesment_Type (table: Assessment)
ALTER TABLE Assessment ADD CONSTRAINT Assesment_Assesment_Type
    FOREIGN KEY (assessment_type_id)
    REFERENCES Assesment_Type (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Assesment_Tag_Assesment (table: Assesment_Tag)
ALTER TABLE Assesment_Tag ADD CONSTRAINT Assesment_Tag_Assesment
    FOREIGN KEY (assesment_id)
    REFERENCES Assessment (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Assesment_Tag_Tag (table: Assesment_Tag)
ALTER TABLE Assesment_Tag ADD CONSTRAINT Assesment_Tag_Tag
    FOREIGN KEY (tag_id)
    REFERENCES Tag (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Assessment_Questions_Assesment (table: Assessment_Question)
ALTER TABLE Assessment_Question ADD CONSTRAINT Assessment_Questions_Assesment
    FOREIGN KEY (assessment_id)
    REFERENCES Assessment (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Assessment_Questions_Questions (table: Assessment_Question)
ALTER TABLE Assessment_Question ADD CONSTRAINT Assessment_Questions_Questions
    FOREIGN KEY (questions_id)
    REFERENCES Question (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Cohort_Enrolment_Cohort (table: Cohort_Enrollment)
ALTER TABLE Cohort_Enrollment ADD CONSTRAINT Cohort_Enrolment_Cohort
    FOREIGN KEY (Cohort_id)
    REFERENCES Cohort (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Cohort_Enrolment_User (table: Cohort_Enrollment)
ALTER TABLE Cohort_Enrollment ADD CONSTRAINT Cohort_Enrolment_User
    FOREIGN KEY (User_id)
    REFERENCES "User" (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Contact_Point_Account (table: Contact_Point)
ALTER TABLE Contact_Point ADD CONSTRAINT Contact_Point_Account
    FOREIGN KEY (Account_id)
    REFERENCES Account (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Contact_Point_Contact_Point_Type (table: Contact_Point)
ALTER TABLE Contact_Point ADD CONSTRAINT Contact_Point_Contact_Point_Type
    FOREIGN KEY (contact_point_type_id)
    REFERENCES Contact_Point_Type (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Contact_Point_User (table: Contact_Point)
ALTER TABLE Contact_Point ADD CONSTRAINT Contact_Point_User
    FOREIGN KEY (user_id)
    REFERENCES "User" (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Email_Contact_Point (table: Email)
ALTER TABLE Email ADD CONSTRAINT Email_Contact_Point
    FOREIGN KEY (Contact_Point_id)
    REFERENCES Contact_Point (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Learning_Module_Component_Assessment (table: Learning_Module_Component)
ALTER TABLE Learning_Module_Component ADD CONSTRAINT Learning_Module_Component_Assessment
    FOREIGN KEY (Assessment_id)
    REFERENCES Assessment (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Learning_Module_Component_Learning_Module (table: Learning_Module_Component)
ALTER TABLE Learning_Module_Component ADD CONSTRAINT Learning_Module_Component_Learning_Module
    FOREIGN KEY (learning_module_id)
    REFERENCES Learning_Module (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Learning_Module_Component_Video (table: Learning_Module_Component)
ALTER TABLE Learning_Module_Component ADD CONSTRAINT Learning_Module_Component_Video
    FOREIGN KEY (Video_id)
    REFERENCES Video (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Learning_Module_Tag_Learning_Module (table: Learning_Module_Tag)
ALTER TABLE Learning_Module_Tag ADD CONSTRAINT Learning_Module_Tag_Learning_Module
    FOREIGN KEY (learning_module_id)
    REFERENCES Learning_Module (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Learning_Module_Tag_Tag (table: Learning_Module_Tag)
ALTER TABLE Learning_Module_Tag ADD CONSTRAINT Learning_Module_Tag_Tag
    FOREIGN KEY (Tag_id)
    REFERENCES Tag (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Mailing_Address_Contact_Point (table: Mailing_Address)
ALTER TABLE Mailing_Address ADD CONSTRAINT Mailing_Address_Contact_Point
    FOREIGN KEY (Contact_Point_id)
    REFERENCES Contact_Point (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Parent_child_tag (table: Tag)
ALTER TABLE Tag ADD CONSTRAINT Parent_child_tag
    FOREIGN KEY (parent_tag_id)
    REFERENCES Tag (id)  
    DEFERRABLE 
    INITIALLY DEFERRED
;

-- Reference: Phone_Contact_Point (table: Phone)
ALTER TABLE Phone ADD CONSTRAINT Phone_Contact_Point
    FOREIGN KEY (Contact_Point_id)
    REFERENCES Contact_Point (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Placement_Course_Enrollment_Cohort_Enrollment (table: Placement_Course_Enrollment)
ALTER TABLE Placement_Course_Enrollment ADD CONSTRAINT Placement_Course_Enrollment_Cohort_Enrollment
    FOREIGN KEY (Cohort_Enrollment_id)
    REFERENCES Cohort_Enrollment (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Placement_Course_Enrollment_Placement_Course (table: Placement_Course_Enrollment)
ALTER TABLE Placement_Course_Enrollment ADD CONSTRAINT Placement_Course_Enrollment_Placement_Course
    FOREIGN KEY (Placement_Course_id)
    REFERENCES Placement_Course (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Placement_Learning_Module_Learning_Module (table: Placement_Learning_Module)
ALTER TABLE Placement_Learning_Module ADD CONSTRAINT Placement_Learning_Module_Learning_Module
    FOREIGN KEY (learning_module_id)
    REFERENCES Learning_Module (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Placement_Learning_Modules_Course (table: Placement_Learning_Module)
ALTER TABLE Placement_Learning_Module ADD CONSTRAINT Placement_Learning_Modules_Course
    FOREIGN KEY (course_id)
    REFERENCES Placement_Course (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Placement_Test_Series (table: Placement_Course)
ALTER TABLE Placement_Course ADD CONSTRAINT Placement_Test_Series
    FOREIGN KEY (test_series_id)
    REFERENCES Test_Series (Id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Question_Question_Category (table: Question)
ALTER TABLE Question ADD CONSTRAINT Question_Question_Category
    FOREIGN KEY (question_category_id)
    REFERENCES Question_Category (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Question_Question_Type (table: Question)
ALTER TABLE Question ADD CONSTRAINT Question_Question_Type
    FOREIGN KEY (question_type_id)
    REFERENCES Question_Type (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

ALTER TABLE Question ADD CONSTRAINT Question_Subject_ID
    FOREIGN KEY (subject_id)
    REFERENCES Subject (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Question_Tag_Question (table: Question_Tag)
ALTER TABLE Question_Tag ADD CONSTRAINT Question_Tag_Question
    FOREIGN KEY (question_Id)
    REFERENCES Question (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Question_Tag_Tag (table: Question_Tag)
ALTER TABLE Question_Tag ADD CONSTRAINT Question_Tag_Tag
    FOREIGN KEY (tag_id)
    REFERENCES Tag (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Role_Privilege_Privilege (table: Role_Privilege)
ALTER TABLE Role_Privilege ADD CONSTRAINT Role_Privilege_Privilege
    FOREIGN KEY (privilege_id)
    REFERENCES Privilege (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Role_Privilege_Role (table: Role_Privilege)
ALTER TABLE Role_Privilege ADD CONSTRAINT Role_Privilege_Role
    FOREIGN KEY (role_id)
    REFERENCES Role (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Subject_College_Type (table: Subject)
ALTER TABLE Subject ADD CONSTRAINT Subject_College_Type
    FOREIGN KEY (college_type_id)
    REFERENCES College_Type (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Subject_Discipline (table: Subject)
ALTER TABLE Subject ADD CONSTRAINT Subject_Discipline
    FOREIGN KEY (discipline_id)
    REFERENCES Discipline (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Subject_Prerequisite_Child (table: Subject_Prerequisite)
ALTER TABLE Subject_Prerequisite ADD CONSTRAINT Subject_Prerequisite_Child
    FOREIGN KEY (prerequisite_subject_id)
    REFERENCES Subject (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Subject_Prerequisite_Parent (table: Subject_Prerequisite)
ALTER TABLE Subject_Prerequisite ADD CONSTRAINT Subject_Prerequisite_Parent
    FOREIGN KEY (subject_id)
    REFERENCES Subject (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Tag_Subject (table: Tag)
ALTER TABLE Tag ADD CONSTRAINT Tag_Subject
    FOREIGN KEY (subject_id)
    REFERENCES Subject (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Test_Series_Assessment_Assesment (table: Test_Series_Assessment)
ALTER TABLE Test_Series_Assessment ADD CONSTRAINT Test_Series_Assessment_Assesment
    FOREIGN KEY (Assessment_Id)
    REFERENCES Assessment (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Test_Series_Assessment_Test_Series (table: Test_Series_Assessment)
ALTER TABLE Test_Series_Assessment ADD CONSTRAINT Test_Series_Assessment_Test_Series
    FOREIGN KEY (Test_Series_Id)
    REFERENCES Test_Series (Id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: User_Group_Role_Role (table: User_Group_Role)
ALTER TABLE User_Group_Role ADD CONSTRAINT User_Group_Role_Role
    FOREIGN KEY (role_id)
    REFERENCES Role (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: User_Group_Role_User_Group (table: User_Group_Role)
ALTER TABLE User_Group_Role ADD CONSTRAINT User_Group_Role_User_Group
    FOREIGN KEY (user_group_id)
    REFERENCES User_Group (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: User_Group_User_User (table: User_Group_User)
ALTER TABLE User_Group_User ADD CONSTRAINT User_Group_User_User
    FOREIGN KEY (user_id)
    REFERENCES "User" (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: User_Group_User_User_Group (table: User_Group_User)
ALTER TABLE User_Group_User ADD CONSTRAINT User_Group_User_User_Group
    FOREIGN KEY (user_group_id)
    REFERENCES User_Group (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Video_Questions_Questions (table: Video_Question)
ALTER TABLE Video_Question ADD CONSTRAINT Video_Questions_Questions
    FOREIGN KEY (questions_id)
    REFERENCES Question (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Video_Questions_Videos (table: Video_Question)
ALTER TABLE Video_Question ADD CONSTRAINT Video_Questions_Videos
    FOREIGN KEY (videos_id)
    REFERENCES Video (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Video_Tag_Tag (table: Video_Tag)
ALTER TABLE Video_Tag ADD CONSTRAINT Video_Tag_Tag
    FOREIGN KEY (Tag_id)
    REFERENCES Tag (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Video_Tag_Video (table: Video_Tag)
ALTER TABLE Video_Tag ADD CONSTRAINT Video_Tag_Video
    FOREIGN KEY (video_Id)
    REFERENCES Video (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: Website_Contact_Point (table: Website)
ALTER TABLE Website ADD CONSTRAINT Website_Contact_Point
    FOREIGN KEY (contact_point_id)
    REFERENCES Contact_Point (id)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- End of file.

