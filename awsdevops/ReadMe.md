ReadMe

CodePipeline
pocing
View progress and manage your pipeline.

Edit Release change
Source
Source
GitHub


Build
CodeBuild
AWS CodeBuild
  - In CodeBuild (here the project in Codebuild was created via Codepipeline hence source is Codepipeline. Start Build
    works only from code pipeline in this case. If we want to run from CodeBuild directly, then throws an error
    for Source so change to Github. See in test phase below. )

Project name my-build
Description None
Source provider AWS CodePipeline
Repository
Artifacts upload location


test
postmanTest
AWS CodeBuild
  - In CodeBuild  (Better create CodeBuild project first with source as Github and then link in CodePipline.
                    If project is created from Codepipline then source become Codepipeline, and then editing or updating Codebuild Project
                    and testing throws error in CodeBuild interface)
        Project namepostman-test
        Description None
        Source provider GitHub
        Repository
        <repo name>
        Git clone depthFull
        Artifacts upload location <S3 BucketName>
        Project details
        Timeout60 minutes
        Encryption key arn:aws:kms:us-east-1:xxxxxxxxx:alias/aws/s3
        PackagingNone
        Build specification
        View build specification
        Report build statustrue
        Build environment
        Imageaws/codebuild/nodejs:8.11.0
        Environment typeLinux
        Compute3 GB memory, 2 vCPU
        Privilegedfalse
        Cache
        TypeNo cache
        Logs
        CloudWatch Logs statusEnabled
        S3 logs statusDisabled
        Webhook
        Webhookfalse
        Service role
        Service role namea rn:aws:iam::xxxxxxxxxxx:role/service-role/code-build-postman-test-service-role
        Advanced
        Build Badge Disabled


Errors seen:
1. the artifacts path relative to github repo.So for postman collection and the output build jar,
2. in test phase , to uplaod teh report, mention it in build yaml under artifacts keyword

Refs:
https://unmesh.me/2017/06/10/api-testing-with-postman-collections-in-aws-codepipeline/
Note that I did not checkin build.yml in repo but prepared the config file on CodeBuild interface only..codeBuildTestPhase


Newman Commands:
npm install -g newman
npm install -g newman-reporter-html
newman run -r html,cli <filename> --reporter-html-export report.html
