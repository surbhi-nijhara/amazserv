-Demonstrates an existing springboot app into lambda
-maven profiles:
  springbootapp - includes test
  lambda - shaded jar, skips tests
  

# Table of Contents
1. [Directory Structure](#directory-structure)
2. [Deployment](#deployment)



#### Directory Structure
```
postgres-demo
    └───src (Source code)
    └───swagger.js (with api-gateway extension)
    └───template.js ()
    └───postman_collection(not here )
    └───README.md (Savkar README)
```


#### Deployment

**Prerequisites:**

For command line usage:

**aws cli** should be installed and configured.

**sam cli** should be installed.

**Local:**
`sam local invoke <lambda fn name> -e payload.json -t <template>`
> `e.g.:sam local invoke LambdaSpringBootFunction -e payload.json -t sam.yaml`
  
API GW: `sam local start-api`  
Start in python virtual of 3.6 if any error of the script 

Source code can be be built, packaged and deployed using different options like  **AWS console**, **Eclipse toolkit**  or  **AWS SAM**.
Below is description using **AWS SAM** via command line.



**To build the code using sam, type command:**

> ** sam package  --template-file  ** template.yaml **--output-template-file** <file name with .yaml extension> **--s3-bucket** <existing bucket name>

e.g. 
> `sam package --template-file template.yaml --output-template-file tag-engine-output.yaml    --s3-bucket savkar-ai-dev`

**To deploy the code on AWS, type command:**

> **sam deploy --template-file** <same file name with .yaml extension> **--stack-name** <stack name> ** --capabilities CAPABILITY_IAM**`

e.g. 
> `sam deploy --template-file tag-engine-output.yaml  --stack-name tagging-tool --capabilities CAPABILITY_IAM`

Note: The function name in swagger-apigateway should match with the one mentioned in template.js



