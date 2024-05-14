#!/usr/bin/env python3

import os, sys
import argparse
import json
from jinja2 import Environment

def checkargs(args):
    return True

def slashescape(value):
    return value.replace('/', '_')

def get_var_from_json(args):
    machdir = os.path.join(args.rity_dir, 'build', 'tmp', 'deploy', 'images', args.machine)
    if not os.path.isdir(machdir):
        return None

    testdata = os.path.join(machdir, '%s-%s.testdata.json' % (args.image_name, args.machine))
    if not os.path.isfile(testdata):
        return None

    with open(testdata) as f:
        j = json.load(f)
        if 'KERNEL_DEVICETREE' not in j:
            return None

        return j['KERNEL_DEVICETREE'].split(' ')

    return None

if __name__ == '__main__':

    desc = 'Script for generating kernel its configuration used for building kernel fitImage outside of Yocto'
    p = argparse.ArgumentParser(description=desc)
    p.add_argument('rity_dir', help='The RITY project folder')
    p.add_argument('machine', help='Machine name')
    p.add_argument('image_name', help='The image name')
    args = p.parse_args()

    if not checkargs(args):
        sys.exit(1)

    devicetrees = get_var_from_json(args)
    if devicetrees == None:
        print('Kernel device-tree could not be found.')
        sys.exit(1)

    tpl_path = os.path.join(args.rity_dir, 'src', 'meta-mediatek-bsp', 'docs', 'kernel-fitimage.j2')
    with open(tpl_path) as f:
        source = f.read()
        env = Environment()
        env.filters['slashescape'] = slashescape
        tpl = env.from_string(source)

        # The default board dtb is the first element of device trees
        out = tpl.render(devicetrees=devicetrees, default_dtb=devicetrees[0])

    with open('%s.its' % args.machine, 'w') as f:
        f.write(out)
