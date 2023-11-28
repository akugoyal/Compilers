	.text
	.globl main
main:		# QTSPIM will automatically look for main
	
		# Begin block
	li $v0 1		# load number
	sw $v0 varx		# save v0 to x
	subu $sp $sp 4
	sw $ra ($sp)		# push $ra
	jal foo		# Call method
	lw $ra ($sp)
	addu $sp $sp 4		# pop to $ra
	lw $v0 varx		# load variable into $v0
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
	
		# Begin block
	li $v0 2		# load number
	sw $v0 varx		# save v0 to x
	subu $sp $sp 4
	sw $ra ($sp)		# push $ra
	jal bar		# Call method
	lw $ra ($sp)
	addu $sp $sp 4		# pop to $ra
	
		# End block
	lw $v0 varfoo		# load variable into $v0
	jr $ra
bar:
	
		# Begin block
	li $v0 3		# load number
	sw $v0 varx		# save v0 to x
	
		# End block
	lw $v0 varbar		# load variable into $v0
	jr $ra
	.data
	newLine:	.asciiz	"\n"
	varx:	.word	0
	varfoo:	.word	0
	varbar:	.word	0
