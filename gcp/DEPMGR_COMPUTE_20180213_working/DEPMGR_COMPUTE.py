COMPUTE_URL_BASE = 'https://www.googleapis.com/compute/v1/'


def GenerateConfig(context):

  datadisk = 'datadisk-'+ context.env['deployment']
  resources = [{
      'type': 'compute.v1.disk',
      'name': datadisk,
      'properties': {
          'zone': context.properties['zone'],
          'sizeGb': 10,
          # Disk type is a full URI.  Example uses pd-standard
          # but pd-ssd can be used as well
          'type': ''.join([COMPUTE_URL_BASE, 'projects/',
                           context.env['project'], '/zones/',
                           context.properties['zone'],
                           '/diskTypes/',context.properties['diskType']])
      }
  }, {
      'type': 'compute.v1.instance',
      'name': 'vm-' + context.env['deployment'],
      'properties': {
          'zone': context.properties['zone'],
          'machineType': ''.join([COMPUTE_URL_BASE, 'projects/',
                                  context.env['project'], '/zones/',
                                  context.properties['zone'],
                                  '/machineTypes/',context.properties['machineType']]),
          'metadata': {
              'items': [{
                  # For more ways to use startup scripts on an instance, see:
                  # https://cloud.google.com/compute/docs/startupscript
                  'key': 'startup-script',
                  'value': '#!/bin/bash\npython -m SimpleHTTPServer 8080'
              }]
          },
          'disks': [{
              'deviceName': 'boot',
              'type': 'PERSISTENT',
              'boot': True,
              'autoDelete': True,
              'initializeParams': {
                  'diskName': 'disk-' + context.env['deployment'],
                  'sourceImage': ''.join([COMPUTE_URL_BASE, 'projects/',
                                          'debian-cloud/global/',
                                          'images/family/debian-8'])}
          }, {
              'deviceName': 'datadisk',
              'type': 'PERSISTENT',
              'source': '$(ref.' + datadisk + '.selfLink)',
              'autoDelete': True
          }],
          'networkInterfaces': [{
              'network': ''.join([COMPUTE_URL_BASE, 'projects/',
                                  context.env['project'],
                                  '/global/networks/default']),
              'accessConfigs': [{
                  'name': 'External NAT',
                  'type': 'ONE_TO_ONE_NAT'
              }]
          }]
      }
  }]
  return {'resources': resources}
