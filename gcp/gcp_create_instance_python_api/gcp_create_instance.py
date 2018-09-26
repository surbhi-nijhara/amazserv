#!/usr/bin/env python

"""Script to create a GCP VM Instance."""

import argparse
import json
import pprint
import sys

from apiclient import discovery
from googleapiclient.errors import HttpError
from oauth2client.client import GoogleCredentials

from instance_config import config


def main():
    """
    Function main.
    """
    parser = argparse.ArgumentParser(
        formatter_class=argparse.RawTextHelpFormatter
    )
    parser.add_argument('--project', '-p', help='GCP Project Name')
    parser.add_argument('--zone', '-z', help='GCP Zone')
    parser.add_argument('--credentials',
                        '-creds', help='GCP Service Account File')
    parser.add_argument('--name', '-n', help='Instance Name')
    parser.add_argument('--type', '-t', help='Instance Type')
    parser.add_argument('--config', '-c', help='Instance Configuration')

    if len(sys.argv) == 1:
        parser.print_help()
        sys.exit(1)
    else:
        args = parser.parse_args()
        create_instance(args, config)


def get_credentials(creds):
    """
    Function get_credentials
    """
    credentials = GoogleCredentials.from_stream(creds)

    return credentials


def create_instance(args, conf):
    """
    Function create_instance
    """
    # Get the credentials from the service account key.
    credentials = get_credentials(args.credentials)

    compute = discovery.build('compute', 'v1', credentials=credentials)

    # Get the latest Debian Jessie image.
    image_response = compute.images().getFromFamily(
        project='debian-cloud', family='debian-8').execute()
    source_disk_image = image_response['selfLink']

    # Update the instance name in the config dictionary.
    conf['name'] = args.name
    # Update machineType in the config dictionary.
    conf['machineType'] = "zones/{}/machineTypes/{}".format(
        args.zone, args.type
    )
    # Update the sourceImage in the config dictionary.
    for disk in conf['disks']:
        disk['initializeParams']['sourceImage'] = source_disk_image
    print('Instance Config:- \n')
    pprint.pprint(conf)

    # Try to create the instance with the specified config.
    try:
        compute.instances().insert(
            project=args.project,
            zone=args.zone,
            body=config).execute()
    # Throw an exception
    except HttpError as gcp_err:
        print('\n{}'.format(
            json.loads(gcp_err.content.decode())['error']['message']))


if __name__ == '__main__':
    main()
