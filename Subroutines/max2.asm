# Inputs two integers from the user and prints the larger integer. 
# 
# Author: Akul Goyal
# Version: 12-4-2023
.text
.globl main

main:
la $a0, in
li $v0, 4
syscall			# Print prompt

li $v0, 5
syscall
move $t0, $v0		# Input first integer to t0

la $a0, in
li $v0, 4
syscall			# Print prompt

li $v0, 5
syscall
move $t1, $v0		# Input second integer to t1

subu $sp, $sp, 4
sw $ra, ($sp)		# Push ra

move $a0, $t0
move $a1, $t1		# Load arguments into a0, a1

jal max2

lw $ra, ($sp)
addu $sp $sp, 4		# Pop ra

move $t0, $v0
la $a0, out
li $v0, 4
syscall
move $v0, $t0		# Print output message

move $a0, $v0
li $v0 1
syscall			# Print output of function

li $v0 10
syscall			# Normal termination



#----------------------------------------------------------------------------------
# The max2 function takes two integers in a0 and a1 and returns the larger integer in v0
max2:
ble, $a0, $a1, ygt	# Jump to ygt if first integer isn't larger

move $v0, $a0		# Return first integer
jr $ra

ygt:
move $v0, $a1		# Return second integer
jr $ra



.data
in:	.asciiz	"Enter an integer: "
out:	.asciiz	"The max is: "