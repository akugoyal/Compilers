	.text
	.globl main
main:		# QTSPIM will automatically look for main
	
		# Begin block
	subu $sp $sp 4
	sw $ra ($sp)		# push $ra
	li $v0 4		# load number
	move $a0 $v0		# Store arguments
	jal fib		# Call method
	lw $ra ($sp)
	addu $sp $sp 4		# pop to $ra
	move $a0, $v0
	li $v0, 1
	syscall		# print the value of the expression
	la $a0, newLine
	li $v0, 4
	syscall		# print a new line
	
		# End block
	li $v0 10		# Normal termination
	syscall
fib:
	sw $a0 varn		# Save argument to variable
	
		# Begin block
	lw $v0 varn		# load variable into $v0
	move $t0 $v0		# store first expression
	li $v0 1		# load number
	bgt $t0 $v0 endif1		# Condition: less than or equal to
	lw $v0 varn		# load variable into $v0
	sw $v0 varfib		# save v0 to fib
endif1:		# exit if condition
	lw $v0 varn		# load variable into $v0
	move $t0 $v0		# store first expression
	li $v0 1		# load number
	ble $t0 $v0 endif2		# Condition: greater than
	subu $sp $sp 4
	sw $ra ($sp)		# push $ra
	li $v0 2		# load number
	subu $sp $sp 4
	sw $v0 ($sp)		# push $v0
	lw $v0 varn		# load variable into $v0
	lw $t0 ($sp)
	addu $sp $sp 4		# pop to $t0
	sub $v0 $v0 $t0		# subtraction
	move $a0 $v0		# Store arguments
	jal fib		# Call method
	lw $ra ($sp)
	addu $sp $sp 4		# pop to $ra
	subu $sp $sp 4
	sw $v0 ($sp)		# push $v0
	subu $sp $sp 4
	sw $ra ($sp)		# push $ra
	li $v0 1		# load number
	subu $sp $sp 4
	sw $v0 ($sp)		# push $v0
	lw $v0 varn		# load variable into $v0
	lw $t0 ($sp)
	addu $sp $sp 4		# pop to $t0
	sub $v0 $v0 $t0		# subtraction
	move $a0 $v0		# Store arguments
	jal fib		# Call method
	lw $ra ($sp)
	addu $sp $sp 4		# pop to $ra
	lw $t0 ($sp)
	addu $sp $sp 4		# pop to $t0
	add $v0 $v0 $t0		# addition
	sw $v0 varfib		# save v0 to fib
endif2:		# exit if condition
	
		# End block
	lw $v0 varfib		# load variable into $v0
	jr $ra
	.data
	newLine:	.asciiz	"\n"
	varn:	.word	0
	varfib:	.word	0
