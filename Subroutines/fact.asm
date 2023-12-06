# Computes and prints the factorial of the given number.
# 
# Author: Akul Goyal
# Version: 12-5-2023
.text
.globl main

main:
la $a0, in
li $v0, 4
syscall			# Print input message

li $v0, 5
syscall
move $a0, $v0		# Take input to a0

subu $sp, $sp, 4
sw $ra, ($sp)		# Push ra

jal fact

lw $ra, ($sp)
addu $sp, $sp, 4	# Pop ra

move $t0, $v0

la $a0, out
li $v0, 4
syscall			# Print output message

move $a0, $t0
li $v0, 1
syscall			# Print output

li $v0, 10
syscall			# Normal termination


#----------------------------------------------------------------------------------
# The fact function takes one integer in a0 and returns its factorial in v0

fact:
subu $sp, $sp, 8
sw $ra, ($sp)
sw $s0, 4($sp)			# Push ra and s0

# Base case
li $v0, 1
beq $a0, 0, done

move $s0, $a0
subu $a0, $a0, 1
jal fact			# Move to s0, decrement and recursive call


mul $v0, $v0, $s0		# Multiply numbers into v0 and return

done:
lw $ra, ($sp)
lw $s0, 4($sp)
addu $sp, $sp, 8		# Pop ra and s0

jr $ra

.data
in:	.asciiz	"Enter a number: "
out:	.asciiz	"The factorial is: "
