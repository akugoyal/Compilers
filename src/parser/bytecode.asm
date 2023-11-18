	.text		# 
	.globl main		# 
main:		# QTSPIM will automatically look for main
	
		# Begin block
	li $v0 5		# load number
	sw $v0 varx		# save v0 to x
	li $v0 10		# Normal termination
	syscall		# 
	la $t0 varx		# 
	lw $v0 ($t0)		# load variable into $v0
	move $a0, $v0		# 
	li $v0, 1		# 
	syscall		# print the value of the expression
	la $a0, newLine		# 
	li $v0, 4		# 
	syscall		# print a new line
	
		# End block
	li $v0 10		# Normal termination
	syscall		# 
	.data		# 
	newLine:	.asciiz	"\n"		# 
	varx:	.word	 0		# 
