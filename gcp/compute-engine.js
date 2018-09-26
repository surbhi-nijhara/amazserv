// Used this to test: different service keys
//uses direct compute engine api
//here i changed the default cred file in home folfer . config, so basically uses default api only.
//default api is fine for local machines.
//as known, jwt to be used when running in non local/container enviorment as jwt doesnt take a file parameter

var google = require('googleapis');
var compute = google.compute('v1');

function auth(callback) {
  google.auth.getApplicationDefault(function(err, authClient) {
    if (err) {
      return callback(err);
    }

    // The createScopedRequired method returns true when running on GAE or a
    // local developer machine. In that case, the desired scopes must be passed
    // in manually. When the code is  running in GCE or GAE Flexible, the scopes
    // are pulled from the GCE metadata server.
    // See https://cloud.google.com/compute/docs/authentication for more
    // information.
    if (authClient.createScopedRequired && authClient.createScopedRequired()) {
      // Scopes can be specified either as an array or as a single,
      // space-delimited string.
      authClient = authClient.createScoped([
        'https://www.googleapis.com/auth/cloud-platform',
        'https://www.googleapis.com/auth/compute',
        'https://www.googleapis.com/auth/compute.readonly',
      ]);
    }
    callback(null, authClient);
  });
}

/**
 * @param {Function} callback Callback function.
 */
function getVmsExample(callback) {
  auth(function(err, authClient) {
    if (err) {
      return callback(err);
    }
    // Retrieve the vms
    compute.instances.aggregatedList(
      {
        auth: authClient,
        //project: process.env.GCLOUD_PROJECT, //Surbhi commented and put below proj id
        project: "",
        // In this example we only want one VM per page
        maxResults: 1,
      },
      function(err, vms) {
        if (err) {
          return callback(err);
        }

        console.log('VMs:', vms);
        callback(null, vms);
      }
    );
  });
}

/**
 * @param {Function} callback Callback function.
 */
function getZones(callback) {
  auth(function(err, authClient) {
    if (err) {
      return callback(err);
    }
    // Retrieve the vms
    compute.zones.list(
      {
        auth: authClient,
        //project: process.env.GCLOUD_PROJECT, //Surbhi commented and put below proj id
        project: "",
          maxResults: 1,

      },
      function(err, zones) {
        if (err) {
          return callback(err);
        }

        console.log('zones:', zones);
        callback(null, zones);
      }
    );
  });
}

// Run the examples
exports.main = function(cb) {
  //getVmsExample(cb);
  getZones(cb);
};

if (module === require.main) {
  exports.main(console.log);
}
