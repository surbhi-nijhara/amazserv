// BEFORE RUNNING:
// ---------------
// 1. If not already done, enable the Google Cloud Deployment Manager API
//    and check the quota for your project at
//    https://console.developers.google.com/apis/api/deploymentmanager
// 2. This sample uses Application Default Credentials for authentication.
//    If not already done, install the gcloud CLI from
//    https://cloud.google.com/sdk and run
//    `gcloud beta auth application-default login`.
//    For more information, see
//    https://developers.google.com/identity/protocols/application-default-credentials
// 3. Install the Node.js client library by running
//    `npm install googleapis --save`

var google = require('googleapis');
var deploymentManager = google.deploymentmanager('v2');
var util = require("util");
var fs=require('fs');

fsReadStream= fs.createReadStream('vm.yaml');

//var key = require('/path/to/key.json');
/* service key file json*/
 var key= {
   "type": "service_account",
  "project_id": "",
  "private_key_id": "",
  "private_key": "",
  "client_email": "",
  "client_id": "",
  "auth_uri": "https://accounts.google.com/o/oauth2/auth",
  "token_uri": "https://accounts.google.com/o/oauth2/token",
  "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
  "client_x509_cert_url": ""
}
/*does not work with app default creds because there is no email and pvt key*/
var defaultKey=  {
  "client_id": "",
  "client_secret": "",
  "refresh_token": "",
  "type": "authorized_user"
}

var jwtClient = null;

authorizeJWT(
  function(authClientJWT) {
  var request = {
    // The project ID for this request.
    project: '',  // TODO: Update placeholder value.

    resource: {
	"target": {
		"imports":[
		  {
			"name":"vm_template.py",
			"content":fs.readFileSync("vm_template.py",'utf8')
		  },
 		{
			"name":"vm_template.py.schema.json",
			"content":  fs.readFileSync("vm_template.py.schema.json",'utf8')
		  }
		],
		"config": {
		  /*"content":"resources:\n"+
		            "- name: vm_template\n"+
    			    "  type: vm_template.py\n"+
			    "  properties:\n"+
 			    "   zone: us-west1-a"
 	    	}*/
      	"content":  fs.readFileSync("vm.yaml",'utf8')}
 	},
	"name": "example-deployment-surbhi-01"//stackname
	},
    //auth: authClient,  //via  app default - this needs file path
    auth: authClientJWT, // via jwt client - here key as json content can be accepted
  };

  deploymentManager.deployments.insert(request, function(err, response) {
    if (err) {
      console.error(err);
      return;
    }

    // TODO: Change code below to process the `response` object:
    console.log(util.inspect(response,false, null));
  });
});

//via  app default - this needs file path, returns authClient
/*function authorize(callback) {
  google.auth.getApplicationDefault(function(err, authClient) {
    if (err) {
      console.error('authentication failed: ', err);
      return;
    }
    if (authClient.createScopedRequired && authClient.createScopedRequired()) {
      var scopes = ['https://www.googleapis.com/auth/cloud-platform'];
      authClient = authClient.createScoped(scopes);
    }
    callback(authClient);
  });
}*/
//can take json key-  returns authClientJWT, this does not work with default app
function authorizeJWT(callback) {
     jwtClient = new google.auth.JWT(
      key.client_email,
      null,
      key.private_key,
      ['https://www.googleapis.com/auth/cloud-platform'], // an array of auth scopes
      null
    );
    /*jwtClient.authorize(function (err, tokens) {
    if (err) {
      console.log(err);
      return;
    }*/
      callback(jwtClient);
    }
//  );

//}
