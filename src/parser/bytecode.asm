	.text
	.globl main
main:		# QTSPIM will automatically look for main
	
		# Begin block
	li $v0 2		# load number
	sw $v0 varx		# save v0 to x
	lw $v0 varx		# Load variable into $v0
	subu $sp $sp 4
	sw $v0 ($sp)		# push $v0
	li $v0 5		# load number
	subu $sp $sp 4
	sw $v0 ($sp)		# push $v0
	jal foo		# Procedure Call
	lw $t0 ($sp)
	addu $sp $sp 4		# pop to $t0
	lw $t0 ($sp)
	addu $sp $sp 4		# pop to $t0
	lw $v0 varx		# Load variable into $v0
	move $a0, $v0
	li $v0, 1
	syscall		# print the value of the expression
	la $a0, newLine
	li $v0, 4
	syscall		# print a new line
	
		# End block
	li $v0 10		# Normal termination
	syscall
foo:
	subu $sp $sp 4
	sw $zero ($sp)		# push $zero
	subu $sp $sp 4
	sw $ra ($sp)		# push $ra
	
		# Begin block
	li $v0 5		# load number
	subu $sp $sp 4
	sw $v0 ($sp)		# push $v0
	lw $v0 16($sp)		# Load local variable into $v0
	lw $t0 ($sp)
	addu $sp $sp 4		# pop to $t0
	add $v0 $v0 $t0		# addition
	sw $v0 12($sp)		# Store local variable onto stack
	lw $v0 12($sp)		# Load local variable into $v0
	move $a0, $v0
	li $v0, 1
	syscall		# print the value of the expression
	la $a0, newLine
	li $v0, 4
	syscall		# print a new line
	lw $v0 8($sp)		# Load local variable into $v0
	move $a0, $v0
	li $v0, 1
	syscall		# print the value of the expression
	la $a0, newLine
	li $v0, 4
	syscall		# print a new line
	
		# End block
	lw $ra ($sp)
	addu $sp $sp 4		# pop to $ra
	lw $v0 ($sp)
	addu $sp $sp 4		# pop to $v0
			# Returned value for method
	jr $ra
	.data
	newLine:	.asciiz	"\n"
	varx:	.word	0
