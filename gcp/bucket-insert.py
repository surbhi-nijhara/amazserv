def get_bucket_property(context):
    storageClass = context.properties.get('storageClass', '')
    location = context.properties.get('location', '')
    locationMap = {
        'Europe': 'eu',
        'Europe (any region)': 'eu',
        'United States': 'us',
        'United States (any region)': 'us',
        'Asia': 'asia',
        'Asia (any region)': 'asia'
    }

    bucketObject = {
        'type': 'storage.v1.bucket',
        'name': context.properties['name'],
        'properties': {
            'storageClass': storageClass
        }
    }

    if storageClass == 'Multi-Regional':
        bucketObject['properties']['storageClass'] = 'MULTI_REGIONAL'
        bucketObject['properties']['location'] = context.properties['multiregional']
    else:
        bucketObject['properties']['location'] = context.properties[storageClass.lower()]

    location = bucketObject['properties']['location']
    bucketObject['properties']['location'] = locationMap.get(location, location)

    if bool(context.properties.get('addLabel', False)):
        bucketObject['properties']['labels'] = {
            context.properties['labelKey']: context.properties.get('labelValue', '')
        }

    return bucketObject


def GenerateConfig(context):
    resources = []

    # Create Storage Bucket
    bucket_object = get_bucket_property(context)
    resources.append(bucket_object)

    return {'resources': resources}
