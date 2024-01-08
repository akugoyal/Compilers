	# This is an auto generated MIPS file from PASCAL source code.
	
	# Author: Akul Goyal
	# Version: 2024-01-08
	
	.text
	.globl main
main:		# QTSPIM will automatically look for main
	
		# Begin block
	li $v0 2		# load number
	sw $v0 varx		# save v0 to x
	li $v0 1		# load number
	subu $sp $sp 4
	sw $v0 ($sp)		# push $v0
	lw $v0 varx		# Load variable into $v0
	lw $t0 ($sp)
	addu $sp $sp 4		# pop to $t0
	add $v0 $v0 $t0		# addition
	sw $v0 vary		# save v0 to y
	lw $v0 vary		# Load variable into $v0
	subu $sp $sp 4
	sw $v0 ($sp)		# push $v0
	lw $v0 varx		# Load variable into $v0
	lw $t0 ($sp)
	addu $sp $sp 4		# pop to $t0
	add $v0 $v0 $t0		# addition
	sw $v0 varx		# save v0 to x
	lw $v0 vary		# Load variable into $v0
	subu $sp $sp 4
	sw $v0 ($sp)		# push $v0
	lw $v0 varx		# Load variable into $v0
	lw $t0 ($sp)
	addu $sp $sp 4		# pop to $t0
	mult $v0 $t0
	mflo $v0		# multiplication
	move $a0, $v0
	li $v0, 1
	syscall		# print the value of the expression
	la $a0, newLine
	li $v0, 4
	syscall		# print a new line
	lw $v0 varx		# Load variable into $v0
	move $t0 $v0		# store first expression
	lw $v0 vary		# Load variable into $v0
	ble $t0 $v0 endif1		# Condition: greater than
	
		# Begin block
	lw $v0 varx		# Load variable into $v0
	move $a0, $v0
	li $v0, 1
	syscall		# print the value of the expression
	la $a0, newLine
	li $v0, 4
	syscall		# print a new line
	lw $v0 vary		# Load variable into $v0
	move $a0, $v0
	li $v0, 1
	syscall		# print the value of the expression
	la $a0, newLine
	li $v0, 4
	syscall		# print a new line
	
		# End block
endif1:		# exit if condition
	li $v0 0		# load number
	sw $v0 varx		# save v0 to x
startwhile3:		# Begin while loop
	lw $v0 varx		# Load variable into $v0
	move $t0 $v0		# store first expression
	li $v0 10		# load number
	bge $t0 $v0 endwhile2		# Condition: less than
	
		# Begin block
	lw $v0 varx		# Load variable into $v0
	move $a0, $v0
	li $v0, 1
	syscall		# print the value of the expression
	la $a0, newLine
	li $v0, 4
	syscall		# print a new line
	li $v0 1		# load number
	subu $sp $sp 4
	sw $v0 ($sp)		# push $v0
	lw $v0 varx		# Load variable into $v0
	lw $t0 ($sp)
	addu $sp $sp 4		# pop to $t0
	add $v0 $v0 $t0		# addition
	sw $v0 varx		# save v0 to x
	
		# End block
	j startwhile3		# Repeat while loop
endwhile2:		# End while loop
	lw $v0 vary		# Load variable into $v0
	move $a0, $v0
	li $v0, 1
	syscall		# print the value of the expression
	la $a0, newLine
	li $v0, 4
	syscall		# print a new line
	li $v0 4		# load number
	subu $sp $sp 4
	sw $v0 ($sp)		# push $v0
	lw $v0 varx		# Load variable into $v0
	subu $sp $sp 4
	sw $v0 ($sp)		# push $v0
	lw $v0 vary		# Load variable into $v0
	subu $sp $sp 4
	sw $v0 ($sp)		# push $v0
	jal Add		# Procedure Call
	lw $t0 ($sp)
	addu $sp $sp 4		# pop to $t0
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
Add:
	subu $sp $sp 4
	sw $zero ($sp)		# push $zero
	subu $sp $sp 4
	sw $ra ($sp)		# push $ra
	
		# Begin block
	lw $v0 16($sp)		# Load local variable into $v0
	move $a0, $v0
	li $v0, 1
	syscall		# print the value of the expression
	la $a0, newLine
	li $v0, 4
	syscall		# print a new line
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
	lw $v0 16($sp)		# Load local variable into $v0
	subu $sp $sp 4
	sw $v0 ($sp)		# push $v0
	lw $v0 varx		# Load variable into $v0
	lw $t0 ($sp)
	addu $sp $sp 4		# pop to $t0
	add $v0 $v0 $t0		# addition
	sw $v0 varx		# save v0 to x
	
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
	vary:	.word	0
	varz:	.word	0
	varw:	.word	0
