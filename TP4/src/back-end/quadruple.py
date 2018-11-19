class Quadruple():

	def __init__(self, dst, op1, op2, operator, branch=None):
		self.dst = dst
		self.op1 = op1
		self.op2 = op2
		self.operator = operator
		self.branch = branch
		self.address = None
		self.tam_code = []
		self.type = self.get_type()

	def get_type(self):
		if self.operator != None and 'if' in self.operator:
			return 1
		elif self.operator == 'goto':
			return 2
		elif self.operator == '[]=':
			return 3
		elif self.operator == '=[]':
			return 4
		elif self.operator == None:
			return 5
		elif self.operator in ['+', '-', '*', '/']:
			return 6
		else:
			return 7
 
	def __str__(self):
		string = ''

		if self.type:
			string += 'Type: ' + str(self.type) + ': '

		if self.dst:
			string += str(self.dst) + ' '

		if self.op1:
			string += str(self.op1) + ' '

		if self.operator:
			string += str(self.operator) + ' '

		if self.op2:
			string += str(self.op2) + ' '

		if self.branch:
			string += str(self.branch)

		return string