#!/usr/bin/env python3

import argparse as ap
import subprocess as sp
import os



def backend():
	os.chdir('src/' + 'back-end')
	sp.call(['./translator.py', '../' + 'front-end' + '/code.out',
		'../../out.tam'])
	sp.call(['rm', '-rf', '../' + 'front-end' + '/code.out'])

def parse_arguments():
	parser = ap.ArgumentParser()
	parser.add_argument('INPUT_FILE', type=str, help='Name of input file')
	return parser.parse_args()

def frontend(input_file):
	os.chdir('src/' + 'front-end')
	sp.call(['make'])
	sp.call(['java', 'main.Main'], stdin=input_file)
	os.chdir('../../')



def main():
	args = parse_arguments()
	input_file = open(args.INPUT_FILE, 'r')
	frontend(input_file)
	input_file.close()
	backend()


main()