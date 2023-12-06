# Inputs three integers from the user and prints the larger integer. 
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
move $t0, $v0		# Take input to t0

la $a0, in
li $v0, 4
syscall			# Print prompt

li $v0, 5
syscall
move $t1, $v0		# Take input to t1

la $a0, in
li $v0, 4
syscall			# Print prompt

li $v0, 5
syscall
move $t2, $v0		# Take input to t2

subu $sp, $sp, 4
sw $ra, ($sp)		# Push ra

move $a0, $t0
move $a1, $t1
move $a2, $t2		# Load arguments into a0, a1, a2

jal max3

lw $ra, ($sp)
addu $sp $sp, 4		# Pop ra

move $t0, $v0		# Store output in t0

la $a0, out
li $v0 4
syscall			# Print output message

move $a0, $t0
li $v0, 1
syscall			# Print output

li $v0 10
syscall			# Normal termination

#----------------------------------------------------------------------------------
# The max3 function takes two integers in a0 and a1 and returns the larger integer in v0
max3:
subu $sp, $sp, 4
sw $ra, ($sp)		# Push ra

jal max2		# Find max of first two numbers

move $a0, $v0
move $a1, $a2		# Put first max in a0 and third number in a1

jal max2

lw $ra, ($sp)
addu $sp $sp, 4		# Pop ra

jr $ra			#return


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
