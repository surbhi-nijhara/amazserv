'use strict';
//This works with aws-api-gateway-client


  
module.exports = {
  test: getProducts
};

const Joi = require('joi');
let internals = {};
var apigClientFactory = require('aws-api-gateway-client').default;
var AWS = require('aws-sdk');
var sts = new AWS.STS();
var chain = new AWS.CredentialProviderChain();
var credentials;

function getProducts(req, res) {
/*var param = {
	RoleArn: 'arn:aws:iam::829427928185:role/ec2-eai-role',
	RoleSessionName:'dev'
}*/

chain.resolve((err, creds)=>{
	 if (err) {
		console.log('No credentials available');
  }	
	else {

	AWS.config.credentials = credentials = creds;
	AWS.config.update({region: 'us-west-2' });
	var config = {
		accessKey:credentials.accessKeyId,
		secretKey:credentials.secretAccessKey,
		sessionToken : credentials.sessionToken,
		invokeUrl:'https://4zcunsvfci.execute-api.us-west-2.amazonaws.com',
		region:'us-west-2'
	};
	
	AWS.config.getCredentials(function(err) {
		
		if (err) 
			console.log(err.stack); // credentials not loaded
		else 
			{		
			
				var apigClient = apigClientFactory.newClient(config);
				var additionalParams = {
					headers:{
					"content-type":'application/json'
				}}
				additionalParams={};
				var pathTemplate = '/dev/restricted/api/v1/users/10/products';
				var method = 'GET';
				var body,params = {};
				apigClient.invokeApi(params, pathTemplate, method, additionalParams, body)
				.then(function(result){
					console.log("result.data ::\n", JSON.stringify(result.data, null, "  "));
					//console.log("Result.data ::\n", result.data);
					//res.write(JSON.stringify(result.data, null, "  "));
					
					res.set('Content-Type', 'application/json');
					//res.json(JSON.stringify(result.data,null,""));
					//res.end();
					res.json(result.data);
				})				
				.catch(function(exception){
				console.log(exception);
				})
			};
	})
	}
	})	   
  }



