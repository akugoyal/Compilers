	.text
	.globl main
main:		# QTSPIM will automatically look for main
	
		# Begin block
	subu $sp $sp 4
	sw $ra ($sp)		# push $ra
	li $v0 7		# load number
	move $a0 $v0		# Store arguments
	li $v0 2		# load number
	move $a1 $v0		# Store arguments
	li $v0 14		# load number
	move $a2 $v0		# Store arguments
	jal max3		# Call method
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
max3:
	sw $a0 varx		# Save argument to variable
	sw $a1 vary		# Save argument to variable
	sw $a2 varz		# Save argument to variable
	
		# Begin block
	subu $sp $sp 4
	sw $ra ($sp)		# push $ra
	subu $sp $sp 4
	sw $ra ($sp)		# push $ra
	lw $v0 varx		# load variable into $v0
	move $a0 $v0		# Store arguments
	lw $v0 vary		# load variable into $v0
	move $a1 $v0		# Store arguments
	jal max2		# Call method
	lw $ra ($sp)
	addu $sp $sp 4		# pop to $ra
	move $a0 $v0		# Store arguments
	lw $v0 varz		# load variable into $v0
	move $a1 $v0		# Store arguments
	jal max2		# Call method
	lw $ra ($sp)
	addu $sp $sp 4		# pop to $ra
	sw $v0 varmax3		# save v0 to max3
	
		# End block
	lw $v0 varmax3		# load variable into $v0
	jr $ra
max2:
	sw $a0 varx		# Save argument to variable
	sw $a1 vary		# Save argument to variable
	
		# Begin block
	lw $v0 varx		# load variable into $v0
	sw $v0 varmax2		# save v0 to max2
	lw $v0 vary		# load variable into $v0
	move $t0 $v0		# store first expression
	lw $v0 varx		# load variable into $v0
	ble $t0 $v0 endif1		# Condition: greater than
	lw $v0 vary		# load variable into $v0
	sw $v0 varmax2		# save v0 to max2
endif1:		# exit if condition
	
		# End block
	lw $v0 varmax2		# load variable into $v0
	jr $ra
	.data
	newLine:	.asciiz	"\n"
	varx:	.word	0
	vary:	.word	0
	varz:	.word	0
	varmax3:	.word	0
	varmax2:	.word	0
