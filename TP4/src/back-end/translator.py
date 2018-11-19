#!/usr/bin/env python3

import argparse as ap
from quadruple import Quadruple
from math import floor

input_file = None
output_file = None
TSIZES = {'int': 2, 'float': 4, 'char': 1, 'bool': 1}
MAX_SIZE = TSIZES['float']
ST = 0
CT = 0
addresses = {}
types = {}
labels = {}
INSTR = '{}\t{}\t{}\t{}\t; {}\n'
INSTR_BUFFER = []

def parse_arguments():
	parser = ap.ArgumentParser()
	parser.add_argument('INPUT_FILE', type=str, help='Name of input file')
	parser.add_argument('OUTPUT_FILE', type=str, help='Name of output file')
	return parser.parse_args()


def add_instr(instr, quad):
	global CT
	INSTR_BUFFER.append((instr, quad))
	CT += 1

def str2bool(string):
	return string.lower() == 'true'



def read_decls():
	global ST, CT
	while True:
		line = input_file.readline()
		print(line.strip('\n'))

		if len(line) <= 2:
			break
		else:
			line = line.replace('[', '')
			line = line.replace(']', '')
			args = line.split()

			if len(args) < 3:
				if args[1] not in addresses:
					size = TSIZES[args[0]]
					addresses[args[1]] = ST
					ST += size
					types[args[1]] = args[0]
			else:
				if args[2] not in addresses:
					size = TSIZES[args[1]] * int(args[0])
					addresses[args[2]] = ST
					ST += size
					types[args[2]] = args[1]

		add_instr(INSTR.format(10, 0, 0, size, 'PUSH ' + str(size)), None)

def backpatching():

	for i in range(len(INSTR_BUFFER)):
		instruction = INSTR_BUFFER[i][0]
		quadruple = INSTR_BUFFER[i][1]
		if '{}' in instruction:
			branch_label = quadruple.branch
			branch_quadruple = labels[branch_label]
			if branch_quadruple == None:
				branch_address = CT
			else:
				branch_address = branch_quadruple.address
			INSTR_BUFFER[i] = \
				(instruction.format(branch_address, branch_address), quadruple)


def finish():
	input_file.close()
	for (instr, quad) in INSTR_BUFFER:
		output_file.write(instr)
	output_file.close()

def build_quadruples():
	global CT, ST
	quads = []
	for line in input_file:
		print(line.strip('\n'))
		newQuad = None
		line_args = line.split()

		L = []
		if ':' in line_args[0]:
			L = [int(x[1:]) for x in line_args[0].split(':') if x != '']
			del line_args[0]

		if len(line_args) != 0:
			if 'if' in line_args[0]:
				op = line_args[0]

				if len(line_args) == 6:
					cond = line_args[1:4]
				else:
					cond = line_args[1:2]

				branch = int(line_args[-1][1:])
				newQuad = Quadruple(None, cond, None, op, branch)
			elif 'goto' == line_args[0]:
				branch = int(line_args[1][1:])
				newQuad = Quadruple(None, None, None, line_args[0], branch)
			else:
				dst = line_args[0]
				if dst not in addresses:
					addresses[dst] = ST
					ST += MAX_SIZE
					types[dst] = 'float'

					add_instr(INSTR.format(10, 0, 0, MAX_SIZE,
						'PUSH ' + str(MAX_SIZE)), None)
				if line_args[1] == '[':
					op = '[]='
					op1 = line_args[2]
					op2 = line_args[5]
					newQuad = Quadruple(dst, op1, op2, op)
				else:
					if len(line_args) == 3:
						op1 = line_args[2]
						newQuad = Quadruple(dst, op1, None, None)
					elif len(line_args) == 5:
						op = line_args[3]
						op1 = line_args[2]
						op2 = line_args[4]
						newQuad = Quadruple(dst, op1, op2, op)
					elif len(line_args) == 6:
						op = '=[]'
						op1 = line_args[2]
						op2 = line_args[4]
						newQuad = Quadruple(dst, op1, op2, op)
					else:
						op = line_args[2]
						op2 = line_args[3]
						newQuad = Quadruple(dst, None, op2, op)
		if newQuad:
			quads.append(newQuad)
		for label in L:
			labels[label] = newQuad
	return quads

def main():
	global input_file, output_file
	args = parse_arguments()
	input_file = open(args.INPUT_FILE, 'r')
	output_file = open(args.OUTPUT_FILE, 'w')
	read_decls()
	quads = build_quadruples()
	translate(quads)
	backpatching()
	finish()

def translate(quads):
	for quad in quads:
		quad.address = CT
		quad_type = quad.type

		if quad_type == 1:
			cond = quad.op1
			if len(cond) == 3:
				if cond[0] in addresses:
					addr_op1 = addresses[cond[0]]
					op1_size = TSIZES[types[cond[0]]]
					add_instr(INSTR.format(1, 4, 0, addr_op1,
						'LOADA ' + str(addr_op1) + '[SB]'), quad)
					add_instr(INSTR.format(2, 0, op1_size, 0,
						'LOADI(' + str(op1_size) + ')'), quad)
				else:
					if cond[0] == 'true' or cond[0] == 'false':
						literal = int(str2bool(cond[0]))
					else:
						literal = int(floor(float(cond[0])))
					add_instr(INSTR.format(3, 0, 0, literal,
						'LOADL ' + str(literal)), quad)
				if cond[2] in addresses:
					addr_op1 = addresses[cond[2]]
					op1_size = TSIZES[types[cond[2]]]
					add_instr(INSTR.format(1, 4, 0, addr_op1,
						'LOADA ' + str(addr_op1) + '[SB]'), quad)
					add_instr(INSTR.format(2, 0, op1_size, 0,
						'LOADI(' + str(op1_size) + ')'), quad)
				else:
					if cond[2] == 'true' or cond[2] == 'false':
						literal = int(str2bool(cond[2]))
					else:
						literal = int(floor(float(cond[2])))
					add_instr(INSTR.format(3, 0, 0, literal,
						'LOADL ' + str(literal)), quad)
				relop = cond[1]
				if relop == '<':
					mnemo = 'lt'
					d = 13
					add_instr(INSTR.format(6, 2, 0, d, mnemo), quad)
				elif relop == '<=':
					mnemo = 'le'
					d = 14
					add_instr(INSTR.format(6, 2, 0, d, mnemo), quad)
				elif relop == '>=':
					mnemo = 'ge'
					d = 15
					add_instr(INSTR.format(6, 2, 0, d, mnemo), quad)
				elif relop == '>':
					mnemo = 'gt'
					d = 16
					add_instr(INSTR.format(6, 2, 0, d, mnemo), quad)
				else:
					op_size = TSIZES[types[cond[0]]]
					add_instr(INSTR.format(3, 0, 0, op_size,
						'LOADL ' + str(op_size)), quad)
					if relop == '==':
						mnemo = 'eq'
						d = 17
						add_instr(INSTR.format(6, 2, 0, d, mnemo), quad)
					else:
						mnemo = 'ne'
						d = 18
						add_instr(INSTR.format(6, 2, 0, d, mnemo), quad)
			else:
				if cond[0] in addresses:
					addr_op1 = addresses[cond[0]]
					op1_size = TSIZES[types[cond[0]]]
					add_instr(INSTR.format(1, 4, 0, addr_op1,
						'LOADA ' + str(addr_op1) + '[SB]'), quad)
					add_instr(INSTR.format(2, 0, op1_size, 0,
						'LOADI(' + str(op1_size) + ')'), quad)
				else:
					if cond[0] == 'true' or cond[0] == 'false':
						literal = int(str2bool(cond[0]))
					else:
						literal = int(floor(float(cond[0])))
					add_instr(INSTR.format(3, 0, 0, literal,
						'LOADL ' + str(literal)), quad)
			n = 1 if quad.operator == 'if' else 0
			add_instr(INSTR.format(14, 0, n, '{}',
				'JUMPIF(' + str(n) + ') {}[CB]'), quad)
		elif quad_type == 2:
			add_instr(INSTR.format(12, 0, 0, '{}', 'JUMP {}[CB]'), quad)
		elif quad_type == 3:
			if quad.op2 in addresses:
				addr_op2 = addresses[quad.op2]
				op2_size = TSIZES[types[quad.op2]]
				add_instr(INSTR.format(1, 4, 0, addr_op2,
					'LOADA ' + str(addr_op2) + '[SB]'), quad)
				add_instr(INSTR.format(2, 0, op2_size, 0,
					'LOADI(' + str(op2_size) + ')'), quad)
			else:
				if quad.op2 == 'true' or quad.op2 == 'false':
					literal = int(str2bool(quad.op2))
				else:
					literal = int(floor(float(quad.op2)))
				add_instr(INSTR.format(3, 0, 0, literal,
					'LOADL ' + str(literal)), quad)
			if quad.op1 in addresses:
				addr_op1 = addresses[quad.op1]
				op1_size = TSIZES[types[quad.op1]]
				
				add_instr(INSTR.format(1, 4, 0, addr_op1,
					'LOADA ' + str(addr_op1) + '[SB]'), quad)
				add_instr(INSTR.format(2, 0, op1_size, 0,
					'LOADI(' + str(op1_size) + ')'), quad)
			else:
				if quad.op1 == 'true' or quad.op1 == 'false':
					literal = int(str2bool(quad.op1))
				else:
					literal = int(floor(float(quad.op1)))
				add_instr(INSTR.format(3, 0, 0, literal,
					'LOADL ' + str(literal)), quad)
			addr_base = addresses[quad.dst]
			add_instr(INSTR.format(1, 4, 0, addr_base,
				'LOADA ' + str(addr_base) + '[SB]'), quad)
			mnemo = 'add'
			d = 8
			add_instr(INSTR.format(6, 2, 0, d, mnemo), quad)
			dst_size = TSIZES[types[quad.dst]]
			add_instr(INSTR.format(5, 0, dst_size, 0,
				'STOREI(' + str(dst_size) + ')'), quad)

		elif quad_type == 4:
			if quad.op2 in addresses:
				addr_op2 = addresses[quad.op2]
				op2_size = TSIZES[types[quad.op2]]
				add_instr(INSTR.format(1, 4, 0, addr_op2,
					'LOADA ' + str(addr_op2) + '[SB]'), quad)
				add_instr(INSTR.format(2, 0, op1_size, 0,
					'LOADI(' + str(op2_size) + ')'), quad)
			else:
				if quad.op2 == 'true' or quad.op2 == 'false':
					literal = int(str2bool(quad.op2))
				else:
					literal = int(floor(float(quad.op2)))
				add_instr(INSTR.format(3, 0, 0, literal,
					'LOADL ' + str(literal)), quad)
			addr_base = addresses[quad.op1]
			add_instr(INSTR.format(1, 4, 0, addr_base,
				'LOADA ' + str(addr_base) + '[SB]'), quad)
			mnemo = 'add'
			d = 8
			add_instr(INSTR.format(6, 2, 0, d, mnemo), quad)
			op_size = TSIZES[types[quad.op1]]
			add_instr(INSTR.format(2, 0, op_size, 0,
				'LOADI(' + str(op_size) + ')'), quad)
			addr_dst = addresses[quad.dst]
			dst_size = TSIZES[types[quad.dst]]
			add_instr(INSTR.format(1, 4, 0, addr_dst,
				'LOADA ' + str(addr_dst) + '[SB]'), quad)
			add_instr(INSTR.format(5, 0, dst_size, 0,
				'STOREI(' + str(dst_size) + ')'), quad)
		elif quad_type == 5:
			if quad.op1 in addresses:
				addr_op1 = addresses[quad.op1]
				op1_size = TSIZES[types[quad.op1]]
				add_instr(INSTR.format(1, 4, 0, addr_op1,
					'LOADA ' + str(addr_op1) + '[SB]'), quad)
				add_instr(INSTR.format(2, 0, op1_size, 0,
					'LOADI(' + str(op1_size) + ')'), quad)
			else:
				if quad.op1 == 'true' or quad.op1 == 'false':
					literal = int(str2bool(quad.op1))
				else:
					literal = int(floor(float(quad.op1)))
				add_instr(INSTR.format(3, 0, 0, literal,
					'LOADL ' + str(literal)), quad)
			addr_dst = addresses[quad.dst]
			dst_size = TSIZES[types[quad.dst]]
			add_instr(INSTR.format(1, 4, 0, addr_dst,
				'LOADA ' + str(addr_dst) + '[SB]'), quad)
			add_instr(INSTR.format(5, 0, dst_size, 0,
				'STOREI(' + str(dst_size) + ')'), quad)
		elif quad_type == 6:
			addr_dst = addresses[quad.dst]
			dst_size = TSIZES[types[quad.dst]]
			if quad.op1 in addresses:
				addr_op1 = addresses[quad.op1]
				op1_size = TSIZES[types[quad.op1]]
				add_instr(INSTR.format(1, 4, 0, addr_op1,
					'LOADA ' + str(addr_op1) + '[SB]'), quad)
				add_instr(INSTR.format(2, 0, op1_size, 0,
					'LOADI(' + str(op1_size) + ')'), quad)
			else:
				if quad.op1 == 'true' or quad.op1 == 'false':
					literal = int(str2bool(quad.op1))
				else:
					literal = int(floor(float(quad.op1)))

				add_instr(INSTR.format(3, 0, 0, literal,
					'LOADL ' + str(literal)), quad)
			if quad.op2 in addresses:
				addr_op2 = addresses[quad.op2]
				op2_size = TSIZES[types[quad.op2]]
				add_instr(INSTR.format(1, 4, 0, addr_op2,
					'LOADA ' + str(addr_op2) + '[SB]'), quad)
				add_instr(INSTR.format(2, 0, op2_size, 0,
					'LOADI(' + str(op2_size) + ')'), quad)
			else:
				if quad.op2 == 'true' or quad.op2 == 'false':
					literal = int(str2bool(quad.op2))
				else:
					literal = int(floor(float(quad.op2)))
				add_instr(INSTR.format(3, 0, 0, literal,
					'LOADL ' + str(literal)), quad)
			if quad.operator == '+':
				mnemo = 'add'
				d = 8
			elif quad.operator == '-':
				mnemo = 'sub'
				d = 9
			elif quad.operator == '*':
				mnemo = 'mult'
				d = 10
			else:
				mnemo = 'div'
				d = 11
			add_instr(INSTR.format(6, 2, 0, d, mnemo), quad)
			add_instr(INSTR.format(1, 4, 0, addr_dst,
				'LOADA ' + str(addr_dst) + '[SB]'), quad)
			add_instr(INSTR.format(5, 0, dst_size, 0,
				'STOREI(' + str(dst_size) + ')'), quad)
		elif quad_type == 7: 
			addr_dst = addresses[quad.dst]
			dst_size = TSIZES[types[quad.dst]]
			add_instr(INSTR.format(3, 0, 0, 0,
				'LOADL 0'), quad)
			if quad.op2 in addresses:
				addr_op2 = addresses[quad.op2]
				op2_size = TSIZES[types[quad.op2]]
				add_instr(INSTR.format(1, 4, 0, addr_op2,
					'LOADA ' + str(addr_op2) + '[SB]'), quad)
				add_instr(INSTR.format(2, 0, op2_size, 0,
					'LOADI(' + str(op2_size) + ')'), quad)
			else:
				if quad.op2 == 'true' or quad.op2 == 'false':
					literal = int(str2bool(quad.op2))
				else:
					literal = int(floor(float(quad.op2)))
				add_instr(INSTR.format(3, 0, 0, literal,
					'LOADL ' + str(literal)), quad)
			d = 9
			mnemo = 'sub'
			add_instr(INSTR.format(6, 2, 0, d, mnemo), quad)
			add_instr(INSTR.format(1, 4, 0, addr_dst,
				'LOADA ' + str(addr_dst) + '[SB]'), quad)
			add_instr(INSTR.format(5, 0, dst_size, 0,
				'STOREI(' + str(dst_size) + ')'), quad)
	add_instr(INSTR.format(15, 0, 0, 0, 'HALT'), None)





main()
