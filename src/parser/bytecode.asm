	.text
	.globl main
main:		# QTSPIM will automatically look for main
	li $v0 1		# load number
	sw $v0 vari		# save v0 to i
startwhile2:		# Start for loop
	la $t0 vari
	lw $v0 ($t0)		# load variable into $v0
	move $t0 $v0		# store first expression
	li $v0 10		# load number
	bgt $t0 $v0 endwhile1		# Condition: less than or equal to
	
		# Begin block
	la $t0 vari
	lw $v0 ($t0)		# load variable into $v0
	move $a0, $v0
	li $v0, 1
	syscall		# print the value of the expression
	la $a0, newLine
	li $v0, 4
	syscall		# print a new line
	
		# End block
	li $v0 1		# load number
	subu $sp $sp 4
	sw $v0 ($sp)		# push $v0
	la $t0 vari
	lw $v0 ($t0)		# load variable into $v0
	lw $t0 ($sp)
	addu $sp $sp 4		# pop to $t0
	add $v0 $v0 $t0		# addition
	sw $v0 vari		# save v0 to i
	j startwhile2		# Jump to start of for loop
endwhile1:		# End for loop
	li $v0 10		# Normal termination
	syscall
	.data
	newLine:	.asciiz	"\n"
	vari:	.word	 0
